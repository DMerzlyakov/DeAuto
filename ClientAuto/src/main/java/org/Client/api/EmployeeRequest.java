package org.Client.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.Client.models.Employee;


import java.net.http.HttpResponse;
import java.util.Map;

/**
 * Класс, отвечающий за отправку запросов для получения данных об Работниках
 */
public class EmployeeRequest extends SuperRequest {

    /**
     * Проверка возможности входа
     *
     * @param login    - логин для проверки
     * @param password - Пароль в MD5 для проверки
     * @return Boolean, отвечающий за нахождения пары ключ значений на сервере.
     */
    public String checkAuth(String login, String password) {
        HttpResponse response = getRequest("http://127.0.0.1:8080/auth?login=" + login + "&password=" + password);

        return (String) response.body();
    }

    /**
     * Получение данных о всех клиентах с сервера
     *
     * @return ObservableList клиентов
     */
    public ObservableList<Employee> getEmployeesInfo() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        HttpResponse<String> response = getRequest("http://127.0.0.1:8080/api/employees?projection=employeeInfo");
        if (response != null) {
            JsonObject jsonResult = JsonParser.parseString(response.body()).getAsJsonObject();

            JsonObject jsonResult_1 = jsonResult.get("_embedded").getAsJsonObject();

            JsonArray bufList = jsonResult_1.get("employees").getAsJsonArray();

            for (int i = 0; i < bufList.size(); i++) {
                JsonObject jsonEmp = bufList.get(i).getAsJsonObject();
                employeeList.add(JsonToEmpModel(jsonEmp));
            }
        }
        return employeeList;
    }


    /**
     * Отправляем запрос на добавление Клиента В СУБД
     *
     * @param data - Клиент для добавления в СУБД
     */
    public void addEmployee(Map<String, ?> data) {

        HttpResponse<String> response = postRequest("http://127.0.0.1:8080/employee/add", data);

    }

    /**
     * Отправляем запрос на изменение клиента В СУБД
     *
     * @param data - Клиент для изменения в СУБД
     * @param id   - ID клиента, у которого необходимо изменить данные
     */
    public void editEmployee(Map<String, ?> data, Long id) {

        HttpResponse<String> response = putRequest("http://127.0.0.1:8080/api/employees/" + id, data);

    }

    /**
     * Отправляем запрос на удаления Клиента из СУБД
     *
     * @param id - ID Клиента, которого необходимо удалить
     */
    public void deleteEmployee(String id) {

        HttpResponse<String> response = deleteRequest("http://127.0.0.1:8080/api/employees/" + id);

    }
}
