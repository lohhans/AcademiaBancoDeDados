package negocios.exception;

public class MensalidadesPagasException extends  Exception {
    public MensalidadesPagasException(){
        super("Todas as mensalidades ja foram quitadas!");
    }
}
