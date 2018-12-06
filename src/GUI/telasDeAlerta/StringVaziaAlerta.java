package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class StringVaziaAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Campo vazio.             ");
        alert.setContentText("Preencha todos os dados!");

        alert.showAndWait();
    }

}