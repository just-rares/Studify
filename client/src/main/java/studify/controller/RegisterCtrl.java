package studify.controller;


import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import studify.dto.User;
import studify.services.SubscriptionService;
import studify.utils.Initializable;
import studify.utils.ServerUtils;

import java.io.IOException;

public class RegisterCtrl implements Initializable {


    public Scene scene;
    ServerUtils serverUtils;
    SubscriptionService subscriptionService;
    MainCtrl mainCtrl;
    @FXML
    Button registerButton, signInButton;
    @FXML
    TextField rUsername, rPassword, rLevel, rExperience;
    @FXML
    TextField siUsername,siPassword;

    @Override
    public void initialize() {

    }

    @Inject
    public RegisterCtrl(ServerUtils serverUtils, MainCtrl mainCtrl, SubscriptionService subscriptionService) {
        this.serverUtils = serverUtils;
        this.mainCtrl = mainCtrl;
        this.subscriptionService = subscriptionService;
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
            mainCtrl.adminCtrl.initialize();
        } catch (IOException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public void adminView(){
        mainCtrl.primaryStage.setScene(mainCtrl.adminCtrl.scene);
        mainCtrl.primaryStage.show();
        mainCtrl.adminCtrl.initialize();
    }



}