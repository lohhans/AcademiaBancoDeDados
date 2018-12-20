package dados;

import connection.ConnectionFactory;
import negocios.entidades.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DbMatricula {



    public void matricular(Matricula matricula){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO matricula (cpfCliente) VALUES (?) ");

            stmt.setString(1, matricula.getCliente().getCpf());

           stmt.executeUpdate();


            for (int i = 0; i < matricula.getListaModalidadesDoCLiente().size(); i++) {
                int codModalidade = matricula.getListaModalidadesDoCLiente().get(i).getCodigoModalidade();
                adicionarMatriculaModalidade( matricula.getCliente().getCpf(), codModalidade);
            }

            for (int i = 0; i < matricula.getListaMensalidadeDoCliente().size(); i++) {
//                int codModalidade = matricula.getListaModalidadesDoCLiente().get(i).getCodigoModalidade();
                System.out.println(matricula.getListaMensalidadeDoCliente().get(i).getValor());
                cadastrarMensalidade( matricula.getCliente().getCpf(), matricula.getListaMensalidadeDoCliente().get(i));
            }

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


        try {
            stmt = conexao.prepareStatement("SELECT nome, sexo, dataDeNascimento, cpf, telefone, cep," +
                    " numero, rua, bairro, cidade, nomeEmergencia, telefoneEmergencia, matriculado " +
                    "FROM cliente WHERE cpf =" + cliente.getCpf());
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            rs = stmt.executeQuery();
            rs.next();

            if (!rs.isBeforeFirst()) {
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


                ArrayList<Modalidade> listaDeModalidades = listarModalidades(cliente.getCpf());
                ArrayList<Mensalidade> listaDeMensalidades = listarMensalidades(cliente.getCpf());
                Matricula matricula = new Matricula(clienteBanco,listaDeModalidades, listaDeMensalidades);


                System.out.println("tamanho"+matricula.getListaMensalidadeDoCliente().size());
                ConnectionFactory.closeConnection(conexao, stmt, rs);
                return matricula;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        return null;

    }


    public ArrayList<Modalidade> listarModalidades( String cpf){

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Modalidade> modalidades = new ArrayList<Modalidade>();
        ArrayList<Integer> idModalidades= new ArrayList<Integer>();

        try {

            stmt = conexao.prepareStatement("SELECT codModalidade FROM matricula_modalidade WHERE codMatricula= "+cpf);
            rs = stmt.executeQuery();

            while (rs.next()){

                idModalidades.add(rs.getInt("codModalidade"));

            }


        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);

        }

        for (int i = 0; i < idModalidades.size(); i++) {

            String cod = idModalidades.get(i).toString();
            Modalidade modalidade = buscarModalidade(cod);
            modalidades.add(modalidade);
        }

        return modalidades;
    }


    public Modalidade buscarModalidade(String codModalidade){

        System.out.println(codModalidade);

        int codigo = Integer.parseInt(codModalidade);

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try {
            stmt = conexao.prepareStatement("SELECT * FROM modalidade WHERE codigoModalidade=?");
            stmt.setInt(1,codigo);
            rs = stmt.executeQuery();
            rs.next();

            //verifica se a consulta nao esta vazia
            if(rs.isBeforeFirst()){

                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                int cod = rs.getInt("codigoModalidade");
                Modalidade modalidade = new Modalidade(cod,nome, preco);
                ConnectionFactory.closeConnection(conexao, stmt);

                return modalidade;
            }


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);

        }

        return null;

    }

    public ArrayList<Mensalidade> listarMensalidades(String codCliente){

        ArrayList<Mensalidade> mesalidades= null;

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conexao.prepareStatement("SELECT * FROM mensalidade WHERE codCliente="+codCliente);
            rs = stmt.executeQuery();

            //verifica se a consulta nao esta vazia
            if(!rs.isBeforeFirst()){

                String dataBanco = rs.getString("data");
                java.util.Date data = new java.util.Date();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    data = (java.util.Date) formato.parse(dataBanco);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                double valor = rs.getDouble("valor");
                boolean pago = rs.getBoolean("pago");

                Mensalidade mensalidade = new Mensalidade(data,valor);
                mensalidade.setPago(pago);
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

            Calendar cal = Calendar.getInstance();
            cal.setTime(mensalidade.getData());
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH)+1;
            int ano = cal.get(Calendar.YEAR);

            String data = dia+"/"+mes+"/"+ano;

            stmt.setString(2, data );
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
