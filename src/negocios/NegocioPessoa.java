package negocios;

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

    public NegocioPessoa(IRepositorioPessoa repositorioPessoa) {
        this.repositorioPessoa = repositorioPessoa;
    }

    public void adicionarPessoa(Pessoa novaPessoa) throws PessoaJaCadastradaException {
        if (repositorioPessoa.esvaziou()){
            repositorioPessoa.adicionarPessoa(novaPessoa);
        } else if (repositorioPessoa.buscarPessoa(novaPessoa) == null){
            repositorioPessoa.adicionarPessoa(novaPessoa);
        } else {
            throw new PessoaJaCadastradaException();
        }
    }

    public void atualizarPessoa(String cpfPessoa, Pessoa pessoaAlterada) throws PessoaNaoEncontradaException {
        if (repositorioPessoa.buscarPessoa(cpfPessoa) == null){
            throw new PessoaNaoEncontradaException();
        } else {
            repositorioPessoa.atualizarPessoa(cpfPessoa, pessoaAlterada);
        }
    }

    public void removerFuncionario(Funcionario funcionarioRecebido) throws PessoaNaoEncontradaException {
        if (repositorioPessoa.buscarPessoa(funcionarioRecebido) == null) {
            throw new PessoaNaoEncontradaException();
        } else {
            repositorioPessoa.removerFuncionario(funcionarioRecebido);
        }
    }

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

    public Pessoa buscaPessoaCpf(String cpf) throws PessoaNaoEncontradaException {
        if (repositorioPessoa.buscarPessoa(cpf) != null){
            return repositorioPessoa.buscarPessoa(cpf);
        } else {
            throw new PessoaNaoEncontradaException();
        }
    }

}
