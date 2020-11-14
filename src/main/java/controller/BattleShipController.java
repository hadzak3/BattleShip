package controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import model.Board;
import model.OneCellShip;
import model.Player;
import model.Ship;
import utils.Constants;
import view.BattleShipView;


public class BattleShipController {
	
	private ArrayList<Player> players;
	private BattleShipView battleShipView;
	
	private final int playersCount = Constants.N_PLAYERS;

	public BattleShipController () {
		players = new ArrayList<>();
		battleShipView = new BattleShipView();
	}
	
	public void playOneGame() {
		initializeGame();
		int j = 0;
		while (!endOfGame()) {
			j = makePlay(j);
		}
		printWinner(j);
	}
	
	private void initializeGame() {
		players = new ArrayList<>();
		ArrayList<Ship> ships = new ArrayList<>();
		
		ships.add(new OneCellShip());
		//ships.add(new TwoCellShip());
		//ships.add(new ThreeCellShip());
		//ships.add(new FourCellShip());
		
		String name;
		for (int j = 1; j <= Constants.N_PLAYERS; j++) { 
			battleShipView.showSectionLine();
			battleShipView.showMsg("inicialización del jugador " + j);
			
			do {
				name = battleShipView.askPlayerName();
			} while (!this.isCorrectPlayerName(name));
			
			Player player = new Player(name);
			
			String x, y, orientation = "";
			for (Ship ship : ships) {
				do {
					battleShipView.showPlayerShipsBoard(player);
					
					battleShipView.showMsg("creación del barco de " + ship.getNCells() + " casilla(s)");
					
					do {
						orientation = battleShipView.askShipOrientation();
					} while(!isCorrectShipOrientation(orientation));
					
					do {
						x = battleShipView.askCoordinateX();
					} while (!isCorrectInputCoordinate(x)); 
					
					do {
						y = battleShipView.askCoordinateY();
					} while (!isCorrectInputCoordinate(y));
					
				  /* Tries to create ship in given coordinates. */
				} while (!player.createShip(Integer.parseInt(x), Integer.parseInt(y), orientation, ship)); 
			}
			
			players.add(player);
			
			battleShipView.showPlayerShipsBoard(player);
			
			waitBetweenChangingPlayerTurn(5);
		}
	}	
	
	private int makePlay(int currentPlayerId) {
		battleShipView.showSectionLine();
		
		battleShipView.showMsg("dispara el jugador " +  players.get(currentPlayerId).getName());
		
		int oponentPlayerId = (currentPlayerId + 1) % playersCount;
		Player oponentPlayer = players.get(oponentPlayerId);
		
		battleShipView.showPlayerShootsBoard(oponentPlayer);
		
		String x, y;
		do {
			x = battleShipView.askCoordinateX();
		} while (!isCorrectInputCoordinate(x));
		
		do {
			y = battleShipView.askCoordinateY();
		} while (!isCorrectInputCoordinate(y)); 
		
		battleShipView.showSubSectionLine();
		
		battleShipView.showMsg("disparando en " + x + ", "+ y);

		boolean isDown = oponentPlayer.shoot(Integer.parseInt(x), Integer.parseInt(y));
		
		int nextPlayerIdToPlay = currentPlayerId; 
		/* The player's turn changes only if the current player has not down a ship. */
		if (!isDown) { 
			battleShipView.showMsg("agua");
			
			nextPlayerIdToPlay = oponentPlayerId;
			battleShipView.showPlayerShootsBoard(oponentPlayer);
			waitBetweenChangingPlayerTurn(5);
		}
		
		return nextPlayerIdToPlay;
	}
	
	private void waitBetweenChangingPlayerTurn(int seconds) {
		battleShipView.showMsg("Cambiando turno en ");
		
		try {
			for(int i = seconds; i > 0; i--) {
				battleShipView.showMsg(i + " seconds...");
				TimeUnit.SECONDS.sleep(1);
			}
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	
	private boolean endOfGame() {
		boolean isTheEnd = true;
		for (int j = 0; j < playersCount && isTheEnd; j++) {
			if (!players.get(j).isEndGame()) {
				isTheEnd = false;
			}
		}
	
		return isTheEnd;
	}
	
	private void printWinner(int playerId) {
		battleShipView.showSectionLine();
		
		Player winner = players.get(playerId);
		System.out.println("El ganador es " + winner.getName());
		
		battleShipView.showSectionLine();
	}
	
	public boolean isCorrectShipOrientation(String shipOrientation) {		
		return (shipOrientation.equals(Constants.SHIP_VERTICAL) || shipOrientation.equals(Constants.SHIP_HORIZONTAL));
	}
	
	public boolean isCorrectInputCoordinate(String coordinate) {
		int coord;
		try {
			coord = Integer.parseInt(coordinate);
	    } catch (Exception e) {
	    	return false;
	    }
		
		if (coord < 0 || coord > Constants.N_BOARD_ROWS_CELLS - 1) {
			return false;
		}
		
		return true;
	}
	
	public boolean isCorrectPlayerName(String playerName) {
		if(playerName.length() == 0) {
			return false;
		}
		return true;
	}

}
