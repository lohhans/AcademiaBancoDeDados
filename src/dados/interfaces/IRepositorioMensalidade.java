package dados.interfaces;

import negocios.entidades.Cliente;

import java.text.ParseException;
import java.util.ArrayList;

public interface IRepositorioMensalidade {

    ArrayList criarDatas(int quantidadeMeses) throws ParseException;

    ArrayList criarMensalidades(Cliente cliente, int quantidadeMeses, double valor) throws ParseException;
}
