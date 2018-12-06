package negocios;

import dados.interfaces.IRepositorioMatricula;
import negocios.entidades.Matricula;
import negocios.exception.MensalidadesPagasException;

public class NegocioMensalidade {

    private IRepositorioMatricula repositorioMatricula;

    public NegocioMensalidade(IRepositorioMatricula repositorioMatricula) {
        this.repositorioMatricula = repositorioMatricula;
    }

    public boolean conferirMensalidadeTotalmentePaga(Matricula matricula){
        boolean foiPago = false;
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
                    break;
                }
            }

        } else {
            throw new MensalidadesPagasException();
        }
    }

    public String conferirPagamentoDosClientes() throws MensalidadesPagasException {
        String nomeCpfDosClientes = "";
        //varre lista de matricula
        for (int i = 0; i < repositorioMatricula.getListaDeMatriculas().size(); i++) {
            //varre lista de mensalidades dentro de cada matricula
            for (int j = 0; j < repositorioMatricula.getListaDeMatriculas().get(i).getListaMensalidadeDoCliente().size(); j++) {
                //se ouver mensalidades a pagar concatena string com nome e CPF
                if (!repositorioMatricula.getListaDeMatriculas().get(i).getListaMensalidadeDoCliente().get(j).isPago()) {
                    nomeCpfDosClientes += repositorioMatricula.getListaDeMatriculas().get(i).getCliente().getNome() + ", de cpf: " + repositorioMatricula.getListaDeMatriculas().get(i).getCliente().getCpf() + "\n";
                    break;
                }
            }
        }
        if(!nomeCpfDosClientes.equals("")){
            return nomeCpfDosClientes;
        } else {
            throw new MensalidadesPagasException();
        }

    }
}
