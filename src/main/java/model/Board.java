package model;

import java.util.ArrayList;
import utils.Constants;

public class Board {

	private int nRows, nCols;
	private Cell cells[][];
	private ArrayList<Ship> ships;
	
	public Board (int nRows, int nCols) {
		if(nRows != Constants.N_BOARD_ROWS_CELLS || nCols != Constants.N_BOARD_ROWS_CELLS) {
			throw new IllegalArgumentException();
		}
		this.nRows = nRows;
		this.nCols = nCols; 
		this.cells = new Cell[nRows][nCols];
		this.ships = new ArrayList<>();
		setAllCellsWater();
	}
	
	public void setAllCellsWater() {
		for(int x = 0; x < this.nRows; x++) {
			for(int y = 0; y < this.nCols; y++) {
				this.cells[x][y] = new Cell(x, y, null);
			}
		}
	}
	
	public int getNRows() {
		return this.nRows;
	}
	
	public int getNCols() {
		return this.nCols;
	}
	
	public Cell[][] getCells() {
		return this.cells;
	}
	
	public boolean createShip(int x0, int y0, String orientation, Ship ship) {	
		boolean isCreated = false;
		
		if (this.isCorrectCoordinates(x0, y0)) {
			if (this.isShipCell(x0, y0)) {
				//System.out.println("Ya hay un barco en las coordenadas proporcionadas.");
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
		}
			
		if (isCreated) {
			//System.out.println("Barco creado correctamente.");
			ships.add(ship);
		} else {
			//System.out.println("No se ha podido crear el barco en las coordenadas fijadas. Prueba con datos nuevos.");
		}
		
		return isCreated;
	}
	
	public boolean isShipCell(int x, int y) {
		boolean isShip = false;
		if (this.isCorrectCoordinates(x, y)) {
			isShip = this.cells[x][y].isShip(x, y);
		}
		
		return isShip;
	}
	
	public boolean shoot(int x, int y) {
		boolean isDown = false;
		if (isCorrectCoordinates(x, y)) {
			if(cells[x][y].isShip(x, y)) {
				 cells[x][y].shootShip(x, y);
				 isDown = true;
			}
		}
		
		return isDown;
	}
	
	public boolean isEndGame() {
		boolean isTheEnd = true;
		for (int i = 0; i < ships.size() && isTheEnd; i++) {
			if (!ships.get(i).isSunk()) {
				isTheEnd = false;
			}
		}
		
		return isTheEnd;
	}
	
	public boolean isCorrectCoordinates(int x, int y) {
		return !(x < 0 || x > nCols - 1 || y < 0 || y > nRows - 1);
	}
}
