module studify.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens studify.client to javafx.fxml;
    exports studify.client;
    exports studify.controller;
    opens studify.controller to javafx.fxml;
}