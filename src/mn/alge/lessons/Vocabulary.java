package mn.alge.lessons;

import java.util.ArrayList;

import mn.alge.english.R;
import mn.alge.db.Word;
import mn.alge.db.ImageAdapter;
import mn.alge.db.TestAdapter;
import mn.alge.db.Utility;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Vocabulary extends Activity{
	
	ArrayList<Word> wordList = new ArrayList<Word>();
	@Override
	protected void onStart() {
		super.onStart();
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocabulary);
		
		TestAdapter mDbHelper = new TestAdapter(this);
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
