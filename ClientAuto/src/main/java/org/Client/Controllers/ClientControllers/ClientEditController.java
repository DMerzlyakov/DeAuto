package org.Client.Controllers.ClientControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.Client.App;
import org.Client.Controllers.SuperController;
import org.Client.models.Client;
import org.Client.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;
/**
 * Контроллер JavaFx для окна "Редактирование данных клиента"
 */
public class ClientEditController extends SuperController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField middleNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField serialField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Long id;

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

    public void setMain(App mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Логика при нажатии на кнопку "Добавить"
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Map<String, Object> data = new HashMap<>();
            data.put("firstName", firstNameField.getText());
            data.put("lastName", lastNameField.getText());
            data.put("middleName", middleNameField.getText());
            data.put("phoneNumber", phoneField.getText());
            data.put("serialPassport", serialField.getText());
            data.put("numberPassport", numberField.getText());
            data.put("email", emailField.getText());
            data.put("birthday", birthdayField.getText());
            data.put("dateOfRegistration", DateUtil.getNowDateAsString());


            connectionClient.editClient(data, id);

            dialogStage.close();
        }
    }
    /**
     * Устанавлениваем данные клиента в графах окна
     * @param client - Данные клиента
     */
    public void setClient(Client client) {
        this.id = client.getId();
        birthdayField.setText(DateUtil.format(client.getBirthday()));
        firstNameField.setText(client.getFirstName());
        lastNameField.setText(client.getLastName());
        middleNameField.setText(client.getMiddleName());
        phoneField.setText(client.getPhoneNumber());
        emailField.setText(client.getEmail());
        serialField.setText(client.getSerialPassport());
        numberField.setText(client.getNumberPassport());

    }
    /**
     * Проверка корректности введенных данных
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Не валидно имя\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Не валидна Фамилия\n";
        }
        if (middleNameField.getText() == null || middleNameField.getText().length() == 0) {
            errorMessage += "Не введено отчество\n";
        }

        if (phoneField.getText() == null || phoneField.getText().length() == 0) {
            errorMessage += "Не валиден номер телефона\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Не валидена почта\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Не введена дата рождения\n";
        } else {
            if (!DateUtil.isValid(birthdayField.getText())) {
                errorMessage += "Введите дату в формате dd.MM.yyyy\n";
            }
        }

        if (serialField.getText() == null || serialField.getText().length() == 0) {
            errorMessage += "Не валидна серия паспорта\n";
        }

        if (numberField.getText() == null || numberField.getText().length() == 0) {
            errorMessage += "Не валиден номер паспорта\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            ErrorWindow(errorMessage, "Введите заново");
            return false;
        }
    }
}

