package mn.alge.lessons;

import java.util.ArrayList;

import mn.alge.db.Word;
import mn.alge.english.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ListenChooseAdapter extends BaseAdapter{
	private Context mContext;
	ArrayList<Word> wordList = new ArrayList<Word>();

	// Constructor
	public ListenChooseAdapter(Context c, ArrayList<Word> wordList) {
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
		View grid;
		
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			grid = new View(mContext);
			grid = inflater.inflate(R.layout.find_picture_grid_single, parent, false);
			ImageView imageView = (ImageView) grid
					.findViewById(R.id.grid_image);
			imageView.setImageBitmap(wordList.get(position).getBitmap());
			imageView.setTag(wordList.get(position).getEnglish().toString());

		} else {
			grid = (View) convertView;
		}
		return grid;
	}

}
