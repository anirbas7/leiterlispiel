package jenb.leiterlispiel.ansicht;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import jenb.leiterlispiel.Objekte.Spieler;
import jenb.leiterlispiel.main.Spiel;

//Maindatei
//erstellung der gesammten Spieloberfläche und Animation der Spielzüge
public class SpielBrett extends Application {
	
	private Group tileGroup = new Group();
	private Spiel spiel = new Spiel();
	
	private static final javafx.scene.paint.Color[] colors = { Color.RED, Color.DARKGREEN, Color.DARKBLUE, Color.YELLOW, Color.BLUEVIOLET, Color.AQUA};
	
	public static final int TILE_SIZE=80;
	public static final int WIDTH=10;
	public static final int HIGHT=10;
	
	public Label randResult;
	public Button gameButton;
	public Button wuerfelButton;
	public Alert winn = new Alert(AlertType.NONE);
	
	
	public Button speichern;
	public TextField textField1;
	public TextField textField2;
	
	public int cirPos[][] = new int [10][10];	
	public boolean gameStart = false;
	
	private Map<Spieler, Circle> spielerToCircle = new HashMap<>();
//Erstellt alle Elemente des GUI's
	private Parent createContent() {
		StackPane root = new StackPane();
		root.setPrefSize(WIDTH * TILE_SIZE, (HIGHT * TILE_SIZE)+80);
		root.getChildren().addAll(tileGroup);
		
		for(int i = 0; i < HIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				Tile tile = new Tile(TILE_SIZE, TILE_SIZE);
				tile.setTranslateX(j * TILE_SIZE);
				tile.setTranslateY(i * TILE_SIZE);
				tileGroup.getChildren().add(tile);
				cirPos[i][j] = j *(TILE_SIZE - 40);
			}
		}
//		System.out.println() funkionen sind gewollt aktiv, um die erstellung des Spielfeldes zu überprüfen.
		for(int i = 0; i < HIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				System.out.print(cirPos[i][j]+ " ");
			}
			System.out.println();
		}
		
		wuerfelButton = new Button("Würfeln");
		wuerfelButton.setTranslateX(820);
		wuerfelButton.setTranslateY(300);
		
		gameButton = new Button("Start");
		gameButton.setTranslateX(820);
		gameButton.setTranslateY(500);
		gameButton.defaultButtonProperty();
		
		gameStart = true;
		
		randResult = new Label("Started das Spiel mit Start.");
		randResult.setTranslateX(820);
		randResult.setTranslateY(600);
		
		ImageView bgImage = new ImageView(getClass().getResource("java_final_board.jpeg").toExternalForm());
		bgImage.setFitHeight(800);
		bgImage.setFitWidth(800);
		
		tileGroup.getChildren().addAll(bgImage, wuerfelButton, gameButton, randResult);
		return root;
	}
//übersetzt die Spieler x und y positionen in die Spielfiguren(Kreise) für die Animation
	private void translateSpieler(int x, int y, Circle b) {
		TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
		animate.setToX(x);
		animate.setToY(y);
		animate.setAutoReverse(false);
		animate.play();
	}
