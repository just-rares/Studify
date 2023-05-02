package studify;

import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Pair;
import studify.AppConfig;
import studify.controller.MainCtrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.inject.Guice.createInjector;

public class MainClient extends Application {

    private static final Injector INJECTOR = createInjector(new MyModule());
    private static final MyFXML FXML = new MyFXML(INJECTOR);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        var mainCtrl = INJECTOR.getInstance(MainCtrl.class);
    }
}
