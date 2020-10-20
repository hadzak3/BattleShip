package boatSink;

class Ship {
	
	private String name;
	
	private boolean isDown;
	
	/* Puede ser H o V */
	private int orientation;
	
	Ship(String name, int orientation){
		this.name = name;
		this.orientation = orientation;
	}
	public boolean getIsDown() {
		return this.isDown;
	}
}
