package mn.alge.lessons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import mn.alge.db.DBAdapter;
import mn.alge.db.Word;
import mn.alge.english.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ListenChoose extends Activity {

	ArrayList<Word> wordList = new ArrayList<Word>();
	ArrayList<Word> wordList4 = new ArrayList<Word>(4);
	private TextToSpeech tts;
	TextView tvListenChoose;
	ImageButton btnHelp, btnDugar;

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listen_choose);
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
		wordList4.add(wordList.get(0));
		wordList4.add(wordList.get(1));
		wordList4.add(wordList.get(2));
		wordList4.add(wordList.get(3));

		mDbHelper.close();

		GridView gridView = (GridView) findViewById(R.id.grid_view_listen_choose);
		// Instance of ImageAdapter Class
		gridView.setAdapter(new ListenChooseAdapter(this, wordList4));

		final int n;
		n = randInt(0, 3);

		tvListenChoose = (TextView) findViewById(R.id.tvListenChoose);
		tvListenChoose.setText(wordList4.get(n).getMongol().toString());

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == n) {
//					tts.speak(wordList4.get(position).getEnglish(),
//							TextToSpeech.QUEUE_FLUSH, null);
					// daraagiin dasgal
					tts.speak("good", TextToSpeech.QUEUE_FLUSH, null);
					Toast.makeText(getApplicationContext(), "+1 оноо авлаа", 0).show();
					finish();
				} else {
					tts.speak("try again", TextToSpeech.QUEUE_FLUSH, null);
					Toast.makeText(getApplicationContext(), "дахин оролдоно уу", Toast.LENGTH_SHORT).show();
				}
			}
		});
		btnHelp = (ImageButton) findViewById(R.id.btnListenChooseHelp);
		btnHelp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvListenChoose.setVisibility(View.VISIBLE);
			}
		});
		btnDugar = (ImageButton) findViewById(R.id.btnListenChooseDugar);
		btnDugar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tts.speak(wordList4.get(n).getEnglish(),
						TextToSpeech.QUEUE_FLUSH, null);
			}
		});
		btnDugar.performClick();

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
