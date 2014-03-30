package de.agbendix.tt_stats;

/**
 * Klasse die alle Infos für einen Spieler verwaltet
 */
public class Player {
	private String name, ttsName;
	private boolean won, lost; // Zustände für Gewonnen/Verloren
	
	public Player(String name, String ttsName, boolean won, boolean lost) {
		this.setName(name);
		this.setTtsName(ttsName);
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
}
