package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.AvaliacaoNaoEncontradaAlerta;
import GUI.telasDeAlerta.ImpossivelRealizarOperacaoAlerta;
import GUI.telasDeAlerta.InformeAvaliacaoAlerta;
import GUI.telasDeAlerta.PessoaNaoEncontradaAlerta;
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
import negocios.exception.AvaliacaoNaoEncontradaException;
import negocios.exception.PessoaNaoEncontradaException;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleBuscarAvaliacao implements Initializable {

    private Fachada fachada;

    public ControleBuscarAvaliacao(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button botaoBuscar, botaoVoltar;

    @FXML
    private Label labelCpf, labelAvaliacao, labelCodAvaliacao;

    @FXML
    private TextField cpf, codAvaliacao;

    @FXML
    private TextArea avaliacao;

    @FXML
    private void buscarAvaliacao(ActionEvent event){
        try {
            String a = fachada.buscarAvalicao(cpf.getText(), Integer.parseInt(codAvaliacao.getText()));
            labelAvaliacao.setText(a);
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        } catch (AvaliacaoNaoEncontradaException e) {
            AvaliacaoNaoEncontradaAlerta a = new AvaliacaoNaoEncontradaAlerta();
            a.alerta();
        } catch (NumberFormatException n){
            InformeAvaliacaoAlerta i = new InformeAvaliacaoAlerta();
            i.alerta();
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
