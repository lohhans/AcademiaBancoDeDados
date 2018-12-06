package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.MensalidadesDosCLientesPagasAlerta;
import GUI.telasDeAlerta.MensalidadesPagasAlerta;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.exception.MensalidadesPagasException;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleConferirPagamentoClientes implements Initializable {

    private Fachada fachada;

    public  ControleConferirPagamentoClientes(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelCpf, labelClientes, labelPagamentosCliente;

    @FXML
    private Button botaoBuscar, botaoVoltar;

    @FXML
    private TextField cpf;

    @FXML
    public void acao(){}

    @FXML
    private void acaoBotaoBuscar(ActionEvent event){
        try {
            labelClientes.setText(fachada.conferirPagamentoDosClientes());
        } catch (MensalidadesPagasException e) {
            MensalidadesDosCLientesPagasAlerta m = new MensalidadesDosCLientesPagasAlerta();
            m.alerta();
        }
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        Main.chamarTela("fxml/GuiMenuGerente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
