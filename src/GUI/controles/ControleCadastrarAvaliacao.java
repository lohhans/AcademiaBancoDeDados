package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.CadastroConcluidoAlerta;
import GUI.telasDeAlerta.DadosInvalidosAlerta;
import GUI.telasDeAlerta.ImpossivelRealizarOperacaoAlerta;
import GUI.telasDeAlerta.PessoaNaoEncontradaAlerta;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import negocios.exception.PessoaNaoEncontradaException;


import java.net.URL;
import java.util.ResourceBundle;

public class ControleCadastrarAvaliacao implements Initializable {

    private Fachada fachada;

    public ControleCadastrarAvaliacao() {
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelEsquerdo, labelDireito, labelCpf;

    @FXML
    private TextField cpf, bracoEsquerdo, antebracoEsquerdo, coxaEsquerda, panturrilhaEsquerda, torax, cAbdominal, cintura, quadril, bracoDireito, antebracoDireito, coxaDireita, panturrilhaDireita;

    @FXML
    private Button botaoVoltar, botaoCadastrar;

    @FXML
    private void acaoAdicionarAvaliacao(ActionEvent event){
        try {
            if(cAbdominal.getText() != null && torax.getText() != null && cintura.getText() != null && quadril.getText() != null && antebracoDireito.getText() != null && antebracoEsquerdo.getText() != null && bracoDireito.getText() != null && bracoEsquerdo.getText() != null && coxaDireita.getText() != null && coxaEsquerda.getText() != null && panturrilhaDireita.getText() != null && panturrilhaEsquerda.getText() != null){
                fachada.adiconarAvaliacao(cpf.getText(), Double.parseDouble(cAbdominal.getText()), Double.parseDouble(torax.getText()), Double.parseDouble(cintura.getText()), Double.parseDouble(quadril.getText()), Double.parseDouble(antebracoDireito.getText()), Double.parseDouble(antebracoEsquerdo.getText()), Double.parseDouble(bracoDireito.getText()), Double.parseDouble(bracoEsquerdo.getText()), Double.parseDouble(coxaDireita.getText()), Double.parseDouble(coxaEsquerda.getText()), Double.parseDouble(panturrilhaDireita.getText()), Double.parseDouble(panturrilhaEsquerda.getText()));

                CadastroConcluidoAlerta c = new CadastroConcluidoAlerta();
                c.alerta();

                acaoBotaoVoltar(event);
            }
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        } catch (ClassCastException c){
            ImpossivelRealizarOperacaoAlerta i = new ImpossivelRealizarOperacaoAlerta();
            i.alerta();
        } catch (NumberFormatException n ){
            DadosInvalidosAlerta d = new DadosInvalidosAlerta();
            d.alerta();
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
