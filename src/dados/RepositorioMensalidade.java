package dados;

import dados.interfaces.IRepositorioMensalidade;
import negocios.entidades.Mensalidade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RepositorioMensalidade implements IRepositorioMensalidade{

    @Override
    public ArrayList criarDatas(int quantidadeMeses) throws ParseException{
        ArrayList<Date> listaDatas = new ArrayList<Date>();

        //pega a data atual e quebra em inteiros
        Date dataAtual = new Date();

        int diaHoje = dataAtual.getDay();
        int mesHoje = dataAtual.getMonth();
        int anoHoje = dataAtual.getYear()+1900;

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
            Date data = dataFormatada.parse(dataEmString);
            listaDatas.add(data);

        }

        return listaDatas;
    }

    @Override
    public ArrayList criarMensalidades(int quantidadeMeses, double valor) throws ParseException {
         ArrayList<Mensalidade> listaMensalidades = new ArrayList<>();
         //utilizando o metodo criarDatas
         ArrayList<Date> listaDatas = criarDatas(quantidadeMeses);
         //adicionando a data especifica a determinada mensalidade
         for(int i = 0; i<quantidadeMeses; i++){
             Mensalidade mensalidade = new Mensalidade(listaDatas.get(i), valor);
             listaMensalidades.add(mensalidade);
         }

         return listaMensalidades;
    }

}