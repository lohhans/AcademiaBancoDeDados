package negocios.exception;

public class ClienteJaMatriculadoException extends Exception  {
    public ClienteJaMatriculadoException(){
        super("Cliente ja matriculado!");
    }
}
