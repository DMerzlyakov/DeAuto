package org.Client.Controllers.CarControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.Client.App;
import org.Client.Controllers.SuperController;
import org.Client.models.Car;
import org.Client.utils.DateUtil;

/**
 * Контроллер JavaFx для окна "Выбор авто из списка"
 */
public class CarListForChooseController extends SuperController {

    @FXML
    private TableView<Car> carTable;

    @FXML
    private TableColumn<Car, String> idColumn;

    @FXML
    private TableColumn<?, ?> markColumn;

    @FXML
    private TableColumn<?, ?> modelColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> colourColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

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
    private Label numb2Label;

    @FXML
    private Label numbLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label vinLabel;

    @FXML
    private TextField sortField;

    private FilteredList<Car> filteredData;
    private ObservableList<Car> carData = FXCollections.observableArrayList();
    Car car = null;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private Stage dialogStage;

    /**
     * Получаем данные об авто с сервера и устанавливаем в TableView
     */
    public void setCarData() {
        this.carData = connectionCar.getAvailableCarsInfo();
        carTable.setItems(carData);
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
     * Логика при нажатии на кнопку "Отмена"
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    public Car getCar() {
        return car;
    }

    /**
     * Логика при нажатии на кнопку "Выбрать"
     */
    @FXML
    private void handleOk() {
        int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            car = carTable.getItems().get(selectedIndex);
            dialogStage.close();
        } else {
            ErrorWindow("Авто не выбран", "Пожалуйста выберите Авто");
        }
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

                if ((car.getBrand().toLowerCase() + " " + car.getModel().toLowerCase()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(car.getId()).indexOf(lowerCaseFilter) != -1)
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
     * Устанавлениваем данные автомобиля в графах окна
     *
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
            numbLabel.setText("");
            countryLabel.setText("");
        }
    }

    public void setMain(App mainApp) {
        super.setMainApp(mainApp);
    }
}
