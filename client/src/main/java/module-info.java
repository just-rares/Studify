module studify.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires okhttp3;


    opens studify to javafx.fxml;
    exports studify.controller;
    opens studify.controller to javafx.fxml;
    exports studify;
}