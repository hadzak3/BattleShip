package boatSink;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class GameController {
	
	private static int playersCount = Constants.N_PLAYERS;
	private static ArrayList<Player> players;
	private static String input;
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {	
		playOneGame(playersCount);
	}
	
	public static void playOneGame(int playersCount) {
		initializeGame();
		int j = 0;
		while (!endOfGame()) {
			j = makePlay(j);
		}
		printWinner(j);
	}
	
	public static void initializeGame() {
		String playerName;
		
		players = new ArrayList<>();
		ArrayList<Ship> gameShips = new ArrayList<>();
		gameShips.add(new OneCellShip());
		gameShips.add(new TwoCellShip());
		gameShips.add(new ThreeCellShip());
		gameShips.add(new FourCellShip());
		
		for (int j = 0; j < playersCount; j++) { 
			do {
				System.out.println("\nIntroduce el nombre del jugador número " + (j + 1));
				input = scanner.nextLine();
			} while (!isCorrectPlayerName(input));
			playerName = input;

			Board board = new Board(Constants.N_BOARD_ROWS_CELLS, Constants.N_BOARD_ROWS_CELLS);
			
			int x, y;
			String shipOrientation = "";
			for (int i = 0; i <= gameShips.size() - 1; i++) {
				do {
					System.out.println(board);
					
					if (i > 0) {
						do {
							System.out.println(playerName + ", introduce la orientacion del barco de " + (i + 1)
									+ " casillas (" + Constants.SHIP_HORIZONTAL + ": horizontal, " + Constants.SHIP_VERTICAL + ": vertical)");
							input = scanner.nextLine();
						} while(!isCorrectShipOrientation(input));
						shipOrientation = input;
					}
					
					do {
						System.out.println(playerName +  ", introduce la coordenada x del barco de " + (i + 1) + " casillas (0-9)");
						input = scanner.nextLine();
					} while (!isCorrectInputCoordinate(input)); 
					x = Integer.parseInt(input);
					
					do {
						System.out.println(playerName + ", introduce la coordenada y del barco de " + (i + 1) + " casillas (0-9)");
						input = scanner.nextLine();
					} while (!isCorrectInputCoordinate(input));
					y = Integer.parseInt(input);
				} while (!board.createShip(x, y, shipOrientation, gameShips.get(i))); /* Tries to create ship cells in given coordinates. */
			}
			
			Player player = new Player(playerName, board);
			players.add(player);
			System.out.println(player.getBoard());
			
			waitBetweenChangingPlayerTurn();
		}
	}	
	
	public static int makePlay(int currentPlayerId) {
		Player currentPlayer = players.get(currentPlayerId);
		
		int oponentPlayerId = (currentPlayerId + 1) % playersCount;
		Player oponentPlayer = players.get(oponentPlayerId);
		
		System.out.println("\nTablero de disparos de " + currentPlayer.getName());
		System.out.println(oponentPlayer);
		
		do {
			System.out.println(currentPlayer.getName() + ", introduce la coordenada x (0-9) de donde quieras disparar al jugador " 
					+ oponentPlayer.getName());
			input = scanner.nextLine();
		} while (!isCorrectInputCoordinate(input));
		int x = Integer.parseInt(input);
		
		do {
			System.out.println(currentPlayer.getName() + ", introduce la coordenada y  (0-9) de donde quieras disparar al jugador " 
					+ oponentPlayer.getName());
			input = scanner.nextLine();
		} while (!isCorrectInputCoordinate(input)); 
		int y = Integer.parseInt(input);

		System.out.println("Disparando en " + x + ", "+ y);
		boolean isDown = oponentPlayer.shoot(x, y);
		
		int nextPlayerIdToPlay = currentPlayerId; 
		/* The player's turn changes only if the current player has not shoot down a ship. */
		if (!isDown) { 
			nextPlayerIdToPlay = oponentPlayerId;
			System.out.println("Tablero de disparos de " + currentPlayer.getName());
			System.out.println(oponentPlayer);
			waitBetweenChangingPlayerTurn();
		}
		
		return nextPlayerIdToPlay;
	}
	
	public static void waitBetweenChangingPlayerTurn() {
		System.out.println("Cambiando turno en ");
		try {
			for(int i = 3; i > 0; i--) {
				System.out.println(i + " seconds...");
				TimeUnit.SECONDS.sleep(1);
			}
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	
	public static boolean endOfGame() {
		assert players != null : "players is null";
		
		boolean isTheEnd = true;
		for (int j = 0; j < playersCount && isTheEnd; j++) {
			if (!players.get(j).isEndGame()) {
				isTheEnd = false;
			}
		}
	
		return isTheEnd;
	}
	
	public static void printWinner(int playerId) {
		Player winner = players.get(playerId);
		System.out.println("El ganador es " + winner.getName());
	}
	
	public static boolean isCorrectShipOrientation(String shipOrientation) {		
		return (shipOrientation.equals(Constants.SHIP_VERTICAL) || shipOrientation.equals(Constants.SHIP_HORIZONTAL));
	}
	
	public static boolean isCorrectInputCoordinate(String coordinate) {
		int coord;
		try {
			coord = Integer.parseInt(coordinate);
	    } catch (Exception e) {
	    	return false;
	    }
		
		if (coord < 0 || coord > Constants.N_BOARD_ROWS_CELLS - 1) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isCorrectPlayerName(String playerName) {
		if(playerName.length() == 0) {
			return false;
		}
		return true;
	}

	//Setters:
	public void setPlayers(ArrayList<Player> players) {
		GameController.players = players;
	}
}
