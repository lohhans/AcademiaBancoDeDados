package negocios.exception;

public class ModalidadeJaCadastradaException extends Exception {
    public ModalidadeJaCadastradaException() {
        super("Modalidade ja cadastrada!");
    }
}
