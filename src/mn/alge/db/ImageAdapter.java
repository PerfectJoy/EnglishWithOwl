package mn.alge.db;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	ArrayList<Word> wordList = new ArrayList<Word>();

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
		ImageView imageView = new ImageView(mContext);
		imageView.setImageBitmap(wordList.get(position).getBitmap());
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
		return imageView;
	}

}
