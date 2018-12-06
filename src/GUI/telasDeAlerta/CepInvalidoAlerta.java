package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class CepInvalidoAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("CEP inválido.");
        alert.setContentText("Verifique o CEP e insira corretamente!");

        alert.showAndWait();
    }
}
