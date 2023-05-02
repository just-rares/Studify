package studify.controller;


import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studify.utils.ServerUtils;

import java.io.IOException;

public class RegisterCtrl {


    public Scene scene;
    ServerUtils server;
    MainCtrl mainCtrl;
    @FXML
    Button registerButton, signInButton;
    @FXML
    TextField rUsername, rPassword, rLevel, rExperience;
    @FXML
    TextField siUsername,siPassword;


    public RegisterCtrl() {

    }

    @Inject
    public RegisterCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.server = server;
        this.mainCtrl = mainCtrl;
    }
    public void signIn(ActionEvent event) {

    }

    public void register(ActionEvent event) {
        String username = rUsername.getText();


        try {
            String response = server.registerUser(username);
            System.out.println("User Created: " + response);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studify/client/scenes/Admin.fxml"));
            Parent adminViewParent = loader.load();
            Scene adminViewScene = new Scene(adminViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(adminViewScene);
            window.show();

        } catch (IOException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public void adminView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/studify/client/scenes/Admin.fxml"));
        Parent adminViewParent = loader.load();
        Scene adminViewScene = new Scene(adminViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminViewScene);
        window.show();
    }
}