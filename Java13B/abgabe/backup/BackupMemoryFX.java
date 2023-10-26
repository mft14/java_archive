package backup;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class BackupMemoryFX extends Application{
	@Override
	public void start(Stage meineStage) throws Exception {
		//den obersten Knoten erzeugen
		//hier verwenden wir ein FlowPane
		//erzeugt wird die Oberfläche über eine eigene Methode in der Klasse MemoryFeld
		FlowPane rootNode = new BackupMemoryFeld().initGUI(new FlowPane());
		//die Szene erzeugen
		//an den Konstruktor werden der oberste Knoten und die Größe übergeben
		Scene meineScene = new Scene(rootNode, 470, 550);
		
		//den Titel über stage setzen
		meineStage.setTitle("Memory");
		//die Szene setzen
		meineStage.setScene(meineScene);
		//Größenänderungen verhindern
		meineStage.setResizable(false);
		//und anzeigen
		meineStage.show();
	}
	
	public static void main(String[] args) {
		//der Start
		launch(args);
	}
}
