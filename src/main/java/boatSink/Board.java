package boatSink;


public class Board {

	private Cell arrayOfCells[][];
	int sizeOfBoard;
	
	/**
	 *  comprueba si es agua o tocado:
 	 *  si es tocado comprueba si se ha hundido el barco
 	 *  
	 * @param x
	 * @param y
	 */
	
	Board(int numberOfCells){
		//inicializamos las celdas del tablero
		System.out.println("Inicializando el tablero con " + numberOfCells + " por " + numberOfCells + " casillas" );
		sizeOfBoard = numberOfCells;
		arrayOfCells = new Cell[numberOfCells][numberOfCells];
		for(int i = 0; i < numberOfCells; i++) {
			for(int j = 0; j < numberOfCells; j++) {
				arrayOfCells[i][j] = new Cell();
			}
		}
	}
	public boolean setShip(int sizeOfShip, String name, int orientation, int x, int y) {
		System.out.println("Seteando parte del barco en la posicion: (" +  x + ", "+ y +")");
		
		arrayOfCells[x][y].setX(x);
		arrayOfCells[x][y].setY(y);
		Ship s = new Ship(sizeOfShip, name,orientation);
		arrayOfCells[x][y].setShip(s);
		System.out.println("Barco insertado correctamente");
		return true;
	}
	public void shoot(int x, int y) {
		
		if(arrayOfCells[x][y].getShip() != null) {
			arrayOfCells[x][y].getShip().shootShip();
			System.out.println("Barco " + arrayOfCells[x][y].getShip().getName() + " tocado");
		}
		else {
			System.out.println("Agua");
		}
		
	}
}
