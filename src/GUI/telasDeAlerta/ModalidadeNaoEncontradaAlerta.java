package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class ModalidadeNaoEncontradaAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Modalidade não foi encontrada.");
        alert.setContentText("Verifique o código inserido!");

        alert.showAndWait();
    }
}
