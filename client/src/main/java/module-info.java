module studify.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;


    opens studify to javafx.fxml;
    exports studify.controller;
    opens studify.controller to javafx.fxml;
    exports studify;
    exports studify.dto;
}