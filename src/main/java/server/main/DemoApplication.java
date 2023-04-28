package server.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static javafx.application.Application.launch;

@SpringBootApplication(scanBasePackages = {"server.controller"})
public class DemoApplication extends Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/Main.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Demo Application");
		stage.setScene(scene);
		stage.show();
	}

}
