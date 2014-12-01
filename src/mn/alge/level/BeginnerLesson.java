package mn.alge.level;

import mn.alge.english.R;
import mn.alge.english.Settings;
import mn.alge.english.WalkThrough.WalkItem;
import mn.alge.util.MultiViewPager;
import android.R.anim;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BeginnerLesson extends ActionBarActivity 
	implements PageTransformer, OnPageChangeListener{

	private MultiViewPager categoryPager;
	private FragmentStatePagerAdapter adapter;
	private View layoutTopic;
	
	private Button btnBeginnerVoc, btnBeginnerFindPictures, btnBeginnerChooseTheWords,
		btnBeginnerListenChoose, btnBeginnerListenWrite, btnBeginnerMatchWords,btnBeginnerWriteWords;
	
    private static float MIN_SCALE = 0.85f;
    private static float MIN_ALPHA = 0.5f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.beginner_lesson);
		
		categoryPager = (MultiViewPager)findViewById(R.id.categoryPager);
		layoutTopic = (View)findViewById(R.id.layoutTopic);
		
				//Topic buttons
		btnBeginnerVoc = (Button)layoutTopic.findViewById(R.id.btnBeginnerVoc);
		btnBeginnerFindPictures = (Button)layoutTopic.findViewById(R.id.btnBeginnerFindPictures);
		btnBeginnerChooseTheWords=(Button)layoutTopic.findViewById(R.id.btnBeginnerChooseTheWords);
		btnBeginnerListenChoose=(Button)layoutTopic.findViewById(R.id.btnBeginnerListenChoose);
		btnBeginnerListenWrite=(Button)layoutTopic.findViewById(R.id.btnBeginnerListenWrite);
		btnBeginnerMatchWords=(Button)layoutTopic.findViewById(R.id.btnBeginnerMatchWords);
		btnBeginnerWriteWords=(Button)layoutTopic.findViewById(R.id.btnBeginnerWriteWords);
		
		
		int width = getWindowManager().getDefaultDisplay().getWidth();
		int height = getWindowManager().getDefaultDisplay().getHeight();
		LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,
				Integer.parseInt(String.valueOf(height/3)));
		categoryPager.setLayoutParams(parms);
		adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
                return new CategoryBeginner();
            }
        };
		
		categoryPager.setAdapter(adapter);
		categoryPager.setPageTransformer(false, this);
		categoryPager.setOnPageChangeListener(this);
	}

	@Override
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            view.setAlpha(0);
        }
	}
	

	@Override
	public void onPageScrollStateChanged(int arg0) {
		switch (arg0) {
		case 0:
			Toast.makeText(getApplicationContext(), String.valueOf(arg0), Toast.LENGTH_SHORT).show();
			break;
		case 1:
			Toast.makeText(getApplicationContext(), String.valueOf(arg0), Toast.LENGTH_SHORT).show();			
			break;
		case 2:
			Toast.makeText(getApplicationContext(), String.valueOf(arg0), Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inf = getMenuInflater();
		inf.inflate(R.menu.beginner, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finishAct();
			return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}
	
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finishAct();
	}
	
	
	private void finishAct(){
		this.finish();
		overridePendingTransition(R.anim.in, R.anim.out);
	}
	
	public static class CategoryBeginner extends Fragment {
		int mNum;
		View v;

		public static CategoryBeginner newInstance(int num) {
			CategoryBeginner f = new CategoryBeginner();
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

			v = inflater.inflate(R.layout.beginner_category_item_image, container, false);
			return v;
		}
	}

}
