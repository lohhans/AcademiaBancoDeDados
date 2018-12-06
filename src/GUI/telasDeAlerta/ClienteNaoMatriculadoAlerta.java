package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class ClienteNaoMatriculadoAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Cliente não matriculado.");
        alert.setContentText("Realize uma antes de remover!");

        alert.showAndWait();
    }
}
