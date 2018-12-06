package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class ImpossivelRemoverMatriculaDeUmMesAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Impossivel remover 1 mes.");
        alert.setContentText("Somente matriculas a partir de 3 meses!");

        alert.showAndWait();
    }
}
