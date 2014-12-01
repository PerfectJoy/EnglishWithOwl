package mn.alge.level;

import mn.alge.english.R;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Intermediate extends Fragment implements OnClickListener{
	
	private Button btnLoadLesson;
	private TextView intermediateCounter;
	private boolean isDownloaded = false;
	
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
		btnLoadLesson = (Button)v.findViewById(R.id.btnLoadIntermediate);
		intermediateCounter = (TextView)v.findViewById(R.id.intermediateCounter);
		
		intermediateCounter.setText("0" + getString(R.string.counter_all));
		
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/font_counter.ttf");
		intermediateCounter.setTypeface(font);
		
		if(isDownloaded)
			btnLoadLesson.setEnabled(true);
		else
			btnLoadLesson.setEnabled(false);
		
		btnLoadLesson.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLoadIntermediate:
			Intent subActivity = new Intent(getActivity(),
                    IntermediateLesson.class);
			
			getActivity().startActivity(subActivity);
			getActivity().overridePendingTransition(R.anim.in, R.anim.out);
			break;

		default:
			break;
		}
	}

}
