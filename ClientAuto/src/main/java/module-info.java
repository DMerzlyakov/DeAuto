module org.Client {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;
    requires commons.codec;

    opens org.Client to javafx.fxml;
    opens org.Client.Controllers to javafx.fxml;
    opens org.Client.Controllers.AuthAndAboutController to javafx.fxml;
    opens org.Client.Controllers.CarControllers to javafx.fxml;
    opens org.Client.Controllers.EmployeeControllers to javafx.fxml;
    opens org.Client.Controllers.ClientControllers to javafx.fxml;
    opens org.Client.Controllers.SaleControllers to javafx.fxml;


    exports org.Client;
    exports org.Client.Controllers;
    exports org.Client.Controllers.AuthAndAboutController;
    exports org.Client.Controllers.CarControllers;
    exports org.Client.Controllers.ClientControllers;
    exports org.Client.Controllers.EmployeeControllers;
    exports org.Client.Controllers.SaleControllers;

    opens org.Client.models to javafx.base;
}