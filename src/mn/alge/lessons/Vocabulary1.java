package mn.alge.lessons;

import java.util.ArrayList;

import mn.alge.db.DBAdapter;
import mn.alge.db.ImageAdapter;
import mn.alge.db.Word;
import mn.alge.english.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class Vocabulary1 extends Activity {

	ArrayList<Word> wordList = new ArrayList<Word>();

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocabulary1);

		DBAdapter mDbHelper = new DBAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();

		wordList = mDbHelper.retriveallWrdDetails();

		mDbHelper.close();

		GridView gridView = (GridView) findViewById(R.id.grid_view);
		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapter(this, wordList));
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
