package boatSink;

import java.util.ArrayList;
import java.util.Scanner;

/* TODO: First create players ships, and then add ships to players boards. */
public final class GameController {
	
	private static Board board;
	private static int playersCount = Constants.PLAYERS_COUNT;
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
		//playOneGame(playersCount);
	}
	
	public static void playOneGame(int playersCount) {
		initializeGame();
		int j = 0;
		while (!endOfGame()) {
			makePlay(j);
			j = (j + 1) % playersCount;
		}
		printWinner(j);
	}
	
	public static void initializeGame() {
		//board = new Board(Constants.N_BOARD_ROW_CELLS, Constants.N_BOARD_ROW_CELLS, playersCount);
		createPlayersAndShips();
	}
	
	/** 
	 * Hay 4 tipos de barcos diferentes:
	 *	1. barcos de una casilla
	 * 	2. barcos de dos casillas.
	 * 	3. barcos de tres casillas.
	 *  4. un barco de cuatro casillas.
	 **/
	public static void createPlayersAndShips() {
		String playerName, shipOrientation; 
		int x, y;
		/*for (int j = 0; j < playersCount; j++) {
			do {
				System.out.println("Introduce el nombre del jugador número " + (j+1));
				input = scanner.nextLine();
			} while (!isCorrectPlayerName(input));
			playerName = input;
			
			for (int i = 1; i <= Constants.N_SHIPS; i++) {
				System.out.println("Creación del barco de " + i + " casilla(s).");
				
				if (i == 1) {
					createShip(i, Constants.ONE_CELL_SHIPS);
				} else if (i == 2) {
					createShip(i, Constants.TWO_CELL_SHIPS);
				} else if (i == 3) {
					createShip(i, Constants.THREE_CELL_SHIPS);
				} else if (i == 4) {
					createShip(i, Constants.FOUR_CELL_SHIPS);
				} else {
					System.out.println("Not implemented ships of " + i + " cells");
				}
				
				Player player = new Player(playerName, board);
				players.add(player);
				
				System.out.println(player.getBoard());
			}
		}*/
	}
	
	public static void createShip(int type) {
		/*for (int k = 1; k < nShipCells; k++) {
			do {
				System.out.println(playerName + ", introduce la orientacion del barco " + i 
						+ " (" + Constant.SHIP_HORIZONTAL + ": horizontal, " + Constant.SHIP_VERTICAL + ": vertical)");
				input = scanner.nextLine();
			} while(!isCorrectShipOrientation(input));
			shipOrientation = input;

			do {
				System.out.println(playerName +  ", introduce la coordenada x del barco " + i + " (0-9)");
				input = scanner.nextLine();
			} while (!isCorrectCoordinate(input)); 
			x = Integer.parseInt(input);
			
			do {
				System.out.println(playerName + ", introduce la coordenada y del barco " + i + " (0-9)");
				input = scanner.nextLine();
			} while (!isCorrectCoordinate(input));
			y = Integer.parseInt(input);
			
			board.setShip(j, i, shipOrientation, x, y);
		}*/
	}
	
	public static void makePlay(int currentPlayerId) {
		/* Get next player id. */
		int oponentPlayerID = (currentPlayerId + 1) % playersCount;
		Player oponentPlayer = players.get(oponentPlayerID);
		
		String playerName = oponentPlayer.getName();
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
		
		//oponentPlayer.shoot(oponentPlayer, x, y); // TODO se le debe pasar al player
		System.out.println(board);
	}
	
	public static boolean endOfGame() {
		assert board != null : "board is null";
		
		return board.isEndGame();
	}
	
	public static void printWinner(int nextPlayerId) {
		// we have to get the prev player id because the j-index increases prematurely in the playOneGame method.
		int winnerId = (nextPlayerId - 1) % playersCount;
		//System.out.println("El ganador es " + board.getPlayerNameById(winnerId));
	}
	
	public static boolean isCorrectShipOrientation(String shipOrientation) {
		if (shipOrientation.equals(Constants.SHIP_HORIZONTAL)) {
			return true;
		}
		
		if (shipOrientation.equals(Constants.SHIP_VERTICAL)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isCorrectCoordinate(String coordinate) {
		// TODO: Debe comprobar que no haya un barco ya posicionado en la coordenada dada
		
		int coord;
		try { 
			coord = Integer.parseInt(coordinate);
	    } catch (Exception e) { // the coordinate cannot be casted to an integer.
	    	return false;
	    }
		
		if (coord < 0 || coord > Constants.N_BOARD_ROW_CELLS-1) {
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
