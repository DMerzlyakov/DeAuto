package org.Client.Controllers.CarControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.Client.Controllers.SuperController;
import org.Client.models.Car;
import org.Client.utils.DateUtil;
/**
 * Контроллер JavaFx для окна "Вся информация об авто"
 */
public class CarInfoController extends SuperController {

    @FXML
    private Label idLabel;

    @FXML
    private Label markLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label colourLabel;

    @FXML
    private Label vinLabel;

    @FXML
    private Label engineLabel;

    @FXML
    private Label bodyLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label countryLabel;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Устанавлениваем данные автомобиля в графах окна
     * @param car - Данные авто
     */
    public void setCar(Car car){

        idLabel.setText(String.valueOf(car.getId()));
        dateLabel.setText(DateUtil.format(car.getDateOfManufacture()));
        markLabel.setText(car.getBrand());
        priceLabel.setText(String.valueOf(car.getPrice()));
        modelLabel.setText(car.getModel());
        colourLabel.setText(car.getColour());
        vinLabel.setText(car.getVin());
        bodyLabel.setText(String.valueOf(car.getNumberBody()));
        engineLabel.setText(String.valueOf(car.getNumberEngine()));
        countryLabel.setText(car.getCountryOfManufacture());
    }
    /**
     * Логика при нажатии на кнопку "Отмена"
     */
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

}
