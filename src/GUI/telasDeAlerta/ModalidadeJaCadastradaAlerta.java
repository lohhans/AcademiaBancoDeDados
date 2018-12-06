package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class ModalidadeJaCadastradaAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Modalidade já cadastrada.");
        alert.setContentText("Já existe uma modalidade com este nome!");

        alert.showAndWait();
    }
}
