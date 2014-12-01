package mn.alge.english;

import mn.alge.level.Beginner;
import mn.alge.level.Expert;
import mn.alge.level.Intermediate;
import mn.alge.util.TabViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Main extends ActionBarActivity implements PageTransformer{  

	private ViewPager vpager;
	private LevelAdapter adapter;
	private TabViewPager tabs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		tabs = (TabViewPager)findViewById(R.id.tabs);
		vpager = (ViewPager)findViewById(R.id.vpager);
		adapter = new LevelAdapter(getSupportFragmentManager());
		vpager.setAdapter(adapter);
		
		vpager.setPageTransformer(false, this);
		tabs.setViewPager(vpager);
	}

	@Override
	public void transformPage(View view, float pos) {
		view.setRotationY(pos*-30);
		
		//int pageWidth = view.getWidth();
		
		/*if (pos < -1) { 
	    	view.setAlpha(1);
		} else if (pos <= 1) { // [-1,1]
			Intermediate.imgIntermediate.setTranslationX(-pos * (pageWidth / 2)); //Half the normal speed
	    } else { 
	    	view.setAlpha(1);
	    }*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.settings:
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}
	
	public class LevelAdapter extends FragmentStatePagerAdapter {

		protected String[] ProductDetailPageAdapterTitles = new String[] { 
				getResources().getString(R.string.beginner),
				getResources().getString(R.string.intermediate),
				getResources().getString(R.string.expert)
		}; 
		
		public LevelAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return Beginner.newInstance(position);
			case 1:
				return Intermediate.newInstance(position);
			case 2:
				return Expert.newInstance(position);

			default:
				return Beginner.newInstance(position);
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return ProductDetailPageAdapterTitles[position % ProductDetailPageAdapterTitles.length];
		}
	}
	
}
