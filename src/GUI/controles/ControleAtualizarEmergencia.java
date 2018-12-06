package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.*;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.exception.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleAtualizarEmergencia implements Initializable {

    private Fachada fachada;

    public ControleAtualizarEmergencia(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelCpf, labelNovoNome, labelNovoTelefone;

    @FXML
    private TextField cpf, novoNome, novoTelefone;

    @FXML
    private Button botaoAtualizar, botaoVoltar;

    @FXML
    private void acaoAtualizarEmergencia(ActionEvent event){
        try {
            fachada.atualizarNomeEmergenciaCliente(cpf.getText(), novoTelefone.getText());
            fachada.atualizarTelefoneEmergenciaCliente(cpf.getText(), novoTelefone.getText());

            AtualizacaoDeDadosConcluidaAlerta a = new AtualizacaoDeDadosConcluidaAlerta();
            a.alerta();

            acaoBotaoVoltar(event);
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        } catch (DadosInvalidosException e) {
            DadosInvalidosAlerta d = new DadosInvalidosAlerta();
            d.alerta();
        } catch (CpfInvalidoException e) {
            CpfInvalidoAlerta c = new CpfInvalidoAlerta();
            c.alerta();
        } catch (CepInvalidoException e) {
            CepInvalidoAlerta c = new CepInvalidoAlerta();
            c.alerta();
        } catch (SexoIncorretoException e) {
            SexoIncorretoAlerta s = new SexoIncorretoAlerta();
            s.alerta();
        } catch (ClassCastException c){
            ImpossivelRealizarOperacaoAlerta i = new ImpossivelRealizarOperacaoAlerta();
            i.alerta();
        }
    }

    @FXML
    private void acaoBotaoVoltar (ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarCliente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
