package dados;

import connection.ConnectionFactory;
import negocios.entidades.Avaliacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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

            Calendar cal = Calendar.getInstance();
            cal.setTime(avaliacao.getData());
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH)+1;
            int ano = cal.get(Calendar.YEAR);

            String data = dia+"/"+mes+"/"+ano;

            stmt.setString(15, data);

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

        ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();


        try {

            stmt = conexao.prepareStatement("SELECT * FROM avaliacao WHERE codCliente="+cpf);
            rs = stmt.executeQuery();

            while (rs.next()){

                int numeroDaAvaliacao = rs.getInt("numeroDaAvaliacao");
                double circunferenciaAbdominal = rs.getDouble("circunferenciaAbdominal");
                double torax = rs.getDouble("torax");
                double cintura = rs.getDouble("cintura");
                double quadril = rs.getDouble("quadril");
                double antebracoDireito = rs.getDouble("antebracoDireito");
                double antebracoEsquerdo = rs.getDouble("antebracoEsquerdo");
                double bracoDireito = rs.getDouble("bracoDireito");
                double bracoEsquerdo = rs.getDouble("bracoEsquerdo");
                double coxaDireita = rs.getDouble("coxaDireita");
                double coxaEsquerda = rs.getDouble("coxaEsquerda");
                double panturrilhaDireita = rs.getDouble("panturrilhaDireita");
                double panturrilhaEsquerda = rs.getDouble("panturrilhaEsquerda");
                String dataB = rs.getString("data");

                java.util.Date data = new java.util.Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    data = (java.util.Date) formato.parse(dataB);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                Avaliacao avaliacao = new Avaliacao(circunferenciaAbdominal, torax, cintura, quadril, antebracoDireito, antebracoEsquerdo, bracoDireito, bracoEsquerdo, coxaDireita, coxaEsquerda, panturrilhaDireita, panturrilhaEsquerda);
                avaliacao.setData(data);
                avaliacao.setNumeroDaAvaliacao(numeroDaAvaliacao);

                avaliacoes.add(avaliacao);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

     return avaliacoes;

    }

}
