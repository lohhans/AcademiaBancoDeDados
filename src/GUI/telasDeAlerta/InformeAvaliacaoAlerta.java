package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class InformeAvaliacaoAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Informe o código da avaliação física.           ");
        alert.setContentText("Pergunte ao aluno quantas foram feitas e qual ele quer!");
        alert.showAndWait();
    }
}