package studify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import studify.utils.ServerUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainCtrl implements Initializable {
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
        ServerUtils serverUtils = new ServerUtils();
        try {
            serverUtils.getUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(ActionEvent event) {
        String username = rUsername.getText();

        ServerUtils serverUtils = new ServerUtils();

        try {
            String response = serverUtils.registerUser(username);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServerUtils serverUtils = new ServerUtils();
        try {
            serverUtils.connectionCheck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}