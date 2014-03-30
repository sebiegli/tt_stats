package de.agbendix.tt_stats;

import java.util.HashMap;
import java.util.LinkedList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * Fragment für die Anzeige der Spielerliste
 * 
 */
public class Fragment_Players extends Fragment {

	private ListView playersListView;
	private LinkedList<Player> players;
	private Button saveButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_players, container,
				false);

		// Anlegen der Liste aller Spieler
		players = new LinkedList<Player>();

		// Erstmaliges Füllen der players-Liste:
		players.add(new Player("Bianca","Bianka",false,false));
		players.add(new Player("Martin","Martin",false,false));
		players.add(new Player("Maik","Maik",false,false));
		players.add(new Player("Sven","Swän",false,false));
		players.add(new Player("Sebastian","Sebastian",false,false));
		players.add(new Player("Brenner","Brenner",false,false));
		players.add(new Player("YiLi","Yielie",false,false));
		players.add(new Player("Stefan","Stefan",false,false));

		playersListView = (ListView) rootView.findViewById(R.id.lv_players);
		setPlayersListAdapter(rootView, R.layout.players_row);

		// Registrieren eines Listeners für den Einloggen-Button:
		saveButton = (Button) rootView.findViewById(R.id.bn_save);
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Checken, ob alle Regeln eingehalten werden:
				// Zähler für Gewinner und Verlierer:
				int counterLosers = 0;
				int counterWinners = 0;
				boolean validInput = false;
				for(Player player : players){
					// Jeder Spieler darf nur verloren ODER gewonnen haben (oder garnichts)
					if(player.hasLost() == true && player.hasWon() == true){
						Util.makeToast(getActivity(), getString(R.string.invalid_combi));
						((MainActivity) getActivity()).speakOut(getString(R.string.invalid_combi));
						break;
					}
					if(player.hasLost()){
						counterLosers ++;
					}
					if(player.hasWon()){
						counterWinners++;
					}
					validInput=true;
				}
				// Nur wenn jeder Spieler entweder Verlierer oder Gewinner oder nichts von beiden ist
				if(validInput){
					// und nur wenn die Anzahl der Verlierer == Gewinner und > 0, dann wird das Ergebnis gespeichert.
					if(counterLosers==counterWinners && counterLosers > 0){
						Util.makeToast(getActivity(), getString(R.string.logged));
						((MainActivity) getActivity()).speakOut(getString(R.string.logged) + Util.getWinnersForTTS(players));
						// SPEICHERN
						
					} else {
						Util.makeToast(getActivity(), getString(R.string.invalid_combi));
						((MainActivity) getActivity()).speakOut(getString(R.string.invalid_combi));
					}
				} else {
					Util.makeToast(getActivity(), getString(R.string.invalid_combi));
					((MainActivity) getActivity()).speakOut(getString(R.string.invalid_combi));
				}
			}
		});

		return rootView;
	}

	protected void setPlayersListAdapter(View rootView, int rowLayout) {
		PlayersListAdapter plListAdapter = new PlayersListAdapter(
				this.getActivity(), rowLayout, getPlayersListAdapterSettings(),
				players);
		playersListView.setAdapter(plListAdapter);
	}

	protected HashMap<String, Integer> getPlayersListAdapterSettings() {
		return new HashMap<String, Integer>() {
			private static final long serialVersionUID = -2804013377753573965L;
			{
				put(PlayersListAdapter.NAME_FIELD, R.id.tv_playername);
				put(PlayersListAdapter.WINNERCB_FIELD, R.id.cb_winner);
				put(PlayersListAdapter.LOSERCB_FIELD, R.id.cb_loser);
			}
		};
	}
}