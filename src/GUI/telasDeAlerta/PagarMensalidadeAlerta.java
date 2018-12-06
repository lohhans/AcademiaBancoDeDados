package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class PagarMensalidadeAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Uma mensalidade foi paga.");
        alert.setContentText(":)");

        alert.showAndWait();
    }
}
