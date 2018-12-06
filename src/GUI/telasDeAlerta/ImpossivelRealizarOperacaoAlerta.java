package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class ImpossivelRealizarOperacaoAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Impossivel realizar operação.                                                   ");
        alert.setContentText("Verifique se você esta usando o campo para Cliente ou Funcionario corretamente!");
        alert.showAndWait();
    }
}

