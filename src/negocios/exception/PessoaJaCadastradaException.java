package negocios.exception;

public class PessoaJaCadastradaException extends Exception {
    public PessoaJaCadastradaException() {
        super("Pessoa ja cadastrada!");
    }

}
