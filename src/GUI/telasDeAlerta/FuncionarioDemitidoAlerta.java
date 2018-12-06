package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class FuncionarioDemitidoAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Concluído!");
        alert.setHeaderText("Funcionário demitido.");
        alert.setContentText(":)");

        alert.showAndWait();
    }
}
