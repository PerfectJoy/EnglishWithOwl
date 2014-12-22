package mn.alge.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionChecker {

	Context mContext;

    public ConnectionChecker(Context context){
        this.mContext = context;
    }

    public String getUserName(){
        return "test";
    }

    public boolean isNetworkConnected() {
          ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo ni = cm.getActiveNetworkInfo();
          if (ni == null) {
           // There are no active networks.
           return false;
          } else
           return true;
    }
}