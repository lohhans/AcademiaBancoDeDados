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

public class ControleMenuGerente implements Initializable {

    private Fachada fachada;

    public ControleMenuGerente(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button botaoCadastrarFuncionario, botaoAtualizarFuncionario, botaoDemitirFuncionario, botaoCadastrarModalidade, botaoAtualizarModalidade, botaoRemoverModalidade, botaoConferirPagamento, botaoVoltar;

    @FXML
    private void chamarTelaCadastroFuncionario(ActionEvent event){
        Main.chamarTela("fxml/GuiCadastrarFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void chamarTelaAtualizarFuncionario(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void chamarTelaDemitirFuncionario(ActionEvent event){
        Main.chamarTela("fxml/GuiDemitirFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void chamarTelaCadastrarModalidade(ActionEvent event){
        Main.chamarTela("fxml/GuiCadastrarModalidade.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void chamarTelaAtualizarModalidade(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarModalidade.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void chamarTelaRemoverModalidade(ActionEvent event){
        Main.chamarTela("fxml/GuiRemoverModalidade.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void chamarTelaConferirPagamentoClientes(ActionEvent event){
        Main.chamarTela("fxml/GuiConferirPagamentoClientes.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        Main.chamarTela("fxml/GuiLogin.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
