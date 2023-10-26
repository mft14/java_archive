package java12b;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class JavaFX_Beispiel1 extends Application{
	@Override
	public void start(Stage meineStage) throws Exception {

		Label ausgabe = new Label("Hallo Welt");
		Label ausgabe2 = new Label();
		
		ausgabe2.setText("Es grüßt dich Max Meier");
		Button schaltflaeche = new Button("Beenden");
		
		FlowPane rootNode = new FlowPane();
		
		rootNode.getChildren().add(ausgabe);
		rootNode.getChildren().add(ausgabe2);
		rootNode.getChildren().add(schaltflaeche);
		
		//Szene erzeugen
		Scene meineScene = new Scene(rootNode, 200, 250);
		
		meineStage.setTitle("Hallo Welt");
		meineStage.setScene(meineScene);
		meineStage.show();
		
		//ActionEvent.
		schaltflaeche.setOnAction(new SchaltflaecheEventHandler());
		
	}
	
	public static void main(String[] args) {
		//der Start
		launch(args);
	}
}
