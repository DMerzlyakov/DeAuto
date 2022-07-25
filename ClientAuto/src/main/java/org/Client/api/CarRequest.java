package org.Client.api;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.Client.models.Car;

import java.net.http.HttpResponse;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Класс, отвечающий за отправку запросов для получения данных об автомобилях
 */
public class CarRequest extends SuperRequest {

    /**
     * Получение данных о всех автомобилях с сервера
     *
     * @return ObservableList автомобилей
     */
    public ObservableList<Car> getAllCarsInfo() {
        HttpResponse<String> response = getRequest("http://127.0.0.1:8080/api/cars?projection=carInfo");
        ObservableList<Car> carList = parseCarsJsonToObservableList(response);
        return carList;
    }

    /**
     * Парсинг ответа от сервера в список автомобилей
     *
     * @param response - ответ от сервера
     * @return ObservableList автомобилей
     */
    public ObservableList<Car> parseCarsJsonToObservableList(HttpResponse<String> response) {
        ObservableList<Car> carList = FXCollections.observableArrayList();
        if (response != null) {
            JsonObject jsonResult = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject jsonResultNew = jsonResult.get("_embedded").getAsJsonObject();

            JsonArray bufList = jsonResultNew.get("cars").getAsJsonArray();

            for (int i = 0; i < bufList.size(); i++) {
                JsonObject jsonCar = bufList.get(i).getAsJsonObject();
                carList.add(JsonToCarModel(jsonCar));
            }


        }
        return carList;
    }

    /**
     * Получение данных о всех автомобилях в наличии с сервера
     *
     * @return ObservableList автомобилей в наличии
     */
    public ObservableList<Car> getAvailableCarsInfo() {
        HttpResponse<String> response = getRequest(
                "http://127.0.0.1:8080/api/cars/search/findAllByAvailable?available=true&projection=carInfo"
        );
        ObservableList<Car> carList = parseCarsJsonToObservableList(response);
        return carList;
    }


    /**
     * Отправляем запрос на добавление автомобиля В СУБД
     *
     * @param data - Автомобиль для добавления в СУБД
     */
    public void addCar(Map<String, ?> data) {

        HttpResponse<String> response = postRequest("http://127.0.0.1:8080/car/add", data);

    }

    /**
     * Отправляем запрос на изменение автомобиля В СУБД
     *
     * @param data - Автомобиль для изменения в СУБД
     * @param id   - ID автомобиля, у которого необходимо изменить данные
     */
    public void editCar(Map<String, ?> data, Long id) {

        HttpResponse<String> response = putRequest("http://127.0.0.1:8080/api/cars/" + id, data);

    }

    /**
     * Отправляем запрос на удаления автомобиля из СУБД
     *
     * @param id - ID автомобиля, которой необходимо удалить
     */
    public void deleteCar(String id) {

        HttpResponse<String> response = deleteRequest("http://127.0.0.1:8080/api/cars/" + id);

    }
}