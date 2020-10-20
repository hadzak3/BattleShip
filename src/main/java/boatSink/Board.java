package boatSink;

public class Board {
	
	private Cell cells;
	
	/**
	 *  comprueba si es agua o tocado:
 	 *  si es tocado comprueba si se ha hundido el barco
 	 *  
	 * @param x
	 * @param y
	 */
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
