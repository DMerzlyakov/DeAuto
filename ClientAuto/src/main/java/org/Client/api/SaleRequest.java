package org.Client.api;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.Client.models.Sale;
import org.Client.utils.DateUtil;

import java.net.http.HttpResponse;
import java.util.Map;

/**
 * Класс, отвечающий за отправку запросов для получения данных о Продажах
 */
public class SaleRequest extends SuperRequest {
    /**
     * Получение данных о всех продажах с сервера
     *
     * @return ObservableList Продаж
     */
    public ObservableList<Sale> getSalesInfo() {
        ObservableList<Sale> saleList = FXCollections.observableArrayList();

        HttpResponse<String> response = getRequest("http://127.0.0.1:8080/api/sales?projection=fullInfo");
        if (response != null) {
            JsonObject jsonResult = JsonParser.parseString(response.body()).getAsJsonObject();

            JsonObject jsonResult_1 = jsonResult.get("_embedded").getAsJsonObject();

            JsonArray bufList = jsonResult_1.get("sales").getAsJsonArray();

            for (int i = 0; i < bufList.size(); i++) {
                JsonObject jsonSale = bufList.get(i).getAsJsonObject();
                saleList.add(JsonToSaleModel(jsonSale));
            }
        }
        return saleList;
    }

    /**
     * Преобразуем Json в Sale
     *
     * @param jsonSale - Json полученный от сервера с данными о Продаже
     * @return
     */
    private Sale JsonToSaleModel(JsonObject jsonSale) {
        Sale sale = new Sale();
        sale.setId(Long.parseLong(jsonSale.get("id").getAsString()));
        sale.setPrice(jsonSale.get("price").getAsInt());
        sale.setStatus(jsonSale.get("status").getAsString());
        sale.setMethodOfPayment(jsonSale.get("methodOfPayment").getAsString());
        sale.setDateOfSale(DateUtil.parse(jsonSale.get("dateOfSale").getAsString()));

        sale.setClient(JsonToClientModel(jsonSale.get("client").getAsJsonObject()));
        sale.setCar(JsonToCarModel(jsonSale.get("car").getAsJsonObject()));
        sale.setEmployee(JsonToEmpModel(jsonSale.get("employee").getAsJsonObject()));
        return sale;
    }

    /**
     * Добаляем продажу в СУБД
     *
     * @param data - данные продажи
     */
    public void addSale(Map<String, ?> data) {

        HttpResponse<String> response = postRequest("http://127.0.0.1:8080/sale/add", data);

    }

    /**
     * Рекдактируем продажу в СУБД
     *
     * @param data - данные продажи
     * @param id   - id продажи
     */
    public void editSale(Map<String, ?> data, Long id) {

        HttpResponse<String> response = putRequest("http://127.0.0.1:8080/api/employees/" + id, data);

    }

    /**
     * Удаляем продажу в СУБД
     *
     * @param id - id продажи
     */
    public void deleteSale(String id) {

        HttpResponse<String> response = deleteRequest("http://127.0.0.1:8080/api/sales/" + id);

    }
}
