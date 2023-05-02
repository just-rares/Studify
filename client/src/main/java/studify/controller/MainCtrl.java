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
import studify.exceptions.NotImplementedException;
import studify.utils.ServerUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainCtrl implements Initializable {
    ServerUtils serverUtils;
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


    /**
     * This method finds a user in the database and handles the log-in.
     *
     * @param event Mouse click on the sign-in button
     */
    public void signIn(ActionEvent event) {
        try {
            throw new NotImplementedException("This method should search" +
                    " for a user and try to log them in");
        }
        catch (NotImplementedException e) {
            System.out.println(e);
        }
    }

    /**
     * This method takes the username inserted by the user and creates a new
     * user with that name.
     *
     * @param event Mouse Click on the register button.
     */
    public void register(ActionEvent event) {
        String username = rUsername.getText();
        try {
            String response = serverUtils.createNewUser(username);
            System.out.println("User Created: " + response);

            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/studify/client/scenes/Admin.fxml"));
            Parent adminViewParent = loader.load();
            Scene adminViewScene = new Scene(adminViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(adminViewScene);
            window.show();

        } catch (IOException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }
    /**
     * ****************** TEMP ******************
     * This method must use a designated loader to avoid duplicate and
     * useless code. TODO implement loader
     * ****************** TEMP ******************
     *
     * This method loads the admin FXML where you can see all the users.
     *
     * @param event Mouse press on the "Normal View" button
     * @throws IOException in case the fxml is missing, the load() method throws
     * an exception
     */
    public void adminView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/studify/client/scenes/Admin.fxml"));
        Parent adminViewParent = loader.load();
        Scene adminViewScene = new Scene(adminViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminViewScene);
        window.show();
    }

    /**
     * ****************** TEMP ******************
     * All controllers must have the same instance of Server Utils
     * which should be injected. TODO fix injection and singleton
     * ****************** TEMP ******************
     *
     * Initializes the instance of ServerUtils and checks whether the connection is
     * present. In case the database is not present, this creates an empty mv.db file
     *
     * @param url The URL location of the FXML file
     * @param resourceBundle The ResourceBundle containing the resources needed for the FXML file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        serverUtils = new ServerUtils();
        System.out.println(serverUtils.connectionCheck() ? "Connection Successful"
                    : "Connection NOT Successful");
    }
}