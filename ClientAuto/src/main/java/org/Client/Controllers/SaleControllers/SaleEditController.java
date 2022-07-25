package org.Client.Controllers.SaleControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.Client.App;
import org.Client.Controllers.SuperController;
import org.Client.models.Sale;
import org.Client.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Контроллер JavaFx для окна "Редактирование данных Продажи"
 */
public class SaleEditController extends SuperController {

    private Stage dialogStage;
    private Long id;
    private Long carId;
    private Long clientId;
    private Long employeeId;

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
     * Устанавлениваем данные продажи в графах окна
     *
     * @param sale - Данные продажи
     */
    public void setSale(Sale sale) {
        this.id = sale.getId();
        this.carId = sale.getCar().getId();
        this.clientId = sale.getClient().getId();
        this.employeeId = sale.getEmployee().getId();
        carLabel.setText(sale.getCarInfo());
        clientLabel.setText(sale.getClientInfo());
        employeeLabel.setText(sale.getEmployeeInfo());
        statusField.setText(sale.getStatus());
        priceField.setText(String.valueOf(sale.getPrice()));
        paymentTypeField.setText(sale.getMethodOfPayment());

    }

    /**
     * Логика при нажатии на кнопку "Обновить"
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            Map<String, Object> data = new HashMap<>();
            data.put("methodOfPayment", paymentTypeField.getText());
            data.put("status", statusField.getText());
            data.put("price", priceField.getText());
            data.put("client_id", clientId);
            data.put("car_id", carId);
            data.put("employee_id", employeeId);
            data.put("dateOfSale", DateUtil.getNowDateAsString());


            connectionSale.editSale(data, id);

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
                Integer priceTest = Integer.parseInt(priceField.getText());
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

        if (errorMessage.length() == 0) {
            return true;
        } else {
            ErrorWindow(errorMessage, "Введите заново");
            return false;
        }

    }


}
