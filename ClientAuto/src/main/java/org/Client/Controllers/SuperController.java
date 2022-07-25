package org.Client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.Client.App;
import org.Client.api.CarRequest;
import org.Client.api.ClientRequest;
import org.Client.api.EmployeeRequest;
import org.Client.api.SaleRequest;

import java.util.Optional;

/**
 * Контроллер, который содержит в себе повторяющиеся элементы других JavaFX контроллеров
 */
public class SuperController {
    public CarRequest connectionCar = new CarRequest();
    public ClientRequest connectionClient = new ClientRequest();
    public SaleRequest connectionSale = new SaleRequest();
    public EmployeeRequest connectionEmp = new EmployeeRequest();
    public App mainApp;


    /**
     * Логика при нажатии на кнопку перехода к окну "Клиенты"
     */
    @FXML
    public void toClients(){
        mainApp.showClients();

    }
    /**
     * Логика при нажатии на кнопку перехода к окну "Авто"
     */
    @FXML
    public void toCars(){
        mainApp.showCars();
    }
    /**
     * Логика при нажатии на кнопку перехода к окну "Работники"
     */
    @FXML
    public void toEmployees(){mainApp.showEmployees();

    }
    /**
     * Логика при нажатии на кнопку перехода к окну "О программе"
     */
    @FXML
    public void toManual(){
        mainApp.showAbout();

    }
    /**
     * Логика при нажатии на кнопку перехода к окну "Продажи"
     */
    @FXML
    public void toSales(){
        mainApp.showSales();

    }

    public void setMainApp(App mainApp){
        this.mainApp = mainApp;
    }

    /**
     * Реализация окна подтверждение пользователем
     * @param message - сообщение для польщователя
     * @return
     */
    public ButtonType confirmWindow(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText("Подтвердите действие");
        alert.setContentText(message);

        Optional<ButtonType> option = alert.showAndWait();

        return option.get();
    }

    /**
     * Реализация окна с ошибкой
     * @param header - заголовок
     * @param content - ссобщение с ошибкой
     */
    public void ErrorWindow(String header, String content) {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.initOwner(mainApp.getPrimaryStage());
        warning.setTitle("ОШИБКА");
        warning.setHeaderText(header);
        warning.setContentText(content);

        warning.showAndWait();
    }

}
