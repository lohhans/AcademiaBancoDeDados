package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class SenhaIncorretaAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Senha incorreta.");
        alert.setContentText("Verifique sua senha e tente novamente!");

        alert.showAndWait();
    }
}
