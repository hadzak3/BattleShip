package boatSink;


public class Board {

	private Cell arrayOfCells[][];
	
	/**
	 *  comprueba si es agua o tocado:
 	 *  si es tocado comprueba si se ha hundido el barco
 	 *  
	 * @param x
	 * @param y
	 */
	
	Board(int numberOfCells){
		System.out.println("Inicializando el tablero con " + numberOfCells + " casillas");
		arrayOfCells = new Cell[numberOfCells][numberOfCells];
	}
	public void setShip(String name, int orientation, int x, int y) {
		System.out.println("Seteando barco en la posicion: " + x + y);
		arrayOfCells[x][y].setX(x);
		arrayOfCells[x][y].setY(y);
		Ship s = new Ship(name,orientation);
		arrayOfCells[x][y].setShip(s);
	}
	private void shoot(int x, int y) {
		/* Pseudo code :
		boolean isDown = true;
		boolean isShip = false;
		for (cell : cells)
		 {
		 	if (cell.isShip()) {
				cell.ship.isDown = true;
				isShip = true;

			    String ship_name = cell.getShipName();
				for (cell : cells)
				{
					 if (cell.getShipName() != ship_name) {
				        isDown = false;
				   		break;
				   }
				}
			}
		 }

		return isShip; */
	}
}
