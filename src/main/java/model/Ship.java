package model;

import utils.Constants;

public abstract class Ship {
	
	private int nCells;
	private int health;
	private boolean down[][];

	Ship(int health){
		this.nCells = health;
		this.health = health; 
		this.down = new boolean[Constants.N_BOARD_ROWS_CELLS][Constants.N_BOARD_ROWS_CELLS];
		setAllShipsDownFalse();
	}
	
	public int getNCells() {
		return nCells;
	}
	
	public void setHealth(int health) {
		if (isCorrectHealth(health)) {
			this.health = health;
		}
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public boolean[][] setAllShipsDownFalse() {
		for(int i = 0; i < Constants.N_BOARD_ROWS_CELLS; i++) {
			for(int j = 0; j < Constants.N_BOARD_ROWS_CELLS; j++) {
				this.down[i][j] = false;
			}
		}
		return this.down;
	}
	
	public void setDown(int x, int y) {
		if(isCorrectCoordinates(x,y)) {
			down[x][y] = true;
		}
	}
	
	public boolean isDown(int x, int y) {
		boolean result = false;
		if(isCorrectCoordinates(x,y)){
			result = this.down[x][y];
		}
		
		return result;
	}
	
	public boolean isSunk() {
		return (this.health == 0);
	}
	public void setSunk() {
		health = 0;
	}
	public void shoot(int x, int y) {
		if (isCorrectCoordinates(x, y)){
			this.down[x][y] = true;
			this.health--;
			if (this.health == 0) {
				System.out.println("Barco de " + this.nCells + " casilla(s) hundido.");
			} else {
				System.out.println("Barco de " + this.nCells + " casilla(s) tocado.");
			}
		}
	}
	
	public boolean isCorrectHealth(int health) {
		return health >= 0;
	}
	
	public boolean isCorrectCoordinates(int x, int y) {
		return !(x < 0 || x > Constants.N_BOARD_ROWS_CELLS -1 || y < 0 || y > Constants.N_BOARD_ROWS_CELLS -1);
	}
}
