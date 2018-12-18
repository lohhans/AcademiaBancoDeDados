package negocios;

import dados.DbModalidade;
import dados.interfaces.IRepositorioModalidade;
import negocios.entidades.Modalidade;
import negocios.exception.ModalidadeJaCadastradaException;
import negocios.exception.ModalidadeNaoEncontradaException;

public class NegocioModalidade {

    private DbModalidade dbModalidade;

    public NegocioModalidade(DbModalidade dbModalidade){
        this.dbModalidade = dbModalidade;
    }

    public String printarModalidades() {
        String modalidades = "";
        for (int i = 0; i < dbModalidade.getListaDeModalidades().size(); i++) {
            modalidades += dbModalidade.getListaDeModalidades().get(i).toString()+"\n";
        }
        return modalidades;
    }

    public Modalidade buscarModalidade(int codigoModalidade) throws ModalidadeNaoEncontradaException {
        if (dbModalidade.buscarModalidade(codigoModalidade) != null){
            return dbModalidade.buscarModalidade(codigoModalidade);
        } else {
            throw new ModalidadeNaoEncontradaException();
        }
    }

    public void cadastrarModalidade(Modalidade modalidade) throws ModalidadeJaCadastradaException{
        if (dbModalidade.esvaziou()){
            dbModalidade.cadastrarModalidade(modalidade);
        } else if (dbModalidade.buscarModalidade(modalidade) == null){
            dbModalidade.cadastrarModalidade(modalidade);
//            dbModalidade.cadastrarModalidade(modalidade);
        } else {
            throw new ModalidadeJaCadastradaException();
        }
    }

    public void atualizarNomeModalidade(int codigoModaliade, String novoNome) throws ModalidadeNaoEncontradaException{
        if (dbModalidade.buscarModalidade(codigoModaliade) == null){
            throw  new ModalidadeNaoEncontradaException();
        } else {
            dbModalidade.atualizarNomeModalidade(codigoModaliade, novoNome);
        }
    }

    public void atualizarPrecoModalidade(int codigoModaliade, double novoPreco) throws ModalidadeNaoEncontradaException{
        if (dbModalidade.buscarModalidade(codigoModaliade) == null){
            throw  new ModalidadeNaoEncontradaException();
        } else {
            dbModalidade.atualizarPrecoModalidade(codigoModaliade, novoPreco);
        }
    }

    public void removerModalidade(Modalidade modalidade) throws ModalidadeNaoEncontradaException {
        if (dbModalidade.buscarModalidade(modalidade) == null){
            throw new ModalidadeNaoEncontradaException();
        } else {
            dbModalidade.removerModalidade(modalidade);
        }
    }
}
