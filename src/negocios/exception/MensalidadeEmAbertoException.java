package negocios.exception;

public class MensalidadeEmAbertoException extends  Exception {
    public MensalidadeEmAbertoException (){
        super("Impossivel remover matricula!\nAinda ha mensalidades em aberto.");
    }
}
