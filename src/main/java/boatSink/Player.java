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
		for(int i = 0; i < Constants.N_BOARD_ROWS_CELLS; i++) {
			for(int j = 0; j < Constants.N_BOARD_ROWS_CELLS; j++) {
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
		/* Prints column number header. */
		System.out.printf("%4s", 0);
		for (int x = 1; x < Constants.N_BOARD_ROWS_CELLS; x++) {
			System.out.printf("%3s", x);
		}
		
		System.out.println("");
		
		/* Shoots board. */
		for (int x = 0; x < Constants.N_BOARD_ROWS_CELLS; x++) {
			// Prints rows number header.
			System.out.printf("%-3s", x); 
			for (int y = 0; y < Constants.N_BOARD_ROWS_CELLS; y++) {
				System.out.printf("%-2s ", this.shootsBoard[x][y]);
			}
			System.out.println("");
		}
	
		return "";
	}
}
