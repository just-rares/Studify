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
import studify.dto.User;
import studify.utils.ServerUtils;

import java.io.IOException;

public class RegisterCtrl {


    public Scene scene;
    ServerUtils serverUtils;
    MainCtrl mainCtrl;
    @FXML
    Button registerButton, signInButton;
    @FXML
    TextField rUsername, rPassword, rLevel, rExperience;
    @FXML
    TextField siUsername,siPassword;

    @Inject
    public RegisterCtrl(ServerUtils serverUtils, MainCtrl mainCtrl) {
        this.serverUtils = serverUtils;
        this.mainCtrl = mainCtrl;
    }
    public void signIn(ActionEvent event) {
        try {
            User user = serverUtils.getUserByUsername(siUsername.getText());
            if(user == null) {
                System.out.println("User not found");
                return;
            }
            System.out.println(user);
        } catch (IOException e) {
            System.out.println("Error in the database");
        }
    }

    public void register() {
        String username = rUsername.getText();
        try {
            String response = serverUtils.createNewUser(username);
            System.out.println(response);
            mainCtrl.primaryStage.setScene(mainCtrl.adminCtrl.scene);
            mainCtrl.primaryStage.show();

        } catch (IOException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public void adminView(ActionEvent event) throws IOException {
        mainCtrl.primaryStage.setScene(mainCtrl.adminCtrl.scene);
        mainCtrl.primaryStage.show();
    }
}