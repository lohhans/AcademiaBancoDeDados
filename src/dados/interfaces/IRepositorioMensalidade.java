package dados.interfaces;

import java.text.ParseException;
import java.util.ArrayList;

public interface IRepositorioMensalidade {

    ArrayList criarDatas(int quantidadeMeses) throws ParseException;

    ArrayList criarMensalidades(int quantidadeMeses, double valor) throws ParseException;
}
