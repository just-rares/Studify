package studify.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studify.dto.User;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.ServerSocket;

public class MainCtrl {
    Scene primaryScene;
    @FXML
    Button registerButton;
    @FXML
    Button signInButton;
    @FXML
    TextField siUsername;
    @FXML
    TextField siPassword;
    @FXML
    TextField rExperience;
    @FXML
    TextField rLevel;
    @FXML
    TextField rUsername;
    @FXML
    TextField rPassword;

    public void signIn(ActionEvent event) {

    }

    public void register(ActionEvent event) throws IOException {

        String username = rUsername.getText();
        User user = new User(username);

        // what now?

        System.out.println("User Created");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/studify/client/scenes/Admin.fxml"));
        Parent adminViewParent = loader.load();
        Scene adminViewScene = new Scene(adminViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminViewScene);
        window.show();
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