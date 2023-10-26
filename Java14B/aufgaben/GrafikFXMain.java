package java14;


import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GrafikFXMain extends Application {
	

	@Override
	public void start(Stage meineStage) throws Exception {

		FlowPane rootNode = new FlowPane();
		Canvas meinCanvas = new Canvas(400, 400);
		GraphicsContext gc = meinCanvas.getGraphicsContext2D();
		
		Zeichner.zeichneFiguren(rootNode);
		
		rootNode.getChildren().add(meinCanvas);
//		Zeichner.zeichneFor(gc, meinCanvas);
//		Zeichner.zeichneLinie(gc, meinCanvas);
//		Zeichner.zeichneKreis(gc);
//		Zeichner.zeichnePunkt(gc);
		
//		Zeichner.zeichnePolygon(gc);
//		Zeichner.zeichneText(gc);
//		Zeichner.zeichneAufgaben(gc);
		
		
		Scene meineScene = new Scene(rootNode, 400, 400);
		
		meineStage.setTitle("Grafikspielerein");
		meineStage.setScene(meineScene);
		meineStage.show();
		
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}

	class Zeichner {
 		
		int randomRot = (int)(Math.random() * 256);
		int randomGruen = (int)(Math.random() * 256);
		int randomBlau = (int)(Math.random() * 256);
		
		public static void zeichneFiguren(FlowPane rootNode) {
			Rectangle rechteck = new Rectangle(200, 100); //Re
			rechteck.setStroke(Color.BLACK);
			rechteck.setStrokeWidth(5);
			rechteck.setFill(Color.YELLOW);
			
			Circle kreis = new Circle(100);
			kreis.setStroke(Color.BLUEVIOLET);
			kreis.setStrokeWidth(5);
			
			//KOmpletter Würfel mit Achsenrotation
			Box wuerfel = new Box(100,100,150); wuerfel.setRotationAxis(new Point3D(50,90,0)); wuerfel.setRotate(45);	
			
			
			
			ScaleTransition zoom = new ScaleTransition(Duration.millis(1000), wuerfel);
			//zoom.setByY(1);
			zoom.setToZ(5);
			zoom.setToX(1);
			zoom.setToY(3);
			
			zoom.setCycleCount(10); //wie oft soll die Animation kommen
			zoom.setAutoReverse(false);
			zoom.play();
			
			//rootNode.getChildren().add(kreis);
			rootNode.getChildren().add(rechteck); //Projezieren
			//rootNode.getChildren().add(wuerfel);
		}
 		
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
 			gc.strokeOval(0, 0, 200, 200);
 		}
 		
 		public static void zeichnePunkt(GraphicsContext gc) {
 			gc.strokeLine(100, 0, 100, 100);
 			gc.setLineWidth(5);
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
