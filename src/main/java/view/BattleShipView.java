package view;

import java.util.Scanner;

import model.Board;
import model.Cell;
import model.Player;
import utils.Constants;

/*
* It contains the output messages.
* */
public class BattleShipView {

	private String input;
	private Scanner scanner = new Scanner(System.in);
	
	public BattleShipView() {}
	
	public void showMsg(String msg) {
		System.out.println(msg);
	}
	
	public void showSectionLine() {
		this.showMsg("################################################");
	}
	
	public void showSubSectionLine() {
		this.showMsg("#####################");
	}
	
	public String askPlayerName() {
		this.showSubSectionLine();
		
		System.out.println("introduce el nombre");
		input = scanner.nextLine();
		
		return input;
	}
	
	public String askShipOrientation() {	
		this.showSubSectionLine();
		
		System.out.println("introduce la orientacion (" + Constants.SHIP_HORIZONTAL 
				+ ")orizontal, o (" + Constants.SHIP_VERTICAL + ")ertical: ");
		input = scanner.nextLine();
		
		return input;
	}
	
	public String askCoordinateX() {
		this.showSubSectionLine();
		
		System.out.println("introduce la coordenada x (0," + (Constants.N_BOARD_ROWS_CELLS - 1) + "): ");
		input = scanner.nextLine();

		return input;
	}
	
	public String askCoordinateY() {
		this.showSubSectionLine();
		
		System.out.println("introduce la coordenada y (0," + (Constants.N_BOARD_ROWS_CELLS - 1) + "): ");
		input = scanner.nextLine();
		
		return input;
	}
	
	public void showPlayerShipsBoard(Player player) {
		Board shipsBoard = player.getShipsBoard();
		int nCols = shipsBoard.getNCols();
		int nRows = shipsBoard.getNRows();
		Cell[][] cells = shipsBoard.getCells();

		this.showSubSectionLine();
		
		this.showMsg("tablero de barcos del jugador " + player.getName() + ": ");
		
		/* Prints columns number header. */
		System.out.printf("%4s", 0);
		for (int x = 1; x < nCols; x++) {
			System.out.printf("%3s", x);
		}
		
		System.out.println("");
		
		/* Main board. */
		for (int x = 0; x < nRows; x++) {
			// prints rows number header.
			System.out.printf("%-3s", x); 
			for (int y = 0; y < nCols; y++) {
				System.out.printf("%-2s ", cells[x][y].getState());
			}
			System.out.println("");
		}
	}
	
	public void showPlayerShootsBoard(Player player) {
		String[][] shootBoard = player.getShootsBoard();
		
		this.showSubSectionLine();
		
		this.showMsg("tablero de disparos: ");
		
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
				System.out.printf("%-2s ", shootBoard[x][y]);
			}
			System.out.println("");
		}
	}
	
}
