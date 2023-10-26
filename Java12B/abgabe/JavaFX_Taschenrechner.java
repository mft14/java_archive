package java12btaschenrechner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFX_Taschenrechner extends Application {
	
	@Override
	public void start(Stage meineStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("sb_taschenrechner.fxml"));
		
		Scene meineScene = new Scene(root, 600, 200);
		meineStage.setTitle("Taschenrechner in JavaFX - Java12B Einsendung");
		meineStage.setScene(meineScene);
		meineStage.show();
	}
	
	public static void main(String[] args) {
		//der Start
		launch(args);
	}
	
}
