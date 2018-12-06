package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class AvaliacaoNaoEncontradaAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Avaliação não foi encontrada.");
        alert.setContentText("Verifique o código inserido!");

        alert.showAndWait();
    }
}
