module com.example.navalbattle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.navalbattle to javafx.fxml;
    opens com.example.navalbattle.controller to javafx.fxml;
    exports com.example.navalbattle;
    exports com.example.navalbattle.view;


}