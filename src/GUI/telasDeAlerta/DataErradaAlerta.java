package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class DataErradaAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Data incorreta.");
        alert.setContentText("Preencha no padrão dd/mm/aaaa!");

        alert.showAndWait();
    }
}
