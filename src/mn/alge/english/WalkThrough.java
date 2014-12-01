package mn.alge.english;

import mn.alge.english.R;
import mn.alge.util.CirclePageIndicator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class WalkThrough extends FragmentActivity implements 
	OnPageChangeListener, OnClickListener{
	
	private Button btnGo;
	private CirclePageIndicator indicator;
	private ViewPager pager;
	
	private WalkAdapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.walk);

				
		btnGo = (Button)findViewById(R.id.btnStart);
		indicator = (CirclePageIndicator)findViewById(R.id.indicator);
		pager = (ViewPager)findViewById(R.id.pager);
		
		btnGo.setOnClickListener(this);

		adapter = new WalkAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);

	    indicator.setViewPager(pager);
	    	
	    indicator.setOnPageChangeListener(this);
			
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnStart:
				SharedPreferences sp = getSharedPreferences("isFirstTimeLaunch", 
						MODE_WORLD_READABLE);
				SharedPreferences.Editor ed = sp.edit();
				ed.putBoolean("isFirstTimeLaunch", true);
				ed.commit();
				
				Intent i = new Intent(getApplicationContext(), Main.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				this.finish();
			break;
			default:
				break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

	@Override
	public void onPageSelected(int arg0) {
		if(arg0 == 2){
			indicator.setVisibility(View.GONE);
			btnGo.setVisibility(View.VISIBLE);
		}else{
			indicator.setVisibility(View.VISIBLE);
			btnGo.setVisibility(View.GONE);
		} 
	}
	
	public class WalkAdapter extends FragmentStatePagerAdapter {

		public WalkAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int position) {
			//if (position == 2) {
			//	return new Login().newInstance();
			//} else {
				return WalkItem.newInstance(position);
			//}
		}

		@Override
		public int getCount() {
			return 3;
		}
	}

	public static class WalkItem extends Fragment {
		int mNum;
		View v;

		public static WalkItem newInstance(int num) {
			WalkItem f = new WalkItem();
			// Supply num input as an argument.
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);
			return f;
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			mNum = getArguments() != null ? getArguments().getInt("num") : 1;

		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			v = inflater.inflate(R.layout.walk_item, container, false);
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
}
