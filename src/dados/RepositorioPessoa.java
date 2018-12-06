package dados;

import dados.interfaces.IRepositorioPessoa;
import negocios.entidades.Funcionario;
import negocios.entidades.Pessoa;

import java.util.ArrayList;

public class RepositorioPessoa implements IRepositorioPessoa{

    private ArrayList <Pessoa> listaDePessoas;

    public RepositorioPessoa(){
        listaDePessoas = new ArrayList<Pessoa>();
    }

    @Override
    public boolean esvaziou(){
        return listaDePessoas.isEmpty();
    }

    @Override
    public Pessoa buscarPessoa(Pessoa pessoaRecebida){
        for (int i = 0; i < listaDePessoas.size(); i++) {
            if (listaDePessoas.get(i).equals(pessoaRecebida)){
                return listaDePessoas.get(i);
            }
        }
        return null;
    }

    @Override
    public Pessoa buscarPessoa(String cpf){
        for (int i = 0; i < listaDePessoas.size(); i++) {
            if (cpf.equals(listaDePessoas.get(i).getCpf())){
                return listaDePessoas.get(i);
            }
        }
        return null;
    }

    @Override
    public void adicionarPessoa(Pessoa novaPessoa){
        listaDePessoas.add(novaPessoa);
    }

    @Override
    public void atualizarPessoa(String cpfPessoa, Pessoa pessoaAlterada){
        Pessoa pessoa = this.buscarPessoa(cpfPessoa);
        int i = listaDePessoas.indexOf(pessoa);
        listaDePessoas.set(i, pessoaAlterada);
    }

    @Override
    public void removerFuncionario(Funcionario pessoa) {
        listaDePessoas.remove(pessoa);
    }

    @Override
    public ArrayList<Pessoa> getListaDePessoas() {
        return listaDePessoas;
    }
}
