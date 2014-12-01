package mn.alge.level;

import mn.alge.english.R;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Beginner extends Fragment implements OnClickListener{
	
	private TextView beginnerCounter;
	private Button btnLoadLesson;
	
	public static Beginner newInstance(int num) {
		Beginner f = new Beginner();
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
		beginnerCounter = (TextView)v.findViewById(R.id.beginnerCounter);
		btnLoadLesson = (Button)v.findViewById(R.id.btnLoadBeginner);
		
		beginnerCounter.setText("0" + getString(R.string.counter_all));
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/font_counter.ttf");
		beginnerCounter.setTypeface(font);
		
		btnLoadLesson.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLoadBeginner:
            Intent subActivity = new Intent(getActivity(),
                    BeginnerLesson.class);
			
			getActivity().startActivity(subActivity);
			getActivity().overridePendingTransition(R.anim.in, R.anim.out);
			break;

		default:
			break;
		}
	}

}
