package de.agbendix.tt_stats;

import java.text.DecimalFormat;

/**
 * Klasse die alle Infos für einen Spieler verwaltet
 */
public class Player {
	private String name, ttsName;
	private boolean won, lost; // Zustände für Gewonnen/Verloren
	private int gamesTotal, gamesWon; // Anzahl aller Spiele und Anzahl gewonnener Spiele
	
	public Player(String name, String ttsName, int gamesTotal, int gamesWon, boolean won, boolean lost) {
		this.setName(name);
		this.setTtsName(ttsName);
		this.setGamesTotal(gamesTotal);
		this.setGamesWon(gamesWon);
		this.setWon(won);
		this.setLost(lost);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTtsName() {
		return ttsName;
	}

	public void setTtsName(String ttsName) {
		this.ttsName = ttsName;
	}

	public boolean hasWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won  = won;
	}

	public boolean hasLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}

	public int getGamesTotal() {
		return gamesTotal;
	}

	public void setGamesTotal(int gamesTotal) {
		this.gamesTotal = gamesTotal;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public double getWinrate() {
		if(gamesTotal>0){
			return (double) gamesWon / (double) gamesTotal * 100;
		}
		else return -1.;
	}
	
	public String getWinrateString() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		if(getWinrate()!=-1.){
			return df.format(getWinrate()) + "%";
		} else{
			return "--";
		}
	}
}
