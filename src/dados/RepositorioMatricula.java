package dados;

import dados.interfaces.IRepositorioMatricula;
import negocios.entidades.Cliente;
import negocios.entidades.Matricula;
import negocios.entidades.Mensalidade;

import java.util.ArrayList;

public class RepositorioMatricula implements IRepositorioMatricula {
    private ArrayList<Matricula> listaDeMatriculas;

    public RepositorioMatricula() {
        listaDeMatriculas = new ArrayList<>();
    }

    @Override
    public Matricula buscarMatricula(Cliente cliente){
        for (int i = 0; i < listaDeMatriculas.size(); i++) {
            if (listaDeMatriculas.get(i).getCliente().getCpf().equalsIgnoreCase(cliente.getCpf())) {
                return listaDeMatriculas.get(i);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Mensalidade> buscarMensalidade(Cliente cliente){
        Matricula matriculaDoClienteBuscado = buscarMatricula(cliente);
        return matriculaDoClienteBuscado.getListaMensalidadeDoCliente();
    }

    @Override
    public void matricular(Matricula matricula){
        listaDeMatriculas.add(matricula);
    }

    @Override
    public void removerMatricula(Matricula matricula){
        listaDeMatriculas.remove(matricula);
    }

    @Override
    public ArrayList<Matricula> getListaDeMatriculas() {
        return listaDeMatriculas;
    }
}