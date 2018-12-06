package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.FuncionarioDemitidoAlerta;
import GUI.telasDeAlerta.ImpossivelRealizarOperacaoAlerta;
import GUI.telasDeAlerta.PessoaNaoEncontradaAlerta;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.exception.PessoaNaoEncontradaException;


import java.net.URL;
import java.util.ResourceBundle;

public class ControleDemitirFuncionario implements Initializable {

    private Fachada fachada;

    public ControleDemitirFuncionario(){
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
    private Button botaoDemitir, botaoVoltar;

    @FXML
    private void acaoDemitirFuncionario(ActionEvent event){
        try {
            fachada.demitirFuncionario(cpf.getText());

            FuncionarioDemitidoAlerta f = new FuncionarioDemitidoAlerta();
            f.alerta();

            acaoBotaoVoltar(event);
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        } catch (ClassCastException c){
            ImpossivelRealizarOperacaoAlerta i = new ImpossivelRealizarOperacaoAlerta();
            i.alerta();
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
