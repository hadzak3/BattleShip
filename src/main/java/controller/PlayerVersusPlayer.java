package controller;

import java.util.ArrayList;

import Constants.Constants;
import model.OneCellShip;
import model.Player;
import model.Ship;


public class PlayerVersusPlayer extends Game {

	public PlayerVersusPlayer() {
		super();
	}
	
	@Override
	public void initializeGame() {
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
					
				  /* if possible, creates a ship in the coordinates provided. */
				} while (!player.createShip(Integer.parseInt(x), Integer.parseInt(y), orientation, ship)); 
			}
			
			this.players.add(player);
			this.battleShipView.showPlayerShipsBoard(player);
			battleShipView.waitBetweenChangingPlayerTurn(3);
		}
	}	
	
	@Override
	public int makePlay(int currentPlayerId) {
		battleShipView.showSectionLine();
		battleShipView.showMsg("turno de disparo del jugador " +  players.get(currentPlayerId).getName());
		
		int oponentPlayerId = (currentPlayerId + 1) % this.playersCount;
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
			battleShipView.waitBetweenChangingPlayerTurn(5);
		}
		
		battleShipView.showPlayerShootsBoard(oponentPlayer);
		
		return nextPlayerIdToPlay;
	}
}
