package mn.alge.level;

import mn.alge.english.R;
import mn.alge.lessons.FindPicture;
import mn.alge.lessons.Vocabulary1;
import mn.alge.util.MultiViewPager;
import mn.alge.util.arcmenu.ArcMenu;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BeginnerLesson extends ActionBarActivity 
	implements PageTransformer, OnPageChangeListener{

	private MultiViewPager categoryPager;
	private FragmentStatePagerAdapter adapter;
	private View layoutTopic;
	
    private static float MIN_SCALE = 0.85f;
    private static float MIN_ALPHA = 0.5f;
    
    /*private static final int[] ITEM_DRAWABLES = { R.drawable.btn_bg_1_vocabulary, 
    			R.drawable.btn_bg_2_findingpictures, R.drawable.btn_bg_3_choosewords, 
    			R.drawable.btn_bg_4_listenchoose, R.drawable.btn_bg_5_matchwords,
				R.drawable.btn_bg_6_listenwrite, R.drawable.btn_bg_7_writewords};*/
    private static final int[] ITEM_DRAWABLES = { R.drawable.btn_ls1_voca, 
		R.drawable.btn_ls2_findpic, R.drawable.btn_ls3_chooseword, 
		R.drawable.btn_ls4_listenchoose, R.drawable.btn_ls5_matchwords,
		R.drawable.btn_ls6_listenwrite, R.drawable.btn_ls7_writewords};

    private ArcMenu arcMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.beginner_lesson);
		
		categoryPager = (MultiViewPager)findViewById(R.id.categoryPager);
		
        
		layoutTopic = (View)findViewById(R.id.layoutTopic);
		
		arcMenu = (ArcMenu)layoutTopic.findViewById(R.id.beginnerMenu);

        initArcMenu(arcMenu, ITEM_DRAWABLES);
		
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
                return CategoryBeginner.newInstance(position);
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
		Animation fadeOut, fadeIn;
		fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
		fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		switch (arg0) {
		case 0:
			layoutTopic.setVisibility(View.VISIBLE);
			break;
		case 1:
			layoutTopic.setVisibility(View.INVISIBLE);	
			layoutTopic.startAnimation(fadeOut);
			break;
		case 2:
			layoutTopic.setVisibility(View.VISIBLE);
			layoutTopic.startAnimation(fadeIn);
			break;

		default:
			break;
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {		
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
	
	private void initArcMenu(ArcMenu menu, int[] itemDrawables) {
        final int itemCount = itemDrawables.length;
        for (int i = 0; i < itemCount; i++) {
            ImageView item = new ImageView(this);
            item.setImageResource(itemDrawables[i]);

            final int position = i;
            menu.addItem(item, new OnClickListener() {

                @Override
                public void onClick(View v) {
                	//FragmentManager fr = getSupportFragmentManager();
                	Intent i = null;
                	switch (position) {
					case 0:
						i = new Intent(getApplicationContext(), Vocabulary1.class);
						/*fr.beginTransaction().replace(R.id.beg_less_layout, Vocabulary.newInstance(position), "Voc")
							.addToBackStack(null).commit();*/
						break;
					case 1:
						i = new Intent(getApplicationContext(), FindPicture.class);
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;

					default:
						break;
					}
                	startActivity(i);
                    Toast.makeText(getApplicationContext(), "position:" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
	
	public static class CategoryBeginner extends Fragment {
		int mNum;
		View v;
		private ImageView categoryImage;

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
			categoryImage = (ImageView)v.findViewById(R.id.img_category);
			switch (mNum) {
			case 0:
				categoryImage.setImageResource(R.drawable.btn_bg_2_findingpictures);
				break;
			case 1:
				categoryImage.setImageResource(R.drawable.btn_bg_2_findingpictures);
				break;
			case 2:
				categoryImage.setImageResource(R.drawable.btn_bg_6_listenwrite);
				break;
			case 3:
				categoryImage.setImageResource(R.drawable.btn_bg_4_listenchoose);
				break;

			default:
				break;
			}
			return v;
		}
	}

}
