package negocios.entidades;

import negocios.exception.CpfInvalidoException;
import negocios.exception.DadosInvalidosException;

import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Pessoa{

    private String nomeEmergencia, telefoneEmergencia;
    private boolean matriculado;
    private ArrayList<Avaliacao> listaDeAvaliacoes;

    public Cliente(String nome, String sexo, Date dataDeNascimento, String cpf, String telefone, Endereco endereco, String nomeEmergencia, String telefoneEmergencia){
        super(nome, sexo, dataDeNascimento, cpf, telefone, endereco);
        this.listaDeAvaliacoes = new ArrayList<>();
        this.nomeEmergencia = nomeEmergencia;
        this.telefoneEmergencia = telefoneEmergencia;
    }

    public String getNomeEmergencia() {
        return nomeEmergencia;
    }

    public void setNomeEmergencia(String nomeEmergencia) {
        this.nomeEmergencia = nomeEmergencia;
    }

    public String getTelefoneEmergencia() {
        return telefoneEmergencia;
    }

    public void setTelefoneEmergencia(String telefoneEmergencia) {
        this.telefoneEmergencia = telefoneEmergencia;
    }

    public boolean isMatriculado() {
        return matriculado;
    }

    public void setMatriculado(boolean matriculado) {
        this.matriculado = matriculado;
    }

    public ArrayList<Avaliacao> getListaDeAvaliacoes() {
        return listaDeAvaliacoes;
    }

    public Avaliacao buscarAvaliacao(int numeroDaAvaliacao) { //Implentar o resto
        for (int i = 0; i < listaDeAvaliacoes.size(); i++) {
            if (listaDeAvaliacoes.get(i).getNumeroDaAvaliacao() == numeroDaAvaliacao) {
                return listaDeAvaliacoes.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if (nomeEmergencia == null && telefoneEmergencia == null) {
            return "Dados do cliente:" +
                    "Nome = " + getNome() + "\n" +
                    "CPF = " + getCpf() + "\n" +
                    "Sexo = " + getSexo() + "\n" +
                    "Data de nascimento = " + getDataDeNascimento() + "\n" +
                    "Telefone = " + getTelefone() + "\n" +
                    this.getEndereco().toString() + "\n" +
                    "Está matriculado? = " + matriculado + "\n";
        } else {
            return "Dados do cliente:" +
                    "Nome = " + getNome() + "\n" +
                    "CPF = " + getCpf() + "\n" +
                    "Sexo = " + getSexo() + "\n" +
                    "Data de nascimento = " + getDataDeNascimento() + "\n" +
                    "Telefone = " + getTelefone() + "\n" +
                    this.getEndereco().toString() + "\n" +
                    "Está matriculado? = " + matriculado + "\n" +
                    "Nome do contato de emergência = " + nomeEmergencia + "\n" +
                    "Telefone do contato emergência = " + telefoneEmergencia + "\n";
        }
    }
}