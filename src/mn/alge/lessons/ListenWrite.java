package mn.alge.lessons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import mn.alge.db.DBAdapter;
import mn.alge.db.Word;
import mn.alge.english.R;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListenWrite extends Activity {
	ArrayList<Word> wordList = new ArrayList<Word>();
	ArrayList<Word> wordList1 = new ArrayList<Word>(1);
	ArrayList<String> wordLetterArray = new ArrayList<String>();
	ArrayList<String> buttons = new ArrayList<String>();

	private TextToSpeech tts;
	Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
			btn11, btn12, btn13, btn14;
	ImageButton btnHelp, btnDugar;
	ImageView ivListenWrite;
	TextView tvListenWrite;

	Button[] numButtons = new Button[15];
	Integer[] numBtnIDs = { R.id.btnWrd0, R.id.btnWrd1, R.id.btnWrd2,
			R.id.btnWrd3, R.id.btnWrd4, R.id.btnWrd5, R.id.btnWrd6,
			R.id.btnWrd7, R.id.btnWrd8, R.id.btnWrd9, R.id.btnWrd10,
			R.id.btnWrd11, R.id.btnWrd12, R.id.btnWrd13, R.id.btnWrd14 };
	String btnLetter;
	char orluul;
	int nextL = 0;
	int i;

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listen_write);
		tts = new TextToSpeech(getApplicationContext(),
				new TextToSpeech.OnInitListener() {
					@Override
					public void onInit(int status) {
						if (status != TextToSpeech.ERROR) {
							tts.setLanguage(Locale.ENGLISH);
						}
					}
				});
		DBAdapter mDbHelper = new DBAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();

		wordList = mDbHelper.retrieveWords1();
		Collections.shuffle(wordList);
		wordList1.add(wordList.get(0));

		mDbHelper.close();

		ivListenWrite = (ImageView) findViewById(R.id.ivListenWrite);
		ivListenWrite.setImageBitmap(wordList1.get(0).getBitmap());
		
		int length = wordList1.get(0).getEnglish().length();

		tvListenWrite = (TextView) findViewById(R.id.tvListenWrite);
		// ugnii urtaar _ ene temdegt tawij bn
		// String repeated = new String(new char[length]).replace("\0", "_");
		// tvListenWrite.setText(repeated);

		// ugiig usgeer ni salgaj uur array ruu hiij bn
		for (int i = 0; i < length; i++) {
			// wordList1.get(0).getEnglish().toString().toCharArray();
			wordLetterArray.add(Character.toString(wordList1.get(0)
					.getEnglish().toString().charAt(i)));
		}
		
		// usegnuudee holii
		Collections.shuffle(wordLetterArray);

		btn0 = (Button) findViewById(R.id.btnWrd0);
		btn1 = (Button) findViewById(R.id.btnWrd1);
		btn2 = (Button) findViewById(R.id.btnWrd2);
		btn3 = (Button) findViewById(R.id.btnWrd3);
		btn4 = (Button) findViewById(R.id.btnWrd4);
		btn5 = (Button) findViewById(R.id.btnWrd5);
		btn6 = (Button) findViewById(R.id.btnWrd6);
		btn7 = (Button) findViewById(R.id.btnWrd7);
		btn8 = (Button) findViewById(R.id.btnWrd8);
		btn9 = (Button) findViewById(R.id.btnWrd9);
		btn10 = (Button) findViewById(R.id.btnWrd10);
		btn11 = (Button) findViewById(R.id.btnWrd11);
		btn12 = (Button) findViewById(R.id.btnWrd12);
		btn13 = (Button) findViewById(R.id.btnWrd13);
		btn14 = (Button) findViewById(R.id.btnWrd14);

		btn0.setText(wordLetterArray.get(0).toString());
		if (length == 2) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn1.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);

		}
		if (length == 3) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
		}
		if (length == 4) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
		}
		if (length == 5) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
		}
		if (length == 6) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
		}
		if (length == 7) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn6.setText(wordLetterArray.get(6).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn6.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
			btn6.setVisibility(View.VISIBLE);
		}
		if (length == 8) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn6.setText(wordLetterArray.get(6).toString());
			btn7.setText(wordLetterArray.get(7).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn6.setEnabled(true);
			btn7.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
			btn6.setVisibility(View.VISIBLE);
			btn7.setVisibility(View.VISIBLE);
		}
		if (length == 9) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn6.setText(wordLetterArray.get(6).toString());
			btn7.setText(wordLetterArray.get(7).toString());
			btn8.setText(wordLetterArray.get(8).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn6.setEnabled(true);
			btn7.setEnabled(true);
			btn8.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
			btn6.setVisibility(View.VISIBLE);
			btn7.setVisibility(View.VISIBLE);
			btn8.setVisibility(View.VISIBLE);
		}
		if (length == 10) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn6.setText(wordLetterArray.get(6).toString());
			btn7.setText(wordLetterArray.get(7).toString());
			btn8.setText(wordLetterArray.get(8).toString());
			btn9.setText(wordLetterArray.get(9).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn6.setEnabled(true);
			btn7.setEnabled(true);
			btn8.setEnabled(true);
			btn9.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
			btn6.setVisibility(View.VISIBLE);
			btn7.setVisibility(View.VISIBLE);
			btn8.setVisibility(View.VISIBLE);
			btn9.setVisibility(View.VISIBLE);
		}
		if (length == 11) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn6.setText(wordLetterArray.get(6).toString());
			btn7.setText(wordLetterArray.get(7).toString());
			btn8.setText(wordLetterArray.get(8).toString());
			btn9.setText(wordLetterArray.get(9).toString());
			btn10.setText(wordLetterArray.get(10).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn6.setEnabled(true);
			btn7.setEnabled(true);
			btn8.setEnabled(true);
			btn9.setEnabled(true);
			btn10.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
			btn6.setVisibility(View.VISIBLE);
			btn7.setVisibility(View.VISIBLE);
			btn8.setVisibility(View.VISIBLE);
			btn9.setVisibility(View.VISIBLE);
			btn10.setVisibility(View.VISIBLE);
		}
		if (length == 12) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn6.setText(wordLetterArray.get(6).toString());
			btn7.setText(wordLetterArray.get(7).toString());
			btn8.setText(wordLetterArray.get(8).toString());
			btn9.setText(wordLetterArray.get(9).toString());
			btn10.setText(wordLetterArray.get(10).toString());
			btn11.setText(wordLetterArray.get(11).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn6.setEnabled(true);
			btn7.setEnabled(true);
			btn8.setEnabled(true);
			btn9.setEnabled(true);
			btn10.setEnabled(true);
			btn11.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
			btn6.setVisibility(View.VISIBLE);
			btn7.setVisibility(View.VISIBLE);
			btn8.setVisibility(View.VISIBLE);
			btn9.setVisibility(View.VISIBLE);
			btn10.setVisibility(View.VISIBLE);
			btn11.setVisibility(View.VISIBLE);
		}
		if (length == 13) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn6.setText(wordLetterArray.get(6).toString());
			btn7.setText(wordLetterArray.get(7).toString());
			btn8.setText(wordLetterArray.get(8).toString());
			btn9.setText(wordLetterArray.get(9).toString());
			btn10.setText(wordLetterArray.get(10).toString());
			btn11.setText(wordLetterArray.get(11).toString());
			btn12.setText(wordLetterArray.get(12).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn6.setEnabled(true);
			btn7.setEnabled(true);
			btn8.setEnabled(true);
			btn9.setEnabled(true);
			btn10.setEnabled(true);
			btn11.setEnabled(true);
			btn12.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
			btn6.setVisibility(View.VISIBLE);
			btn7.setVisibility(View.VISIBLE);
			btn8.setVisibility(View.VISIBLE);
			btn9.setVisibility(View.VISIBLE);
			btn10.setVisibility(View.VISIBLE);
			btn11.setVisibility(View.VISIBLE);
			btn12.setVisibility(View.VISIBLE);
		}
		if (length == 14) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn6.setText(wordLetterArray.get(6).toString());
			btn7.setText(wordLetterArray.get(7).toString());
			btn8.setText(wordLetterArray.get(8).toString());
			btn9.setText(wordLetterArray.get(9).toString());
			btn10.setText(wordLetterArray.get(10).toString());
			btn11.setText(wordLetterArray.get(11).toString());
			btn12.setText(wordLetterArray.get(12).toString());
			btn13.setText(wordLetterArray.get(13).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn6.setEnabled(true);
			btn7.setEnabled(true);
			btn8.setEnabled(true);
			btn9.setEnabled(true);
			btn10.setEnabled(true);
			btn11.setEnabled(true);
			btn12.setEnabled(true);
			btn13.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
			btn6.setVisibility(View.VISIBLE);
			btn7.setVisibility(View.VISIBLE);
			btn8.setVisibility(View.VISIBLE);
			btn9.setVisibility(View.VISIBLE);
			btn10.setVisibility(View.VISIBLE);
			btn11.setVisibility(View.VISIBLE);
			btn12.setVisibility(View.VISIBLE);
			btn13.setVisibility(View.VISIBLE);
		}
		if (length == 15) {
			btn1.setText(wordLetterArray.get(1).toString());
			btn2.setText(wordLetterArray.get(2).toString());
			btn3.setText(wordLetterArray.get(3).toString());
			btn4.setText(wordLetterArray.get(4).toString());
			btn5.setText(wordLetterArray.get(5).toString());
			btn6.setText(wordLetterArray.get(6).toString());
			btn7.setText(wordLetterArray.get(7).toString());
			btn8.setText(wordLetterArray.get(8).toString());
			btn9.setText(wordLetterArray.get(9).toString());
			btn10.setText(wordLetterArray.get(10).toString());
			btn11.setText(wordLetterArray.get(11).toString());
			btn12.setText(wordLetterArray.get(12).toString());
			btn13.setText(wordLetterArray.get(13).toString());
			btn14.setText(wordLetterArray.get(14).toString());
			btn1.setEnabled(true);
			btn2.setEnabled(true);
			btn3.setEnabled(true);
			btn4.setEnabled(true);
			btn5.setEnabled(true);
			btn6.setEnabled(true);
			btn7.setEnabled(true);
			btn8.setEnabled(true);
			btn9.setEnabled(true);
			btn10.setEnabled(true);
			btn11.setEnabled(true);
			btn12.setEnabled(true);
			btn13.setEnabled(true);
			btn14.setEnabled(true);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.VISIBLE);
			btn6.setVisibility(View.VISIBLE);
			btn7.setVisibility(View.VISIBLE);
			btn8.setVisibility(View.VISIBLE);
			btn9.setVisibility(View.VISIBLE);
			btn10.setVisibility(View.VISIBLE);
			btn11.setVisibility(View.VISIBLE);
			btn12.setVisibility(View.VISIBLE);
			btn13.setVisibility(View.VISIBLE);
			btn14.setVisibility(View.VISIBLE);
		}
		for (i = 0; i < numBtnIDs.length; i++) {
			numButtons[i] = (Button) findViewById(numBtnIDs[i]);
		}
		for (i = 0; i < numBtnIDs.length; i++) {
			final int index;
			index = i;
			numButtons[index].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					if (wordList1.get(0).getEnglish().toString().charAt(nextL) == numButtons[index]
							.getText().charAt(0)) {
						btnLetter = tvListenWrite.getText().toString()
								+ numButtons[index].getText().toString();
						tvListenWrite.setText(btnLetter);
						nextL++;
						numButtons[index].setVisibility(View.INVISIBLE);
						if(tvListenWrite.getText().toString().equals(wordList1.get(0).getEnglish())){
							tts.speak("fantastic", TextToSpeech.QUEUE_FLUSH, null);
							Toast.makeText(getApplicationContext(), "+1 оноо авлаа", 0).show();
							finish();
						}
					} else {
						tts.speak("try again", TextToSpeech.QUEUE_FLUSH, null);
						Toast.makeText(getApplicationContext(),
								"дахин оролдоно уу", Toast.LENGTH_SHORT).show();
					}
				}
			});
		}
		
		
		btnHelp = (ImageButton) findViewById(R.id.btnListenWriteHelp);
		btnHelp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		btnDugar = (ImageButton) findViewById(R.id.btnListenWriteDugar);
		btnDugar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tts.speak(wordList1.get(0).getEnglish(),
						TextToSpeech.QUEUE_FLUSH, null);
			}
		});
		btnDugar.performClick();
	}

	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
