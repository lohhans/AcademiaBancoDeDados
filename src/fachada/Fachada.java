package fachada;

import dados.*;
import dados.interfaces.IRepositorioMatricula;
import dados.interfaces.IRepositorioMensalidade;
import dados.interfaces.IRepositorioPessoa;
import negocios.*;
import negocios.entidades.*;
import negocios.exception.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Fachada {

    private static Fachada fachada;
    private IRepositorioMatricula repositorioMatricula;
    private IRepositorioPessoa repositorioPessoa;
    private IRepositorioMensalidade repositorioMensalidade;

    private NegocioPessoa negocioPessoa;
    private NegocioMatricula negocioMatricula;
    private NegocioMensalidade negocioMensalidade;
    private NegocioModalidade negocioModalidade;

    private Fachada() {
        this.repositorioMatricula = new RepositorioMatricula();
        this.repositorioPessoa = new RepositorioPessoa();
        this.repositorioMensalidade = new RepositorioMensalidade();
        this.negocioPessoa = new NegocioPessoa(repositorioPessoa);
        this.negocioMatricula = new NegocioMatricula(repositorioMatricula, repositorioPessoa, repositorioMensalidade);
        this.negocioMensalidade = new NegocioMensalidade(repositorioMatricula);
        this.negocioModalidade = new NegocioModalidade(new RepositorioModalidade());
    }

    public static Fachada getInstancia() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    // Login

    public boolean login(String cpf, String senha) throws PessoaNaoEncontradaException, SenhaIncorretaException {
        Pessoa pessoa = negocioPessoa.buscaPessoaCpf(cpf);
        Funcionario funcionario = (Funcionario) pessoa;
        if(funcionario.getSenha().equals(senha)){
            return true;
        } else {
            throw new SenhaIncorretaException();
        }
    }

    // Funcionalidades de cliente

    public void cadastrarCliente(String nome, String sexo, Date dataDeNascimento, String cpf, String telefone, String cep, String numero, String rua, String bairro, String cidade, String nomeEmergencia, String telefoneEmergencia) throws PessoaJaCadastradaException, DadosInvalidosException, CpfInvalidoException, CepInvalidoException, SexoIncorretoException {
        Endereco endereco = new Endereco(cep, numero, rua, bairro, cidade);

        Pessoa cliente = new Cliente(nome, sexo, dataDeNascimento, cpf, telefone, endereco, nomeEmergencia, telefoneEmergencia);
        cliente.eValido(cliente);
        negocioPessoa.adicionarPessoa(cliente);
    }

    public void atualizarNomeCliente(String cpfCliente, String novoNome) throws PessoaNaoEncontradaException, CpfInvalidoException, DadosInvalidosException, SexoIncorretoException, CepInvalidoException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfCliente);
        Cliente c = (Cliente) p;
        Cliente novoCliente = new Cliente(novoNome, c.getSexo(), c.getDataDeNascimento(), c.getCpf(), c.getTelefone(), c.getEndereco(), c.getNomeEmergencia(), c.getTelefoneEmergencia());
        novoCliente.eValido(novoCliente);
        negocioPessoa.atualizarPessoa(cpfCliente, novoCliente);
    }

    public void atualizarTelefoneCliente(String cpfCliente, String novoTelefone) throws PessoaNaoEncontradaException, CpfInvalidoException, DadosInvalidosException, SexoIncorretoException, CepInvalidoException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfCliente);
        Cliente c = (Cliente) p;
        Cliente novoCliente = new Cliente(c.getNome(), c.getSexo(), c.getDataDeNascimento(), c.getCpf(), novoTelefone, c.getEndereco(), c.getNomeEmergencia(), c.getTelefoneEmergencia());
        novoCliente.eValido(novoCliente);
        negocioPessoa.atualizarPessoa(cpfCliente, novoCliente);
    }

    public void atualizarEnderecoCliente(String cpfCliente, String cep, String numero, String rua, String bairro, String cidade) throws PessoaNaoEncontradaException, CpfInvalidoException, DadosInvalidosException, SexoIncorretoException, CepInvalidoException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfCliente);
        Cliente c = (Cliente) p;
        Endereco endereco = new Endereco(cep, numero, rua, bairro, cidade);
        Cliente novoCliente = new Cliente(c.getNome(), c.getSexo(), c.getDataDeNascimento(), c.getCpf(), c.getTelefone(), endereco, c.getNomeEmergencia(), c.getTelefoneEmergencia());
        novoCliente.eValido(novoCliente);
        negocioPessoa.atualizarPessoa(cpfCliente, novoCliente);
    }

    public void atualizarNomeEmergenciaCliente(String cpfCliente, String novoNomeEmergencia) throws PessoaNaoEncontradaException, CpfInvalidoException, DadosInvalidosException, SexoIncorretoException, CepInvalidoException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfCliente);
        Cliente c = (Cliente) p;
        Cliente novoCliente = new Cliente(c.getNome(), c.getSexo(), c.getDataDeNascimento(), c.getCpf(), c.getTelefone(), c.getEndereco(), novoNomeEmergencia, c.getTelefoneEmergencia());
        novoCliente.eValido(novoCliente);
        negocioPessoa.atualizarPessoa(cpfCliente, novoCliente);
    }

    public void atualizarTelefoneEmergenciaCliente(String cpfCliente, String novoTelefoneEmergencia) throws PessoaNaoEncontradaException, CpfInvalidoException, DadosInvalidosException, SexoIncorretoException, CepInvalidoException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfCliente);
        Cliente c = (Cliente) p;
        Cliente novoCliente = new Cliente(c.getNome(), c.getSexo(), c.getDataDeNascimento(), c.getCpf(), c.getTelefone(), c.getEndereco(), c.getNomeEmergencia(), novoTelefoneEmergencia);
        novoCliente.eValido(novoCliente);
        negocioPessoa.atualizarPessoa(cpfCliente, novoCliente);
    }

    public void adiconarAvaliacao(String cpf, double circunferenciaAbdominal, double torax, double cintura, double quadril, double antebracoDireito, double antebracoEsquerdo, double bracoDireito, double bracoEsquerdo, double coxaDireita, double coxaEsquerda, double panturrilhaDireita, double panturrilhaEsquerda) throws PessoaNaoEncontradaException {
        Avaliacao avaliacao = new Avaliacao(circunferenciaAbdominal, torax, cintura, quadril, antebracoDireito, antebracoEsquerdo, bracoDireito, bracoEsquerdo, coxaDireita, coxaEsquerda, panturrilhaDireita, panturrilhaEsquerda);
        negocioPessoa.adicionarAvaliacao(cpf, avaliacao);
    }

    // Funcionalidades de funcionario

    public void cadastrarFuncionario(String nome, String sexo, Date dataDeNascimento, String cpf, String telefone, String cep, String numero, String rua, String bairro, String cidade, String senha) throws PessoaJaCadastradaException, CpfInvalidoException, DadosInvalidosException, SexoIncorretoException, CepInvalidoException {
        Endereco endereco = new Endereco(cep, numero, rua, bairro, cidade);

        Funcionario funcionario = new Funcionario(nome, sexo, dataDeNascimento, cpf, telefone, endereco, senha);
        funcionario.eValido(funcionario);
        negocioPessoa.adicionarPessoa(funcionario);
    }

    public void atualizarNomeFuncionario(String cpfFuncionario, String novoNome) throws PessoaNaoEncontradaException, CpfInvalidoException, DadosInvalidosException, SexoIncorretoException, CepInvalidoException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfFuncionario);
        Funcionario f = (Funcionario) p;
        Funcionario novoFuncionario = new Funcionario(novoNome, f.getSexo(), f.getDataDeNascimento(), f.getCpf(), f.getTelefone(), f.getEndereco(), f.getSenha());
        novoFuncionario.eValido(novoFuncionario);
        negocioPessoa.atualizarPessoa(cpfFuncionario, novoFuncionario);
    }

    public void atualizarTelefoneFuncionario(String cpfFuncionario, String novoTelefone) throws PessoaNaoEncontradaException, CpfInvalidoException, DadosInvalidosException, SexoIncorretoException, CepInvalidoException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfFuncionario);
        Funcionario f = (Funcionario) p;
        Funcionario novoFuncionario = new Funcionario(f.getNome(), f.getSexo(), f.getDataDeNascimento(), f.getCpf(), novoTelefone, f.getEndereco(), f.getSenha());
        novoFuncionario.eValido(novoFuncionario);
        negocioPessoa.atualizarPessoa(cpfFuncionario, novoFuncionario);
    }

    public void atualizarEnderecoFuncionario(String cpfFuncionario, String cep, String numero, String rua, String bairro, String cidade) throws PessoaNaoEncontradaException, CpfInvalidoException, DadosInvalidosException, SexoIncorretoException, CepInvalidoException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfFuncionario);
        Funcionario f = (Funcionario) p;
        Endereco endereco = new Endereco(cep, numero, rua, bairro, cidade);
        Funcionario novoFuncionario = new Funcionario(f.getNome(), f.getSexo(), f.getDataDeNascimento(), f.getCpf(), f.getTelefone(), endereco, f.getSenha());
        novoFuncionario.eValido(novoFuncionario);
        negocioPessoa.atualizarPessoa(cpfFuncionario, novoFuncionario);
    }

    public void definirGerente(String cpfFuncionario, boolean gerente) throws PessoaNaoEncontradaException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfFuncionario);
        Funcionario f = (Funcionario) p;
        f.setGerente(gerente);
    }

    public void mudarSenhaFuncionario(String cpfFuncionario, String novaSenha) throws PessoaNaoEncontradaException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfFuncionario);
        Funcionario f = (Funcionario) p;
        Funcionario novoFuncionario = new Funcionario(f.getNome(), f.getSexo(), f.getDataDeNascimento(), f.getCpf(), f.getTelefone(), f.getEndereco(), novaSenha);
        negocioPessoa.atualizarPessoa(cpfFuncionario, novoFuncionario);
    }

    public void demitirFuncionario(String cpfFuncionario) throws PessoaNaoEncontradaException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfFuncionario);
        Funcionario funcionario = (Funcionario) p;
        negocioPessoa.removerFuncionario(funcionario);
    }

    // Matricula

    public void matricular(String cpfCliente, int quantidadeMeses, ArrayList<Modalidade> modalidadesDoCliente) throws PessoaNaoEncontradaException, ClienteJaMatriculadoException, QuantidadeDeMesesIndisponivelException, ParseException, ArrayListVazioException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfCliente);
        Cliente cliente = (Cliente) p;
        negocioMatricula.matricular(cliente, quantidadeMeses, modalidadesDoCliente);
    }

    public double removerMatricula(String cpfCliente) throws ClienteNaoMatriculadoException, PessoaNaoEncontradaException, MensalidadeEmAbertoException, ImpossivelRemoverMatriculaDeUmMesException {
        Pessoa pessoa = negocioPessoa.buscaPessoaCpf(cpfCliente);
        Cliente cliente = (Cliente) pessoa;
        Matricula matricula = negocioMatricula.buscarMatricula(cliente);
        double multa = 0;
        if ( negocioMensalidade.conferirMensalidadeTotalmentePaga(matricula)){
            negocioMatricula.removerMatriculaPaga(cliente);
            return multa;
        } else {
            multa = negocioMatricula.removerMatriculaEmAberto(cliente);
        }

        return multa;
    }

    // Funcionalidades de mensalidade

    public void pagarMensalidade(String cpfCliente) throws MensalidadesPagasException, PessoaNaoEncontradaException, ClienteNaoMatriculadoException {
        Pessoa p = negocioPessoa.buscaPessoaCpf(cpfCliente);
        Cliente c = (Cliente) p;
        Matricula matricula = negocioMatricula.buscarMatricula(c);
        negocioMensalidade.pagarMensalidade(matricula);
    }

    public String conferirPagamentoDosClientes() throws MensalidadesPagasException {
        return negocioMensalidade.conferirPagamentoDosClientes();
    }

    // Funcionalidades de modalidade

    public String listarModalidades(){
        return negocioModalidade.printarModalidades();
    }

    public void cadastrarModalidade(String nome, double preco) throws ModalidadeJaCadastradaException {
        Modalidade modalidade = new Modalidade(nome, preco);
        negocioModalidade.cadastrarModalidade(modalidade);
    }

    public void alterarNomeModalidade(int codigoModalidade, String novoNome) throws ModalidadeNaoEncontradaException {
        negocioModalidade.atualizarNomeModalidade(codigoModalidade, novoNome);
    }

    public void alterarPrecoModalidade(int codigoModalidade, double novoPreco) throws ModalidadeNaoEncontradaException {
        negocioModalidade.atualizarPrecoModalidade(codigoModalidade, novoPreco);
    }

    public void removerModalidade(int codigoModalidade) throws ModalidadeNaoEncontradaException {
        Modalidade modalidade = negocioModalidade.buscarModalidade(codigoModalidade);
        negocioModalidade.removerModalidade(modalidade);
    }


    // Metodos de busca

    public Pessoa buscarPessoa(String cpf) throws PessoaNaoEncontradaException {
        return negocioPessoa.buscaPessoaCpf(cpf);
    }

    public String buscarAvalicao(String cpf, int numeroAvaliacao) throws PessoaNaoEncontradaException, AvaliacaoNaoEncontradaException {
        return negocioPessoa.buscarAvaliacao(cpf, numeroAvaliacao);
    }

    public Modalidade buscarModalidade(int c) throws ModalidadeNaoEncontradaException {
        return negocioModalidade.buscarModalidade(c);
    }

}