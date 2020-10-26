package boatSink;

public class Printer {

    /**
     *  Se encarga de printar todo el tablero.
     *  Representa con el simbolo "+" si el barco está hundido o no.
     **/
	
	public void showBoard() {
		System.out.println("*se muestra tablero*");
	}
	public void askNumberOfShips(int nCells) {
		System.out.println("¿Cuántos barcos quieres insertar?" + " maximo " + nCells + " barcos");
	}
	public void askPositionShip(String nombre) {
		System.out.println("Donde quieres colocar el barco " + nombre + "?");
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
		System.out.println("Introduce el tamaño del nuevo barco: ");
	}
	public void askOrientationShip() {
		System.out.println("Introduce la orientacion del barco 0: horizontal, 1: vertial");
	}
   

}