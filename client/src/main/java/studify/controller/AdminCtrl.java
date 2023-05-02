package studify.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import studify.dto.User;
import studify.utils.ServerUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCtrl implements Initializable {
    @FXML
    Button normalView;
    @FXML
    ScrollPane scrollPane;


    /**
     * Override for the 'initialize' method in the Initializable interface
     * Displays all users in the database in red/blue rectangles
     *
     * @param url The URL location of the FXML file
     * @param resourceBundle The ResourceBundle containing the resources needed for the FXML file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServerUtils serverUtils = new ServerUtils();
            List<User> users = serverUtils.getUsers();
            displayUsers(users);
    }

    private void displayUsers(List<User> users) {
        int i = 0;
        for(User u : users) {
            i++;
            VBox container = new VBox();
            Node curr = scrollPane.getContent();
            Rectangle rect = new Rectangle(150, 50);
            rect.setFill((i%2 == 1) ? Color.RED : Color.BLUE);

            // create a Text node with the user information
            Text text = new Text(u.username + "\nLevel: " + u.level + "\nExperience: " + u.experience);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

            // add the Text node to the rectangle
            StackPane stack = new StackPane();
            stack.getChildren().addAll(rect, text);
            if(curr != null)
                container.getChildren().add(curr);
            container.getChildren().add(stack);
            scrollPane.setContent(container);
        }
    }

    /**
     * ****************** TEMP ******************
     * This method must use a designated loader to avoid duplicate and
     * useless code. TODO implement loader
     * ****************** TEMP ******************
     *
     * This method loads the main FXML where you can register a user.
     *
     * @param event Mouse press on the "Normal View" button
     * @throws IOException in case the fxml is missing, the load() method throws
     * an exception
     */
    public void normalView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/studify/client/scenes/Main.fxml"));
        Parent adminViewParent = loader.load();
        Scene adminViewScene = new Scene(adminViewParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminViewScene);
        window.show();
    }


}
