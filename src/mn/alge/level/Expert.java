package mn.alge.level;

import mn.alge.english.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Expert extends Fragment{
	
	
	View v;
	public static Expert newInstance(int num) {
		Expert f = new Expert();
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
			v = inflater.inflate(R.layout.expert, container, false);

		/*Regular text = (Regular) v.findViewById(R.id.walk_item_text);
		switch (mNum) {
		case 0:
			text.setText(getActivity().getString(R.string.walk_one));
			break;
		case 1:
			text.setText(getActivity().getString(R.string.walk_two));
			break;
		default:
			break;
		}*/
		return v;
	}

}
