package boatSink;

public class Printer {

    /**
     *  Se encarga de printar todo el tablero.
     *  Representa con el simbolo "+" si el barco está hundido o no.
     **/
	public void askBoardCells() {
		System.out.println("¿De cuántas casillas quieres el tablero?");
	}
	public void askNumberOfShips() {
		System.out.println("¿Cuántos barcos quieres insertar?");
	}
	public void askPositionShip() {
		System.out.println("Introduce cordenadas del tablero para introducir un barco");
	}
	public void askX() {
		System.out.println("Introduce coordenada x: ");
	}
	public void askY() {
		System.out.println("Introduce coordenada y: ");
	}
	public void askNameShip() {
		System.out.println("Introduce el nombre del barco: ");
	}
	public void askLengthShip() {
		System.out.println("Introduce el tamaño del barco: ");
	}
	public void askOrientationShip() {
		System.out.println("Introduce la orientacion del barco 0: horizontal, 1: vertial");
	}
    public void printBoard(Board board) {
    	
    }
}