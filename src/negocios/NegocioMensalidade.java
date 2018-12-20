package negocios;

import dados.DbMatricula;
import dados.DbMensalidade;
import dados.interfaces.IRepositorioMatricula;
import negocios.entidades.Matricula;
import negocios.entidades.Mensalidade;
import negocios.exception.MensalidadesPagasException;

public class NegocioMensalidade {

    private DbMatricula dbMatricula;

    public NegocioMensalidade(DbMatricula dbMatricula) {
        this.dbMatricula= dbMatricula;
    }

    public boolean conferirMensalidadeTotalmentePaga(Matricula matricula){
        boolean foiPago = false;

        System.out.println(matricula.getListaMensalidadeDoCliente().get(matricula.getListaMensalidadeDoCliente().size()-1).getValor());

        if (matricula.getListaMensalidadeDoCliente().get(matricula.getListaMensalidadeDoCliente().size()-1).isPago()) {
            foiPago = true;
        }
        return foiPago;
    }

    //Metodo recebe uma matricula e verifica se todas as mensalidades foram pagas
    //caso nao tenham sido pagas, ele seta como pago a seguinte a ser paga
    // caso contrario retorna que todas ja foram pagas
    public void pagarMensalidade(Matricula matricula) throws MensalidadesPagasException {
        if (!conferirMensalidadeTotalmentePaga(matricula)) {
            for (int i = 0; i < matricula.getListaMensalidadeDoCliente().size(); i++) {
                if (!matricula.getListaMensalidadeDoCliente().get(i).isPago()) {
                    matricula.getListaMensalidadeDoCliente().get(i).setPago(true);
                    Mensalidade mensalidade = matricula.getListaMensalidadeDoCliente().get(i);
                    dbMatricula.pagarMensalidade(matricula.getCliente().getCpf(), mensalidade);

                    break;
                }
            }

        } else {
            throw new MensalidadesPagasException();
        }
    }

    public String conferirPagamentoDosClientes() throws MensalidadesPagasException {
        String nomeCpfDosClientes = null;
        nomeCpfDosClientes = dbMatricula.conferirPagamentos();
        if(!nomeCpfDosClientes.equals(null)){
            return nomeCpfDosClientes;
        } else {
            throw new MensalidadesPagasException();
        }

    }
}
