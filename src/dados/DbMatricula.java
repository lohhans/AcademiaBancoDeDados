package dados;

import connection.ConnectionFactory;
import negocios.entidades.*;

import java.sql.*;
import java.util.ArrayList;

public class DbMatricula {



    public void matricular(Matricula matricula){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;


        for (int i = 0; i < matricula.getListaModalidadesDoCLiente().size(); i++) {
            int codModalidade = matricula.getListaModalidadesDoCLiente().get(i).getCodigoModalidade();
            adicionarMatriculaModalidade( matricula.getCliente().getCpf(), codModalidade);
        }


        try {
            stmt = conexao.prepareStatement("INSERT INTO matricula (cpfCliente) VALUES (?) ");

            stmt.setString(1, matricula.getCliente().getCpf());

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public void adicionarMatriculaModalidade( String codMatricula, int codModalidade){


        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO matricula_modalidade (codMatricula, codModalidade) VALUES (?, ?) ");

            stmt.setString(1, codMatricula);
            stmt.setInt(2, codModalidade);

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }


    public Matricula buscarMatricula(Cliente cliente){


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

        ArrayList<Modalidade> listaDeModalidades = listarModalidades(cliente.getCpf());
        ArrayList<Mensalidade> listaDeMensalidades = listarMensalidades(cliente.getCpf());

        Matricula matricula = null;
        matricula.setCliente(clienteBanco);
        matricula.setListaMensalidadeDoCliente(listaDeMensalidades);
        matricula.setListaModalidadesDoCLiente(listaDeModalidades);

        return matricula;

    }


    public ArrayList<Modalidade> listarModalidades( String cpf){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Modalidade> modalidades = null;
        ArrayList<String> idModalidades= null;

        try {

            stmt = conexao.prepareStatement("SELECT codModalidade FROM matricula_modalidade WHERE codMatricula= "+cpf);
            rs = stmt.executeQuery();

            while (rs.next()){

                idModalidades.add("codModalidade");

            }


        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        for (int i = 0; i < idModalidades.size(); i++) {

            Modalidade modalidade = buscarModalidade(idModalidades.get(i));
            modalidades.add(modalidade);
        }

        return modalidades;
    }


    public Modalidade buscarModalidade(String codModalidade){

        int codigo = Integer.parseInt(codModalidade);
        Modalidade modalidade = null;

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = conexao.prepareStatement("SELECT * FROM modalidade WHERE codigoModalidade="+codigo);
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()){

                modalidade.setNome(rs.getString("nome"));
                modalidade.setPreco(rs.getDouble("preco"));

            }


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

        return modalidade;

    }

    public ArrayList<Mensalidade> listarMensalidades(String codCliente){

        ArrayList<Mensalidade> mesalidades= null;
        Mensalidade mensalidade = null;


        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM mensalidade WHERE codCliente="+codCliente);
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()){

                mensalidade.setData(rs.getDate("data"));
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setPago(rs.getBoolean("pago"));
                mesalidades.add(mensalidade);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return mesalidades;
    }


    public void removerMatricula(Matricula matricula){

        removerMatriculaModalidade(matricula.getCliente().getCpf());
        removerMensalidade(matricula.getCliente().getCpf());

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE  FROM  matricula WHERE cpfCliente =" + matricula.getCliente().getCpf());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }


    public void removerMatriculaModalidade(String codMatricula){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE  FROM  matricula_modalidade WHERE codMatricula =" + codMatricula);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }


    public void removerMensalidade(String codCliente){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE  FROM  mensalidade WHERE codCliente ="+ codCliente);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public ArrayList<Mensalidade> buscarMensalidade(Cliente cliente){
       return listarMensalidades(cliente.getCpf());
    }


    public String conferirPagamentos(){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String resultado = null;

        try {
            stmt = conexao.prepareStatement("SELECT cliente.nome, mensalidade.codCliente FROM cliente, mensalidade WHERE mensalidade.pago=false GROUP BY  mensalidade.codCliente");
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()){

                resultado += rs.getString("nome")+", de cpf: "+rs.getString("codCliente")+"\n";

            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }


        return resultado;


    }


    public void pagarMensalidade(String cpf, Mensalidade mensalidade){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE mensalidade SET codCliente = ?, data = ?, valor= ?," +
                    " pago = ?, codMatricula = ? WHERE (codCliente =" +cpf+") AND data ="+mensalidade.getData());

            stmt.setString(1, cpf);
            stmt.setDate(2, (Date) mensalidade.getData());
            stmt.setDouble(3, mensalidade.getValor());
            stmt.setBoolean(4, mensalidade.isPago());
            stmt.setString(5, cpf);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

}
