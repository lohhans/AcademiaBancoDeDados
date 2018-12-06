package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.AtualizacaoDeDadosConcluidaAlerta;
import GUI.telasDeAlerta.CadastroConcluidoAlerta;
import GUI.telasDeAlerta.ModalidadeJaCadastradaAlerta;
import GUI.telasDeAlerta.ModalidadeNaoEncontradaAlerta;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.exception.ModalidadeJaCadastradaException;
import negocios.exception.ModalidadeNaoEncontradaException;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleRemoverModalidade implements Initializable {

    private Fachada fachada;

    public ControleRemoverModalidade(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelId;

    @FXML
    private TextField id;

    @FXML
    private Button botaoRemover, botaoVoltar;

    @FXML
    private void acaoRemoverModalidade(ActionEvent event){
        try {
            fachada.removerModalidade(Integer.parseInt(id.getText()));

            AtualizacaoDeDadosConcluidaAlerta a = new AtualizacaoDeDadosConcluidaAlerta();
            a.alerta();

            acaoBotaoVoltar(event);
        } catch (ModalidadeNaoEncontradaException e) {
            ModalidadeNaoEncontradaAlerta m = new ModalidadeNaoEncontradaAlerta();
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
