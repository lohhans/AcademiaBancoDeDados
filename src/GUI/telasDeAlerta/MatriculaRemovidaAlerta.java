package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class MatriculaRemovidaAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Concluído!");
        alert.setHeaderText("Matricula removida.");
        alert.setContentText(":)");

        alert.showAndWait();
    }
}