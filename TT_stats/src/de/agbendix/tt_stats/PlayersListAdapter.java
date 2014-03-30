package de.agbendix.tt_stats;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Adapter für die Anzeige von Spielern
 */
public class PlayersListAdapter extends ArrayAdapter<Player> {
	public static final String NAME_FIELD = "nameField";
	public static final String WINNERCB_FIELD = "winnerCBField";
	public static final String LOSERCB_FIELD = "loserCBField";
	
	protected final List<Player>       	 listOfElements;
	protected final LayoutInflater       inflater;
	protected final int                  layout;
	protected final Map<String, Integer> layoutfields;
	protected final Activity			 context;
	
	public PlayersListAdapter(Activity context, int layout, Map<String, Integer> layoutfields, List<Player> players) {
		super(context, layout, players);
		this.listOfElements     = players;
		this.inflater     		= context.getLayoutInflater();
		this.layout       		= layout;
		this.layoutfields 		= layoutfields;
		this.context			= context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView == null) {
			rowView = this.inflater.inflate(this.layout, null);
			// Damit Listenelemente nicht anwählbar sind:
			// Seltsamerweise muss man die Werte auf true setzen und nicht auf false...
			rowView.setFocusable(true);
			rowView.setClickable(true);
			rowView.setEnabled(true);
			assignViewHolderElements(rowView);
		}
		fillViewHolder(rowView,position);	
		return rowView;
	}

	protected void assignViewHolderElements(View rowView) {
		final ViewHolder viewHolder = new ViewHolder();
		viewHolder.nameTextView = (TextView) rowView
				.findViewById(layoutfields.get(NAME_FIELD));
		viewHolder.winnerCB = (CheckBox) rowView
				.findViewById(layoutfields.get(WINNERCB_FIELD));
		viewHolder.loserCB = (CheckBox) rowView
				.findViewById(layoutfields.get(LOSERCB_FIELD));
		rowView.setTag(viewHolder);
	}

	protected void fillViewHolder(View rowView, int position) {
		ViewHolder holder = (ViewHolder) rowView.getTag();
		Player player = listOfElements.get(position);

		// Setzen des Namens
		holder.nameTextView.setText(player.getName());
	}

	static class ViewHolder {
		private TextView nameTextView;
		private CheckBox winnerCB, loserCB;
	}
}
