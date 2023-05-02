package studify;

import javafx.scene.Parent;
import javafx.util.Pair;
import studify.controller.AdminCtrl;
import studify.controller.RegisterCtrl;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private final Map<SceneType, Pair<?, Parent>> scenes;
    private final MyFXML FXML;

    public AppConfig(MyFXML FXML) {
        scenes = new HashMap<>();
        this.FXML = FXML;
    }

    public void loadScenes() {
        //scenes.put(SceneType.REGISTER, FXML.load(RegisterCtrl.class,  "studify", "client", "scenes", "Register.fxml"));
        URL adminUrl = getClass().getClassLoader().getResource("studify/client/scenes/Admin.fxml");
        scenes.put(SceneType.ADMIN, FXML.load(AdminCtrl.class, adminUrl));
    }

    public Map<SceneType, Pair<?, Parent>> getScenes(){
        return scenes;
    }



}
