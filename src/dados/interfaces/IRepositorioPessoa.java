package dados.interfaces;

import negocios.entidades.Funcionario;
import negocios.entidades.Pessoa;

import java.util.ArrayList;

public interface IRepositorioPessoa {
    boolean esvaziou();

    Pessoa buscarPessoa(Pessoa pessoaRecebida);

    Pessoa buscarPessoa(String cpf);

    void adicionarPessoa(Pessoa novaPessoa);

    void atualizarPessoa(String cpfPessoa, Pessoa pessoaAlterada);

    void removerFuncionario(Funcionario pessoa);

    ArrayList<Pessoa> getListaDePessoas();
}
