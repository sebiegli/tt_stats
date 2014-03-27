package de.agbendix.tt_stats.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.widget.ToggleButton;
import de.agbendix.tt_stats.R;

public class MainActivity extends Activity implements OnInitListener {

	private CheckBox chk_Bianca, chk_Martin, chk_Maik, chk_Sebastian, chk_Sven, chk_Li_Yi, chk_Sandro;
	private Button btnSave;
	private Button btnReadout;
	
	private TextToSpeech tts;
	
	int maik, martin, sebastian, bianca, sven, li_yi, sandro; 
	
	String mPath=Environment.getExternalStorageDirectory()+"/TT_stats/";
	
	String speakwords_winners = "";
	String speakwords_loosers = "";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 tts = new TextToSpeech(this, this);
		
		setContentView(R.layout.main);

		//addListenerOnChkIos();
		addListenerOnButton();
	}

//	public void addListenerOnChkIos() {
//
//		chkIos = (CheckBox) findViewById(R.id.chkIos);
//
//		chkIos.setOnClickListener(new OnClickListener() {
//
////			@Override
////			public void onClick(View v) {
////
////				if (((CheckBox) v).isChecked()) {
////					Toast.makeText(MyAndroidAppActivity.this,
////							"Bro, try Android :)", Toast.LENGTH_LONG).show();
////				}
////
////			}
////		});
//
//	}

	public void addListenerOnButton() {

		chk_Bianca = (CheckBox) findViewById(R.id.checkBox_Bianca);
		chk_Maik = (CheckBox) findViewById(R.id.checkBox_Maik);
		chk_Martin = (CheckBox) findViewById(R.id.checkBox_Martin);
		chk_Sebastian = (CheckBox) findViewById(R.id.checkBox_Sebastian);
		chk_Sven = (CheckBox) findViewById(R.id.checkBox_Sven);
		chk_Li_Yi = (CheckBox) findViewById(R.id.checkBox_Li_Yi);
		chk_Sandro = (CheckBox) findViewById(R.id.checkBox_Sandro);
		
		
		
	
		
		btnSave = (Button) findViewById(R.id.btnSave);

		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				speakwords_winners = "";
				speakwords_loosers = "";
				
				ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton1);
				
				FileReader fileReader;
				try {
					fileReader = new FileReader(new File(mPath+"stats.txt"));
				
				BufferedReader br = new BufferedReader(fileReader);

				 String line = null;
				 // if no more lines the readLine() returns null
				 try {
					while ((line = br.readLine()) != null) {
					      // reading lines until the end of the file
						String[] information = line.split("\\s+");
						
						if ( information[0].contains("Maik") ) {
							maik = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Martin") ) {
							martin = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Sebastian") ) {
							sebastian = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Bianca") ) {
							bianca = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Sven") ) {
							sven = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Li_Yi") ) {
							li_yi = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Sandro") ) {
							sandro = Integer.valueOf(information[1]);
						}
						
						


					 }
					
				 } catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//				StringBuffer result = new StringBuffer();
//				result.append("IPhone check : ")
//						.append(chkIos.isChecked());
//				result.append("\nAndroid check : ").append(
//						chkAndroid.isChecked());
//				result.append("\nWindows Mobile check :").append(
//						chkWindows.isChecked());
//
//				Toast.makeText(MyAndroidAppActivity.this, result.toString(),
//						Toast.LENGTH_LONG).show();
				
				
				if (toggle.isChecked()) {
				
					if (chk_Bianca.isChecked()) {
						bianca += 1;
						speakwords_winners = "Bianca";
					}
					

					if (chk_Maik.isChecked()) {
						maik += 1;
						speakwords_winners = speakwords_winners+" Maik";
					}
					
					if (chk_Martin.isChecked()) {
						martin += 1;
				        speakwords_winners = speakwords_winners+" Martin";
					}
					
					if (chk_Sebastian.isChecked()) {
						sebastian += 1;
						speakwords_winners = speakwords_winners+" Sebastian";
					}
					
					if (chk_Sven.isChecked()) {
						sven += 1;
						speakwords_winners = speakwords_winners+" Swen";
					}
					
					if (chk_Li_Yi.isChecked()) {
						li_yi += 1;
						speakwords_winners = speakwords_winners+" Lie Jie";
					}
					
					if (chk_Sandro.isChecked()) {
						sandro += 1;
						speakwords_winners = speakwords_winners+" Sandro";
					}
					
					speakOut("Die Gewinner: "+speakwords_winners);
					
				} else {
					
					speakwords_loosers = "";
					
					if (chk_Bianca.isChecked()) {
						bianca -= 1;
						speakwords_loosers = "Bianca";
					}
					

					if (chk_Maik.isChecked()) {
						maik -= 1;
						speakwords_loosers = speakwords_loosers+" Maik";
					}
					
					if (chk_Martin.isChecked()) {
						martin -= 1;
				        speakwords_loosers = speakwords_loosers+" Martin";
					}
					
					if (chk_Sebastian.isChecked()) {
						sebastian -= 1;
						speakwords_loosers = speakwords_loosers+" Sebastian";
					}
					
					if (chk_Sven.isChecked()) {
						sven -= 1;
						speakwords_loosers = speakwords_loosers+" Swen";
					}
					
					if (chk_Li_Yi.isChecked()) {
						li_yi -= 1;
						speakwords_loosers = speakwords_loosers+" Lie Jie";
					}
					
					if (chk_Sandro.isChecked()) {
						sandro -= 1;
						speakwords_loosers = speakwords_loosers+" Sandro";
					}
					
					speakOut("Die Verlierer: "+speakwords_loosers);
					
				}
				
				
				Writer writer = null;

				try {
				    writer = new BufferedWriter(new OutputStreamWriter(
				          new FileOutputStream(mPath+"stats.txt"), "utf-8"));
				    writer.write("Bianca "+bianca+"\n"+"Maik "+maik+"\n"+"Martin "+martin+"\n"+"Sebastian "+sebastian+"\n"+"Sven "+sven+"\n"+"Li_Yi "+li_yi+"\n"+"Sandro "+sandro+"\n");
				} catch (IOException ex) {
				  // report
				} finally {
				   try {writer.close();} catch (Exception ex) {}
				}
				
				

			}
		});
		
		
		
		
		
		

		btnReadout = (Button) findViewById(R.id.btnReadout);

		btnReadout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				FileReader fileReader;
				try {
					fileReader = new FileReader(new File(mPath+"stats.txt"));
				
				BufferedReader br = new BufferedReader(fileReader);

				 String line = null;
				 // if no more lines the readLine() returns null
				 try {
					while ((line = br.readLine()) != null) {
					      // reading lines until the end of the file
						String[] information = line.split("\\s+");
						
						if ( information[0].contains("Maik") ) {
							maik = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Martin") ) {
							martin = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Sebastian") ) {
							sebastian = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Bianca") ) {
							bianca = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Sven") ) {
							sven = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Li_Yi") ) {
							li_yi = Integer.valueOf(information[1]);
						}
						
						if ( information[0].contains("Sandro") ) {
							sandro = Integer.valueOf(information[1]);
						}
	 }
					
				 } catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (chk_Bianca.isChecked()) {
					speakOut("Bianca steht bei: "+Integer.toString(bianca));
				}
				

				if (chk_Maik.isChecked()) {
					speakOut("Maik steht bei: "+Integer.toString(maik));
				}
				
				if (chk_Martin.isChecked()) {
					speakOut("Martin steht bei: "+Integer.toString(martin));
				}
				
				if (chk_Sebastian.isChecked()) {
					speakOut("Sebastian steht bei: "+Integer.toString(sebastian));
				}
				
				if (chk_Sven.isChecked()) {
					speakOut("Swen steht bei: "+Integer.toString(sven));
				}
				
				if (chk_Li_Yi.isChecked()) {
					speakOut("Lie Jie steht bei: "+Integer.toString(li_yi));
				}
				
				if (chk_Sandro.isChecked()) {
					speakOut("Sandro steht bei: "+Integer.toString(sandro));
				}
						
					
			}
			
		
	});
	
		

		

	}
	
	
	 //setup TTS
	 public void onInit(int initStatus) {
	  
	         //check for successful instantiation
	     if (initStatus == TextToSpeech.SUCCESS) {
	         if(tts.isLanguageAvailable(Locale.GERMAN)==TextToSpeech.LANG_AVAILABLE)
	             tts.setLanguage(Locale.GERMAN);
	     }
	     else if (initStatus == TextToSpeech.ERROR) {
	         Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
	     }
	 }
	    
	    private void speakOut(String speakthis) {
	        tts.speak(speakthis, TextToSpeech.QUEUE_FLUSH, null);
	}
	
}