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

public class ControleMenuFuncionario implements Initializable {

    private Fachada fachada;

    public ControleMenuFuncionario() {
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button botaoVoltar, botaoAtualizarInfoCliente, botaoCadastrarCliente, botaoRealizarMatricula, botaoRealizarAvaliacao, botaoBuscarAvaliacao, botaoPagarMensalidade, botaoRemoverMatricula;

    @FXML
    private void acaoChamarTelaAtualizarCliente(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarCliente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaRealizarMatricula(ActionEvent event){
        Main.chamarTela("fxml/GuiRealizarMatricula.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
        //Corrigir
    }

    @FXML
    private void acaoChamarTelaCadastrarCliente(ActionEvent event){
        Main.chamarTela("fxml/GuiCadastrarCliente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaCadastrarAvaliacao(ActionEvent event){
        Main.chamarTela("fxml/GuiCadastrarAvaliacao.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaRemoverMatricula(ActionEvent event){
        Main.chamarTela("fxml/GuiRemoverMatricula.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaBuscarAvaliacao(ActionEvent event){
        Main.chamarTela("fxml/GuiBuscarAvaliacao.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaPagarMensalidade(ActionEvent event){
        Main.chamarTela("fxml/GuiPagarMensalidade.fxml", 800, 600);
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
