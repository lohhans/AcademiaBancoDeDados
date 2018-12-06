package GUI;

import fachada.Fachada;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import negocios.exception.*;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/GuiLogin.fxml"));
        primaryStage.setTitle("Academia A-V-A");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void chamarTela(String nomeDaTela, int largura, int altura){
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(Main.class.getResource(nomeDaTela));
            stage.setTitle("Academia AVA");
            stage.setScene(new Scene(root, largura, altura));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Fachada fachada;
        fachada = Fachada.getInstancia();

        try {
            fachada.cadastrarFuncionario("Funcionario", "Masculino", new Date(), "12345678900", "87988285821", "55740000", "02", "Rua Numero Dois", "Centro", "Machados", "00");
            fachada.cadastrarFuncionario("Gerente", "Masculino", new Date(), "98765432100", "87991500860", "55320000", "01", "Rua Numero Um", "Centro", "Lagoa do Ouro", "00");
            fachada.definirGerente("98765432100", true);
            fachada.cadastrarCliente("Antonio", "Masculino", new Date(), "11111111111", "87990900876", "55450234", "10", "Rua casa", "Centro", "Garanhuns", null, null);
            fachada.cadastrarCliente("Vitu", "Masculino", new Date(), "22222222222", "87990900876", "55450234", "10", "Rua casa", "Centro", "Garanhuns", null, null);
            fachada.cadastrarModalidade("Academia", 60);
            fachada.cadastrarModalidade("Fit Dance", 50);
            fachada.cadastrarModalidade("Jump", 55);
        } catch (PessoaJaCadastradaException e) {
            e.printStackTrace();
        } catch (CepInvalidoException e) {
            e.printStackTrace();
        } catch (SexoIncorretoException e) {
            e.printStackTrace();
        } catch (CpfInvalidoException e) {
            e.printStackTrace();
        } catch (DadosInvalidosException e) {
            e.printStackTrace();
        } catch (PessoaNaoEncontradaException e) {
            e.printStackTrace();
        } catch (ModalidadeJaCadastradaException e) {
            e.printStackTrace();
        }

        launch(args);
    }

}
