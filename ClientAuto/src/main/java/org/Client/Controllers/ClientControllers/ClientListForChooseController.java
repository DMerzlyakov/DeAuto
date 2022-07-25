package org.Client.Controllers.ClientControllers;

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
import org.Client.models.Client;
import org.Client.utils.DateUtil;

/**
 * Контроллер JavaFx для окна "Выбор Клиента из списка"
 */
public class ClientListForChooseController extends SuperController {
    @FXML
    private TextField sortField;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, Long> idColumn;

    @FXML
    private TableColumn<Client, String> firstNameColumn;

    @FXML
    private TableColumn<Client, String> lastNameColumn;

    @FXML
    private TableColumn<Client, String> phoneColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;


    @FXML
    private TableColumn<Client, String> passportSerColumn;

    @FXML
    private TableColumn<Client, String> passportNumColumn;

    @FXML
    private Label dateLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label secondNameLabel;

    @FXML
    private Label passportSerialLabel;

    @FXML
    private Label passportNumberLabel;

    @FXML
    private Label phoneLabel;

    private FilteredList<Client> filteredData;
    private ObservableList<Client> clientData = FXCollections.observableArrayList();

    private Stage dialogStage;

    private Client client;

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
     * Получаем данные об клиентах с сервера и устанавливаем в TableView
     */
    private void setClientData() {
        this.clientData = connectionClient.getClientsInfo();
        clientTable.setItems(clientData);
    }

    /**
     * Инициальзация TableView и реализация фильтрации данных
     */
    @FXML
    private void initialize() {
        setClientData();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passportSerColumn.setCellValueFactory(new PropertyValueFactory<>("serialPassport"));
        passportNumColumn.setCellValueFactory(new PropertyValueFactory<>("numberPassport"));

        showClientDetails(null);
        clientTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showClientDetails(newValue))
        );

        filteredData = new FilteredList<>(clientData, b -> true);
        sortField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if ((client.getFirstName().toLowerCase() + " " + client.getLastName()
                        .toLowerCase()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(client.getId()).contains(lowerCaseFilter)) {
                    return true;
                } else if ((client.getSerialPassport().toLowerCase() + " " + client.getNumberPassport()
                        .toLowerCase()).contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });


            SortedList<Client> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(clientTable.comparatorProperty());

            clientTable.setItems(sortedData);
        });
    }

    /**
     * Устанавлениваем данные клиентах в графах окна
     *
     * @param client - Данные клиента
     */
    public void showClientDetails(Client client) {
        if (client != null) {
            dateLabel.setText(DateUtil.format(client.getDateOfRegistration()));
            firstNameLabel.setText(client.getFirstName());
            lastNameLabel.setText(String.valueOf(client.getLastName()));
            phoneLabel.setText(client.getPhoneNumber());
            emailLabel.setText(client.getEmail());
            birthdayLabel.setText(DateUtil.format(client.getBirthday()));
            idLabel.setText(String.valueOf(client.getId()));
            passportNumberLabel.setText(client.getNumberPassport());
            passportSerialLabel.setText(client.getSerialPassport());
            secondNameLabel.setText(client.getMiddleName());
            ageLabel.setText(client.getAge());
        } else {
            dateLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            secondNameLabel.setText("");
            phoneLabel.setText("");
            emailLabel.setText("");
            birthdayLabel.setText("");
            idLabel.setText("");
            passportNumberLabel.setText("");
            passportSerialLabel.setText("");
            ageLabel.setText("");
        }
    }

    /**
     * Логика при нажатии кнопки "Добавить Клиента"
     */
    @FXML
    public void addClient() {
        mainApp.showClientAddDialog();
        setClientData();
        filteredData = new FilteredList<>(clientData, b -> true);
        sortField.setText("");
    }

    public void setMain(App mainApp) {
        super.setMainApp(mainApp);

    }

    public Client getClient() {
        return client;
    }

    /**
     * Логика при нажатии на кнопку "Выбрать"
     */
    @FXML
    void handleOk() {
        int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            client = clientTable.getItems().get(selectedIndex);
            dialogStage.close();
        } else {
            ErrorWindow("Авто не выбран", "Пожалуйста выберите Авто");
        }

    }
}
