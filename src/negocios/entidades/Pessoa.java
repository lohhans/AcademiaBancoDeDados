package negocios.entidades;

import negocios.exception.CepInvalidoException;
import negocios.exception.CpfInvalidoException;
import negocios.exception.DadosInvalidosException;
import negocios.exception.SexoIncorretoException;

import java.util.Date;

public abstract class Pessoa {

    private String nome, sexo, cpf, telefone;
    private Endereco endereco;
    private Date dataDeNascimento;

    public Pessoa(String nome, String sexo, Date dataDeNascimento, String cpf, String telefone, Endereco endereco) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCpf() {
        return cpf;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    //Confere se os dados recebidos sao validos
    public boolean eValido(Pessoa pessoa) throws CpfInvalidoException, DadosInvalidosException, CepInvalidoException, SexoIncorretoException {
        boolean pessoaValida;
        if (!pessoa.getNome().isEmpty() && !pessoa.getSexo().isEmpty() && pessoa.getDataDeNascimento() != null && !pessoa.getCpf().isEmpty() && !pessoa.getTelefone().isEmpty() && !pessoa.getEndereco().getCep().isEmpty() && !pessoa.getEndereco().getBairro().isEmpty() && !pessoa.getEndereco().getRua().isEmpty() && !pessoa.getEndereco().getNumero().isEmpty() && !pessoa.getEndereco().getCidade().isEmpty()) {
            char[] tamanhoCep = pessoa.getEndereco().getCep().toCharArray();
            char[] tamanhoCpf = pessoa.getCpf().toCharArray();
            if (tamanhoCpf.length == 11) {
                if (tamanhoCep.length == 8) {
                    if (getSexo().equalsIgnoreCase("masculino") || getSexo().equalsIgnoreCase("feminino")) {
                        pessoaValida = true;
                        return pessoaValida;
                    } else {
                        throw new SexoIncorretoException();
                    }
                } else {
                    throw new CepInvalidoException();
                }
            } else {
                throw new CpfInvalidoException();
            }
        } else {
            throw new DadosInvalidosException();
        }
    }

    @Override
    public boolean equals(Object objetoPessoa) {
        if(objetoPessoa instanceof Pessoa){
            Pessoa pessoa = (Pessoa) objetoPessoa;
            if (pessoa.getCpf().equalsIgnoreCase(this.cpf)){
                return true;
            }
        }
        return false;
    }
}