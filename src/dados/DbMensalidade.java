package dados;

import connection.ConnectionFactory;
import negocios.entidades.Mensalidade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbMensalidade {
    public void cadastrarModalidade(Mensalidade mensalidade){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO mensalidade (data, valor, pago) VALUES (?, ?, ?)");

            stmt.setDate(1, (Date) mensalidade.getData());
            stmt.setDouble(2, mensalidade.getValor());
            stmt.setBoolean(3, mensalidade.isPago());


            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

    }
}
