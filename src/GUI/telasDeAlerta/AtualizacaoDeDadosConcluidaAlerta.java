package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class AtualizacaoDeDadosConcluidaAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Concluída!");
        alert.setHeaderText("Atualização de dados realizada.");
        alert.setContentText(":)");
        alert.showAndWait();
    }
}
