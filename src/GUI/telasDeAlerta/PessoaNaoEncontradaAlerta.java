package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class PessoaNaoEncontradaAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Pessoa não foi encontrada.");
        alert.setContentText("Verifique o CPF inserido!");

        alert.showAndWait();
    }
}
