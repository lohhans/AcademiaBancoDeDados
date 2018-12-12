package negocios;

import dados.DbCliente;
import dados.DbFuncionario;
import dados.interfaces.IRepositorioPessoa;
import negocios.entidades.Avaliacao;
import negocios.entidades.Cliente;
import negocios.entidades.Funcionario;
import negocios.entidades.Pessoa;
import negocios.exception.AvaliacaoNaoEncontradaException;
import negocios.exception.PessoaJaCadastradaException;
import negocios.exception.PessoaNaoEncontradaException;

public class NegocioPessoa {

    private IRepositorioPessoa repositorioPessoa;
    private DbCliente dbCliente;
    private DbFuncionario dbFuncionario;

    public NegocioPessoa(IRepositorioPessoa repositorioPessoa) {
        this.repositorioPessoa = repositorioPessoa;
    }

    public void adicionarPessoa(Pessoa novaPessoa) throws PessoaJaCadastradaException {

        if (novaPessoa instanceof Cliente){
            adicionarCliente((Cliente) novaPessoa);
        } else{
            adicionarFuncionario((Funcionario) novaPessoa);
        }
    }

    public void adicionarCliente(Cliente cliente){

        if (dbCliente.esvaziou()){
            dbCliente.adicionarCliente(cliente);
        } else if (dbCliente.buscarCliente(cliente) == null){
            dbCliente.adicionarCliente(cliente);
        } else {
            try {
                throw new PessoaJaCadastradaException();
            } catch (PessoaJaCadastradaException e) {
                e.printStackTrace();
            }
        }
    }

    public void adicionarFuncionario(Funcionario funcionario){

        if (dbFuncionario.esvaziou()){
            dbFuncionario.adicionarFuncionario(funcionario);
        } else if (dbFuncionario.buscarFuncionario(funcionario) == null){
            dbFuncionario.adicionarFuncionario(funcionario);
        } else {
            try {
                throw new PessoaJaCadastradaException();
            } catch (PessoaJaCadastradaException e) {
                e.printStackTrace();
            }
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

////////////////////////////////////////////FALTA MUDAR//////////////////////////////////////////////////////////////////

    //Metodo especifico para cliente
    public void adicionarAvaliacao(String cpfCliente, Avaliacao avaliacao) throws PessoaNaoEncontradaException {
        Pessoa pessoa = repositorioPessoa.buscarPessoa(cpfCliente);
        if (pessoa != null){
            Cliente c = (Cliente) pessoa;
            avaliacao.setNumeroDaAvaliacao((c.getListaDeAvaliacoes().size())+1);
            c.getListaDeAvaliacoes().add(avaliacao);
        } else {
            throw new PessoaNaoEncontradaException();
        }
    }

    //Metodo especifico para cliente
    public String buscarAvaliacao(String cpfCliente, int numeroDaAvaliacao) throws PessoaNaoEncontradaException, AvaliacaoNaoEncontradaException {
        Pessoa pessoa = repositorioPessoa.buscarPessoa(cpfCliente);
        if (pessoa != null) {
            Cliente c = (Cliente) pessoa;
            Avaliacao avaliacao = c.buscarAvaliacao(numeroDaAvaliacao);
            if (avaliacao != null){
                return avaliacao.toString();
            } else {
                throw new AvaliacaoNaoEncontradaException();
            }
        } else {
            throw new PessoaNaoEncontradaException();
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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


