package boatSink;

import java.util.Scanner;

public final class MainController {

	
	/**
	 *  1. manda a crear el tablero
	 *  2. mientras la partida no ha finalizado
	 *  2a. pide al usuario las coordendas y:
     *   2b. dispara en la coordenadas introducidas, y dice al printer si se ha tocado un barco
     *   2c. llama al printer para mostrar el estado del tablero 
	 *  
	 **/
	public static void main(String[] args) {
		//Variables para construir los objetos*****************
		int numberOfShips;
		int x,y; //Coordenadas
		String nameShip;
		int sizeShip;
		int orientation;//0: horizontal, 1: vertical
		//**************************
		//Objeto scanner para pedir datos a usuario
		Scanner scanner = new Scanner(System.in);
		Printer printer = new Printer();
		//pedimos numero de casillas del tablero
		printer.askBoardCells();
		int nCells = scanner.nextInt();
		Board board = new Board(nCells);
		//pedimos numero de barcos
		printer.askNumberOfShips();
		numberOfShips = scanner.nextInt();
		//por cada barco pedimos los atributos
		for(int i = 0; i < numberOfShips; i++) {
			//Pedimos nombre de barco
			printer.askNameShip();
			nameShip = scanner.nextLine();
			//pedimos tamaño de barco
			printer.askLengthShip();
			sizeShip = scanner.nextInt();
			//Pedimos orientacion del barco (horizontal o vertical)
			printer.askOrientationShip();
			orientation = scanner.nextInt();
			//pedimos posicion del barco (x,y)
			//por cada casilla del barco pedimos coordenada
			for(int j = 0; j < sizeShip; j++) {
				printer.askPositionShip();
				printer.askX();
				x = scanner.nextInt();
				printer.askY();
				y = scanner.nextInt();
				board.setShip(nameShip, orientation, x, y);
				
			}


		}
	}

}
