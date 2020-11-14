package model;

import utils.Constants;

public class Player {

	private String name;
	private Board shipsBoard;
	private String shootsBoard[][];
	
	public Player() {
		this.name = "player";
		this.shipsBoard = new Board(Constants.N_BOARD_ROWS_CELLS, Constants.N_BOARD_ROWS_CELLS);
		this.shootsBoard = new String[Constants.N_BOARD_ROWS_CELLS][Constants.N_BOARD_ROWS_CELLS];
		setAllShootsBoardWater();
	}
	
	public Player(String name) {
		if (!this.isCorrectName(name)) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.shipsBoard = new Board(Constants.N_BOARD_ROWS_CELLS, Constants.N_BOARD_ROWS_CELLS);
		this.shootsBoard = new String[Constants.N_BOARD_ROWS_CELLS][Constants.N_BOARD_ROWS_CELLS];
		setAllShootsBoardWater();
	}
	
	public String[][] setAllShootsBoardWater() {
		// TODO create attributes nRows and nCols to get by constructor
		for(int i = 0; i < Constants.N_BOARD_ROWS_CELLS; i++) {
			for(int j = 0; j < Constants.N_BOARD_ROWS_CELLS; j++) {
				this.shootsBoard[i][j] = "-"; // means water
			}
		}
		return this.shootsBoard;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Board getShipsBoard() {
		return this.shipsBoard;
	}
	
	public String[][] getShootsBoard() {
		return this.shootsBoard;
	}
	
	public boolean isEndGame() {
		return this.shipsBoard.isEndGame();
	}
	
	public boolean createShip(int x, int y, String orientation, Ship ship) {
		boolean isCreated = false;
		if (isCorrectCoordinates(x, y)) {
			isCreated = this.shipsBoard.createShip(x, y, orientation, ship);
		}
		
		return isCreated;
	}
	
	/**
	 *  Returns true if a ship is down at given coordinates. 
	 **/ 
	public boolean shoot(int x , int y) {
		boolean isDown = false;
		if (isCorrectCoordinates(x, y)) {			
			isDown = this.shipsBoard.shoot(x, y);
			if (isDown) {
				shootsBoard[x][y] = "o";
			} else {
				shootsBoard[x][y] = "x";
			}
		} 
		
		return (isDown);
	}
	
	public boolean isCorrectName(String name) {
		return !name.equals("");
	}
	
	public boolean isCorrectBoard(Board board) {
		return board != null;
	}
	
	public boolean isCorrectCoordinates(int x, int y) {
		return !(x < 0 || x > Constants.N_BOARD_ROWS_CELLS - 1 || y < 0 || y > Constants.N_BOARD_ROWS_CELLS - 1);
	}
}
