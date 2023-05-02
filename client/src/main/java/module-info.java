module studify.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires okhttp3;
    requires com.google.guice;

    opens studify to javafx.fxml;
    exports studify.controller;
    opens studify.controller to javafx.fxml;
    exports studify;

    // Add the export statement for studify.utils
    exports studify.utils to com.google.guice;
}
