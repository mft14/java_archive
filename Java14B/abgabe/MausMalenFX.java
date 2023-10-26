import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MausMalenFX extends Application {
	
	private int xPos, yPos;
	private int windowSizeX = 500;
	private int windowSizeY = 500;

	@Override
	public void start(Stage meineStage) throws Exception {
		FlowPane rootNode = new FlowPane();
		
		Canvas meinCanvas = new Canvas(windowSizeX,windowSizeY);
		rootNode.getChildren().add(meinCanvas);
		GraphicsContext gc = meinCanvas.getGraphicsContext2D();
		
		//Eventhandler für Maus
		meinCanvas.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY) {
					xPos = (int)e.getX();
					yPos = (int)e.getY();
				}
			}
		}); //MousePressed End
		
		meinCanvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.getButton() == MouseButton.PRIMARY) {
					gc.strokeLine(xPos, yPos, e.getX(), e.getY());
				}
			}
		}); //MousePressed End
		
		Scene meineScene = new Scene(rootNode, windowSizeX, windowSizeY);
		meineStage.setTitle("Zeichenprogramm");
		meineStage.setScene(meineScene);
		meineStage.show();
		
	} //start End

	
	
	public static void main(String[] args) {
		launch(args);
	}
}
