package dados;

import connection.ConnectionFactory;
import negocios.entidades.Avaliacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbAvaliacao {


    public void adicionar(String cpf, Avaliacao avaliacao){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO avaliacao (codCliente, numeroDaAvaliacao, circunferenciaAbdominal, torax, cintura, quadril," +
                    " antebracoDireito, antebracoEsquerdo, bracoDireito, bracoEsquerdo, coxaDireita, coxaEsquerda, panturrilhaDireita, panturrilhaEsquerda, data ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ?) ");

            stmt.setString(1, cpf);
            stmt.setInt(2, avaliacao.getNumeroDaAvaliacao());
            stmt.setDouble(3,avaliacao.getCircunferenciaAbdominal());
            stmt.setDouble(4,avaliacao.getTorax());
            stmt.setDouble(5,avaliacao.getCintura());
            stmt.setDouble(6,avaliacao.getQuadril());
            stmt.setDouble(7,avaliacao.getAntebracoDireito());
            stmt.setDouble(8,avaliacao.getAntebracoEsquerdo());
            stmt.setDouble(9,avaliacao.getBracoDireito());
            stmt.setDouble(10,avaliacao.getBracoEsquerdo());
            stmt.setDouble(11,avaliacao.getCoxaDireita());
            stmt.setDouble(12,avaliacao.getCoxaEsquerda());
            stmt.setDouble(13,avaliacao.getPanturrilhaDireita());
            stmt.setDouble(14,avaliacao.getPanturrilhaEsquerda());
            java.sql.Date sqlDate = new java.sql.Date(avaliacao.getData().getDate());
            stmt.setDate(15, sqlDate);


            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }

    public ArrayList<Avaliacao> getListaDeAvaliacoes(String cpf){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Avaliacao avaliacao = null;
        ArrayList<Avaliacao> avaliacoes = null;


        try {

            stmt = conexao.prepareStatement("SELECT * FROM avaliacao WHERE codCliente="+cpf);
            rs = stmt.executeQuery();

            while (rs.next()){


                avaliacao.setNumeroDaAvaliacao(rs.getInt("numeroDaAvaliacao"));
                avaliacao.setCircunferenciaAbdominal(rs.getDouble("circunferenciaAbdominal"));
                avaliacao.setTorax(rs.getDouble("circunferenciaAbdominal"));
                avaliacao.setCintura(rs.getDouble("cintura"));
                avaliacao.setQuadril(rs.getDouble("quadril"));
                avaliacao.setAntebracoDireito(rs.getDouble("antebracoDireito"));
                avaliacao.setAntebracoEsquerdo(rs.getDouble("antebracoEsquerdo"));
                avaliacao.setBracoDireito(rs.getDouble("bracoDireito"));
                avaliacao.setBracoEsquerdo(rs.getDouble("bracoEsquerdo"));
                avaliacao.setCoxaDireita(rs.getDouble("coxaDireita"));
                avaliacao.setCoxaEsquerda(rs.getDouble("coxaEsquerda"));
                avaliacao.setPanturrilhaDireita(rs.getDouble("panturrilhaDireita"));
                avaliacao.setPanturrilhaEsquerda(rs.getDouble("panturrilhaEsquerda"));
                avaliacao.setData(rs.getDate("data"));

                avaliacoes.add(avaliacao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

     return avaliacoes;

    }

}
