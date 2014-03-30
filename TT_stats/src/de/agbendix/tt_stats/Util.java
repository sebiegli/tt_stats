package de.agbendix.tt_stats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;

import android.content.Context;
import android.os.Environment;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Util {
	public static final String mPath = Environment
			.getExternalStorageDirectory() + "/TT_stats/";

	public static void makeToast(Context c, String text) {
		Toast toast = Toast.makeText(c, text, Toast.LENGTH_LONG);
		LinearLayout toastLayout = (LinearLayout) toast.getView();
		TextView toastTV = (TextView) toastLayout.getChildAt(0);
		int textSize = c.getResources().getInteger(
				R.integer.toast_textsize_default);
		toastTV.setTextSize(textSize);
		toast.show();
	}

	/**
	 * Methode zum Ausschreiben der Daten in die TXT-Datei
	 */
	public static void saveToTXT(LinkedList<Player> players) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(mPath + "stats.txt"), "utf-8"));

			for (Player player : players) {
				writer.write(player.getName() + "," + player.getTtsName() + ","
						+ player.getGamesTotal() + "," + player.getGamesWon()
						+ "\n");
			}
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * Für einmalige Initialisierung der TXT-Datei
	 */
	public static void initTXTFile() {
		Writer writer = null;

		try {
			final File dir = new File(mPath);
			dir.mkdirs(); //create folders where write files
			final File file = new File(dir, "stats.txt");
			file.delete();

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(mPath + "stats.txt"), "utf-8"));

			writer.write("Bianca,Bianka,0,0\nMartin,Martiin,0,0\nMaik,Maik,0,0\nSven,Swän,0,0\nSebastian,Sebastian,0,0\nBrenner,Brenner,0,0\nYiLi,Jilii,0,0\nStefan,Stefan,0,0");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static String getWinnersForTTS(LinkedList<Player> players) {
		StringBuffer winners = new StringBuffer();
		// Checken ob es mehr als einen Gewinner gibt:
		int winnerCount = 0;
		for (Player player : players) {
			if (player.hasWon())
				winnerCount++;
		}
		if (winnerCount > 1) {
			winners.append("Glückwunsch an die Gewinner ");
		} else {
			winners.append("Glückwunsch an den Gewinner ");
		}

		for (Player player : players) {
			if (player.hasWon()) {
				winners.append(player.getTtsName() + " und ");
			}
		}

		// Löschen des letzten "und" und zurück geben...
		return winners.substring(0, winners.length() - 4);
	}
}
