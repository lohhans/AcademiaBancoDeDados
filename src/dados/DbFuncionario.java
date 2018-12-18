package dados;

import connection.ConnectionFactory;
import negocios.entidades.Endereco;
import negocios.entidades.Funcionario;

import java.sql.*;

public class DbFuncionario {

    public void adicionarFuncionario(Funcionario funcionario){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO funcionario (nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, senha, gerente) VALUES (?, ?, ?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ?) ");




            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSexo());
            java.sql.Date sqlDate = new java.sql.Date(funcionario.getDataDeNascimento().getDate());
            stmt.setDate(3, sqlDate);
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getEndereco().getCep());
            stmt.setString(7, funcionario.getEndereco().getNumero());
            stmt.setString(8, funcionario.getEndereco().getRua());
            stmt.setString(9, funcionario.getEndereco().getBairro());
            stmt.setString(10, funcionario.getEndereco().getCidade());
            stmt.setString(11, funcionario.getSenha());
            stmt.setBoolean(12, funcionario.isGerente());

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public Funcionario buscarFuncionario(Funcionario funcionario){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, cidade, senha, gerente " +
                    "FROM funcionario WHERE cpf ="+funcionario.getCpf());
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
                Date dataNasc = rs.getDate("dataDeNascimento");
                String telefone = rs.getString("telefone");
                String senha = rs.getString("senha");
                String cpf = rs.getString("cpf");

                Funcionario funcionarioBanco= new Funcionario(nome, sexo, dataNasc, cpf, telefone, endereco,senha);
                funcionarioBanco.setGerente(rs.getBoolean("gerente"));

                return funcionarioBanco;
            }



        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return null;
    }

    //Metodo de sobrecarga
    public Funcionario buscarFuncionario(String cpf){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, cidade, senha, gerente " +
                    "FROM funcionario WHERE cpf ="+cpf);
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
                Date dataNasc = rs.getDate("dataDeNascimento");
                String telefone = rs.getString("telefone");
                String senha = rs.getString("senha");

                Funcionario funcionarioBanco= new Funcionario(nome, sexo, dataNasc, cpf, telefone, endereco,senha);
                funcionarioBanco.setGerente(rs.getBoolean("gerente"));

                return funcionarioBanco;
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
            stmt = conexao.prepareStatement("SELECT * FROM funcionario");
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

    public void atualizarFuncionario(String cpfFuncionario, Funcionario funcionario){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE funcionario SET nome = ?, sexo = ?, dataDeNascimento = ?," +
                    " cpf = ?, telefone = ?, cep = ?, numero = ?, rua = ?, bairro = ?, cidade = ?, senha = ?," +
                    " gerente = ? WHERE cpf =" + cpfFuncionario);

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSexo());
            stmt.setDate(3, (Date) funcionario.getDataDeNascimento());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getEndereco().getCep());
            stmt.setString(7, funcionario.getEndereco().getNumero());
            stmt.setString(8, funcionario.getEndereco().getRua());
            stmt.setString(9, funcionario.getEndereco().getBairro());
            stmt.setString(10, funcionario.getEndereco().getCidade());
            stmt.setString(11, funcionario.getSenha());
            stmt.setBoolean(12, funcionario.isGerente());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public void removerFuncionario(Funcionario funcionario){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE  FROM  funcionario WHERE cpf =" + funcionario.getCpf());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }
}
