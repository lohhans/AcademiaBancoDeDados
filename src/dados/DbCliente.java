package dados;

import connection.ConnectionFactory;
import negocios.entidades.Cliente;
import negocios.entidades.Endereco;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DbCliente {

    public void adicionarCliente(Cliente cliente){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO cliente (nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, nomeEmergencia, telefoneEmergencia, matriculado) VALUES (?, ?, ?, ?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ?) ");

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            Calendar cal = Calendar.getInstance();
            cal.setTime(cliente.getDataDeNascimento());
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH)+1;
            int ano = cal.get(Calendar.YEAR);

            String dataNasc = dia+"/"+mes+"/"+ano;


            stmt.setString(3, dataNasc);
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEndereco().getCep());
            stmt.setString(7, cliente.getEndereco().getNumero());
            stmt.setString(8, cliente.getEndereco().getRua());
            stmt.setString(9, cliente.getEndereco().getBairro());
            stmt.setString(10, cliente.getEndereco().getCidade());
            stmt.setString(11, cliente.getNomeEmergencia());
            stmt.setString(12, cliente.getTelefoneEmergencia());
            stmt.setBoolean(13, cliente.isMatriculado());

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public Cliente buscarCliente(Cliente cliente){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, nomeEmergencia, telefoneEmergencia, matriculado " +
                    "FROM cliente WHERE cpf ="+cliente.getCpf());
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            rs = stmt.executeQuery();
            rs.next();

            if(!rs.isBeforeFirst()){
                String cep = rs.getString("cep");
                String numero = rs.getString("numero");
                String rua = rs.getString("rua");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");

                Endereco endereco = new Endereco(cep,numero,rua,bairro,cidade);

                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                String dataNasc = rs.getString("dataDeNascimento");
                java.util.Date data = new java.util.Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    data = (java.util.Date) formato.parse(dataNasc);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String telefone = rs.getString("telefone");
                String nomeEmergencia = rs.getString("nomeEmergencia");
                String telefoneEmergencia = rs.getString("telefoneEmergencia");
                String cpf = rs.getString("cpf");

                Cliente clienteBanco = new Cliente(nome, sexo, data, cpf, telefone, endereco, nomeEmergencia, telefoneEmergencia);
                clienteBanco.setMatriculado(rs.getBoolean("matriculado"));


                return clienteBanco;
            }



        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return null;
    }



    //Metodo de sobrecarga
    public Cliente buscarCliente(String cpf){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, nomeEmergencia, telefoneEmergencia, matriculado " +
                    "FROM cliente WHERE cpf ="+cpf);
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            rs = stmt.executeQuery();
            rs.next();

            if(!rs.isBeforeFirst()){
                String cep = rs.getString("cep");
                String numero = rs.getString("numero");
                String rua = rs.getString("rua");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");

                Endereco endereco = new Endereco(cep,numero,rua,bairro,cidade);

                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                String dataNasc = rs.getString("dataDeNascimento");
                java.util.Date data = new java.util.Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    data = (java.util.Date) formato.parse(dataNasc);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String telefone = rs.getString("telefone");
                String nomeEmergencia = rs.getString("nomeEmergencia");
                String telefoneEmergencia = rs.getString("telefoneEmergencia");
                String cpfBanco = rs.getString("cpf");

                Cliente clienteBanco = new Cliente(nome, sexo, data, cpfBanco, telefone, endereco, nomeEmergencia, telefoneEmergencia);
                clienteBanco.setMatriculado(rs.getBoolean("matriculado"));


                return clienteBanco;
            }



        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return null;
    }

    public boolean esvaziou(){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean vazio = false;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();
            rs.next();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()) {
                vazio = true;
            }else{
                vazio = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return vazio;
    }

    public void atualizarCliente(String cpfCliente, Cliente cliente){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE cliente SET nome = ?, sexo = ?, dataDeNascimento = ?," +
                    " cpf = ?, telefone = ?, cep = ?, numero = ?, rua = ?, bairro = ?, cidade = ?, nomeEmergencia = ?," +
                    " telefoneEmergencia = ?, matriculado = ? WHERE cpf =" + cpfCliente);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            Calendar cal = Calendar.getInstance();
            cal.setTime(cliente.getDataDeNascimento());
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH)+1;
            int ano = cal.get(Calendar.YEAR);

            String dataNasc = dia+"/"+mes+"/"+ano;


            stmt.setString(3, dataNasc);
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEndereco().getCep());
            stmt.setString(7, cliente.getEndereco().getNumero());
            stmt.setString(8, cliente.getEndereco().getRua());
            stmt.setString(9, cliente.getEndereco().getBairro());
            stmt.setString(10, cliente.getEndereco().getCidade());
            stmt.setString(11, cliente.getNomeEmergencia());
            stmt.setString(12, cliente.getTelefoneEmergencia());
            stmt.setBoolean(13, cliente.isMatriculado());

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public ArrayList<Cliente> getListaDePessoas() {

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList <Cliente> listaDePessoas = new ArrayList<Cliente>();

        try {
            stmt = conexao.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();
            rs.next();


            while (rs.next()){

                String cep = rs.getString("cep");
                String numero = rs.getString("numero");
                String rua = rs.getString("rua");
                String bairro = rs.getString("bairro");
                String cidade = rs.getString("cidade");

                Endereco endereco = new Endereco(cep,numero,rua,bairro,cidade);

                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                String dataNasc = rs.getString("dataDeNascimento");
                java.util.Date data = new java.util.Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    data = (Date) formato.parse(dataNasc);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String telefone = rs.getString("telefone");
                String nomeEmergencia = rs.getString("nomeEmergencia");
                String telefoneEmergencia = rs.getString("telefoneEmergencia");
                String cpfBanco = rs.getString("cpf");

                Cliente clienteBanco = new Cliente(nome, sexo, data, cpfBanco, telefone, endereco, nomeEmergencia, telefoneEmergencia);
                clienteBanco.setMatriculado(rs.getBoolean("matriculado"));

                listaDePessoas.add(clienteBanco);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  listaDePessoas;

    }


}