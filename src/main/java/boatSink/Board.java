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
	
	protected boolean isShipCell(int x, int y) {
		return this.cells[x][y].isShip();
	}
	
	private void setAllCellsWater() {
		for(int x = 0; x <= this.nRows - 1; x++) {
			for(int y = 0; y <= this.nCols - 1; y++) {
				this.cells[x][y] = new Cell(x, y, null);
			}
		}
	}
	
	/* TODO Revisar caso que el jugador dispara en en un barco contiguo pero muestra tocado en. */
	protected boolean createShip(int x0, int y0, String orientation, Ship ship) {	
		boolean isCreated = false;
		
		if (this.isShipCell(x0, y0)) {
			System.out.println("Ya hay un barco en las coordenadas proporcionadas.");
		} else {
			int nCells = ship.getNCells();
			
			if (nCells == 1) {
				cells[x0][y0].setShip(ship);
				isCreated = true;
			} else if (nCells > 1) {
				int coordinateLimit = 0;
				if (orientation.equals(Constants.SHIP_HORIZONTAL)) {
					if (y0 + nCells < this.nCols) { /* try to create current position right cells. */
						coordinateLimit = y0 + nCells;
						int y = y0;
						do {
							y++;
						} while (!this.isShipCell(x0, y) && y < coordinateLimit);
						
						if (y == coordinateLimit) {
							for (y = y0; y < coordinateLimit; y++) {
								cells[x0][y].setShip(ship);
							}
							isCreated = true;
						}
		    		} 
					
					if (!isCreated && (y0 - nCells >= 0)) { /* try to create current position left cells. */
						coordinateLimit = y0 - nCells;
		    			int y = y0;
						do {
							y--;
						} while (!this.isShipCell(x0, y) && y > coordinateLimit);
						
						if (y == coordinateLimit) {
							for (y = y0; y > coordinateLimit; y--) {
								cells[x0][y].setShip(ship);
							}
							isCreated = true;
						}
		    		}
				} else if (orientation.equals(Constants.SHIP_VERTICAL)) {
					if (x0 + nCells < this.nRows) { /* try to create current position upper cells. */
						coordinateLimit = x0 + nCells;
						int x = x0;
						do {
							x++;
						} while (!this.isShipCell(x, y0) && x < coordinateLimit);
						
						if (x == coordinateLimit) {
							for (x = x0; x < coordinateLimit; x++) {
								cells[x][y0].setShip(ship);
							}
							isCreated = true;
						}
		    		} 
					
					if (!isCreated && (x0 - nCells >= 0)) { /* try to create current position lower cells. */
						coordinateLimit = x0 - nCells;
		    			int x = x0;
						do {
							x--;
						} while (!this.isShipCell(x, y0) && x > coordinateLimit);
						
						if (x == coordinateLimit) {
							for (x = x0; x > coordinateLimit; x--) {
								cells[x][y0].setShip(ship);
							}
							isCreated = true;
						}
		    		} 
				} else {
					System.out.println("Orientation not implemented: " + orientation);
				}
			}
		}
			
		
		
		if (isCreated) {
			System.out.println("Barco creado correctamente.");
			ships.add(ship);
		} else {
			System.out.println("No se ha podido crear el barco en las coordenadas fijadas. Prueba con datos nuevos.");
		}
		
		return isCreated;
	}

	
	protected boolean shoot(int x, int y) {
		boolean isDown = false;
		if(cells[x][y].isShip()) {
			 cells[x][y].shootShip(x, y);
			 isDown = true;
		}
		else {
			System.out.println("Agua");
		}
		
		return isDown;
	}
	
	protected boolean isEndGame() {
		boolean isTheEnd = true;
		for (int i = 0; i < ships.size() && isTheEnd; i++) {
			if (!ships.get(i).isSunk()) {
				isTheEnd = false;
			}
		}
		
		return isTheEnd;
	}
	
	@Override
	public String toString() {
		int x;
		// first prints column numbers
		for (x = 0; x <= this.nCols - 1; x++) {
			if (x == 0) {
				System.out.printf("%4s", x);
			} else {
				System.out.printf("%3s", x);
			}
		}
		
		System.out.println("");
		
		/* Board */
		for (x = 0; x <= this.nRows - 1; x++) {
			System.out.printf("%-3s", x); //  rows number every at every row start
			for (int y = 0; y <= this.nCols - 1; y++) {
				System.out.printf("%-2s ", this.cells[x][y]);
			}
			System.out.println("");
		}
	
		return "";
	}
	
}
