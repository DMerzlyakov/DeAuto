package org.Client.Controllers.ClientControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.Client.Controllers.SuperController;
import org.Client.models.Client;
import org.Client.utils.DateUtil;
/**
 * Контроллер JavaFx для окна "Вся информация о Клиенте"
 */
public class ClientInfoController extends SuperController {
    @FXML
    private Label firstNameLabel;

    @FXML
    private Label middleNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label serialLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label ageLabel;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Логика при нажатии на кнопку "Отмена"
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Устанавлениваем данные клиентах в графах окна
     *
     * @param client - Данные клиента
     */
    public void setClient(Client client) {
        idLabel.setText(String.valueOf(client.getId()));
        birthdayLabel.setText(DateUtil.format(client.getBirthday()));
        firstNameLabel.setText(client.getFirstName());
        lastNameLabel.setText(client.getLastName());
        middleNameLabel.setText(client.getMiddleName());
        phoneLabel.setText(client.getPhoneNumber());
        emailLabel.setText(client.getEmail());
        serialLabel.setText(client.getSerialPassport());
        numberLabel.setText(client.getNumberPassport());
        ageLabel.setText(client.getAge());
    }
}
