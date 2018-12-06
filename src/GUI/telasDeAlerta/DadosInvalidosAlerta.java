package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class DadosInvalidosAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Dados incorretos.                                  ");
        alert.setContentText("Verifique todos os campos e preencha corretamente!");

        alert.showAndWait();
    }
}
