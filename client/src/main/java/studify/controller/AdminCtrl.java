package studify.controller;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import studify.utils.ServerUtils;

import java.io.IOException;

public class AdminCtrl {

    public Scene scene;
    @FXML
    Button normalView;

    MainCtrl mainCtrl;
    ServerUtils server;


    public AdminCtrl() {

    }

    @Inject
    public AdminCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }

    public void normalView(ActionEvent event) throws IOException {
        mainCtrl.primaryStage.setScene(this.scene);
    }
}
