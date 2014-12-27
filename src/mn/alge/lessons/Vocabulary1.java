package mn.alge.lessons;

import java.util.ArrayList;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class Vocabulary1 extends Activity {

	ArrayList<Word> wordList = new ArrayList<Word>();
	private TextToSpeech tts;

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocabulary1);
		tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
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

		wordList = mDbHelper.retriveallWrdDetails();

		mDbHelper.close();

		GridView gridView = (GridView) findViewById(R.id.grid_view);
		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapter(this, wordList));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				tts.speak(wordList.get(position).getEnglish(), TextToSpeech.QUEUE_FLUSH, null);
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
