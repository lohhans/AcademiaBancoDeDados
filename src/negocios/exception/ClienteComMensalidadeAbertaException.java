package negocios.exception;

public class ClienteComMensalidadeAbertaException extends Exception {
    public ClienteComMensalidadeAbertaException() {
        super("Cliente com mensalidade em aberto!");
    }

}