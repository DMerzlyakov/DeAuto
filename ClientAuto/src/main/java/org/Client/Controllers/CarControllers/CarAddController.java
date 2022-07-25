package org.Client.Controllers.CarControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.Client.App;

import org.Client.Controllers.SuperController;
import org.Client.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;
/**
 * Контроллер JavaFx для окна "добавление авто"
 */
public class CarAddController extends SuperController {
    @FXML
    private TextField markField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField colourField;

    @FXML
    private TextField engineField;

    @FXML
    private TextField bodyField;
    @FXML
    private TextField vinField;

    @FXML
    private TextField dateOfManufactureField;

    @FXML
    private TextField countryField;

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
     * Логика при нажатии на кнопку "Добавить"
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Map<String, Object> data = new HashMap<>();
            data.put("model", modelField.getText());
            data.put("brand", markField.getText());
            data.put("price", Integer.parseInt(priceField.getText()));
            data.put("available", true);
            data.put("colour", colourField.getText());
            data.put("dateOfManufacture", dateOfManufactureField.getText());
            data.put("locationOfAssembly", countryField.getText());
            data.put("numberOfBody", bodyField.getText());
            data.put("numberOfEngine", engineField.getText());
            data.put("VIN", vinField.getText());


            connectionCar.addCar(data);

            dialogStage.close();
        }
    }
    /**
     * Проверка корректности введенных данных
     */
    private boolean isInputValid() {
        String errorMessage = "";
        if (modelField.getText() == null || modelField.getText().length() == 0) {
            errorMessage += "Не валидна модель\n";
        }
        if (markField.getText() == null || markField.getText().length() == 0) {
            errorMessage += "Не валидна марка\n";
        }
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "Не введена цена\n";
        } else {
            try {
                Integer priceTest = Integer.parseInt(priceField.getText());
                if (priceTest <= 0) {
                    errorMessage += "Не валидна цена\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Цена состоит не из цифр\n";
            }
        }

        if (colourField.getText() == null || colourField.getText().length() == 0) {
            errorMessage += "Не валиден цвет\n";
        }
        if (vinField.getText() == null || vinField.getText().length() == 0) {
            errorMessage += "Не валиден vin\n";
        }

        if (dateOfManufactureField.getText() == null || dateOfManufactureField.getText().length() == 0) {
            errorMessage += "Не введена дата производства\n";
        } else {
            if (!DateUtil.isValid(dateOfManufactureField.getText())) {
                errorMessage += "Введите дату в формате dd.MM.yyyy\n";
            }
        }

        if (engineField.getText() == null || engineField.getText().length() == 0) {
            errorMessage += "Не валиден номер двигателя\n";
        }

        if (bodyField.getText() == null || bodyField.getText().length() == 0) {
            errorMessage += "Не валиден номер кузова\n";
        }

        if (countryField.getText() == null || countryField.getText().length() == 0) {
            errorMessage += "Не валидна страна производства\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            ErrorWindow(errorMessage, "Введите заново");
            return false;
        }
    }

    public void setMain(App mainApp) {
        super.setMainApp(mainApp);
    }

}
