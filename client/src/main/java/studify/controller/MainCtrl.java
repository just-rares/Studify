package studify.controller;

import com.google.inject.Inject;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import studify.SceneType;
import studify.utils.ServerUtils;

import java.util.Map;

public class MainCtrl {

    Stage primaryStage;
    ServerUtils server;
    RegisterCtrl registerCtrl;
    AdminCtrl adminCtrl;

    /**
     * Constructor of the MainCtrl class
     * @param server interface for sending HTTP requests to the server
     */
    @Inject
    public MainCtrl(ServerUtils server) {
        this.server = server;
    }

    public void initialize(Stage primaryStage, Map<SceneType, Pair<?, Parent>> scenes) {
        this.primaryStage = primaryStage;

        this.registerCtrl = (RegisterCtrl) scenes.get(SceneType.REGISTER).getKey();
        registerCtrl.scene = new Scene(scenes.get(SceneType.REGISTER).getValue());

        this.adminCtrl = (AdminCtrl) scenes.get(SceneType.ADMIN).getKey();
        adminCtrl.scene = new Scene(scenes.get(SceneType.ADMIN).getValue());

        primaryStage.setScene(registerCtrl.scene);
        primaryStage.show();
    }
}
