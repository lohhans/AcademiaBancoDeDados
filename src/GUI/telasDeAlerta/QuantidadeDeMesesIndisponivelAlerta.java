package GUI.telasDeAlerta;

import javafx.scene.control.Alert;

public class QuantidadeDeMesesIndisponivelAlerta {

    public void alerta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Quantidade de meses indisponivel.");
        alert.setContentText("Ofertamos somente planos de 1, 3, 6 ou 12 meses");

        alert.showAndWait();
    }
}
