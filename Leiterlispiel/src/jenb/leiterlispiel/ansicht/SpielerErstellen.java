package jenb.leiterlispiel.ansicht;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jenb.leiterlispiel.Objekte.Spieler;

public class SpielerErstellen extends Application implements EventHandler<ActionEvent> {

	private Queue<Spieler> spielerWarteschlange = new ConcurrentLinkedQueue<>();
	private GridPane root;
	public Button speichern;
	public TextField textField1;
	public TextField textField2;
	
	   public static void main(String[] args) {
		      launch(args);
		   }
	   
	   @Override
	   public void start(Stage primaryStage){
		   try {
			      this.root = new GridPane();
			      
			      Label lable1 = new Label("Spieler 1");
			      TextField textField1 = new TextField();
			      Label lable2 = new Label("Spieler 2");
			      TextField textField2 = new TextField();
				  speichern = new Button ("Speichern");
				  speichern.setOnAction(this);
				  
					  /*public void handle(ActionEvent evt) {
						  SpielBrett spielBrett = new SpielBrett();
						  spielBrett.start(secondaryStage);
						  
						  String name = "";
						  name = textField1.getText();
						  System.out.println(name);
						  spielerWarteschlange.add(new Spieler(name));
						  name = textField2.getText();
						  System.out.println(name);
						  spielerWarteschlange.add(new Spieler(name));
						  System.out.println(spielerWarteschlange);
						  primaryStage.close();
						
					  }*/
				  
			      Scene scene = new Scene(root, 400, 400);
			      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			      root.add(lable1, 0, 0);
			      root.add(textField1, 1, 0);
			      root.add(lable2, 0, 1);
			      root.add(textField2, 1, 1);
			      root.add(speichern, 0, 2);
			      
			      primaryStage.setScene(scene);
			      primaryStage.show();

	       } catch(Exception e) {
	         e.printStackTrace();  
	      }
		   
	   }

	@Override
	public void handle(ActionEvent arg0) {
		if (arg0.getSource()==speichern) {
			String name = "";
			name = textField1.getText();
			System.out.println(name);
			spielerWarteschlange.add(new Spieler(name));
			name = textField2.getText();
			System.out.println(name);
			spielerWarteschlange.add(new Spieler(name));
			System.out.println(spielerWarteschlange);
		}
	}
}
