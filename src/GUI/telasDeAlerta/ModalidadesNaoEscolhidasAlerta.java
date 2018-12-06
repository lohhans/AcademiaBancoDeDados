package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class ModalidadesNaoEscolhidasAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Sem modalidades escolhidas.");
        alert.setContentText("Escolha e tente novamente!");

        alert.showAndWait();
    }
}
