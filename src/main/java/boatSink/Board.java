package boatSink;

import java.util.ArrayList;

public class Board {

	private int nRows, nCols;
	private Cell cells[][];
	private ArrayList<Ship> ships;
	
	Board (int nRows, int nCols) {
		this.nRows = nRows; 
		this.nCols = nCols; 
		this.cells = new Cell[nRows][nCols];
		this.ships = new ArrayList<>();
		setAllCellsWater();
	}
	
	private void setAllCellsWater() {
		for(int i = 0; i < nRows; i++) {
			for(int j = 0; j < nCols; j++) {
				this.cells[i][j] = new Cell(i, j, null);
			}
		}
	}
	
	protected void addShip(int x, int y, Ship ship) {
		System.out.println("Seteando parte del barco en la posicion: (" +  x + ", "+ y +")");
		cells[x][y].setShip(ship);
		System.out.println("Barco insertado correctamente");
	}
	
	/* Comprueba si se ha tocado un barco o es agua, y si es un barco dispara en el. */
	protected void shoot(int x, int y) {
		if(cells[x][y].isShip()) {
			cells[x][y].shootShip();
		}
		else {
			System.out.println("Agua");
		}
	}
	
	protected boolean isEndGame() {
		boolean isEndGame = true;
		for (Ship ship : ships) {
			if (ship.isSunk()) {
				isEndGame = false;
			}
		}
		
		return isEndGame;
	}
	
	@Override
	public String toString() {
		int x;
		/* Columns header */
		for (x = 0; x <= this.cells.length-1; x++) {
			if (x == 0) {
				System.out.printf("%4s", x);
			} else {
				System.out.printf("%3s", x);
			}
		}
		
		System.out.println("");
		
		/* Board */
		for (x = 0; x <= this.cells.length-1; x++) {
			System.out.printf("%-3s", x); // Every row start print the row header;
			for (int y = 0; y <= this.cells.length-1; y++) {
				System.out.printf("%-2s ", this.cells[x][y]);
			}
			System.out.println("");
		}
	
		return "";
	}
	
}
