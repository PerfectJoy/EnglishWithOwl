package mn.alge.level;

import mn.alge.english.R;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Expert extends Fragment implements OnClickListener{
	
	private TextView expertCounter;
	private Button btnLoadLesson;
	private ImageView imgLock;
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

		btnLoadLesson = (Button)v.findViewById(R.id.btnLoadExpert);
		expertCounter = (TextView)v.findViewById(R.id.expertCounter);
		imgLock = (ImageView)v.findViewById(R.id.imgLock);
		expertCounter.setText("0" + getString(R.string.counter_all));
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/font_counter.ttf");
		expertCounter.setTypeface(font);
		
		btnLoadLesson.setOnClickListener(this);
		imgLock.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLoadExpert:
			Intent subActivity = new Intent(getActivity(),
                    ExpertLesson.class);
			
			getActivity().startActivity(subActivity);
			getActivity().overridePendingTransition(R.anim.in, R.anim.out);
			break;
		case R.id.imgLock:
			Toast.makeText(getActivity(), getString(R.string.error_load_activity), Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}


}
