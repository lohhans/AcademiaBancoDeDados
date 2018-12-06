package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class ClienteJaMatriculadoAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Cliente já matriculado.");
        alert.setContentText("Remova a matricula do cliente antes de remover!");

        alert.showAndWait();
    }
}
