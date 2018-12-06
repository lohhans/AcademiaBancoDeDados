package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class SexoIncorretoAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Sexo incorreto.");
        alert.setContentText("Preencha com masculino ou feminino!");

        alert.showAndWait();
    }

}
