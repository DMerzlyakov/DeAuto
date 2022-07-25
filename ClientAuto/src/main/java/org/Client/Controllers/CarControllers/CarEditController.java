package org.Client.Controllers.CarControllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.Client.App;
import org.Client.Controllers.SuperController;
import org.Client.models.Car;
import org.Client.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;
/**
 * Контроллер JavaFx для окна "Редактирование данных авто"
 */
public class CarEditController extends SuperController {

    @FXML
    private TextField markField;

    @FXML
    private TextField modeField;

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
    @FXML
    private CheckBox availableBox = new CheckBox("");

    private Stage dialogStage;
    private Long id;


    /**
     * Устанавлениваем данные автомобиля в графах окна
     * @param car - Данные авто
     */
    public void setCar(Car car){
        this.id = car.getId();
        dateOfManufactureField.setText(DateUtil.format(car.getDateOfManufacture()));
        markField.setText(car.getBrand());
        priceField.setText(String.valueOf(car.getPrice()));
        modeField.setText(car.getModel());
        colourField.setText(car.getColour());
        vinField.setText(car.getVin());
        bodyField.setText(String.valueOf(car.getNumberBody()));
        engineField.setText(String.valueOf(car.getNumberEngine()));
        colourField.setText(car.getCountryOfManufacture());
        availableBox.setSelected(car.isAvailable());
        countryField.setText(car.getCountryOfManufacture());


    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Логика при нажатии на кнопку "Отмена"
     */
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    /**
     * Логика при нажатии на кнопку "Добавить"
     */
    @FXML
    private void handleOk(){
        if(isInputValid()){
            Map<String, Object> data = new HashMap<>();
            data.put("model", modeField.getText());
            data.put("brand", markField.getText());
            data.put("price", Integer.parseInt(priceField.getText()));
            data.put("available", availableBox.isSelected());
            data.put("colour", colourField.getText());
            data.put("dateOfManufacture", dateOfManufactureField.getText());
            data.put("locationOfAssembly", countryField.getText());
            data.put("numberOfBody", bodyField.getText());
            data.put("numberOfEngine", engineField.getText());
            data.put("vin", vinField.getText());


            connectionCar.editCar(data, id);

            dialogStage.close();
        }
    }
    /**
     * Проверка корректности введенных данных
     */
    private boolean isInputValid(){
        String errorMessage = "";
        if(modeField.getText() == null || modeField.getText().length() == 0){
            errorMessage += "Не валидна модель\n";
        }
        if(markField.getText() == null || markField.getText().length() == 0){
            errorMessage += "Не валидна марка\n";
        }
        if(priceField.getText() == null || priceField.getText().length() == 0){
            errorMessage += "Не введена цена\n";
        } else {
            try{
                Integer priceTest = Integer.parseInt(priceField.getText());
                if (priceTest <= 0){
                    errorMessage += "Не валидна цена\n";
                }
            }catch (NumberFormatException e){
                errorMessage += "Почтовый индекс состоит не из цифр\n";
            }
        }

        if(colourField.getText() == null || colourField.getText().length() == 0){
            errorMessage += "Не валиден цвет\n";
        }
        if(vinField.getText() == null || vinField.getText().length() == 0){
            errorMessage += "Не валиден vin\n";
        }

        if(dateOfManufactureField.getText() == null || dateOfManufactureField.getText().length() == 0){
            errorMessage += "Не введена дата производства\n";
        }else{
            if(!DateUtil.isValid(dateOfManufactureField.getText())){
                errorMessage += "Введите дату в формате dd.MM.yyyy\n";
            }
        }

        if(engineField.getText() == null || engineField.getText().length() == 0){
            errorMessage += "Не валиден номер двигателя\n";
        }

        if(bodyField.getText() == null || bodyField.getText().length() == 0){
            errorMessage += "Не валиден номер кузова\n";
        }

        if(countryField.getText() == null || countryField.getText().length() == 0){
            errorMessage += "Не валидна страна производства\n";
        }

        if(errorMessage.length() == 0){
            return true;
        }else{
            ErrorWindow(errorMessage, "Введите заново");
            return false;
        }
    }

    public void setMain(App mainApp){
        super.setMainApp(mainApp);
    }

}

