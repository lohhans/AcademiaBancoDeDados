package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class MensalidadesDosCLientesPagasAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Mensalidades dos clientes totalmente pagas.");
        alert.setContentText("Não existem clientes com as mensalidades pendentes!");

        alert.showAndWait();
    }

}

