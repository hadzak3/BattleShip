package controller;

import java.util.ArrayList;

import Constants.Constants;
import model.Player;
import view.View;

public abstract class Game {
	
	public ArrayList<Player> players;
	public Player winner;
	
	protected View battleShipView;
	
	protected final int playersCount = Constants.N_PLAYERS;

	public Game() {
		players = new ArrayList<>();
		winner = null;
		
		battleShipView = new View();
	}
	
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	public void playOneGame() {
		initializeGame();
		int j = 0;
		while (!endOfGame()) {
			j = makePlay(j);
		}
		printWinner(j);
	}
	
	public abstract void initializeGame();
	public abstract int makePlay(int currentPlayerId);
	
	public boolean endOfGame() {
		boolean isTheEnd = true;
		for (int j = 0; j < playersCount && isTheEnd; j++) {
			if (!players.get(j).isAllShipsSunk()) {
				isTheEnd = false;
			}
		}
	
		return isTheEnd;
	}
	
	public void printWinner(int playerId) {
		this.winner = players.get(playerId);
		battleShipView.printWinner(this.winner.getName());
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

