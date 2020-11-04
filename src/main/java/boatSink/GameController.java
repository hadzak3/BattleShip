package boatSink;

import java.util.ArrayList;
import java.util.Scanner;
import boatSink.Ship;

/* TODO: First create players ships, and then add ships to players boards. */
public final class GameController {
	
	private static int playersCount = Constants.N_PLAYERS;
	private static ArrayList<Player> players;
	private static String input;
	private static Scanner scanner = new Scanner(System.in);
	
	/** 
	 * Método inicial del juego:
	 *  1. manda a crear el tablero
	 *  2. mientras la partida no ha finalizado:
	 *  	2a. pide al usuario las coordendas
     *   	2b. dispara en la coordenadas introducidas
     *   	2c. imprime el estado del tablero 
	 **/
	public static void main(String[] args) {	
		playOneGame(playersCount);
	}
	
	public static void playOneGame(int playersCount) {
		initializeGame();
		int j = 0;
		while (!endOfGame()) {
			if (!makePlay(j)) { /* If ship is down keep same player id. */
				j = (j + 1) % playersCount;
			} 
		}
		printWinner(j);
	}
	
	public static void initializeGame() {
		String playerName;
		
		players = new ArrayList<>();
		ArrayList<Ship> gameShips = new ArrayList<>();
		gameShips.add(new OneCellShip());
		gameShips.add(new TwoCellShip());
		//gameShips.add(new ThreeCellShip());
		//gameShips.add(new FourCellShip());
		
		for (int j = 0; j < playersCount; j++) { 
			do {
				System.out.println("Introduce el nombre del jugador número " + (j + 1));
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
					} while (!isCorrectCoordinate(input)); 
					x = Integer.parseInt(input);
					
					do {
						System.out.println(playerName + ", introduce la coordenada y del barco de " + (i + 1) + " casillas (0-9)");
						input = scanner.nextLine();
					} while (!isCorrectCoordinate(input));
					y = Integer.parseInt(input);
				} while (!board.createShip(x, y, shipOrientation, gameShips.get(i))); /* Tries to create ship cells in given coordinates. */
			}
			
			Player player = new Player(playerName, board);
			players.add(player);
			
			System.out.println(player.getBoard());
		}
	}	
	
	public static boolean makePlay(int currentPlayerId) {
		Player currentPlayer = players.get(currentPlayerId);
		
		int oponentPlayerID = (currentPlayerId + 1) % playersCount;
		Player oponentPlayer = players.get(oponentPlayerID);
		
		System.out.println(oponentPlayer);
		
		String playerName = currentPlayer.getName();
		do {
			System.out.println(playerName + ", introduce la coordenada x de donde quieras disparar (0-9)");
			input = scanner.nextLine();
		} while (!isCorrectCoordinate(input));
		int x = Integer.parseInt(input);
		
		do {
			System.out.println(playerName + ", introduce la coordenada y de donde quieras disparar (0-9)");
			input = scanner.nextLine();
		} while (!isCorrectCoordinate(input)); 
		int y = Integer.parseInt(input);

		System.out.println("Disparando en " + x + ", "+ y);
		
		/* TODO Revisar caso que el jugador dispara en en un barco contiguo pero muestra tocado en [0,0]. */
		Boolean isDown = oponentPlayer.shoot(x, y);
		
		//System.out.println(oponentPlayer.getBoard());
		
		return isDown;
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
	
	public static void printWinner(int nextPlayerId) {
		System.out.println("El ganador es " + players.get(nextPlayerId).getName());
	}
	
	public static boolean isCorrectShipOrientation(String shipOrientation) {		
		return (shipOrientation.equals(Constants.SHIP_VERTICAL) || shipOrientation.equals(Constants.SHIP_HORIZONTAL));
	}
	
	public static boolean isCorrectCoordinate(String coordinate) {
		int coord;
		try { 
			coord = Integer.parseInt(coordinate);
	    } catch (Exception e) { // the coordinate cannot be casted to an integer.
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

}
