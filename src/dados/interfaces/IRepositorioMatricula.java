package dados.interfaces;

import negocios.entidades.Cliente;
import negocios.entidades.Matricula;
import negocios.entidades.Mensalidade;

import java.util.ArrayList;

public interface IRepositorioMatricula {

    Matricula buscarMatricula(Cliente cliente);

    ArrayList<Mensalidade> buscarMensalidade(Cliente cliente);

    void matricular(Matricula matricula);

    void removerMatricula(Matricula matricula);

    ArrayList<Matricula> getListaDeMatriculas();
}
