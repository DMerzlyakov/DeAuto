package org.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.Client.Controllers.AuthAndAboutController.AboutController;
import org.Client.Controllers.AuthAndAboutController.LoginController;
import org.Client.Controllers.CarControllers.*;
import org.Client.Controllers.ClientControllers.*;
import org.Client.Controllers.EmployeeControllers.*;
import org.Client.Controllers.SaleControllers.SaleAddController;
import org.Client.Controllers.SaleControllers.SaleController;
import org.Client.Controllers.SaleControllers.SaleEditController;
import org.Client.models.Car;
import org.Client.models.Client;
import org.Client.models.Employee;
import org.Client.models.Sale;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * Запуск приложения
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Address Application");

        initRootLayout();

        showAuthorization();
    }


    /**
     * Показываем основное окно, на котором далее будем всё распологать
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("root.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Окно авторизации
     */
    public void showAuthorization() {

        FXMLLoader loader = loadView("authorization.fxml");

        LoginController controller = loader.getController();
        controller.setMain(this);

    }

    /**
     * Загружаем необходиую модель
     *
     * @param path - путь к моделе
     * @return loader модели
     */
    private FXMLLoader loadView(String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(path));
            AnchorPane bufOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(bufOverview);
            return loader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Загружаем необходимое окно
     *
     * @param path  - путь к расположению
     * @param title - Название
     */
    private Object[] loadDialog(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            return new Object[]{loader, dialogStage};
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Object[0];
    }

    /**
     * Окно информации о программе
     */
    public void showAbout() {
        FXMLLoader loader = loadView("aboutCreator.fxml");
        AboutController controller = loader.getController();
        controller.setMain(this);
    }

    /**
     * Окно информации о автомобилях
     */
    public void showCars() {

        FXMLLoader loader = loadView("Car/cars.fxml");
        CarController controller = loader.getController();
        controller.setMain(this);

    }

    /**
     * Окно добавления авто
     */
    public void showCarAddDialog() {
        Object[] buf = loadDialog("Car/addCar.fxml", "Новое авто");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        CarAddController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();


    }

    /**
     * Окно редактирования авто
     */
    public void showCarEditDialog(Car car) {
        Object[] buf = loadDialog("Car/editCar.fxml", "Редактирование авто");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];

        CarEditController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        controller.setCar(car);
        dialogStage.showAndWait();


    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Окно информации о клиентах
     */
    public void showClients() {
        FXMLLoader loader = loadView("Client/clients.fxml");
        ClientController controller = loader.getController();
        controller.setMain(this);


    }

    /**
     * Окно добавления клиента
     */
    public void showClientAddDialog() {
        Object[] buf = loadDialog("Client/addClient.fxml", "Новый Клиент");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];

        ClientAddController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();


    }

    /**
     * Окно редактирования клиента
     */
    public void showClientEditDialog(Client client) {
        Object[] buf = loadDialog("Client/editClient.fxml", "Редактирование данных клиента");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        ClientEditController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        controller.setClient(client);
        dialogStage.showAndWait();

    }

    /**
     * Окно информации о работниках
     */
    public void showEmployees() {

        FXMLLoader loader = loadView("Employee/employees.fxml");
        EmployeeController controller = loader.getController();
        controller.setMain(this);


    }

    /**
     * Окно добавления Работника
     */
    public void showEmployeeAddDialog() {
        Object[] buf = loadDialog("Employee/addEmployee.fxml", "Новый Работник");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        EmployeeAddController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();


    }

    /**
     * Окно редактирования Работника
     */
    public void showEmployeeEditDialog(Employee employee) {
        Object[] buf = loadDialog("Employee/editEmployee.fxml", "Редактирование данных работника");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        EmployeeEditController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        controller.setEmployee(employee);
        dialogStage.showAndWait();

    }

    /**
     * Окно добавления Продажи
     */
    public void showSaleAddDialog() {
        Object[] buf = loadDialog("Sale/addSale.fxml", "Новая Продажа");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        SaleAddController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();


    }

    /**
     * Окно редактирования Продажи
     */
    public void showSaleEditDialog(Sale sale) {
        Object[] buf = loadDialog("Sale/editSale.fxml", "Редактирование данных продажи");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        SaleEditController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        controller.setSale(sale);
        dialogStage.showAndWait();

    }

    /**
     * Окно информации о Продажах
     */
    public void showSales() {
        FXMLLoader loader = loadView("Sale/sales.fxml");
        SaleController controller = loader.getController();
        controller.setMain(this);

    }

    /**
     * Окно выбора Работника
     */
    public Employee showEmployeeListForChoose() {
        Object[] buf = loadDialog("Employee/employeeListForChoose.fxml", "Выбор Работника");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        EmployeeListForChoose controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();

        return controller.getEmployee();

    }

    /**
     * Окно выбора Клиента
     */
    public Client showClientListForChoose() {
        Object[] buf = loadDialog("Client/clientListForChoose.fxml", "Выбор клиента");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        ClientListForChooseController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();

        return controller.getClient();
    }

    /**
     * Окно выбора Автомобиля
     */
    public Car showCarListForChoose() {
        Object[] buf = loadDialog("Car/carListForChoose.fxml", "Выбор автомобиля");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        CarListForChooseController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();

        return controller.getCar();
    }

    /**
     * Окно подробной информации об авто
     */
    public void showCarInfoDialog(Car car) {
        Object[] buf = loadDialog("Car/carInfo.fxml", "Информация об автомобиле");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        CarInfoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCar(car);
        dialogStage.showAndWait();
    }

    /**
     * Окно подробной информации о Клиенте
     */
    public void showClientInfoDialog(Client client) {
        Object[] buf = loadDialog("Client/clientInfo.fxml", "Информация о клиенте");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        ClientInfoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setClient(client);
        dialogStage.showAndWait();
    }

    /**
     * Окно подробной информации о Работнике
     */
    public void showEmployeeInfoDialog(Employee employee) {
        Object[] buf = loadDialog("Employee/employeeInfo.fxml", "Информация о работнике");
        Stage dialogStage = (Stage) buf[1];
        FXMLLoader loader = (FXMLLoader) buf[0];
        EmployeeInfoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEmployee(employee);
        dialogStage.showAndWait();
    }
}