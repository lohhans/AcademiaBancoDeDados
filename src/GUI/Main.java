package GUI;

import connection.ConnectionFactory;
import fachada.Fachada;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import negocios.exception.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        Date dataFormatada = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataFormatada = formato.parse("1/1/2017");
        } catch (ParseException e) {
            e.printStackTrace();
        }

       try {
              fachada.cadastrarFuncionario("Funcionario", "Masculino", dataFormatada, "00000000000", "87988285821", "55740000", "02", "Rua Numero Dois", "Centro", "Machados", "00");

        } catch (PessoaJaCadastradaException | CepInvalidoException | SexoIncorretoException | CpfInvalidoException | DadosInvalidosException e) {
            e.printStackTrace();
       }

        launch(args);
    }

}
