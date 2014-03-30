package de.agbendix.tt_stats;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * Fragment für die Anzeige der Spielerliste
 *
 */
public class Fragment_Players extends Fragment {
	
	private ListView playersListView;
	private List<Player> players;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_players,
				container, false);
		
		// Anlegen der Liste aller Spieler
		players = new LinkedList<Player>();
		
		// Füllen der players-Liste:
		players.add(new Player("Bianca"));
		players.add(new Player("Martin"));
		players.add(new Player("Maik"));
		players.add(new Player("Sven"));
		players.add(new Player("Sebastian"));
		players.add(new Player("Brenner"));
		players.add(new Player("YiLi"));
		players.add(new Player("Stefan"));		
		
		playersListView = (ListView) rootView.findViewById(R.id.lv_players);
		setPlayersListAdapter(rootView, R.layout.players_row);
		
		return rootView;
	}
	
	protected void setPlayersListAdapter(View rootView, int rowLayout) {
		PlayersListAdapter plListAdapter = new PlayersListAdapter(
				this.getActivity(), rowLayout, getPlayersListAdapterSettings(),players);
		playersListView.setAdapter(plListAdapter);
	}
	
	protected HashMap<String, Integer> getPlayersListAdapterSettings() {
		return new HashMap<String, Integer>() {
			private static final long serialVersionUID = -2804013377753573965L;
			{
				put(PlayersListAdapter.NAME_FIELD,
						R.id.tv_playername);
				put(PlayersListAdapter.WINNERCB_FIELD,
						R.id.cb_winner);
				put(PlayersListAdapter.LOSERCB_FIELD,
						R.id.cb_loser);
			}
		};
	}
}