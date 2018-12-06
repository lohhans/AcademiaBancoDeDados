package negocios.entidades;

import java.util.Date;

public class Mensalidade {

    private Date data;
    private double valor;
    private boolean pago;

    public Mensalidade(Date data, double valor) {
        this.data = data;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    @Override
    public String toString() {
        return "Mensalidade:\n" +
                "Data = " + data + "\n" +
                "Valor = " + valor + "\n" +
                "Pago = " + pago;
    }
}