package java12b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXCaseTerror extends Application {

	@Override
	public void start(Stage meineStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("sb_caseterror.fxml"));
		
		Scene meineScene = new Scene(root, 480, 720);
		meineStage.setTitle("JavaFX Caseterror");
		meineStage.setScene(meineScene);
		meineStage.show();
		
	}
	
	public static void main(String[] args) {
		//der Start
		launch(args);
	}

}
