package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class ModalidadeRemovidaAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Concluído!");
        alert.setHeaderText("Modalidade removida.");
        alert.setContentText(":)");

        alert.showAndWait();
    }
}
