package negocios;

import dados.DbAvaliacao;
import dados.DbCliente;
import dados.DbFuncionario;
import negocios.entidades.Avaliacao;
import negocios.entidades.Cliente;
import negocios.entidades.Funcionario;
import negocios.entidades.Pessoa;
import negocios.exception.AvaliacaoNaoEncontradaException;
import negocios.exception.PessoaJaCadastradaException;
import negocios.exception.PessoaNaoEncontradaException;

import java.util.ArrayList;

public class NegocioPessoa {

    private DbCliente dbCliente;
    private DbFuncionario dbFuncionario;
    private DbAvaliacao dbAvaliacao = new DbAvaliacao();

    public NegocioPessoa(DbCliente dbCliente, DbFuncionario dbFuncionario ) {
        this.dbCliente = dbCliente;
        this.dbFuncionario = dbFuncionario;
    }

    public void adicionarPessoa(Pessoa novaPessoa) throws PessoaJaCadastradaException {

        if (novaPessoa instanceof Cliente){
            adicionarCliente((Cliente) novaPessoa);
        } else{
            adicionarFuncionario((Funcionario) novaPessoa);
        }
    }

    public void adicionarCliente(Cliente cliente) throws PessoaJaCadastradaException {

        if (dbCliente.esvaziou()){
            dbCliente.adicionarCliente(cliente);
        } else if (dbCliente.buscarCliente(cliente) == null){
            dbCliente.adicionarCliente(cliente);
        } else {
            throw new PessoaJaCadastradaException();

        }
    }

    public void adicionarFuncionario(Funcionario funcionario) throws PessoaJaCadastradaException {
        if (dbFuncionario.esvaziou()){
            dbFuncionario.adicionarFuncionario(funcionario);

        } else if (dbFuncionario.buscarFuncionario(funcionario) == null){
            dbFuncionario.adicionarFuncionario(funcionario);

        } else {
                throw new PessoaJaCadastradaException();

        }
    }

    public void atualizarPessoa(String cpfPessoa, Pessoa pessoaAlterada) throws PessoaNaoEncontradaException {

        if (pessoaAlterada instanceof Cliente) {
            atualizarCliente(cpfPessoa, (Cliente) pessoaAlterada);
        } else {
            atualizarFuncionario(cpfPessoa, (Funcionario) pessoaAlterada);
        }
    }
    public void atualizarCliente (String cpfCliente, Cliente clienteAlterado) throws PessoaNaoEncontradaException{

        if (dbCliente.buscarCliente(cpfCliente) == null){
            throw new PessoaNaoEncontradaException();
        } else {
            dbCliente.atualizarCliente(cpfCliente, clienteAlterado);
        }
    }

    public void atualizarFuncionario(String cpfFuncionario, Funcionario funcionarioAlterado) throws PessoaNaoEncontradaException{

        if (dbFuncionario.buscarFuncionario(cpfFuncionario) == null){
            throw new PessoaNaoEncontradaException();
        } else {
            dbFuncionario.atualizarFuncionario(cpfFuncionario, funcionarioAlterado);
        }
    }


    public void removerFuncionario(Funcionario funcionarioRecebido) throws PessoaNaoEncontradaException {
        if (dbFuncionario.buscarFuncionario(funcionarioRecebido) == null) {
            throw new PessoaNaoEncontradaException();
        } else {
            dbFuncionario.removerFuncionario(funcionarioRecebido);
        }
    }

    //Metodo especifico para cliente
    public void adicionarAvaliacao(String cpfCliente, Avaliacao avaliacao) throws PessoaNaoEncontradaException {
        Cliente cliente = dbCliente.buscarCliente(cpfCliente);
        if (cliente != null){
            avaliacao.setNumeroDaAvaliacao((dbAvaliacao.getListaDeAvaliacoes(cpfCliente).size())+1);
            dbAvaliacao.adicionar(cpfCliente, avaliacao);

        } else {
            throw new PessoaNaoEncontradaException();
        }
    }


    //Metodo especifico para cliente
    public String buscarAvaliacao(String cpfCliente, int numeroDaAvaliacao) throws PessoaNaoEncontradaException, AvaliacaoNaoEncontradaException {
        Cliente cliente = dbCliente.buscarCliente(cpfCliente);
        if (cliente != null) {

            ArrayList<Avaliacao> avaliacoes = dbAvaliacao.getListaDeAvaliacoes(cpfCliente);
            Avaliacao avaliacao = null;

            for (int i = 0; i < avaliacoes.size(); i++) {
                if (avaliacoes.get(i).getNumeroDaAvaliacao() == numeroDaAvaliacao) {
                    avaliacao = avaliacoes.get(i);
                }
            }

            if (avaliacao != null){
                return avaliacao.toString();
            } else {
                throw new AvaliacaoNaoEncontradaException();
            }
        } else {
            throw new PessoaNaoEncontradaException();
        }
    }

    public Pessoa buscaPessoaCpf(String cpf) throws PessoaNaoEncontradaException {

        if (dbFuncionario.buscarFuncionario(cpf) != null){
            return dbFuncionario.buscarFuncionario(cpf);
        }else if (dbCliente.buscarCliente(cpf) != null){
            return dbCliente.buscarCliente(cpf);

        } else {
            throw new PessoaNaoEncontradaException();
        }
    }

}


