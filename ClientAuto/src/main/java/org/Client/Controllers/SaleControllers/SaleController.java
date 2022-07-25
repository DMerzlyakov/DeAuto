package org.Client.Controllers.SaleControllers;

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
import org.Client.models.Client;
import org.Client.models.Employee;
import org.Client.models.Sale;
import org.Client.utils.DateUtil;

import java.time.LocalDate;
/**
 * Контроллер JavaFx для окна "Информация о Продажах"
 */
public class SaleController extends SuperController {

    private ObservableList<Sale> saleData = FXCollections.observableArrayList();
    private FilteredList<Sale> filteredData;

    @FXML
    private TextField sortField;

    @FXML
    private TableView<Sale> saleTable;

    @FXML
    private TableColumn<Sale, Float> idColumn;

    @FXML
    private TableColumn<Sale, String> clientColumn;

    @FXML
    private TableColumn<Sale, String> carColumn;

    @FXML
    private TableColumn<Sale, String> employeeColumn;

    @FXML
    private TableColumn<Sale, String> priceColumn;

    @FXML
    private TableColumn<Sale, String> statusColumn;

    @FXML
    private TableColumn<Sale, LocalDate> dateColumn;

    @FXML
    private Label priceLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label clientLabel;
    @FXML
    private Label carLabel;
    @FXML
    private Label employeeLabel;

    /**
     * Обработка кнопки "Информация об Автомобиле"
     */
    @FXML
    void CarInfo() {
        int selectedIndex = saleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Car buf = saleTable.getItems().get(selectedIndex).getCar();
            mainApp.showCarInfoDialog(buf);
        } else {
            ErrorWindow("Продажа не выбрана", "Пожалуйста выберите продажу");
        }

    }
    /**
     * Обработка кнопки "Информация о Клиенте"
     */
    @FXML
    void ClientInfo() {
        int selectedIndex = saleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Client buf = saleTable.getItems().get(selectedIndex).getClient();
            mainApp.showClientInfoDialog(buf);
        } else {
            ErrorWindow("Продажа не выбрана", "Пожалуйста выберите продажу");
        }
    }
    /**
     * Обработка кнопки "Информация о Работнике"
     */
    @FXML
    void EmployeeInfo() {
        int selectedIndex = saleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee buf = saleTable.getItems().get(selectedIndex).getEmployee();
            mainApp.showEmployeeInfoDialog(buf);
        } else {
            ErrorWindow("Продажа не выбрана", "Пожалуйста выберите продажу");
        }
    }

    /**
     * Логика при нажатии кнопки "Удалить Продажу"
     */
    @FXML
    private void deleteSale() {
        int selectedIndex = saleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Sale buf = saleTable.getItems().get(selectedIndex);

            if (confirmWindow("") == ButtonType.OK) {
                connectionSale.deleteSale(String.valueOf(buf.getId()));
                setSaleData();
                filteredData = new FilteredList<>(saleData, b -> true);
                sortField.setText("");
            }
        } else {
            ErrorWindow("Продажа не выбрана", "Пожалуйста выберите продажу");
        }
    }
    /**
     * Логика при нажатии кнопки "Редактировать Продажу"
     */
    @FXML
    private void editSale() {
        int selectedIndex = saleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Sale buf = saleTable.getItems().get(selectedIndex);
            mainApp.showSaleEditDialog(buf);
            setSaleData();
            filteredData = new FilteredList<>(saleData, b -> true);
            sortField.setText("");
        } else {
            ErrorWindow("Продажа не выбрана", "Пожалуйста выберите продажу");
        }
    }
    /**
     * Логика при нажатии кнопки "добавить Продажу"
     */
    @FXML
    private void addSale() {
        mainApp.showSaleAddDialog();
        setSaleData();
        filteredData = new FilteredList<>(saleData, b -> true);
        sortField.setText("");

    }
    /**
     * Получаем данные о продажах с сервера и устанавливаем в TableView
     */
    private void setSaleData() {
        this.saleData = connectionSale.getSalesInfo();
        saleTable.setItems(saleData);
    }
    /**
     * Инициальзация TableView и реализация фильтрации данных
     */
    @FXML
    private void initialize() {
        setSaleData();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientInfo"));
        carColumn.setCellValueFactory(new PropertyValueFactory<>("carInfo"));
        employeeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeInfo"));

        showSaleDetails(null);

        saleTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showSaleDetails(newValue))
        );

        filteredData = new FilteredList<>(saleData, b -> true);
        sortField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(sale -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if ((sale.getCarInfo().toLowerCase().contains(lowerCaseFilter))) {
                    return true;
                } else if (String.valueOf(sale.getId()).contains(lowerCaseFilter)) {
                    return true;
                } else if (sale.getClientInfo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (sale.getEmployeeInfo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });


            SortedList<Sale> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(saleTable.comparatorProperty());

            saleTable.setItems(sortedData);
        });
    }

    public void setMain(App mainApp) {
        super.setMainApp(mainApp);
        saleTable.setItems(saleData);

    }
    /**
     * Устанавлениваем данные продажи в графах окна
     *
     * @param sale - Данные продажи
     */
    private void showSaleDetails(Sale sale) {
        if (sale != null) {
            idLabel.setText(String.valueOf(sale.getId()));
            statusLabel.setText(sale.getStatus());
            priceLabel.setText(String.valueOf(sale.getPrice()));
            dateLabel.setText(DateUtil.format(sale.getDateOfSale()));
            clientLabel.setText(sale.getClientInfo());
            carLabel.setText(sale.getCarInfo());
            employeeLabel.setText(sale.getEmployeeInfo());

        } else {
            idLabel.setText("");
            statusLabel.setText("");
            priceLabel.setText("");
            dateLabel.setText("");
            clientLabel.setText("");
            carLabel.setText("");
            employeeLabel.setText("");
        }

    }

}
