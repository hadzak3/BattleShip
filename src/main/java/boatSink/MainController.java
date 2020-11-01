package boatSink;

import java.util.ArrayList;
import java.util.Scanner;


public final class MainController {
	private static Scanner scanner = new Scanner(System.in);
	private static Board board;
	private static ArrayList<Player> players;
	private static int playersCount = Constant.PLAYERS_COUNT;
	private static String input;
	
	/**
	 *  1. manda a crear el tablero
	 *  2. mientras la partida no ha finalizado:
	 *  	2a. pide al usuario las coordendas
     *   	2b. dispara en la coordenadas introducidas
     *   	2c. imprime el estado del tablero 
	 **/
	public static void main(String[] args) {	
		playOneGame(playersCount);
	}
	
	public final static void playOneGame(int playersCount) {
		initializeGame();
		int j = 0;
		while (!endOfGame()) {
			makePlay(j);
			j = (j + 1) % playersCount;
		}
		printWinner(j);
	}
	
	static void initializeGame() {
		board = new Board(Constant.N_BOARD_ROW_CELLS, playersCount);
		createPlayersAndShips();
	}
	
	/** 
	 * Hay 4 tipos de barcos diferentes:
	 *	1. barcos de una casilla
	 * 	2. barcos de dos casillas.
	 * 	3. barcos de tres casillas.
	 *  4. un barco de cuatro casillas.
	 **/
	static void createPlayersAndShips() {
		String playerName, shipOrientation; 
		int x, y;
		for (int j = 0; j < playersCount; j++) {
			do {
				System.out.println("Introduce el nombre del jugador número " + (j+1));
				input = scanner.nextLine();
			} while (!isCorrectPlayerName(input));
			playerName = input;
			
			Player player = new Player(playerName);
			board.addPlayer(player);
			
			for (int i = 1; i <= Constant.N_SHIPS; i++) {
				System.out.println("Creación del barco de " + i + " casilla(s).");
				
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
				System.out.println(board);
			}
		}
	}
	
	static void makePlay(int playerId) {
		String playerName = board.getPlayerNameById(playerId);
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
		
		board.shoot(playerId, x, y);
		System.out.println(board);
	}
	
	static boolean endOfGame() {
		assert board != null : "board is null";
		
		return board.isEndGame();
	}
	
	static void printWinner(int nextPlayerId) {
		// we have to get the prev player id because the j-index increases prematurely in the playOneGame method.
		int winnerId = (nextPlayerId - 1) % playersCount;
		System.out.println("El ganador es " + board.getPlayerNameById(winnerId));
	}
	
	static boolean isCorrectShipOrientation(String shipOrientation) {
		if (shipOrientation.equals(Constant.SHIP_HORIZONTAL)) {
			return true;
		}
		
		if (shipOrientation.equals(Constant.SHIP_VERTICAL)) {
			return true;
		}
		
		return false;
	}
	
	static boolean isCorrectCoordinate(String coordinate) {
		// TODO: Debe comprobar que no haya un barco ya posicionado en la coordenada dada
		
		int coord;
		try { 
			coord = Integer.parseInt(coordinate);
	    } catch (Exception e) { // the coordinate cannot be casted to an integer.
	    	return false;
	    }
		
		if (coord < 0 || coord > Constant.N_BOARD_ROW_CELLS-1) {
			return false;
		}
		
		return true;
	}
	
	static boolean isCorrectPlayerName(String playerName) {
		if(playerName.length() == 0) {
			return false;
		}
		return true;
	}

}
