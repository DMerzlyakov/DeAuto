package org.Client.Controllers.EmployeeControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.Client.App;
import org.Client.Controllers.SuperController;
import org.Client.models.Employee;

/**
 * Контроллер JavaFx для окна "Информация о Работниках"
 */
public class EmployeeController extends SuperController {
    @FXML
    private TextField sortField;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<String, Long> idColumn;

    @FXML
    private TableColumn<String, String> firstNameColumn;

    @FXML
    private TableColumn<String, String> lastNameColumn;

    @FXML
    private TableColumn<String, String> phoneColumn;

    @FXML
    private TableColumn<String, String> vacancyColumn;

    @FXML
    private TableColumn<String, String> passportNumColumn;
    @FXML
    private TableColumn<String, String> passportSerColumn;

    @FXML
    private Label dateLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label vacancyLabel;

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

    @FXML
    private Label birthdayLabel;

    private FilteredList<Employee> filteredData;
    private ObservableList<Employee> employeeData = FXCollections.observableArrayList();

    /**
     * Логика при нажатии кнопки "Удалить Работника"
     */
    @FXML
    private void deleteEmp() {
        int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee buf = employeeTable.getItems().get(selectedIndex);

            if (confirmWindow("Данное действие повлечет за собой удаление всех продаж работника") == ButtonType.OK) {
                connectionEmp.deleteEmployee(String.valueOf(buf.getId()));
                setEmployeeData();
                filteredData = new FilteredList<>(employeeData, b -> true);
                sortField.setText("");
            }
        } else {
            ErrorWindow("Клиент не выбран", "Пожалуйста выберите клиента");
        }

    }

    /**
     * Логика при нажатии кнопки "Редактировать Работника"
     */
    @FXML
    private void editEmp() {

        int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee buf = employeeTable.getItems().get(selectedIndex);
            mainApp.showEmployeeEditDialog(buf);
            setEmployeeData();
            filteredData = new FilteredList<>(employeeData, b -> true);
            sortField.setText("");
        } else {
            ErrorWindow("Клиент не выбран", "Пожалуйста выберите клиента");
        }

    }

    /**
     * Логика при нажатии кнопки "Добавить Работника"
     */
    @FXML
    private void addEmp() {
        mainApp.showEmployeeAddDialog();
        setEmployeeData();
        filteredData = new FilteredList<>(employeeData, b -> true);
        sortField.setText("");

    }

    /**
     * Получаем данные о сотрудниках с сервера и устанавливаем в TableView
     */
    private void setEmployeeData() {
        this.employeeData = connectionEmp.getEmployeesInfo();
        employeeTable.setItems(employeeData);
    }

    /**
     * Инициальзация TableView и реализация фильтрации данных
     */
    @FXML
    private void initialize() {
        setEmployeeData();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        vacancyColumn.setCellValueFactory(new PropertyValueFactory<>("vacancy"));
        passportSerColumn.setCellValueFactory(new PropertyValueFactory<>("serialPassport"));
        passportNumColumn.setCellValueFactory(new PropertyValueFactory<>("numberPassport"));

        showEmployeeDetails(null);
        employeeTable.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> showEmployeeDetails(newValue))
        );

        filteredData = new FilteredList<>(employeeData, b -> true);
        sortField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if ((employee.getFirstName().toLowerCase() + " " + employee.getLastName()
                        .toLowerCase()).contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(employee.getId()).contains(lowerCaseFilter)) {
                    return true;
                } else if ((employee.getSerialPassport().toLowerCase() + " " + employee.getNumberPassport()
                        .toLowerCase()).contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });


            SortedList<Employee> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(employeeTable.comparatorProperty());

            employeeTable.setItems(sortedData);
        });
    }

    /**
     * Устанавлениваем данные работника в графах окна
     *
     * @param employee - Данные Работника
     */
    public void showEmployeeDetails(Employee employee) {
        if (employee != null) {
            dateLabel.setText(employee.getDateOfRegistration());
            firstNameLabel.setText(employee.getFirstName());
            lastNameLabel.setText(String.valueOf(employee.getLastName()));
            phoneLabel.setText(employee.getPhoneNumber());
            emailLabel.setText(employee.getEmail());
            birthdayLabel.setText(employee.getBirthday());
            idLabel.setText(String.valueOf(employee.getId()));
            passportNumberLabel.setText(employee.getNumberPassport());
            passportSerialLabel.setText(employee.getSerialPassport());
            secondNameLabel.setText(employee.getMiddleName());
            ageLabel.setText(employee.getAge());
            vacancyLabel.setText(employee.getVacancy());

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
            vacancyLabel.setText("");
        }
    }


    public void setMain(App mainApp) {
        super.setMainApp(mainApp);

    }

}
