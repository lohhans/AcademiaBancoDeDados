package negocios;

import dados.interfaces.IRepositorioModalidade;
import negocios.entidades.Modalidade;
import negocios.exception.ModalidadeJaCadastradaException;
import negocios.exception.ModalidadeNaoEncontradaException;

public class NegocioModalidade {

    private IRepositorioModalidade repositorioModalidade;

    public NegocioModalidade(IRepositorioModalidade repositorioModalidade){
        this.repositorioModalidade = repositorioModalidade;
    }

    public String printarModalidades() {
        String modalidades = "";
        for (int i = 0; i < repositorioModalidade.getListaDeModalidades().size(); i++) {
            modalidades += repositorioModalidade.getListaDeModalidades().get(i).toString()+"\n";
        }
        return modalidades;
    }

    public Modalidade buscarModalidade(int codigoModalidade) throws ModalidadeNaoEncontradaException {
        if (repositorioModalidade.buscarModalidade(codigoModalidade) != null){
            return repositorioModalidade.buscarModalidade(codigoModalidade);
        } else {
            throw new ModalidadeNaoEncontradaException();
        }
    }

    public void cadastrarModalidade(Modalidade modalidade) throws ModalidadeJaCadastradaException{
        if (repositorioModalidade.esvaziou()){
            repositorioModalidade.cadastrarModalidade(modalidade);
        } else if (repositorioModalidade.buscarModalidade(modalidade) == null){
            repositorioModalidade.cadastrarModalidade(modalidade);
        } else {
            throw new ModalidadeJaCadastradaException();
        }
    }

    public void atualizarNomeModalidade(int codigoModaliade, String novoNome) throws ModalidadeNaoEncontradaException{
        if (repositorioModalidade.buscarModalidade(codigoModaliade) == null){
            throw  new ModalidadeNaoEncontradaException();
        } else {
            repositorioModalidade.atualizarNomeModalidade(codigoModaliade, novoNome);
        }
    }

    public void atualizarPrecoModalidade(int codigoModaliade, double novoPreco) throws ModalidadeNaoEncontradaException{
        if (repositorioModalidade.buscarModalidade(codigoModaliade) == null){
            throw  new ModalidadeNaoEncontradaException();
        } else {
            repositorioModalidade.atualizarPrecoModalidade(codigoModaliade, novoPreco);
        }
    }

    public void removerModalidade(Modalidade modalidade) throws ModalidadeNaoEncontradaException {
        if (repositorioModalidade.buscarModalidade(modalidade) == null){
            throw new ModalidadeNaoEncontradaException();
        } else {
            repositorioModalidade.removerModalidade(modalidade);
        }
    }
}
