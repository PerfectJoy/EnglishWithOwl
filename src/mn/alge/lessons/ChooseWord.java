package mn.alge.lessons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import mn.alge.db.DBAdapter;
import mn.alge.db.Word;
import mn.alge.english.R;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ChooseWord extends Activity {
	ArrayList<Word> wordList = new ArrayList<Word>();
	ArrayList<Word> wordList1 = new ArrayList<Word>(2);
	private TextToSpeech tts;
	Button btn1, btn2;
	ImageView ivChooseWord;

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_word);
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

		wordList = mDbHelper.retrieveWordsFindPic();
		Collections.shuffle(wordList);
		wordList1.add(wordList.get(0));
		wordList1.add(wordList.get(1));

		mDbHelper.close();

		ivChooseWord = (ImageView) findViewById(R.id.ivChooseWord);
		ivChooseWord.setImageBitmap(wordList1.get(0).getBitmap());

		final int n;
		// wordList1.get(randInt(0, 1));
		n = randInt(0, 1);

		btn1 = (Button) findViewById(R.id.btnChooseWord1);
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (n == 1) {
					tts.speak("try again", TextToSpeech.QUEUE_FLUSH, null);
				} else {
					tts.speak(wordList1.get(n).getEnglish(),
							TextToSpeech.QUEUE_FLUSH, null);
				}

			}
		});
		btn2 = (Button) findViewById(R.id.btnChooseWord2);
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (n == 1) {
					tts.speak(btn2.getText().toString(),
							TextToSpeech.QUEUE_FLUSH, null);
				} else {
					tts.speak("try again", TextToSpeech.QUEUE_FLUSH, null);
				}
			}
		});
		btn1.setText(wordList1.get(n).getEnglish().toString());
		if (n == 1) {
			btn2.setText(wordList1.get(0).getEnglish().toString());
		} else {
			btn2.setText(wordList1.get(1).getEnglish().toString());
		}

	}

	public int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
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
