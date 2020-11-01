package boatSink;

import java.util.ArrayList;

public class Board {

	private Cell cells[][];
	private int boardSize;
	private ArrayList<Player> players;
	
	//private ArrayList<Ship> shipPlayer1;
	//private ArrayList<Ship> shipIA;
	
	/**
	 *  comprueba si es agua o tocado:
 	 *  si es tocado comprueba si se ha hundido el barco
 	 *  
	 * @param x
	 * @param y
	 */
	
	Board (int nCells, int nPlayers) {
		players = new ArrayList<>(nPlayers);
		boardSize = nCells;
		cells = new Cell[boardSize][boardSize];
		setAllCellsWater();
	}
	
	private void setAllCellsWater() {
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				this.cells[i][j] = new Cell(i, j, null);
			}
		}
	}
	
	public void setShip(int playerId, int shipSize, String orientation, int x, int y) {
		assert (players.size() >= playerId + 1) : "player with id " + playerId + " not added.";
		
		System.out.println("Seteando parte del barco en la posicion: (" +  x + ", "+ y +")");
		
		Ship ship = new Ship(shipSize, orientation, playerId);
		cells[x][y].setShip(ship);
		players.get(playerId).addShip(cells[x][y]);
		
		System.out.println("Barco insertado correctamente");
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public String getPlayerNameById(int playerId) {
		return players.get(playerId).getName();
	}
	
	public void shoot(int playerId, int x, int y) {
		// TODO: debe disparar sobre el tablero del otro jugador.
		if(cells[x][y].isShip()) {
			cells[x][y].shootShip();
		}
		else {
			System.out.println("Agua");
		}
	}
	
	public boolean isEndGame() {
		// TODO: Debe comprobar si los barcos del otro jugador están hundidos.
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				if(cells[i][j].isShip()) {
					if(!cells[i][j].isShipDown()) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		int x;
		/* Columns header */
		for (x = 0; x <= this.cells.length-1; x++) {
			if (x == 0) {
				System.out.printf("%4s", x);
			} else {
				System.out.printf("%3s", x);
			}
		}
		
		System.out.println("");
		
		/* Board */
		for (x = 0; x <= this.cells.length-1; x++) {
			System.out.printf("%-3s", x); // Every row start print the row header;
			for (int y = 0; y <= this.cells.length-1; y++) {
				System.out.printf("%-2s ", this.cells[x][y]);
			}
			System.out.println("");
		}
	
		return "";
	}
	
}
