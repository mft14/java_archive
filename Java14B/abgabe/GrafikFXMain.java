


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GrafikFXMain extends Application {
	

	@Override
	public void start(Stage meineStage) throws Exception {

		
		FlowPane rootNode = new FlowPane();
				
		Canvas meinCanvas = new Canvas(400, 400);
		
		GraphicsContext gc = meinCanvas.getGraphicsContext2D();
		
		Button btnDraw = new Button("Draw");
		Label ausgabe = new Label("");
		
		rootNode.getChildren().add(ausgabe);
		rootNode.getChildren().add(meinCanvas);
//		Zeichner.zeichneFor(gc, meinCanvas);
//		Zeichner.zeichneLinie(gc, meinCanvas);
//		Zeichner.zeichneKreis(gc);
//		Zeichner.zeichnePolygon(gc);
//		Zeichner.zeichneText(gc);
		Zeichner.zeichneAufgaben(gc);
		
		
		Scene meineScene = new Scene(rootNode, 400, 400);
		
		meineStage.setTitle("Grafikspielerein");
		meineStage.setScene(meineScene);
		meineStage.show();
		
		//Innere anonyme Mausklasse

		meinCanvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				String ausgabetext;
				ausgabetext = "X: " + Double.toString(e.getX()) + "Y: " + Double.toString(e.getY());
				if(e.getButton() == MouseButton.PRIMARY) {
					ausgabetext = ausgabetext + " linke Taste";
				}
				if (e.getButton() == MouseButton.SECONDARY) {
					ausgabetext = ausgabetext + " rechte Taste";
				}
				//Die Anzahl der Klicks ermitteln
				ausgabetext = ausgabetext + " mit " + 
				e.getClickCount() + " Klicks";
				ausgabe.setText(ausgabetext);
			}
		});
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

	

}



	class Zeichner {
 		
		int randomRot = (int)(Math.random() * 256);
		int randomGruen = (int)(Math.random() * 256);
		int randomBlau = (int)(Math.random() * 256);
 		
 		public static void zeichneAufgaben(GraphicsContext gc) {
 			gc.setStroke(Color.BLUE);
 			gc.strokeLine(0, 0, 200, 100);
 		}
		public static void zeichneText(GraphicsContext gc) {
			gc.setFont(new Font("Arial", 20));
			gc.strokeText("Ich stehe hier auf der  Zeichenfläche", 20, 20);
		}
 		public static void zeichnePolygon(GraphicsContext gc) {
 			double xPunkte[] = {5,100,558,167,78};
 			double yPunkte[] = {5,100,66,700,528};
 			gc.strokePolygon(xPunkte, yPunkte, 5);
 		}
 		public static void zeichneKreis(GraphicsContext gc) {
 			gc.setStroke(Color.RED);
 			gc.strokeOval(10, 10, 200, 10);
 		}
 		public static void zeichneLinie(GraphicsContext gc, Canvas meinCanvas) {

 			int xPos = 1;
 			int yPos = 1;
 			int xMax = (int) meinCanvas.getWidth()-1;
 			int yMax = (int) meinCanvas.getHeight()-1;
 			
 			while(yPos < yMax) {
 	 			int randomRot = (int)(Math.random() * 256);
 	 			int randomGruen = (int)(Math.random() * 256);
 	 			int randomBlau = (int)(Math.random() * 256);
 				gc.setStroke(Color.rgb(randomRot, randomGruen, randomBlau));
 				gc.strokeLine(0, yPos, xMax, yPos);
 				yPos = yPos + 10;
 			}
 			
 			while (xPos < xMax) {
 	 			int randomRot = (int)(Math.random() * 256);
 	 			int randomGruen = (int)(Math.random() * 256);
 	 			int randomBlau = (int)(Math.random() * 256);
 	 			gc.setLineWidth(5);
 				gc.setStroke(Color.rgb(randomRot, randomGruen, randomBlau));
 				gc.strokeLine(xPos, 0, xPos, yMax);
 				xPos = xPos + 10;
 			}
 			
 			yPos = (yMax/2) - 50;
 			xPos = (xMax/2) - 50;
 			
 			gc.fillRect(xPos, yPos, 100, 100);
 			gc.strokeLine(0, 0, xMax, yMax);
 			gc.strokeLine(0, yMax, xMax, 0);
 		}
 		public static void zeichneQuadrat(GraphicsContext gc) {
 			gc.fillRect(0, 0, 100, 100);
 		}
 		public static void zeichne(GraphicsContext gc) {
 			gc.strokeRect(10, 10, 100, 100);
 		}
 		public static void zeichneMax(GraphicsContext gc, Canvas meinCanvas) {
 			gc.strokeRect(1, 1, meinCanvas.getWidth()-2, meinCanvas.getHeight()-2);
 		}
 		public static void zeichneFor(GraphicsContext gc, Canvas meinCanvas) {
 			int x = 1;
 			int y = 1;
 			int breite = (int)meinCanvas.getWidth()-2;
 			int hoehe = (int)meinCanvas.getHeight()-2;
 			
 			for (int i = 0; i < 30; i++) {
				gc.strokeRect(x, y, breite, hoehe);
				x += 5;
				y += 5;
				breite = breite - 10;
				hoehe = hoehe - 10;
			}
 		}
 	}
