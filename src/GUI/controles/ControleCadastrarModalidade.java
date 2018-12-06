package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.AtualizacaoDeDadosConcluidaAlerta;
import GUI.telasDeAlerta.CadastroConcluidoAlerta;
import GUI.telasDeAlerta.ModalidadeJaCadastradaAlerta;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.entidades.Modalidade;
import negocios.exception.ModalidadeJaCadastradaException;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleCadastrarModalidade implements Initializable {

    private Fachada fachada;

    public ControleCadastrarModalidade() {
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelNome, labelValor;

    @FXML
    private TextField nome, valor;

    @FXML
    private Button botaoCadastrar, botaoVoltar;

    @FXML
    private void acaoCadastrarModalidade(ActionEvent event){
        try {
            fachada.cadastrarModalidade(nome.getText(), Double.parseDouble(valor.getText().replace(",",".")));

            CadastroConcluidoAlerta c = new CadastroConcluidoAlerta();
            c.alerta();

            acaoBotaoVoltar(event);
        } catch (ModalidadeJaCadastradaException e) {
            ModalidadeJaCadastradaAlerta m = new ModalidadeJaCadastradaAlerta();
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
