package dados;
import connection.ConnectionFactory;
import negocios.entidades.Modalidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
