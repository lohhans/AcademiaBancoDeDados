package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.AtualizacaoDeDadosConcluidaAlerta;
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
import negocios.entidades.Funcionario;
import negocios.entidades.Pessoa;
import negocios.exception.PessoaNaoEncontradaException;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleDefinirGerente implements Initializable {

    private Fachada fachada;

    public ControleDefinirGerente(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelCpf, labelGerente, labelResposta;

    @FXML
    private TextField cpf;

    @FXML
    private Button botaoBuscar, botaoRebaixar, botaoPromover, botaoVoltar;

    @FXML
    private void verificarGerencia(ActionEvent event){
        try {
            Pessoa pessoa = fachada.buscarPessoa(cpf.getText());
            Funcionario funcionario = (Funcionario) pessoa;

            AtualizacaoDeDadosConcluidaAlerta a = new AtualizacaoDeDadosConcluidaAlerta();

            if (funcionario.isGerente()){
                labelResposta.setText(funcionario.getNome()+" é gerente!");
                botaoRebaixar.setVisible(true);
            } else {
                labelResposta.setText(funcionario.getNome()+" não é gerente!");
                botaoPromover.setVisible(true);
            }
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        } catch (ClassCastException c){
            ImpossivelRealizarOperacaoAlerta i = new ImpossivelRealizarOperacaoAlerta();
            i.alerta();
        }
    }

    @FXML
    private void promover(ActionEvent event){
        try {
            fachada.definirGerente(cpf.getText(), true);

            AtualizacaoDeDadosConcluidaAlerta a = new AtualizacaoDeDadosConcluidaAlerta();
            a.alerta();

            acaoBotaoVoltar(event);
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        }
    }

    @FXML
    private void rebaixar(ActionEvent event){
        try {
            fachada.definirGerente(cpf.getText(), false);

            AtualizacaoDeDadosConcluidaAlerta a = new AtualizacaoDeDadosConcluidaAlerta();
            a.alerta();

            acaoBotaoVoltar(event);
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        }
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        Main.chamarTela("fxml/GuiAtualizarFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
