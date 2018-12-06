package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class MensalidadesPagasAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Todas as mensalidades já foram quitadas.");
        alert.setContentText("Não há o que remover!");

        alert.showAndWait();
    }
}
