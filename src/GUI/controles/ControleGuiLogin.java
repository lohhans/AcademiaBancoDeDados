package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.PessoaNaoEncontradaAlerta;
import GUI.telasDeAlerta.SenhaIncorretaAlerta;
import GUI.telasDeAlerta.SexoIncorretoAlerta;
import fachada.Fachada;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.entidades.Funcionario;
import negocios.entidades.Pessoa;
import negocios.exception.PessoaNaoEncontradaException;
import negocios.exception.SenhaIncorretaException;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleGuiLogin implements Initializable{

    private Fachada fachada;

    public ControleGuiLogin() {
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField login, senha;

    @FXML
    private Label labelLogin, labelSenha;

    @FXML
    private Button botaoEntrar;

    @FXML
    private void acaoLogin(ActionEvent event){
        try {
            fachada.login(login.getText(), senha.getText());
            Pessoa p = fachada.buscarPessoa(login.getText());
            Funcionario funcionario = (Funcionario) p;

            if (funcionario.isGerente()){
                Main.chamarTela("fxml/GuiMenuGerente.fxml", 800, 600);
                this.tela = (Stage) this.pane.getScene().getWindow();
                tela.close();
            } else {
                Main.chamarTela("fxml/GuiMenuFuncionario.fxml", 800, 600);
                this.tela = (Stage) this.pane.getScene().getWindow();
                tela.close();
            }

        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        } catch (SenhaIncorretaException e) {
            SenhaIncorretaAlerta s = new SenhaIncorretaAlerta();
            s.alerta();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
