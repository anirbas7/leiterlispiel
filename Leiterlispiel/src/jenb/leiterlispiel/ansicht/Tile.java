package jenb.leiterlispiel.ansicht;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile extends Rectangle {
	
	public Tile (int x, int y) {
		setWidth(SpielBrett.TILE_SIZE);
		setHeight(SpielBrett.TILE_SIZE);
		
		setFill(Color.WHITE);
		setStroke(Color.BLACK);
		
	}
}
