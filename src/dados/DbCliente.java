package dados;

import connection.ConnectionFactory;
import negocios.entidades.Cliente;
import negocios.entidades.Endereco;

import java.sql.*;

public class DbCliente {

    public void adicionarCliente(Cliente cliente){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO cliente (nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, nomeEmergencia, telefoneEmergencia) VALUES (?, ?, ?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ?) ");

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            java.sql.Date sqlDate = new java.sql.Date(cliente.getDataDeNascimento().getDate());
            stmt.setDate(3, sqlDate);
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEndereco().getCep());
            stmt.setString(7, cliente.getEndereco().getNumero());
            stmt.setString(8, cliente.getEndereco().getRua());
            stmt.setString(9, cliente.getEndereco().getBairro());
            stmt.setString(10, cliente.getEndereco().getCidade());
            stmt.setString(11, cliente.getNomeEmergencia());
            stmt.setString(12, cliente.getTelefoneEmergencia());

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

        Cliente clienteBanco= null;
        Endereco endereco = null;
        try {
            stmt = conexao.prepareStatement("SELECT nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                                                 " numero, rua, bairro, cidade, nomeEmergencia, telefoneEmergencia " +
                                                 "FROM cliente WHERE cpf ="+cliente.getCpf());
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()){

                endereco.setCep(rs.getString("cep"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));

                clienteBanco.setNome(rs.getString("nome"));
                clienteBanco.setSexo(rs.getString("sexo"));
                clienteBanco.setDataDeNascimento(rs.getDate("dataDeNacimento"));
                clienteBanco.setEndereco(endereco);
                clienteBanco.setNomeEmergencia(rs.getString("nomeEmergencia"));
                clienteBanco.setTelefoneEmergencia(rs.getString("telefoneEmergencia"));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return clienteBanco;
    }

    //Metodo de sobrecarga
    public Cliente buscarCliente(String cpf){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Cliente clienteBanco= null;
        Endereco endereco = null;
        try {
            stmt = conexao.prepareStatement("SELECT nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, nomeEmergencia, telefoneEmergencia " +
                    "FROM cliente WHERE cpf ="+cpf);
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()){

                endereco.setCep(rs.getString("cep"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));

                clienteBanco.setNome(rs.getString("nome"));
                clienteBanco.setSexo(rs.getString("sexo"));
                clienteBanco.setDataDeNascimento(rs.getDate("dataDeNacimento"));
                clienteBanco.setEndereco(endereco);
                clienteBanco.setNomeEmergencia(rs.getString("nomeEmergencia"));
                clienteBanco.setTelefoneEmergencia(rs.getString("telefoneEmergencia"));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return clienteBanco;
    }

    public boolean esvaziou(){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean vazio = false;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

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
                    " telefoneEmergencia = ? WHERE cpf =" + cpfCliente);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSexo());
            stmt.setDate(3, (Date) cliente.getDataDeNascimento());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getEndereco().getCep());
            stmt.setString(7, cliente.getEndereco().getNumero());
            stmt.setString(8, cliente.getEndereco().getRua());
            stmt.setString(9, cliente.getEndereco().getBairro());
            stmt.setString(10, cliente.getEndereco().getCidade());
            stmt.setString(11, cliente.getNomeEmergencia());
            stmt.setString(12, cliente.getTelefoneEmergencia());

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }



}