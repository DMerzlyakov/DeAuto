package org.Client.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.Client.models.Client;

import java.net.http.HttpResponse;
import java.util.Map;
/**
 * Класс, отвечающий за отправку запросов для получения данных об Клиентах
 */
public class ClientRequest extends SuperRequest {

    /**
     * Получение данных о всех клиентах с сервера
     *
     * @return ObservableList клиентов
     */
    public ObservableList<Client> getClientsInfo() {
        ObservableList<Client> clientList = FXCollections.observableArrayList();

        HttpResponse<String> response = getRequest("http://127.0.0.1:8080/api/clients?projection=clientInfo");
        if (response != null) {
            JsonObject jsonResult = JsonParser.parseString(response.body()).getAsJsonObject();

            JsonObject jsonResult_1 = jsonResult.get("_embedded").getAsJsonObject();

            JsonArray bufList = jsonResult_1.get("clients").getAsJsonArray();

            for (int i = 0; i < bufList.size(); i++) {
                JsonObject jsonClient = bufList.get(i).getAsJsonObject();
                clientList.add(JsonToClientModel(jsonClient));
            }
        }
        return clientList;
    }

    /**
     * Отправляем запрос на добавление Клиента В СУБД
     *
     * @param data - Клиент для добавления в СУБД
     */
    public void addClient(Map<String, ?> data) {

        HttpResponse<String> response = postRequest("http://127.0.0.1:8080/client/add", data);

    }

    /**
     * Отправляем запрос на изменение клиента В СУБД
     *
     * @param data - Клиент для изменения в СУБД
     * @param id   - ID клиента, у которого необходимо изменить данные
     */
    public void editClient(Map<String, ?> data, Long id) {

        HttpResponse<String> response = putRequest("http://127.0.0.1:8080/api/clients/" + id, data);

    }

    /**
     * Отправляем запрос на удаления Клиента из СУБД
     *
     * @param id - ID Клиента, которого необходимо удалить
     */
    public void deleteClient(String id) {

        HttpResponse<String> response = deleteRequest("http://127.0.0.1:8080/api/clients/" + id);

    }
}
