package boatSink;

public class Player {

	private String name;
	private Board shipsBoard;
	private String shootsBoard[][];
	
	Player(String name, Board board) {
		if(name == "" || board == null) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.shipsBoard = board;
		this.shootsBoard = new String[Constants.N_BOARD_ROWS_CELLS][Constants.N_BOARD_ROWS_CELLS];
		setAllShootsBoardWater();
	}
	Player(){
		this.name = "player";
		shipsBoard = new Board(Constants.N_BOARD_ROWS_CELLS, Constants.N_BOARD_ROWS_CELLS);
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
	
	public Board getBoard() {
		return this.shipsBoard;
	}
	
	public boolean isEndGame() {
		return this.shipsBoard.isEndGame();
	}
	
	/* Returns true if ship is down at given coordinates. */ 
	public boolean shoot(int x , int y) {
		if(x < 0 || x >= Constants.N_BOARD_ROWS_CELLS || y < 0 || y >= Constants.N_BOARD_ROWS_CELLS) {
			throw new IllegalArgumentException();
		}
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

	
		return "";
	}
}
