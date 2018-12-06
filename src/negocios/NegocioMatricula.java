package negocios;

import dados.interfaces.IRepositorioMatricula;
import dados.interfaces.IRepositorioMensalidade;
import dados.interfaces.IRepositorioPessoa;
import negocios.entidades.*;
import negocios.exception.*;


import java.text.ParseException;
import java.util.ArrayList;

public class NegocioMatricula {

    private IRepositorioMatricula repositorioMatricula;
    private IRepositorioPessoa repositorioPessoa;
    private IRepositorioMensalidade repositorioMensalidade;

    public NegocioMatricula(IRepositorioMatricula repositorioMatricula, IRepositorioPessoa repositorioPessoa, IRepositorioMensalidade repositorioMensalidade) {
        this.repositorioMatricula = repositorioMatricula;
        this.repositorioPessoa = repositorioPessoa;
        this.repositorioMensalidade = repositorioMensalidade;
    }

    private double gerarPrecoDaMatricula(ArrayList<Modalidade> modalidadesDoCliente) throws ArrayListVazioException{ //trt modalidades in
        if(!modalidadesDoCliente.isEmpty()){
            double precoDaMatriculaSemDesconto = 0;
            for (int i = 0; i < modalidadesDoCliente.size(); i++) {
                precoDaMatriculaSemDesconto += modalidadesDoCliente.get(i).getPreco();
            }
            return precoDaMatriculaSemDesconto;
        } else {
            throw new ArrayListVazioException();
        }
    }

    private double descontoEndereco(Cliente cliente, double precoDaMatriculaSemDesconto){
        double descontoEndereco = 0;
        //varre lista de pessoas buscando endereço igual para dar desconto de 5%
        for (int i = 0; i < repositorioPessoa.getListaDePessoas().size(); i++) {
            if (cliente.getEndereco().equals(repositorioPessoa.getListaDePessoas().get(i).getEndereco())) {
                Cliente c = (Cliente) repositorioPessoa.getListaDePessoas().get(i);
                if(c.isMatriculado()) {
                    descontoEndereco = precoDaMatriculaSemDesconto * 0.05;
                }
            }
        }
        return descontoEndereco;
    }

    private double descontoQuantidadeMeses(int quantidadeMeses, double precoDaMatriculaSemDesconto) throws QuantidadeDeMesesIndisponivelException{
        double desconto3Meses = 0, desconto6Meses = 0, desconto12Meses = 0;

        //aplica desconto de acordo com o mes passado como parametro
        // 5% 3 meses
        // 10% 6 meses
        // 15%  12 meses

        if (quantidadeMeses == 1 || quantidadeMeses == 3 || quantidadeMeses == 6 || quantidadeMeses == 12){
            if (quantidadeMeses == 3) {
                desconto3Meses = precoDaMatriculaSemDesconto * 0.05;
            } else if (quantidadeMeses == 6) {
                desconto6Meses = precoDaMatriculaSemDesconto * 0.10;
            } else if (quantidadeMeses == 12) {
                desconto12Meses = precoDaMatriculaSemDesconto * 0.15;
            }
            return desconto3Meses + desconto6Meses + desconto12Meses;
        } else {
            throw new QuantidadeDeMesesIndisponivelException();
        }
    }

    public void matricular(Cliente cliente, int quantidadeMeses, ArrayList<Modalidade> modalidadesDoCliente) throws ArrayListVazioException, QuantidadeDeMesesIndisponivelException, ParseException, PessoaNaoEncontradaException, ClienteJaMatriculadoException {
        //Compara se o cliente existe
        if (repositorioPessoa.buscarPessoa(cliente.getCpf()) != null && !cliente.isMatriculado()) {
            double precoMatricula = gerarPrecoDaMatricula(modalidadesDoCliente);
            double descontoPorEndereco = descontoEndereco(cliente, precoMatricula);
            double descontoPorQuantiaMeses = descontoQuantidadeMeses(quantidadeMeses, precoMatricula);

            //Criacao do objeto matricula
            double descontoTotal = descontoPorEndereco + descontoPorQuantiaMeses;
            double precoFinal = precoMatricula - descontoTotal;

            ArrayList<Mensalidade> listaMensalidades = repositorioMensalidade.criarMensalidades(quantidadeMeses, precoFinal);

            cliente.setMatriculado(true);

            Matricula matricula = new Matricula(cliente, modalidadesDoCliente, listaMensalidades);

            //Usou metodo do Repositorio e criou
            repositorioMatricula.matricular(matricula);

        } else if (cliente.isMatriculado()){
            throw new ClienteJaMatriculadoException();
        } else {
            throw new PessoaNaoEncontradaException();
        }
    }

    public void removerMatriculaPaga(Cliente cliente) throws PessoaNaoEncontradaException {
        //Compara se o cliente existe
        if(repositorioPessoa.buscarPessoa(cliente) != null) {
            repositorioMatricula.removerMatricula(repositorioMatricula.buscarMatricula(cliente));
            cliente.setMatriculado(false);
        } else {
            throw new PessoaNaoEncontradaException();
        }
    }

    public double removerMatriculaEmAberto(Cliente cliente) throws PessoaNaoEncontradaException, ImpossivelRemoverMatriculaDeUmMesException {
        System.out.println(repositorioMatricula.buscarMensalidade(cliente).get(1).getValor());
        double multa;
        //Compara se o cliente existe
        if(repositorioPessoa.buscarPessoa(cliente) != null) {
            multa = repositorioMatricula.buscarMensalidade(cliente).get(1).getValor()*3;
            repositorioMatricula.removerMatricula(repositorioMatricula.buscarMatricula(cliente));
            cliente.setMatriculado(false);
        } else if (repositorioMatricula.buscarMensalidade(cliente).size() == 1){ //Confere se o plano eh maior que 1 mes
            throw new ImpossivelRemoverMatriculaDeUmMesException();
        } else {
            throw new PessoaNaoEncontradaException();
        }
        return multa;
    }

    public Matricula buscarMatricula(Cliente cliente) throws ClienteNaoMatriculadoException{
        if (cliente.isMatriculado()) {
            return repositorioMatricula.buscarMatricula(cliente);
        } else {
            throw new ClienteNaoMatriculadoException();
        }
    }
}