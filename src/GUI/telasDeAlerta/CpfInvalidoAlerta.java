package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class CpfInvalidoAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("CPF incorreto.");
        alert.setContentText("Verifique o CPF e insira corretamente!");

        alert.showAndWait();
    }
}
