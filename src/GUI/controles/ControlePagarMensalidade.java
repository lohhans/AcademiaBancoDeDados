package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.*;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.exception.ClienteNaoMatriculadoException;
import negocios.exception.MensalidadesPagasException;
import negocios.exception.PessoaNaoEncontradaException;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlePagarMensalidade implements Initializable {

    private Fachada fachada;

    public ControlePagarMensalidade(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelCpf;

    @FXML
    private TextField cpf;

    @FXML
    private Button botaoPagar, botaoVoltar;

    @FXML
    private void acaoPagarMensalidade(ActionEvent event){
        try {
            fachada.pagarMensalidade(cpf.getText());

            PagarMensalidadeAlerta p = new PagarMensalidadeAlerta();
            p.alerta();
        } catch (MensalidadesPagasException e) {
            MensalidadesPagasAlerta m = new MensalidadesPagasAlerta();
            m.alerta();
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        } catch (ClienteNaoMatriculadoException e) {
            ClienteNaoMatriculadoAlerta c = new ClienteNaoMatriculadoAlerta();
            c.alerta();
        } catch (ClassCastException c){
            ImpossivelRealizarOperacaoAlerta i = new ImpossivelRealizarOperacaoAlerta();
            i.alerta();
        }
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        Main.chamarTela("fxml/GuiMenuFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
