package dados;

import connection.ConnectionFactory;
import negocios.entidades.Cliente;
import negocios.entidades.Mensalidade;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DbMensalidade {



    public ArrayList criarMensalidades(Cliente cliente, int quantidadeMeses, double valor) throws ParseException {
        ArrayList<Mensalidade> listaMensalidades = new ArrayList<>();
        //utilizando o metodo criarDatas
        ArrayList<java.util.Date> listaDatas = criarDatas(quantidadeMeses);
        //adicionando a data especifica a determinada mensalidade
        for(int i = 0; i<quantidadeMeses; i++){
            Mensalidade mensalidade = new Mensalidade(listaDatas.get(i), valor);
            cadastrarMensalidade(cliente.getCpf(), mensalidade);
        }

        return listaMensalidades;
    }

    public ArrayList criarDatas(int quantidadeMeses) throws ParseException {
        ArrayList<java.util.Date> listaDatas = new ArrayList<java.util.Date>();

        //pega a data atual e quebra em inteiros
        java.util.Date dataAtual = new java.util.Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(dataAtual);
        int diaHoje = cal.get(Calendar.DAY_OF_MONTH);
        int mesHoje = cal.get(Calendar.MONTH)+1;
        int anoHoje = cal.get(Calendar.YEAR);

        String dataNasc = diaHoje+"/"+mesHoje+"/"+anoHoje;

        //gera as datas das mensalidades
        for(int i = 0; i < quantidadeMeses; i++){

            mesHoje += 1;

            if(mesHoje > 11){
                mesHoje = 0;
                anoHoje += 1;
            }

            //converte a data de Sting para Date e adiciona a lista de datas
            String dataEmString = diaHoje+"/"+mesHoje+"/"+anoHoje;

            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date data = dataFormatada.parse(dataEmString);
            listaDatas.add(data);

        }

        return listaDatas;
    }


    public void cadastrarMensalidade(String cpfCliente, Mensalidade mensalidade){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO mensalidade (codCliente, data, valor, pago) VALUES ( ?, ?, ?, ?)");

            stmt.setString(1, cpfCliente);

            Calendar cal = Calendar.getInstance();
            cal.setTime(mensalidade.getData());
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH)+1;
            int ano = cal.get(Calendar.YEAR);

            String data = dia+"/"+mes+"/"+ano;

            stmt.setString(2, data);
            stmt.setDouble(3, mensalidade.getValor());
            stmt.setBoolean(4, mensalidade.isPago());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }
    }
}
