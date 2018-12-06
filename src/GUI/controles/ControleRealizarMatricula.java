package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.*;
import fachada.Fachada;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.entidades.Modalidade;
import negocios.exception.*;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControleRealizarMatricula implements Initializable {

    private Fachada fachada;

    public ControleRealizarMatricula(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    ObservableList<String> listaMeses = FXCollections.observableArrayList("1 mes", "3 meses" ,"6 meses", "12 meses");

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelCpf, labelSelecionarModalidade, labelMese, labelModalidadeCodigo;

    @FXML
    private TextField cpf, idsModalidades;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private Button botaoConfirmar, botaoVoltar, botaoMostrarId;

    @FXML
    private ScrollBar scroll;

    @FXML
    private void matricular(ActionEvent event){
        try {
            int codigo;
            ArrayList<Modalidade> listaModalidades = new ArrayList<>();

            String ids = idsModalidades.getText();
            String[] vetorIds = ids.split("-");

            for (int i = 0; i < vetorIds.length; i++) {
                int codModalidade = Integer.parseInt(vetorIds[i]);
                listaModalidades.add(fachada.buscarModalidade(codModalidade));
            }

            if (choiceBox.getValue().equals("1 mes")) {
                codigo = 1;
            } else if (choiceBox.getValue().equals("3 meses")) {
                codigo = 3;
            } else if (choiceBox.getValue().equals("6 meses")) {
                codigo = 6;
            } else {
                codigo = 12;
            }

            fachada.matricular(cpf.getText(), codigo, listaModalidades);

            CadastroConcluidoAlerta c = new CadastroConcluidoAlerta();
            c.alerta();

            acaoBotaoVoltar(event);
        } catch (PessoaNaoEncontradaException e) {
            PessoaNaoEncontradaAlerta p = new PessoaNaoEncontradaAlerta();
            p.alerta();
        } catch (ClienteJaMatriculadoException e) {
            ClienteJaMatriculadoAlerta c = new ClienteJaMatriculadoAlerta();
            c.alerta();
        } catch (QuantidadeDeMesesIndisponivelException e) {
            QuantidadeDeMesesIndisponivelAlerta q = new QuantidadeDeMesesIndisponivelAlerta();
            q.alerta();
        } catch (ParseException e) {
            DataErradaAlerta d = new DataErradaAlerta();
            d.alerta();
        } catch (ArrayListVazioException e) {
            ModalidadesNaoEscolhidasAlerta m = new ModalidadesNaoEscolhidasAlerta();
            m.alerta();
        } catch (ModalidadeNaoEncontradaException e) {
            ModalidadeNaoEncontradaAlerta m = new ModalidadeNaoEncontradaAlerta();
            m.alerta();
        } catch (NumberFormatException j){
            ModalidadesErradasAlerta m = new ModalidadesErradasAlerta();
            m.alerta();
        } catch (ClassCastException c){
            ImpossivelRealizarOperacaoAlerta i = new ImpossivelRealizarOperacaoAlerta();
            i.alerta();
        }
    }

    @FXML
    private void acaoBotaoCodigos(ActionEvent event){
        labelModalidadeCodigo.setText(fachada.listarModalidades());
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        Main.chamarTela("fxml/GuiMenuFuncionario.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.setItems(listaMeses);

    }
}