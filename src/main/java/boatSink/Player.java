package boatSink;

public class Player {

	private String name;
	private Board board;
	
	Player(String name, Board board) {
		this.name = name;
		this.board = board;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public void shoot (int x , int y) {
		this.board.shoot(x, y);
	}
}
