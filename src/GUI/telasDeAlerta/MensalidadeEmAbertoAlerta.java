package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class MensalidadeEmAbertoAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Mensalidade em aberto.");
        alert.setContentText("Será removida com multa de 3 meses!");

        alert.showAndWait();
    }

}
