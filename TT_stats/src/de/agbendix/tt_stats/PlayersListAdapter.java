package de.agbendix.tt_stats;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * Adapter für die Anzeige von Spielern
 */
public class PlayersListAdapter extends ArrayAdapter<Player> {
	public static final String NAME_FIELD = "nameField";
	public static final String WINNERCB_FIELD = "winnerCBField";
	public static final String LOSERCB_FIELD = "loserCBField";
	public static final String WINRATE_FIELD = "winrateField";

	protected final List<Player> listOfElements;
	protected final LayoutInflater inflater;
	protected final int layout;
	protected final Map<String, Integer> layoutfields;
	protected final Activity context;

	public PlayersListAdapter(Activity context, int layout,
			Map<String, Integer> layoutfields, List<Player> players) {
		super(context, layout, players);
		this.listOfElements = players;
		this.inflater = context.getLayoutInflater();
		this.layout = layout;
		this.layoutfields = layoutfields;
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			rowView = this.inflater.inflate(this.layout, null);
			// Damit Listenelemente nicht anwählbar sind:
			// Seltsamerweise muss man die Werte auf true setzen und nicht auf
			// false...
			rowView.setFocusable(true);
			rowView.setClickable(true);
			rowView.setEnabled(true);
			assignViewHolderElements(rowView);
		}

		// Listener für die Winner-CheckBox registrieren...
		CheckBox WinnerCB = (CheckBox) rowView.findViewById(R.id.cb_winner);
		WinnerCB.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// Bei Klick auf die CheckBox wird der Status des Spielers
				// entsprechend upgedated...
				PlayersListAdapter.this.getItem(position).setWon(isChecked);
			}
		});

		// Listener für die Loser-CheckBox registrieren...
		CheckBox LoserCB = (CheckBox) rowView.findViewById(R.id.cb_loser);
		LoserCB.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// Bei Klick auf die CheckBox wird der Status des Spielers
				// entsprechend upgedated...
				PlayersListAdapter.this.getItem(position).setLost(isChecked);
			}
		});

		fillViewHolder(rowView, position);
		return rowView;
	}

	protected void assignViewHolderElements(View rowView) {
		final ViewHolder viewHolder = new ViewHolder();
		viewHolder.nameTextView = (TextView) rowView.findViewById(layoutfields
				.get(NAME_FIELD));
		viewHolder.winnerCB = (CheckBox) rowView.findViewById(layoutfields
				.get(WINNERCB_FIELD));
		viewHolder.loserCB = (CheckBox) rowView.findViewById(layoutfields
				.get(LOSERCB_FIELD));
		viewHolder.winrateTextView = (TextView) rowView
				.findViewById(layoutfields.get(WINRATE_FIELD));
		rowView.setTag(viewHolder);
	}

	protected void fillViewHolder(View rowView, int position) {
		ViewHolder holder = (ViewHolder) rowView.getTag();
		Player player = listOfElements.get(position);

		// Setzen der holder-Elemente:
		holder.nameTextView.setText(player.getName());
		holder.loserCB.setChecked(player.hasLost());
		holder.winnerCB.setChecked(player.hasWon());
		holder.winrateTextView.setText(player.getWinrateString());
	}

	static class ViewHolder {
		private TextView nameTextView, winrateTextView;
		private CheckBox winnerCB, loserCB;
	}
}
