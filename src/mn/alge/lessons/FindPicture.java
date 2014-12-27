package mn.alge.lessons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import mn.alge.db.DBAdapter;
import mn.alge.db.ImageAdapter;
import mn.alge.db.Word;
import mn.alge.english.R;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class FindPicture extends Activity {
	ArrayList<Word> wordList = new ArrayList<Word>();
	ArrayList<Word> wordList4 = new ArrayList<Word>(4);
	private TextToSpeech tts;
	TextView tvFindPicture;

	@Override
	protected void onStart() {
		super.onStart();
	}

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
		DBAdapter mDbHelper = new DBAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();

		wordList = mDbHelper.retrieveWordsFindPic();
		Collections.shuffle(wordList);
		wordList4.add(wordList.get(0));
		wordList4.add(wordList.get(1));
		wordList4.add(wordList.get(2));
		wordList4.add(wordList.get(3));

		mDbHelper.close();

		GridView gridView = (GridView) findViewById(R.id.grid_view_find_picture);
		// Instance of ImageAdapter Class
		gridView.setAdapter(new FindPictureAdapter(this, wordList4));

		Collections.shuffle(wordList4);

		tvFindPicture = (TextView) findViewById(R.id.tvFindPicture);
		tvFindPicture.setText(wordList4.get(0).getEnglish().toString());

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					tts.speak(wordList4.get(position).getEnglish(),
							TextToSpeech.QUEUE_FLUSH, null);
					//daraagiin dasgal
				}else{
					tts.speak("try again",
							TextToSpeech.QUEUE_FLUSH, null);
				}
			}
		});

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
