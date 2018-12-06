package GUI.controles;

import GUI.Main;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleAtualizarCliente implements Initializable  {

    private Fachada fachada;

    public ControleAtualizarCliente(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button botaoAtualizarNome, botaoAtualizarTelefone, botaoAtualizarEndereco, botaoAtualizarContatoEmergencia, botaoVoltar;

    @FXML
    private void acaoChamarTelaAtualizarNomeCliente(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarNomeCliente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaAtualizarTelefoneCliente(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarTelefoneCliente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaAtualizarEnderecoCliente(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarEnderecoCliente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaAtualizarContatoEmergenciaCliente(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarEmergencia.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void botaoVoltar(ActionEvent event){
        Main.chamarTela("fxml/GuiMenuFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
