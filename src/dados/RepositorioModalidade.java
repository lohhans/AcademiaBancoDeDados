package dados;
import dados.interfaces.IRepositorioModalidade;
import negocios.entidades.*;
import java.util.ArrayList;

public class RepositorioModalidade implements IRepositorioModalidade{

    ArrayList<Modalidade> listaDeModalidades;

    public RepositorioModalidade() {
        listaDeModalidades = new ArrayList<>();
    }

    @Override
    public boolean esvaziou(){
        return listaDeModalidades.isEmpty();
    }

    @Override
    public void cadastrarModalidade(Modalidade modalidade) {
        listaDeModalidades.add(modalidade);
    }

    @Override
    public Modalidade buscarModalidade(Modalidade modalidade){
        for (int i = 0; i < listaDeModalidades.size(); i++) {
            if (listaDeModalidades.get(i).getNome().equalsIgnoreCase(modalidade.getNome())){
                return listaDeModalidades.get(i);
            }
        }
        return null;
    }

    @Override
    public Modalidade buscarModalidade(int codigoModalidade) {
        for (int i = 0; i < listaDeModalidades.size(); i++) {
            if (listaDeModalidades.get(i).getCodigoModalidade() == codigoModalidade) {
                return listaDeModalidades.get(i);
            }
        }
        return null;
    }

    @Override
    public void atualizarNomeModalidade(int codigoModaliade, String novoNome){
        Modalidade modalidade = this.buscarModalidade(codigoModaliade);
        modalidade.setNome(novoNome);
    }

    @Override
    public void atualizarPrecoModalidade(int codigoModaliade, double novoPreco){
        Modalidade modalidade = this.buscarModalidade(codigoModaliade);
        modalidade.setPreco(novoPreco);
    }

    @Override
    public void removerModalidade(Modalidade modalidade) {
        listaDeModalidades.remove(modalidade);
    }

    @Override
    public ArrayList<Modalidade> getListaDeModalidades() {
        return listaDeModalidades;
    }
}
