import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Java14B_Einsend_Aufgabe4 extends Application {

	@Override
	public void start(Stage meineStage) throws Exception {
		
		HBox vbox = new HBox();
		vbox.setAlignment(Pos.CENTER);
		int animationsDauer = 1000;
		int wiederholungen = 10;
		
		//KK4: Quadrat und Kreis erstellen.
		Rectangle quadrat = new Rectangle(100, 100);
		quadrat.setStrokeWidth(10);
		quadrat.setFill(Color.RED);
		
		Circle kreis= new Circle(50);
		kreis.setStrokeWidth(10);
		kreis.setFill(Color.BLUE);
		
		//KK4: Quadrat von innen nach außen bewegen		
		TranslateTransition ttQuadrat = new TranslateTransition(Duration.millis(animationsDauer), quadrat);
		ttQuadrat.setByX(-300);
		ttQuadrat.setCycleCount(wiederholungen);
		ttQuadrat.setAutoReverse(true);
		ttQuadrat.play();
		
		//KK4: Kreis von innen nach außen bewegen			
		TranslateTransition ttKreis = new TranslateTransition(Duration.millis(animationsDauer), kreis);
		ttKreis.setByX(300);
		ttKreis.setCycleCount(wiederholungen);
		ttKreis.setAutoReverse(true);
		ttKreis.play();
		
		//KK4: Quadrat verkleinern		
		ScaleTransition stQuadrat = new ScaleTransition(Duration.millis(animationsDauer), quadrat);
		stQuadrat.setByY(-1); //Verkleinert Quadrat
		stQuadrat.setByX(-1);
		stQuadrat.setCycleCount(wiederholungen);
		stQuadrat.setAutoReverse(true);
		stQuadrat.play();
		
		//KK4: Kreis vergrößern		
		ScaleTransition stKreis = new ScaleTransition(Duration.millis(animationsDauer), kreis);
		stKreis.setByY(2); //Vergrößert Kreis
		stKreis.setByX(2);
		stKreis.setCycleCount(wiederholungen);
		stKreis.setAutoReverse(true);
		stKreis.play();
		
		//KK4: Kreis und Quadrat projizieren
		vbox.getChildren().add(quadrat);
		vbox.getChildren().add(kreis);
		
		//Szene erstellen
		Scene meineScene = new Scene(vbox, 1000, 500);
		meineStage.setTitle("Java14B_Einsend_Aufgabe4 - Kreis und Quadrat Animation");
		meineStage.setScene(meineScene);
		meineStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
