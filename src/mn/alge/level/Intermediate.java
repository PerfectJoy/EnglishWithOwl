package mn.alge.level;

import mn.alge.english.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Intermediate extends Fragment{
	
	public static ImageView imgIntermediate;
	
	public static Intermediate newInstance(int num) {
		Intermediate f = new Intermediate();
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

		View v = inflater.inflate(R.layout.intermediate, container, false);
		imgIntermediate = (ImageView)v.findViewById(R.id.imgIntermediate);
		return v;
	}

}
