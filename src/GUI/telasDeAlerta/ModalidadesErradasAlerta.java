package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class ModalidadesErradasAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Informe o código das modalidades como descrito.");
        alert.setContentText("Utilize o padrão ID-ID-ID... (Separados por hífen e sem espaço)!");
        alert.showAndWait();
    }
}

