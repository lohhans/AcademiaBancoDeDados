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
import negocios.entidades.Mensalidade;
import negocios.exception.ClienteNaoMatriculadoException;
import negocios.exception.ImpossivelRemoverMatriculaDeUmMesException;
import negocios.exception.MensalidadeEmAbertoException;
import negocios.exception.PessoaNaoEncontradaException;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleRemoverMatricula implements Initializable {

    private Fachada fachada;

    public ControleRemoverMatricula(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelCpf, labelMulta;

    @FXML
    private TextField cpf;

    @FXML
    private Button botaoRemover, botaoVoltar;

    @FXML
    private void acaoRemoverMatricula(ActionEvent event){
        try {
            double multa = fachada.removerMatricula(cpf.getText());
            if(multa == 0){
                MatriculaRemovidaAlerta m = new MatriculaRemovidaAlerta();
                m.alerta();

                labelMulta.setText("Sem multa");
            } else {
                MensalidadeEmAbertoAlerta m = new MensalidadeEmAbertoAlerta();
                m.alerta();

                labelMulta.setText("Multa = "+multa);
            }
        } catch (ClienteNaoMatriculadoException e) {
            ClienteNaoMatriculadoAlerta c = new ClienteNaoMatriculadoAlerta();
            c.alerta();
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        } catch (MensalidadeEmAbertoException e) {
            MensalidadeEmAbertoAlerta m = new MensalidadeEmAbertoAlerta();
            m.alerta();
        } catch (ImpossivelRemoverMatriculaDeUmMesException e) {
            ImpossivelRemoverMatriculaDeUmMesAlerta i = new ImpossivelRemoverMatriculaDeUmMesAlerta();
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
