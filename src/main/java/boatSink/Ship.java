package boatSink;

abstract class Ship {
	
	protected int nCells;
	private boolean down[][];
	protected int health;
	protected String name;
	
	Ship(String name, int health){
		this.nCells = health;
		// TODO create attributes nRows and nCols to get by constructor
		this.health = health; 
		this.name = name;
		this.down = new boolean[Constants.N_BOARD_ROWS_CELLS][Constants.N_BOARD_ROWS_CELLS];
		setAllShipsDown();
	}
	
	public int getNCells() {
		return nCells;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	private void setAllShipsDown() {
		// TODO create attributes nRows and nCols to get by constructor
		for(int i = 0; i < Constants.N_BOARD_ROWS_CELLS; i++) {
			for(int j = 0; j < Constants.N_BOARD_ROWS_CELLS - 1; j++) {
				this.down[i][j] = false;
			}
		}
	}
	
	public boolean isDown(int x, int y) {
		return this.down[x][y];
	}
	
	public boolean isSunk() {
		return (this.health == 0);
	}
	
	public void shoot(int x, int y) {
		this.down[x][y] = true;
		this.health--;
		if (this.health == 0) {
			System.out.println("Barco de " + this.nCells + " casilla(s) hundido.");
		} else {
			System.out.println("Barco de " + this.nCells + " casilla(s) tocado.");
		}
	}
}