//	eigendliche Spielfunktionen
	@Override
	public void start(Stage primaryStage) throws Exception{
	     Scene scene = new Scene(createContent());
	     primaryStage.setTitle("Leiterlispiel");
	     primaryStage.setScene(scene);
	     primaryStage.show();
	     
	     wuerfelButton.setOnAction(new EventHandler <ActionEvent>() {
				public void handle (ActionEvent event) {
					Spieler dran = spiel.getNextSpieler();
					spiel.spielen();
					int feld = dran.getSpielerPosition();
					
					if (feld >= 100) {
						feld = 100;
						int y =720 - ((feld-1) / 10) * 80 +40;
						int x = ((feld-1) % 10) * 80 + 40;
						if ((feld-1)/10 % 2 == 1) {
							x =800 - x;
						}
						translateSpieler(x, y, spielerToCircle.get(dran));
						gameStart = false;
						congrats("Klicke 'ok' und starte das Spiel neu.");
					}
					
					int x = ((feld-1) % 10) * 80 + 40;
					
					randResult.setText(String.valueOf("Du hast eine " + spiel.punkte + " gewürfelt."));
				
					
					if ((feld-1)/10 % 2 == 1) {
						x =800 - x;
					}
					
					int y =720 - ((feld-1) / 10) * 80 +40;

					translateSpieler(x, y, spielerToCircle.get(dran));
					}
	     	});
	     
	     gameButton.setOnAction(new EventHandler <ActionEvent>() {
				public void handle (ActionEvent event) {
					final Stage spielerErstellen = new Stage();
			        spielerErstellen.initModality(Modality.APPLICATION_MODAL);
			        spielerErstellen.initOwner(primaryStage);
			        GridPane root = new GridPane();
				
			        Label lable1 = new Label("Spieler 1");
				    TextField textField1 = new TextField();
				   	Label lable2 = new Label("Spieler 2");
				   	TextField textField2 = new TextField();
				   	Label lable3 = new Label("Spieler 3");
				    TextField textField3 = new TextField();
				    Label lable4 = new Label("Spieler 4");
				    TextField textField4 = new TextField();
				    Label lable5 = new Label("Spieler 5");
				    TextField textField5 = new TextField();
				    Label lable6 = new Label("Spieler 6");
				    TextField textField6 = new TextField();
			     	speichern = new Button ("Speichern");
			     	speichern.setOnAction(new EventHandler <ActionEvent>() {
			     		public void handle (ActionEvent event) {
			     			String name = "";
			     			name = textField1.getText();
			     			//System.out.println(name);
				    		spiel.addPlayer(name);
				    		
				   			name = textField2.getText();
				   			//System.out.println(name);
				    		spiel.addPlayer(name);
				    		
				    		if (textField3.getText() != "") {
				    			name = textField3.getText();
				    			//System.out.println(name);
				    			spiel.addPlayer(name);
				    		}
				    		
				    		if (textField4.getText()!= "") {
				    			name = textField4.getText();
					   			//System.out.println(name);
					    		spiel.addPlayer(name);
				    		}
				    		
				    		if (textField5.getText()!= "") {
				    			name = textField5.getText();
					   			//System.out.println(name);
					    		spiel.addPlayer(name);
				    		}
				    		
				    		if (textField6.getText()!= "") {
				    			name = textField6.getText();
					   			//System.out.println(name);
					    		spiel.addPlayer(name);
				    		}
				   			
				   			spielerErstellen.close();
				   			
				   			int i = 0;
				   			for (Spieler s : spiel.spielerWarteschlange) {
				   				Circle circle = new Circle (20);
				   				circle.setId(s.getName());
				   				circle.setFill(colors[i]);
				   				circle.getStyleClass().add("style.css");
				   				circle.setTranslateX(40);
				   				circle.setTranslateY(760);
				   				
				   				spielerToCircle.put(s, circle);
				   				tileGroup.getChildren().add(circle);
				   				
				   				i++;
				   			}
				   			}
				     	});
					 
			     		root.autosize();
				     	root.add(lable1, 0, 0);
				     	root.add(textField1, 1, 0);
				     	
				     	root.add(lable2, 0, 1);
				     	root.add(textField2, 1, 1);
				     	
				     	root.add(lable3, 0, 2);
				     	root.add(textField3, 1, 2);
				     	
				     	root.add(lable4, 0, 3);
				     	root.add(textField4, 1, 3);
				     	
				     	root.add(lable5, 0, 4);
				     	root.add(textField5, 1, 4);
				     	
				     	root.add(lable6, 0, 5);
				     	root.add(textField6, 1, 5);
				     	
				     	root.add(speichern, 0, 6);
				     	
				     	Scene spielerErstellenScene = new Scene(root, 400, 400);
				     	spielerErstellen.setTitle("Gibt eure Namen ein!");
					    spielerErstellen.setScene(spielerErstellenScene);
					    spielerErstellen.show();

					    gameButton.setText("Spielmodus");
					    gameStart = true;
					    randResult.setText(String.valueOf("Spieler1 beginnt!"));
				}
			});
	}
	public void  congrats (String message) {
		winn.setAlertType(AlertType.INFORMATION);
		winn.setTitle("Congrats");
		winn.setHeaderText("Du hast gewonnen!!" + "\n" + "Herzlichen Glückwunsch!");
		winn.setContentText(message);
		winn.showAndWait();
	}	
	   public static void main(String[] args) {
		      launch(args);
		   }
}