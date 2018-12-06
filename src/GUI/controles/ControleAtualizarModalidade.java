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

public class ControleAtualizarModalidade implements Initializable {

    private Fachada fachada;

    public ControleAtualizarModalidade(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button botaoAtualizarNomeModalidade, botaoAtualizarPrecoModalidade, botaoVoltar;

    @FXML
    private void acaoChamarTelaAtualizarNomeModalidade(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarNomeModalidade.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @FXML
    private void acaoChamarTelaAtualizarPrecoModalidade(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarPrecoModalidade.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
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
