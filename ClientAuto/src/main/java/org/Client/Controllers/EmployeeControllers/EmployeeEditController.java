package org.Client.Controllers.EmployeeControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.Client.App;
import org.Client.Controllers.SuperController;
import org.Client.models.Employee;
import org.Client.utils.DateUtil;
import org.Client.utils.MD5Util;

import java.util.HashMap;
import java.util.Map;
/**
 * Контроллер JavaFx для окна "Редактирование данных Работника"
 */
public class EmployeeEditController extends SuperController {

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

    @FXML
    private TextField vacancyField;

    @FXML
    private TextField passwordField;


    private Stage dialogStage;
    private Long id;
    private String password;

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
        super.setMainApp(mainApp);
    }

    /**
     * Логика при нажатии на кнопку "Обновить"
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
            data.put("vacancy", vacancyField.getText());
            if (passwordField.getText() != ""){
                data.put("password", MD5Util.md5(passwordField.getText()));
            } else{
                data.put("password", password);
            }

            connectionEmp.editEmployee(data, id);

            dialogStage.close();
        }
    }
    /**
     * Устанавлениваем данные работника в графах окна
     *
     * @param employee - Данные Работника
     */
    public void setEmployee(Employee employee) {
        this.id = employee.getId();
        birthdayField.setText(employee.getBirthday());
        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        middleNameField.setText(employee.getMiddleName());
        phoneField.setText(employee.getPhoneNumber());
        emailField.setText(employee.getEmail());
        serialField.setText(employee.getSerialPassport());
        numberField.setText(employee.getNumberPassport());
        vacancyField.setText(employee.getVacancy());
        passwordField.setPromptText("Если хотите изменить, введите новый пароль");
        password = employee.getPassword();

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

        if (vacancyField.getText() == null || vacancyField.getText().length() == 0) {
            errorMessage += "Не валидна должность\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            ErrorWindow(errorMessage, "Введите заново");
            return false;
        }
    }
}

