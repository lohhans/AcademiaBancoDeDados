package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.AtualizacaoDeDadosConcluidaAlerta;
import GUI.telasDeAlerta.ModalidadeNaoEncontradaAlerta;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.exception.ModalidadeNaoEncontradaException;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleAtualizarPrecoModalidade implements Initializable {
    private Fachada fachada;

    public ControleAtualizarPrecoModalidade() {
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private ScrollBar scroll;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField idModalidade, novoPrecoMod;

    @FXML
    private Label labelId, labelNovoPreco, labelModalidadeCodigo;

    @FXML
    private Button botaoAtualizar, botaoVoltar;

    @FXML
    private void atualizarPrecoModalidade(ActionEvent event) {
        try {
            fachada.alterarPrecoModalidade(Integer.parseInt(idModalidade.getText()), Double.parseDouble(novoPrecoMod.getText().replace(",",".")));

            AtualizacaoDeDadosConcluidaAlerta a = new AtualizacaoDeDadosConcluidaAlerta();
            a.alerta();

            acaoBotaoVoltar(event);
        } catch (ModalidadeNaoEncontradaException e) {
            ModalidadeNaoEncontradaAlerta m = new ModalidadeNaoEncontradaAlerta();
            m.alerta();
        }
    }

    @FXML
    private void acaoBotaoCodigos(ActionEvent event){
        labelModalidadeCodigo.setText(fachada.listarModalidades());
    }

    @FXML
    private void acaoBotaoVoltar (ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarModalidade.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}