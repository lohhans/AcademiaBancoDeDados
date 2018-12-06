package negocios.exception;

public class DadosInvalidosException extends Exception {
    public DadosInvalidosException() {
        super("Dados invalidos, verifique os campos preenchidos!");
    }
}