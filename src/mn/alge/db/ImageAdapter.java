package mn.alge.db;

import java.util.ArrayList;

import mn.alge.english.R;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	ArrayList<Word> wordList = new ArrayList<Word>();
	private DBAdapter mDbHelper;

	// Constructor
	public ImageAdapter(Context c, ArrayList<Word> wordList) {
		mContext = c;
		this.wordList = wordList;
	}

	@Override
	public int getCount() {
		return wordList.size();

	}

	@Override
	public Object getItem(int position) {
		return wordList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ImageView imageView = new ImageView(mContext);
		// imageView.setImageBitmap(wordList.get(position).getBitmap());
		// imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		// imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
		// return imageView;
		View grid;
		
		//DBAdapter mDbHelper = new DBAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();

		Cursor testdata = mDbHelper.getTestDataContent();
		
		String english = Utility.GetColumnValueWord(testdata, "english");
		String mongol = Utility.GetColumnValueWord(testdata, "mongol");
		byte[] blob = Utility.GetColumnValuePicture(testdata, "picture");
		
		Bitmap b;
		b=Utility.getPhoto(blob);
		
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			grid = new View(mContext);
			grid = inflater.inflate(R.layout.vocabulary1_grid_single, null);
			TextView textView1 = (TextView) grid.findViewById(R.id.grid_text1);
			TextView textView2 = (TextView) grid.findViewById(R.id.grid_text2);
			ImageView imageView = (ImageView) grid
					.findViewById(R.id.grid_image);
			textView1.setText(english);
			textView2.setText(mongol);
			imageView.setImageBitmap(b);
		} else {
			grid = (View) convertView;
		}
		return grid;
	}

}
