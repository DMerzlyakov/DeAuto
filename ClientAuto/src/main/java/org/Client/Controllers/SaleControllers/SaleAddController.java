package org.Client.Controllers.SaleControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.Client.App;
import org.Client.Controllers.SuperController;
import org.Client.models.Car;
import org.Client.models.Client;
import org.Client.models.Employee;
import org.Client.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер JavaFx для окна "добавление продажи"
 */
public class SaleAddController extends SuperController {
    private Stage dialogStage;
    private App mainApp;

    @FXML
    private TextField priceField;

    @FXML
    private TextField paymentTypeField;

    @FXML
    private TextField statusField;


    @FXML
    private Label carLabel;

    @FXML
    private Label clientLabel;


    @FXML
    private Label employeeLabel;

    private Car car;
    private Client client;
    private Employee employee;

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
     * Логика при нажатии кнокпи "Выбрать авто"
     */
    @FXML
    void chooseCar() {
        car = mainApp.showCarListForChoose();
        if (car != null) {
            carLabel.setText(car.getBrand() + " " + car.getModel());
            priceField.setText(String.valueOf(car.getPrice()));
        } else {
            carLabel.setText("");
        }
    }

    /**
     * Логика при нажатии кнокпи "Выбрать клиента"
     */
    @FXML
    void chooseClient() {
        client = mainApp.showClientListForChoose();
        if (client != null) {
            clientLabel.setText(client.getFirstAndLastName().get());
        } else {
            clientLabel.setText("");
        }

    }

    /**
     * Логика при нажатии кнокпи "Выбрать работника"
     */
    @FXML
    void chooseEmployee() {
        employee = mainApp.showEmployeeListForChoose();
        if (employee != null) {
            employeeLabel.setText(employee.getEmployeeFirstAndSecondName().get());
        } else {
            employeeLabel.setText("");
        }

    }

    /**
     * Логика при нажатии на кнопку "Добавить"
     */
    @FXML
    void handleOk() {
        if (isInputValid()) {
            Map<String, Object> data = new HashMap<>();
            data.put("methodOfPayment", paymentTypeField.getText());
            data.put("status", statusField.getText());
            data.put("price", priceField.getText());
            data.put("client_id", client.getId());
            data.put("car_id", car.getId());
            data.put("employee_id", employee.getId());
            data.put("dateOfSale", DateUtil.getNowDateAsString());

            connectionSale.addSale(data);

            Map<String, Object> carUpdate = new HashMap<>();

            carUpdate.put("model", car.getModel());
            carUpdate.put("brand", car.getBrand());
            carUpdate.put("price", car.getPrice());
            carUpdate.put("available", false);
            carUpdate.put("colour", car.getColour());
            carUpdate.put("dateOfManufacture", DateUtil.format(car.getDateOfManufacture()));
            carUpdate.put("locationOfAssembly", car.getCountryOfManufacture());
            carUpdate.put("numberOfBody", car.getNumberBody());
            carUpdate.put("numberOfEngine", car.getNumberEngine());
            carUpdate.put("vin", car.getVin());

            connectionCar.editCar(carUpdate, car.getId());

            dialogStage.close();

        }

    }

    /**
     * Проверка корректности введенных данных
     */
    private Boolean isInputValid() {
        String errorMessage = "";
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "Не введена цена\n";
        } else {
            try {
                int priceTest = Integer.parseInt(priceField.getText());
                if (priceTest <= 0) {
                    errorMessage += "Не валидна цена\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Цена состоит не из цифр\n";
            }
        }
        if (statusField.getText() == null || statusField.getText().length() == 0) {
            errorMessage += "Не валиден статус\n";
        }
        if (paymentTypeField.getText() == null || paymentTypeField.getText().length() == 0) {
            errorMessage += "Не введён метод оплаты\n";
        }

        if (clientLabel.getText() == null || clientLabel.getText().length() == 0) {
            errorMessage += "Клиент не выбран\n";
        }
        if (carLabel.getText() == null || carLabel.getText().length() == 0) {
            errorMessage += "Авто не выбрано\n";
        }

        if (employeeLabel.getText() == null || employeeLabel.getText().length() == 0) {
            errorMessage += "Работник не выбран\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            ErrorWindow(errorMessage, "Введите заново");
            return false;
        }
    }
}