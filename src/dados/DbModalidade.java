package dados;
import connection.ConnectionFactory;
import negocios.entidades.Modalidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbModalidade {

    public void cadastrarModalidade(Modalidade modalidade){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO modalidade (nome, codigoModalidade, preco) VALUES (?, ?, ?)");

            stmt.setString(1, modalidade.getNome());
            stmt.setInt(2, modalidade.getCodigoModalidade());
            stmt.setDouble(3, modalidade.getPreco());

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }


    public boolean esvaziou(){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean vazio = false;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM modalidade");
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


    public Modalidade buscarModalidade(Modalidade modalidade){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM  modalidade WHERE codigoModalidade ="+modalidade.getCodigoModalidade());
            rs = stmt.executeQuery();
            rs.next();
            //verifica se a consulta nao esta vazia
            if(!rs.isBeforeFirst()){

                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int codigo = rs.getInt("codigoModalidade");

                Modalidade modalidadeBanco = new Modalidade(codigo, nome, preco);
                ConnectionFactory.closeConnection(conexao, stmt, rs);
                return modalidade;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return null;
    }


    public Modalidade buscarModalidade(int codigoModalidade){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM modalidade WHERE codigoModalidade ="+codigoModalidade);
            rs = stmt.executeQuery();
            rs.next();
            //verifica se a consulta nao esta vazia
            if(!rs.isBeforeFirst()){

                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int codigo = rs.getInt("codigoModalidade");

                Modalidade modalidade = new Modalidade(codigo, nome, preco);
                ConnectionFactory.closeConnection(conexao, stmt, rs);
                return modalidade;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return null;
    }

    public void atualizarNomeModalidade(int codigoModaliade, String novoNome){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE modalidade SET nome = ? WHERE codigoModalidade=" + codigoModaliade);

            stmt.setString(1, novoNome);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public void atualizarPrecoModalidade(int codigoModaliade, double novoPreco){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE modalidade SET preco = ? WHERE codigoModalidade=" + codigoModaliade);

            stmt.setDouble(1, novoPreco);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public void removerModalidade(Modalidade modalidade){
        removerMatriculaModalidade(modalidade.getCodigoModalidade());

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE  FROM  modalidade WHERE codigoModalidade =" + modalidade.getCodigoModalidade());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public void removerMatriculaModalidade(int codModalidade){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("DELETE  FROM  matricula_modalidade WHERE codModalidade=" + codModalidade);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public ArrayList<Modalidade> getListaDeModalidades(){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        ArrayList <Modalidade> modalidades = new ArrayList<Modalidade>();

        try {
            stmt = conexao.prepareStatement("SELECT * FROM modalidade");
            rs = stmt.executeQuery();

            while (rs.next()){

                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int codigo = rs.getInt("codigoModalidade");
                Modalidade modalidade = new Modalidade(codigo, nome, preco);
                modalidades.add(modalidade);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  modalidades;

    }

}
