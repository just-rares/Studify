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
     * This method is an overridden method from implementing the Initializable interface.
     * It is called whenever a new user is registered, and it adds a new rectangle with the
     * user's details to the UI.
     * It first retrieves the list of all registered users from the server using the ServerUtils class.
     * Then, it creates a VBox container to hold all the rectangles and text nodes for each user.
     * For each user, it creates a new rectangle and fills it with a color (either red or blue)
     * based on the user's position in the list.
     * It then creates a Text node with the user's information (username, level, and experience),
     * and adds it to the rectangle.
     * Finally, it creates a StackPane to combine the rectangle and text node, and adds it
     * to the container.
     * If there are already existing nodes in the scroll pane content, the new container will
     * be added to it before setting it as the new content.
     * If an IOException occurs while trying to retrieve the list of users from the server,
     * it throws a RuntimeException.
     * @param url The URL location of the FXML file
     * @param resourceBundle The ResourceBundle containing the resources needed for the FXML file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServerUtils serverUtils = new ServerUtils();
        try {
            List<User> users = serverUtils.getUsers();
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
        } catch (IOException e) {
            throw new RuntimeException(e);
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
