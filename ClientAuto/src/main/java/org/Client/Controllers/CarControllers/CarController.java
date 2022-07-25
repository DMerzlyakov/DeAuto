package org.Client.Controllers.CarControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.Client.App;
import org.Client.Controllers.SuperController;
import org.Client.models.Car;
import org.Client.utils.DateUtil;
/**
 * Контроллер JavaFx для окна "Информация об Авто"
 */
public class CarController extends SuperController {

    private ObservableList<Car> carData = FXCollections.observableArrayList();

    @FXML
    private Label dateLabel;
    @FXML
    private Label markLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label modelLabel;

    @FXML
    private Label colourLabel;

    @FXML
    private Label idLabel;
    @FXML
    private Label vinLabel;
    @FXML
    private Label numb2Label;

    @FXML
    private Label availableLabel;
    @FXML
    private Label numbLabel;
    @FXML
    private Label countryLabel;

    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<Car, String> idColumn;
    @FXML
    private TableColumn<Car, String> markColumn;
    @FXML
    private TableColumn<Car, String> modelColumn;
    @FXML
    private TableColumn<Car, String> priceColumn;
    @FXML
    private TableColumn<Car, String> colourColumn;
    @FXML
    private TableColumn<Car, String> dateColumn;
    @FXML
    private TableColumn<Car, Boolean> availableColumn;


    @FXML
    private RadioButton availableSort;

    @FXML
    private TextField sortField;

    private FilteredList<Car> filteredData;


    public CarController() {
    }

    /**
     * Получаем данные об авто с сервера и устанавливаем в TableView
     */
    public void setCarData() {
        this.carData = connectionCar.getAllCarsInfo();
        carTable.setItems(carData);
    }


    /**
     * Инициальзация TableView и реализация фильтрации данных
     */
    @FXML
    private void initialize() {
        setCarData();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        markColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        colourColumn.setCellValueFactory(new PropertyValueFactory<>("colour"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfManufacture"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        showCarDetails(null);
        carTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showCarDetails(newValue))
        );

        filteredData = new FilteredList<>(carData, b -> true);
        sortField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(car -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if ((car.getBrand().toLowerCase() + " " + car.getModel().toLowerCase()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(car.getId()).contains(lowerCaseFilter))
                    return true;
                else
                    return false;
            });


            SortedList<Car> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(carTable.comparatorProperty());

            carTable.setItems(sortedData);
        });
    }

    /**
     * Логика при нажатии кнопки "Добавить Авто"
     */
    @FXML
    public void addCar() {
        mainApp.showCarAddDialog();
        setCarData();
        filteredData = new FilteredList<>(carData, b -> true);
        sortField.setText("");
    }

    /**
     * Сортировка по автомобилям в наличии
     */
    @FXML
    public void SortByAvailable() {
        if (availableSort.isSelected()) {
            this.carData = connectionCar.getAvailableCarsInfo();
            carTable.setItems(carData);
        } else {
            setCarData();
        }
        filteredData = new FilteredList<>(carData, b -> true);
        sortField.setText("");
    }

    /**
     * логика при нажатии кнопки "Удалить авто"
     */
    @FXML
    public void deleteCar() {

        int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Car buf = carTable.getItems().get(selectedIndex);

            if (confirmWindow("Данное действие повлечёт за собой удаление продажи данного автомобиля") == ButtonType.OK) {
                connectionCar.deleteCar(String.valueOf(buf.getId()));
                setCarData();
                filteredData = new FilteredList<>(carData, b -> true);
                sortField.setText("");
            }
        } else {
            ErrorWindow("Авто не выбран", "Пожалуйста выберите Авто");
        }
    }
    /**
     * логика при нажатии кнопки "Редактировать авто"
     */
    @FXML
    private void editCar() {

        int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Car buf = carTable.getItems().get(selectedIndex);
            mainApp.showCarEditDialog(buf);
            setCarData();
            filteredData = new FilteredList<>(carData, b -> true);
            sortField.setText("");
        } else {
            ErrorWindow("Авто не выбран", "Пожалуйста выберите Авто");
        }
    }
    /**
     * Устанавлениваем данные автомобиля в графах окна
     * @param car - Данные авто
     */
    public void showCarDetails(Car car) {
        if (car != null) {
            dateLabel.setText(DateUtil.format(car.getDateOfManufacture()));
            markLabel.setText(car.getBrand());
            priceLabel.setText(String.valueOf(car.getPrice()));
            modelLabel.setText(car.getModel());
            colourLabel.setText(car.getColour());
            vinLabel.setText(car.getVin());
            idLabel.setText(String.valueOf(car.getId()));
            numb2Label.setText(String.valueOf(car.getNumberBody()));
            availableLabel.setText(car.isAvailable() ? "В наличии" : "Нет в наличии");
            numbLabel.setText(String.valueOf(car.getNumberEngine()));
            countryLabel.setText(car.getCountryOfManufacture());
        } else {
            dateLabel.setText("");
            markLabel.setText("");
            priceLabel.setText("");
            modelLabel.setText("");
            colourLabel.setText("");
            vinLabel.setText("");
            idLabel.setText("");
            numb2Label.setText("");
            availableLabel.setText("");
            numbLabel.setText("");
            countryLabel.setText("");
        }
    }

    public void setMain(App mainApp) {
        super.setMainApp(mainApp);

    }
}
