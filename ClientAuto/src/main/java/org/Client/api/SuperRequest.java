package org.Client.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.Client.models.Car;
import org.Client.models.Client;
import org.Client.models.Employee;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


/**
 * Класс реализующий отправку GET, POST, PUT, DELETE запросов и вспомогательных функций
 */
public class SuperRequest {
    private static HttpClient connection = HttpClient.newHttpClient();
    private static Gson gsonObj = new Gson();

    /**
     * GET запрос на сервер
     * @param url - адрес запроса
     * @return
     */
    public static HttpResponse<String>  getRequest(String url){

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try{
            HttpResponse<String> response = connection.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * POST запрос на сервер
     * @param url - адрес запроса
     * @param data - данные запроса
     * @return
     */
    public static HttpResponse<String>  postRequest(String url, Map<String, ?> data){


        String jsonStr = gsonObj.toJson(data);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(jsonStr))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = connection.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * PUT запрос на сервер
     * @param url - адрес запроса
     * @param data - даные запроса
     * @return
     */
    public static HttpResponse<String> putRequest(String url, Map<String, ?> data){

        String jsonStr = gsonObj.toJson(data);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonStr))
                .header("Content-Type", "application/json")
                .build();
        try{
            HttpResponse<String> response = connection.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DELETE запрос на сервер
     * @param url - url для соединения с сервером
     * @return
     */
    public static HttpResponse<String> deleteRequest(String url){

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try{
            HttpResponse<String> response = connection.send(request, HttpResponse.BodyHandlers.ofString());
            return response;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Преобразуем Json в Client
     *
     * @param jsonEmp - Json полученный от сервера с данными о Клиенте
     * @return
     */
    public static Client JsonToClientModel(JsonObject jsonEmp) {
        Client client = new Client();

        client.setFirstName(jsonEmp.get("firstName").getAsString());
        client.setLastName(jsonEmp.get("lastName").getAsString());
        client.setMiddleName(jsonEmp.get("middleName").getAsString());
        client.setPhoneNumber(jsonEmp.get("phoneNumber").getAsString());
        client.setNumberPassport(jsonEmp.get("numberPassport").getAsString());
        client.setSerialPassport(jsonEmp.get("serialPassport").getAsString());
        client.setEmail(jsonEmp.get("email").getAsString());
        client.setBirthday(jsonEmp.get("birthday").getAsString());
        client.setDateOfRegistration(jsonEmp.get("dateOfRegistration").getAsString());
        client.setId(jsonEmp.get("id").getAsString());

        return client;
    }
    /**
     * Преобразуем Json в Employee
     *
     * @param jsonEmp - Json полученный от сервера с данными о Работнике
     * @return
     */
    public static Employee JsonToEmpModel(JsonObject jsonEmp) {
        Employee employee = new Employee();

        employee.setFirstName(jsonEmp.get("firstName").getAsString());
        employee.setLastName(jsonEmp.get("lastName").getAsString());
        employee.setMiddleName(jsonEmp.get("middleName").getAsString());
        employee.setPhoneNumber(jsonEmp.get("phoneNumber").getAsString());
        employee.setVacancy(jsonEmp.get("vacancy").getAsString());
        employee.setNumberPassport(jsonEmp.get("numberPassport").getAsString());
        employee.setSerialPassport(jsonEmp.get("serialPassport").getAsString());
        employee.setEmail(jsonEmp.get("email").getAsString());
        employee.setPassword(jsonEmp.get("password").getAsString());
        employee.setBirthday(jsonEmp.get("birthday").getAsString());
        employee.setDateOfRegistration(jsonEmp.get("dateOfRegistration").getAsString());
        employee.setId(jsonEmp.get("id").getAsString());

        return employee;
    }
    /**
     * Преобразуем Json в Car
     *
     * @param jsonCar - Json полученный от сервера с данными об авто
     * @return
     */
    public static Car JsonToCarModel(JsonObject jsonCar) {
        Car car = new Car();
        car.setBrand(jsonCar.get("brand").getAsString());
        car.setAvailable(jsonCar.get("available").getAsBoolean());
        car.setModel(jsonCar.get("model").getAsString());
        car.setColour(jsonCar.get("colour").getAsString());
        car.setDateOfManufacture(jsonCar.get("dateOfManufacture").getAsString());
        car.setPrice(jsonCar.get("price").getAsInt());
        car.setCountryOfManufacture(jsonCar.get("locationOfAssembly").getAsString());
        car.setNumberBody(jsonCar.get("numberOfBody").getAsString());
        car.setNumberEngine(jsonCar.get("numberOfEngine").getAsString());
        car.setVin(jsonCar.get("vin").getAsString());
        car.setId(jsonCar.get("id").getAsString());

        return car;

    }
}
