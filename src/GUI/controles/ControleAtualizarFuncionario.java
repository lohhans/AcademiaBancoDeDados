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

public class ControleAtualizarFuncionario implements Initializable {

    private Fachada fachada;

    public ControleAtualizarFuncionario(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button botaoAtualizarNome, botaoAtualizarTelefone, botaoAtualizarEndereco, botaoDefinirGerente, botaoMudarSenha, botaoVoltar;

    @FXML
    private void acaoChamarTelaAtualizarNomeFuncionario(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarNomeFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaAtualizarTelefoneFuncionario(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarTelefoneFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaAtualizarEnderecoFuncionario(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarEnderecoFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaDefinirGerente(ActionEvent event){
        Main.chamarTela("fxml/GuiDefinirGerente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaMudarSenha(ActionEvent event){
        Main.chamarTela("fxml/GuiMudarSenha.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void botaoVoltar(ActionEvent event){
        Main.chamarTela("fxml/GuiMenuGerente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
