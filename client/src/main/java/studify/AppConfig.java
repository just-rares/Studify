package studify;

import javafx.scene.Parent;
import javafx.util.Pair;
import studify.controller.AdminCtrl;
import studify.controller.RegisterCtrl;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private final Map<SceneType, Pair<?, Parent>> scenes;
    @SuppressWarnings("MemberName")
    private final MyFXML FXML;

    public AppConfig(MyFXML FXML) {
        scenes = new HashMap<>();
        this.FXML = FXML;
    }

    public void loadScenes() {
        scenes.put(SceneType.REGISTER, FXML.load(RegisterCtrl.class,
                "studify", "client", "scenes", "Register.fxml"));
        scenes.put(SceneType.ADMIN, FXML.load(AdminCtrl.class,
                "studify", "client", "scenes", "Admin.fxml"));
    }

    public Map<SceneType, Pair<?, Parent>> getScenes(){
        return scenes;
    }



}
