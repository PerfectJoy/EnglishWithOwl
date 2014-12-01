package mn.alge.level;

import mn.alge.english.R;
import mn.alge.english.WalkThrough.WalkItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Beginner extends Fragment{
	
	public static Beginner newInstance(int num) {
		Beginner f = new Beginner();
		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.beginner, container, false);
		
		return v;
	}

}
