package org.Client.Controllers.EmployeeControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.Client.Controllers.SuperController;
import org.Client.models.Employee;

/**
 * Контроллер JavaFx для окна "Вся информация о Работнике"
 */
public class EmployeeInfoController extends SuperController {

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

    @FXML
    private Label vacancyLabel;

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
     * Устанавлениваем данные работника в графах окна
     *
     * @param employee - Данные Работника
     */
    public void setEmployee(Employee employee) {
        idLabel.setText(String.valueOf(employee.getId()));
        birthdayLabel.setText(employee.getBirthday());
        firstNameLabel.setText(employee.getFirstName());
        lastNameLabel.setText(employee.getLastName());
        middleNameLabel.setText(employee.getMiddleName());
        phoneLabel.setText(employee.getPhoneNumber());
        emailLabel.setText(employee.getEmail());
        serialLabel.setText(employee.getSerialPassport());
        numberLabel.setText(employee.getNumberPassport());
        ageLabel.setText(employee.getAge());
        vacancyLabel.setText(employee.getVacancy());
    }
}
