package studify.controller;

import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
import studify.dto.User;
import studify.utils.Initializable;
import studify.utils.ServerUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminCtrl implements Initializable {

    public Scene scene;

    MainCtrl mainCtrl;
    ServerUtils serverUtils;
    @FXML
    Button normalView;
    @FXML
    ScrollPane scrollPane;

    List<User> users = new ArrayList<>();

    @Inject
    public AdminCtrl(ServerUtils serverUtils, MainCtrl mainCtrl) {
        this.serverUtils = serverUtils;
        this.mainCtrl = mainCtrl;
    }

    @Override
    public void initialize() {
        users = serverUtils.getUsers();
        displayUsers(users);
        serverUtils.registerForMessage("topic/users/add", User.class, user -> {
            Platform.runLater(() -> {
                users.add(user);
                displayUsers(users);
            });
        });
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
            Text text = new Text(u.username + "\nLevel: "
                    + u.level + "\nExperience: " + u.experience);
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

    public void normalView(ActionEvent event) throws IOException {
        mainCtrl.primaryStage.setScene(mainCtrl.registerCtrl.scene);
        mainCtrl.primaryStage.show();
    }
}
