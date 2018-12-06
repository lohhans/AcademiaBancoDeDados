package negocios.exception;

public class QuantidadeDeMesesIndisponivelException extends Exception {
    public QuantidadeDeMesesIndisponivelException() {
        super("Quantidade de meses indisponivel!\nEscolha entre: 1, 3, 6 ou 12 meses.");
    }
}
