package jenb.leiterlispiel.ansicht;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import jenb.leiterlispiel.Objekte.Wuerfel;
import jenb.leiterlispiel.control.Controler;
import jenb.leiterlispiel.main.Spiel;


public class SpielBrett extends Application {
	private Wuerfel wuerfel = new Wuerfel();
	private Queue<Spieler> spielerWarteschlange = new ConcurrentLinkedQueue<>();
	private Controler controler = new Controler();
	private Group tileGroup = new Group();
	private Spiel spiel = new Spiel();
	
	public static final int TILE_SIZE=80;
	public static final int WIDTH=10;
	public static final int HIGHT=10;
	
	public Label randResult;
	public Button gameButton;
	public Button wuerfelButton;
	public Circle spieler1;
	public Circle spieler2;
	
	public Button speichern;
	public TextField textField1;
	public TextField textField2;
	
	public int cirPos[][] = new int [10][10];
	//public int leadderPosition[][] = new int [6][3];
	public int spielerPosition1 = 1;
	public int spielerPosition2 = 2;
	//public int posCir1 = 1;
	//public int posCir2 = 1;
	public static int playerXPos = 40;
	public static int playerYPos = 760;
	public static int player2XPos = 40;
	public static int player2YPos = 760;
	public boolean spieler1Aktiv = true;
	public boolean spieler2Aktiv = true;
	public boolean gameStart = false;

	
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
		
		
		for(int i = 0; i < HIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				System.out.print(cirPos[i][j]+ " ");
			}
			System.out.println();
		}
		
		spieler1 = new Circle (20);
		spieler1.setId("Spieler1");
		spieler1.setFill(Color.AQUA);
		spieler1.getStyleClass().add("style.css");
		spieler1.setTranslateX(playerXPos);
		spieler1.setTranslateY(playerYPos);

		spieler2 = new Circle (20);
		spieler2.setId("Spieler2");
		spieler2.setFill(Color.GREEN);
		spieler2.getStyleClass().add("style.css");
		spieler2.setTranslateX(player2XPos);
		spieler2.setTranslateY(player2YPos);
		
		wuerfelButton = new Button("Würfeln");
		wuerfelButton.setTranslateX(820);
		wuerfelButton.setTranslateY(300);
		
		gameButton = new Button("Start");
		gameButton.setTranslateX(820);
		gameButton.setTranslateY(500);
		gameButton.defaultButtonProperty();
		
		spieler1.setTranslateX(playerXPos);
		spieler1.setTranslateY(playerYPos);
		spieler2.setTranslateX(player2XPos);
		spieler2.setTranslateY(player2YPos);
		
		gameStart = true;
		
		randResult = new Label("Startet das Spiel mit dem Knopf Start.");
		randResult.setTranslateX(820);
		randResult.setTranslateY(600);
		
		ImageView bgImage = new ImageView(getClass().getResource("java_final_board.jpeg").toExternalForm());
		bgImage.setFitHeight(800);
		bgImage.setFitWidth(800);
		
		
		tileGroup.getChildren().addAll(bgImage, spieler1, spieler2, wuerfelButton, gameButton, randResult);
		return root;
	}

	private void spieler1AmZug() {
		for(int i = 0; i < controler.getGewuerfelteZahl(); i++) {
			if(controler.getPosCir1() % 2 == 1) {
				playerXPos+=80;
			}
			
			if (controler.getPosCir1() % 2 == 0) {
				playerXPos-=80;
			}
			
			if (playerXPos > 760) {
				playerYPos-=80;
				playerXPos-=80;
				controler.setPosCir1(controler.getPosCir1() + 1);
				
			}
			
			if (playerXPos < 40) {
				playerYPos-=80;
				playerXPos+=80;
				controler.setPosCir1(controler.getPosCir1() + 1);
			}
			
			if (playerXPos < 30 || playerYPos < 30) {
				playerXPos =40;
				playerYPos = 40;
				randResult.setText("Player 1 hat gewonnen");
				gameButton.setText("Starte erneut");
			}
		}
	}
	
	
	private void spieler2AmZug() {
		for(int i = 0; i < controler.getGewuerfelteZahl(); i++) {
			if(controler.getPosCir2() % 2 == 1) {
				player2XPos+=80;
			}
			
			if (controler.getPosCir2() % 2 == 0) {
				player2XPos-=80;
			}
			
			if (player2XPos > 760) {
				player2YPos-=80;
				player2XPos-=80;
				controler.setPosCir2(controler.getPosCir2() + 1);
				
			}
			
			if (player2XPos < 40) {
				player2YPos-=80;
				player2XPos+=80;
				controler.setPosCir2(controler.getPosCir2() + 1);
			}
			
			if (player2XPos < 30 || player2YPos < 30) {
				player2XPos =40;
				player2YPos = 40;
				randResult.setText("Player 2 hat gewonnen");
				gameButton.setText("Starte erneut");
			}
		}
	}
	
	private void translateSpieler(int x, int y, Circle b) {
		TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
		animate.setToX(x);
		animate.setToY(y);
		animate.setAutoReverse(false);
		animate.play();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
	     Scene scene2 = new Scene(createContent());
	     primaryStage.setTitle("Leiterlispiel");
	     primaryStage.setScene(scene2);
	     primaryStage.show();
	     
	     if(gameStart) {
				if (spieler1Aktiv) {
					wuerfelButton.getOnAction();
					randResult.setText(String.valueOf("Du hat eine " + controler.getGewuerfelteZahl()));
					spiel.spielen();
					spieler1AmZug();
					translateSpieler(playerXPos,playerYPos,spieler1);
					spieler1Aktiv = false;
					spieler2Aktiv = true;
				}else{
					controler.getGewuerfelteZahl();
					randResult.setText(String.valueOf("Du hat eine " + controler.getGewuerfelteZahl()));
					spieler2AmZug();
					translateSpieler(player2XPos,player2YPos,spieler2);
					spieler1Aktiv = true;
					spieler2Aktiv = false;
				}
			}
	     
	     wuerfelButton.setOnAction(new EventHandler <ActionEvent>() {
				public void handle (ActionEvent event) {
					controler.setGewuerfelteZahl(wuerfel.wuerfeln());
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
			     	speichern = new Button ("Speichern");
			     	speichern.setOnAction(new EventHandler <ActionEvent>() {
			     		public void handle (ActionEvent event) {
			     			String name = "";
			     			name = textField1.getText();
				    		System.out.println(name);
				    		spielerWarteschlange.add(new Spieler(name));
				   			name = textField2.getText();
				   			System.out.println(name);
				   			spielerWarteschlange.add(new Spieler(name));
				   			System.out.println(spielerWarteschlange);
				    		spielerErstellen.close();
				    		new Spiel();
				     		}
				     	});
					 
				     	root.add(lable1, 0, 0);
				     	root.add(textField1, 1, 0);
				     	root.add(lable2, 0, 1);
				     	root.add(textField2, 1, 1);
				     	root.add(speichern, 0, 2);
				     	
				     	Scene spielerErstellenScene = new Scene(root, 400, 400);
				     	spielerErstellen.setTitle("Gibt eure Namen ein!");
					    spielerErstellen.setScene(spielerErstellenScene);
					    spielerErstellen.show();

					    gameButton.setText("Game Stated");
					    playerXPos = 40;
					    playerYPos = 760;
					
					    player2XPos = 40;
					    player2YPos = 760;
					
					    spieler1.setTranslateX(playerXPos);
					    spieler1.setTranslateY(playerYPos);
					
					    spieler2.setTranslateX(player2XPos);
					    spieler2.setTranslateY(player2YPos);
					
					    gameStart = true;
				}
			});
	}
	
	public Queue<Spieler> getSpielerWarteschlange(String spieler) {
		return spielerWarteschlange;
	}

	public void setSpielerWarteschlange(Queue<Spieler> spielerWarteschlange) {
		this.spielerWarteschlange = spielerWarteschlange;
	}

	public static void main (String[] args) {
		launch(args);
	}
}