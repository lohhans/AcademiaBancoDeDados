package negocios.entidades;

import java.util.ArrayList;

public class Matricula {

    private ArrayList<Modalidade> listaModalidadesDoCLiente;
    private ArrayList<Mensalidade> listaMensalidadeDoCliente;
    private Cliente cliente;

    public Matricula(Cliente cliente, ArrayList<Modalidade> listaModalidadesDoCLiente, ArrayList<Mensalidade> listaMensalidadeDoCliente){
        this.cliente = cliente;
        this.listaModalidadesDoCLiente = listaModalidadesDoCLiente;
        this.listaMensalidadeDoCliente = listaMensalidadeDoCliente;
    }

    public ArrayList<Modalidade> getListaModalidadesDoCLiente() {
        return listaModalidadesDoCLiente;
    }

    public void setListaModalidadesDoCLiente(ArrayList<Modalidade> listaModalidadesDoCLiente) {
        this.listaModalidadesDoCLiente = listaModalidadesDoCLiente;
    }

    public ArrayList<Mensalidade> getListaMensalidadeDoCliente() {
        return listaMensalidadeDoCliente;
    }

    public void setListaMensalidadeDoCliente(ArrayList<Mensalidade> listaMensalidadeDoCliente) {
        this.listaMensalidadeDoCliente = listaMensalidadeDoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Matricula:\n" +
                "Lista Le modalidades do cliente = " + listaModalidadesDoCLiente + "\n" + //retorna array
                "Lista de mensalidade do cliente = " + listaMensalidadeDoCliente + "\n" + //retorna array
                "Cliente = " + cliente;
    }
}
