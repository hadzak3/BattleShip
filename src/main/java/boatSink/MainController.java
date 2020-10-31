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
		int numberOfShips, x,y,sizeShip, orientation, nCells;
		String nameShip;
		//**************************
		//Objeto scanner para pedir datos a usuario
		Scanner scanner = new Scanner(System.in);
		Printer printer = new Printer();
		//El tablero siempre sera de 10x10 casillas
		nCells = 10;
		
		
		Board board = new Board(nCells);
		
		//Preguntar a usuario por ubicacion de barcos:
		/*	1 barco que ocupa 4 cuadrados.
		 	2 barcos de tres cuadros
		 	3 barcos de 2 cuadros
			4 barcos de un solo cuadro
		*/
		
		System.out.println("IA coloca sus barcos");
		
		String input;

		do {
			System.out.println("Nombre de barco que ocupa 4 casillas: ");
			input = scanner.nextLine();
		}while(!isCorrectShipName(input));
		nameShip = input;
		
		do {
			System.out.println("Orientacion del barco: " + nameShip + "(0: horizontal, 1:vertical)");
			input = scanner.nextLine();
			
		}while(!isCorrectOrientation(input));
		orientation = Integer.parseInt(input);
		

		
		
		for(int i = 0; i < 4; i++) {
			System.out.println("Introduce coordenada x: ");
			do {
				while(!scanner.hasNextInt()) {
					System.out.println("Introduce un numero valido");
					scanner.next();
				}
				x = scanner.nextInt();
				if(x < 0 || x > 9) {
					System.out.println("Introduce una coordenada valida (0-9)");
				}
			}while(x < 0 || x > 9);
			
			System.out.println("Introduce coordenada y: ");
			do {
				while(!scanner.hasNextInt()) {
					System.out.println("Introduce un numero valido");
					scanner.next();
				}
				y = scanner.nextInt();
				if(y < 0 || y > 9) {
					System.out.println("Introduce una coordenada valida (0-9)");
				}
			}while(y < 0 || y > 9);
			
			board.setShip(4, nameShip, orientation, x, y);
		}

		while(!board.finishGame()) {
			System.out.println("Tablero listo");
			//mientras no se haya acabado la partida
			printer.showBoard();
			System.out.println("Donde quieres disparar?");
			printer.askX();
			x = scanner.nextInt();
			printer.askY();
			y = scanner.nextInt();
			System.out.println("Disparando en " + x + ", "+ y);
			board.shoot(x,y);
		}
	}
	static boolean isCorrectShipName(String nameShip) {
		if(nameShip.length() == 0) {
			return false;
		}
		return true;
	}
	static boolean isCorrectOrientation(String orientation) {
		String horizontal, vertical;
		horizontal = Integer.toString(Constant.SHIP_HORIZONTAL);
		vertical = Integer.toString(Constant.SHIP_VERTICAL);
		if(orientation.equals(horizontal)) {
			return true;
		}
		if(orientation.equals(vertical)) {
			return true;
		}
		return false;
	}
}
