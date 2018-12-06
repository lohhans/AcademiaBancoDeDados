package negocios.entidades;

import negocios.exception.CpfInvalidoException;
import negocios.exception.DadosInvalidosException;

import java.util.Date;

public class Funcionario extends Pessoa{

    private String senha;
    private boolean gerente;

    public Funcionario(String nome, String sexo, Date dataDeNascimento, String cpf, String telefone, Endereco endereco, String senha) {
        super(nome, sexo, dataDeNascimento, cpf, telefone, endereco);
        this.gerente = gerente;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isGerente() {
        return gerente;
    }

    public void setGerente(boolean gerente) {
        this.gerente = gerente;
    }

    @Override
    public String toString() {
        return "Dados do funcionário:" + "\n" +
                "Nome = " + getNome() + "\n" +
                "CPF = " + getCpf() + "\n" +
                "Sexo = " + getSexo() + "\n" +
                "Data de nascimento = " + getDataDeNascimento() + "\n" +
                "Telefone = " + getTelefone() + "\n" +
                this.getEndereco().toString() + "\n" +
                "É gerente? = " + gerente + "\n";
    }
}