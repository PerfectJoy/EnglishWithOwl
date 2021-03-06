package mn.alge.english;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		final SharedPreferences sp = getSharedPreferences("isFirstTimeLaunch", MODE_WORLD_READABLE);
				
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		    @Override
		    public void run() {
		    	
		    	if(sp.getBoolean("isFirstTimeLaunch", false)){
		    		Intent load =  new Intent(Splash.this, Main.class);
		            startActivity(load);
		            finish();
					
				}else{
					Intent load =  new Intent(Splash.this, WalkThrough.class);
		            startActivity(load);
		            finish();
				}
		    	
		    }
		}, 2000); //2 second iin daraa uildel hiigdene
	}
	
	

}
