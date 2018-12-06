package negocios.exception;

public class ImpossivelRemoverMatriculaDeUmMesException extends  Exception {
    public ImpossivelRemoverMatriculaDeUmMesException() {
        super("Impossivel remover!\nAs matriculas com duracao igual a um mes nao podem ser removidas.");
    }
}
