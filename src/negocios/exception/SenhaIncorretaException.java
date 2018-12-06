package negocios.exception;

public class SenhaIncorretaException extends Exception {
    public SenhaIncorretaException(){
        super("Senha inserida esta incorreta!");
    }
}
