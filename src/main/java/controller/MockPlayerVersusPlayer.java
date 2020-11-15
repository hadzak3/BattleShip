package controller;

import java.util.ArrayList;

import model.FourCellShip;
import model.OneCellShip;
import model.Player;
import model.Ship;
import model.ThreeCellShip;
import model.TwoCellShip;

public class MockPlayerVersusPlayer extends Game {
	
	private int[] shootsX = {0, 1, 1, 0, 0, 9, 8, 7, 5, 5, 5, 5};
	private int[] shootsY = {0, 2, 2, 1, 2, 9, 9, 9, 5, 6, 7, 8};
	private int index = 0;

	public MockPlayerVersusPlayer() {
		super();
	}

	@Override
	public void initializeGame() {
		Player player1 = new Player("player 1");
		Player player2 = new Player("player 2");
		
		/* Ships initialization data. Both players have same ships coordinates. */
		String[] orientation = {"h", "h", "v", "h"};
		int[] xs = {0, 0, 9, 5};
		int[] ys = {0, 1, 9, 5};
		
		ArrayList<Ship> ships = new ArrayList<>();
		ships.add(new OneCellShip());
		ships.add(new TwoCellShip());
		ships.add(new ThreeCellShip());
		ships.add(new FourCellShip());
		
		battleShipView.showSectionLine();
		battleShipView.showMsg("inicialización de los tableros");
		
		for (int i = 0; i < xs.length; i++) {
			player1.createShip(xs[i], ys[i], orientation[i], ships.get(i));
			battleShipView.showPlayerShipsBoard(player1);
			
			player2.createShip(xs[i], ys[i], orientation[i], ships.get(i));
			battleShipView.showPlayerShipsBoard(player2);
		}
		this.players.add(player1);
		this.battleShipView.showPlayerShipsBoard(player1);
		
		this.players.add(player2);
		this.battleShipView.showPlayerShipsBoard(player2);
	}

	@Override
	public int makePlay(int currentPlayerId) {
		battleShipView.showSectionLine();
		battleShipView.showMsg("turno de disparo del jugador " + players.get(currentPlayerId).getName());
		
		int oponentPlayerId = (currentPlayerId + 1) % this.playersCount;
		Player oponentPlayer = players.get(oponentPlayerId);
		battleShipView.showPlayerShootsBoard(oponentPlayer);
		
		battleShipView.showSubSectionLine();
		battleShipView.showMsg("disparando en " + this.shootsX[index] + ", "+ this.shootsY[index]);
		boolean isDown = oponentPlayer.shoot(this.shootsX[index], this.shootsY[index]);
		index++;
		
		int nextPlayerIdToPlay = currentPlayerId;
		/* The player's turn changes only if the current player has not down a ship. */
		if (!isDown) { 
			battleShipView.showMsg("agua");
			nextPlayerIdToPlay = oponentPlayerId;
		}
		battleShipView.showPlayerShootsBoard(oponentPlayer);
		
		return nextPlayerIdToPlay;
	}
}
