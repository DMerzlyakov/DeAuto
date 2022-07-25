package org.Client.Controllers.AuthAndAboutController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.Client.App;
import org.Client.Controllers.SuperController;
import org.Client.utils.MD5Util;

import java.io.IOException;

/**
 * Контроллер JavaFx для окна
 */
public class LoginController extends SuperController {

    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;

    private App mainApp;

    public void setMain(App mainApp){
        this.mainApp = mainApp;
    }

    /**
     * Обработка нажатия на кнопку "Авторизация"
     */
    @FXML
    public void authButtonClicked(){
        String login = loginField.getText();
        String password = MD5Util.md5(passwordField.getText());


        String validation = connectionEmp.checkAuth(login, password);
        if (validation.equals("valid")){
            mainApp.showAbout();
        }
        else{
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.initOwner(mainApp.getPrimaryStage());
            warning.setTitle("Ошибка входа");
            warning.setHeaderText("Неверный логин/пароль");
            warning.setContentText("Введите заново");

            warning.showAndWait();

        }

    }
}
