package dados.interfaces;

import negocios.entidades.Modalidade;

import java.util.ArrayList;

public interface IRepositorioModalidade{
    boolean esvaziou();

    void cadastrarModalidade(Modalidade modalidade);

    Modalidade buscarModalidade(Modalidade modalidade);

    Modalidade buscarModalidade(int codigoModalidade);

    void atualizarNomeModalidade(int codigoModaliade, String novoNome);

    void atualizarPrecoModalidade(int codigoModaliade, double novoPreco);

    void removerModalidade(Modalidade modalidade);

    ArrayList<Modalidade> getListaDeModalidades();
}