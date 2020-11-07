package boatSink;

public class OneCellShip extends Ship {
	
	private static int HEALTH = 1;
	
	OneCellShip() {
		super(HEALTH);
	}
	
	OneCellShip(int health) {
		super(health);
	}
}
