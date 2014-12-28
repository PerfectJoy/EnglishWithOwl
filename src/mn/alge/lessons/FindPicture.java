package mn.alge.lessons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import mn.alge.db.DBAdapter;
import mn.alge.db.Word;
import mn.alge.english.R;
import mn.alge.text.CounterText;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class FindPicture extends Activity {
	ArrayList<Word> wordList = new ArrayList<Word>();
	ArrayList<Word> wordList4 = new ArrayList<Word>(4);
	private TextToSpeech tts;
	private TextView tvFindPicture,score;
	private GridView grid;

	@Override
	protected void onStart() {
		super.onStart();
	}
	
	private int result;
	private int onoo=0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.find_picture);
		
		
		tts = new TextToSpeech(getApplicationContext(),
				new TextToSpeech.OnInitListener() {
					@Override
					public void onInit(int status) {
						if (status != TextToSpeech.ERROR) {
							tts.setLanguage(Locale.ENGLISH);
						}
					}
				});
		grid = (GridView) findViewById(R.id.grid_view_find_picture);
		tvFindPicture = (TextView) findViewById(R.id.tvFindPicture);
		score = (TextView)findViewById(R.id.vocaCounterText);
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/font_counter.ttf");
		score.setTypeface(font);
		setImages();
		
		result = randInt(0, 3);
		tvFindPicture.setText(wordList4.get(result).getEnglish().toString());
		
		
		grid.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == result) {
					tts.speak(wordList4.get(position).getEnglish(),
							TextToSpeech.QUEUE_FLUSH, null);
					//tvFindPicture.setText("");
					//grid.setAdapter(null);
					//setImages();
				}else{
					tts.speak("try again",
							TextToSpeech.QUEUE_FLUSH, null);
					Toast.makeText(getApplicationContext(), "wrong answer", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private void setImages(){
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

		Collections.shuffle(wordList4);
		
		grid.setAdapter(new FindPictureAdapter(this, wordList4));

		
	}

	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}
	public int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

}
