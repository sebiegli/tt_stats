package de.agbendix.tt_stats;

/**
 * Klasse die alle Infos für einen Spieler verwaltet
 */
public class Player {
	private String name;

	public Player(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
