package mn.alge.lessons;

import mn.alge.english.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Vocabulary extends Activity{
	
	/*public static Vocabulary newInstance(int num) {
		Vocabulary f = new Vocabulary();
		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);
		return f;
	}*/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vocabulary);
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