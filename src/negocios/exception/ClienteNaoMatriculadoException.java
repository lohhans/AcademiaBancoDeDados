package negocios.exception;

public class ClienteNaoMatriculadoException extends Exception {
    public ClienteNaoMatriculadoException() {
        super ("Cliente nao esta matriculado");
    }
}
