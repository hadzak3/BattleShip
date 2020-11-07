package boatSink;

public class Player {

	private String name;
	private Board shipsBoard;
	private String shootsBoard[][];
	
	Player(String name, Board board) {
		this.name = name;
		this.shipsBoard = board;
		this.shootsBoard = new String[Constants.N_BOARD_ROWS_CELLS][Constants.N_BOARD_ROWS_CELLS];
		setAllShootsBoardWater();
	}
	
	private void setAllShootsBoardWater() {
		// TODO create attributes nRows and nCols to get by constructor
		for(int i = 0; i <= Constants.N_BOARD_ROWS_CELLS - 1; i++) {
			for(int j = 0; j <= Constants.N_BOARD_ROWS_CELLS - 1; j++) {
				this.shootsBoard[i][j] = "-"; // means water
			}
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public Board getBoard() {
		return this.shipsBoard;
	}
	
	public boolean isEndGame() {
		return this.shipsBoard.isEndGame();
	}
	
	/* Returns true if ship is down at given coordinates. */ 
	public boolean shoot(int x , int y) {
		boolean isDown = this.shipsBoard.shoot(x, y);
		if (isDown) {
			shootsBoard[x][y] = "o";
		} else {
			shootsBoard[x][y] = "x";
		}

		return (isDown);
	}
	
	@Override
	public String toString() {
		/* prints column numbers. */
		for (int x = 0; x <= Constants.N_BOARD_ROWS_CELLS - 1; x++) {
			if (x == 0) {
				System.out.printf("%4s", x);
			} else {
				System.out.printf("%3s", x);
			}
		}
		
		System.out.println("");
		
		/* Board */
		for (int x = 0; x <= Constants.N_BOARD_ROWS_CELLS - 1; x++) {
			System.out.printf("%-3s", x); // prints row numbers every row start
			for (int y = 0; y <= Constants.N_BOARD_ROWS_CELLS - 1; y++) {
				System.out.printf("%-2s ", this.shootsBoard[x][y]);
			}
			System.out.println("");
		}
	
		return "";
	}
}
