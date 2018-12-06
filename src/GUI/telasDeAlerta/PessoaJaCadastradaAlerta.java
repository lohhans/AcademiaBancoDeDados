package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class PessoaJaCadastradaAlerta {

    public void alerta(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Pessoa já cadastrada.");
        alert.setContentText("Já existe um cadastro com este CPF!");

        alert.showAndWait();
    }
}
