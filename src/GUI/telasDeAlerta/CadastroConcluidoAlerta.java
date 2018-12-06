package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class CadastroConcluidoAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Concluído!");
        alert.setHeaderText("Cadastrado realizado.");
        alert.setContentText(":)");
        alert.showAndWait();
    }
}
