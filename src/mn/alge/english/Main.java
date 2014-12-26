package mn.alge.english;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.request.Requests.LoadRequestsResult;
import com.google.android.gms.games.request.Requests.UpdateRequestsResult;
import com.google.android.gms.plus.Plus;

import mn.alge.gameutils.BaseGameUtils;
import mn.alge.level.Beginner;
import mn.alge.level.Expert;
import mn.alge.level.Intermediate;
import mn.alge.util.ConnectionChecker;
import mn.alge.util.TabViewPager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends ActionBarActivity implements PageTransformer,
	GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
	View.OnClickListener{


	private static final String TAG = "EnglishWithOwl";

	private static final int SHOW_INBOX = 1;

	private static final int SEND_GIFT_CODE = 2;

	private static final int SEND_REQUEST_CODE = 3;

	/** Default lifetime of a request, 1 week. */
	private static final int DEFAULT_LIFETIME = 7;

	/** Icon to be used to send gifts/requests */
	private Bitmap mGiftIcon;
	private static final int RC_SIGN_IN = 9001;
	private GoogleApiClient mGoogleApiClient;
	private boolean mResolvingConnectionFailure = false;
	private boolean mSignInClicked = false;
	private boolean mAutoStartSignInFlow = true;
	
	ConnectionChecker checkConnection;
	boolean isConnectedInternet;
	
	private ImageView imgAvatar;
	
	private ViewPager vpager;
	private LevelAdapter adapter;
	private TabViewPager tabs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		checkConnection = new ConnectionChecker(getApplicationContext());
		isConnectedInternet = checkConnection.isNetworkConnected();
		setContentView(R.layout.main);
		
		mGoogleApiClient = new GoogleApiClient.Builder(this)
        	.addConnectionCallbacks(this)
        	.addOnConnectionFailedListener(this)
        	.addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
        	.addApi(Games.API).addScope(Games.SCOPE_GAMES)
        	.build();
		
		tabs = (TabViewPager)findViewById(R.id.tabs);
		vpager = (ViewPager)findViewById(R.id.vpager);
		adapter = new LevelAdapter(getSupportFragmentManager());
		vpager.setAdapter(adapter);
		
		vpager.setPageTransformer(false, this);
		tabs.setViewPager(vpager);
		
		imgAvatar = (ImageView)findViewById(R.id.avatar);

		findViewById(R.id.button_sign_in).setOnClickListener(this);
		findViewById(R.id.button_sign_out).setOnClickListener(this);

	}
	
	
	@Override
	public void transformPage(View view, float pos) {
		view.setRotationY(pos*-45);

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
		case R.id.userLogin:
			return true;
        case R.id.settings:
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
            return true;
        default:
            return super.onOptionsItemSelected(item);
		}
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
	    case R.id.button_sign_in:
	        mSignInClicked = true;
	        mGoogleApiClient.connect();
	        break;
	    case R.id.button_sign_out:
	        mSignInClicked = false;
	        Games.signOut(mGoogleApiClient);
	        mGoogleApiClient.disconnect();
	        showSignInBar();
	        break;

		}
	}


	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		if (mResolvingConnectionFailure) {
		    return;
		}

		if (mSignInClicked || mAutoStartSignInFlow) {
		    mAutoStartSignInFlow = false;
		    mSignInClicked = false;
		    mResolvingConnectionFailure = BaseGameUtils
		            .resolveConnectionFailure(this, mGoogleApiClient,
		            		arg0, RC_SIGN_IN, getString(R.string.signin_other_error));
		}
		showSignInBar();
	}
	
	
	
	@Override
	protected void onResume() {
//        mSignInClicked = true;
//        if(isConnectedInternet)
//        	mGoogleApiClient.connect();
		super.onResume();
	}


	private void showSignInBar() {
		findViewById(R.id.sign_in_bar).setVisibility(View.VISIBLE);
		findViewById(R.id.sign_out_bar).setVisibility(View.GONE);
		imgAvatar.setImageBitmap(null);
	}

	private void showSignOutBar() {
		findViewById(R.id.sign_in_bar).setVisibility(View.GONE);
		findViewById(R.id.sign_out_bar).setVisibility(View.VISIBLE);
		Player player = Games.Players.getCurrentPlayer(mGoogleApiClient);
        String url = player.getIconImageUrl();
        if (url != null) {
            ImageView vw = (ImageView) findViewById(R.id.avatar);

            // load the image in the background.
            new DownloadImageTask(vw).execute(url);
         }
        String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
	}
	class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap mIcon11 = null;
            String url = strings[0];
            try {
                InputStream in = new URL(url).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            bmImage.setVisibility(View.VISIBLE);
        }
    }
	private int countNotExpired(GameRequestBuffer buf) {
		if (buf == null) {
		    return 0;
		}

		int giftCount = 0;
		for (GameRequest gr : buf) {
		    if (gr.getExpirationTimestamp() > System.currentTimeMillis()) {
		        giftCount++;
		    }
		}
		return giftCount;
	}
	private void updateRequestCounts() {
		PendingResult<Requests.LoadRequestsResult> result = Games.Requests
		        .loadRequests(mGoogleApiClient,
		                Requests.REQUEST_DIRECTION_INBOUND,
		                GameRequest.TYPE_ALL,
		                Requests.SORT_ORDER_EXPIRING_SOON_FIRST);
		result.setResultCallback(mLoadRequestsCallback);
	}

	private final ResultCallback<Requests.LoadRequestsResult> mLoadRequestsCallback =
		    new ResultCallback<Requests.LoadRequestsResult>() {

		        @Override
		        public void onResult(LoadRequestsResult result) {
		            int giftCount = countNotExpired(result.getRequests(GameRequest.TYPE_GIFT));
		            int wishCount = countNotExpired(result.getRequests(GameRequest.TYPE_WISH));

		            /*((TextView) findViewById(R.id.tv_gift_count)).setText(String
		                    .format(getString(R.string.gift_count), giftCount));
		            ((TextView) findViewById(R.id.tv_request_count)).setText(String
		                    .format(getString(R.string.request_count), wishCount));*/
		        }

	};
	private final OnRequestReceivedListener mRequestListener = new OnRequestReceivedListener() {

		@Override
		public void onRequestReceived(GameRequest request) {
		    int requestStringResource;
		    switch (request.getType()) {
		        case GameRequest.TYPE_GIFT:
		            requestStringResource = R.string.new_gift_received;
		            break;
		        case GameRequest.TYPE_WISH:
		            requestStringResource = R.string.new_request_received;
		            break;
		        default:
		            return;
		    }
		    Toast.makeText(Main.this, requestStringResource,
		            Toast.LENGTH_LONG).show();
		    updateRequestCounts();
		}


		@Override
		public void onRequestRemoved(String requestId) {
		    updateRequestCounts();
		}
	};

	@Override
	public void onConnected(Bundle connectionHint) {
		showSignOutBar();
		Games.Requests.registerRequestListener(mGoogleApiClient, mRequestListener);

		if (connectionHint != null) {

		    ArrayList<GameRequest> requests;
		    requests = Games.Requests.getGameRequestsFromBundle(connectionHint);
		    if (!requests.isEmpty()) {
		        // We have requests in onConnected's connectionHint.
		        Log.d(TAG, "onConnected: connection hint has " + requests.size() + " request(s)");
		    }
		    Log.d(TAG, "===========\nRequests count " + requests.size());
		    handleRequests(requests);
		}

		// Our sample displays the request counts.
		updateRequestCounts();
	}


	@Override
	public void onConnectionSuspended(int arg0) {
		mGoogleApiClient.connect();
	}
	
	
	private void showSendIntent(int type) {
		// Make sure we have a valid API client.
		if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {

		    String description;
		    int intentCode;
		    Bitmap icon;
		    switch (type) {
		    case GameRequest.TYPE_GIFT:
		        description = getString(R.string.send_gift_description);
		        intentCode = SEND_GIFT_CODE;
		        icon = mGiftIcon;
		        break;
		    case GameRequest.TYPE_WISH:
		        description = getString(R.string.send_request_description);
		        intentCode = SEND_REQUEST_CODE;
		        icon = mGiftIcon;
		        break;
		    default:
		        return;
		    }
		    Intent intent = Games.Requests.getSendIntent(mGoogleApiClient, type,
		            "".getBytes(), DEFAULT_LIFETIME, icon, description);
		    startActivityForResult(intent, intentCode);
		}
		}

		private String getRequestsString(ArrayList<GameRequest> requests) {
		if (requests.size() == 0) {
		    return "You have no requests to accept.";
		}

		if (requests.size() == 1) {
		    return "Do you want to accept this request from "
		            + requests.get(0).getSender().getDisplayName() + "?";
		}

		StringBuffer retVal = new StringBuffer(
		        "Do you want to accept the following requests?\n\n");

		for (GameRequest request : requests) {
		    retVal.append("  A "
		            + (request.getType() == GameRequest.TYPE_GIFT ? "gift"
		                    : "game request") + " from "
		            + request.getSender().getDisplayName() + "\n");
		}

		return retVal.toString();
		}

		// Actually accepts the requests
		private void acceptRequests(ArrayList<GameRequest> requests) {
		// Attempt to accept these requests.
		ArrayList<String> requestIds = new ArrayList<String>();

		/**
		 * Map of cached game request ID to its corresponding game request
		 * object.
		 */
		final HashMap<String, GameRequest> gameRequestMap = new HashMap<String, GameRequest>();

		// Cache the requests.
		for (GameRequest request : requests) {
		    String requestId = request.getRequestId();
		    requestIds.add(requestId);
		    gameRequestMap.put(requestId, request);

		    Log.d(TAG, "Processing request " + requestId);
		}
		// Accept the requests.
		Games.Requests.acceptRequests(mGoogleApiClient, requestIds).setResultCallback(
		        new ResultCallback<UpdateRequestsResult>() {
		            @Override
		            public void onResult(UpdateRequestsResult result) {
		                int numGifts = 0;
		                int numRequests = 0;
		                // Scan each result outcome.
		                for (String requestId : result.getRequestIds()) {
		                    // We must have a local cached copy of the request
		                    // and the request needs to be a
		                    // success in order to continue.
		                    if (!gameRequestMap.containsKey(requestId)
		                            || result.getRequestOutcome(requestId) != Requests.REQUEST_UPDATE_OUTCOME_SUCCESS) {
		                        continue;
		                    }
		                    // Update succeeded here. Find the type of request
		                    // and act accordingly. For wishes, a
		                    // responding gift will be automatically sent.
		                    switch (gameRequestMap.get(requestId).getType()) {
		                    case GameRequest.TYPE_GIFT:
		                        // Toast the player!
		                        ++numGifts;
		                        break;
		                    case GameRequest.TYPE_WISH:
		                        ++numRequests;
		                        break;
		                    }
		                }

		                if (numGifts != 0) {
		                    // Toast our gifts.
		                    Toast.makeText(
		                            Main.this,
		                            String.format(
		                                    getString(R.string.gift_toast),
		                                    numGifts), Toast.LENGTH_LONG)
		                            .show();
		                }
		                if (numGifts != 0 || numRequests != 0) {
		                    // if the user accepted any gifts or requests,
		                    // update
		                    // the displayed counts
		                    updateRequestCounts();
		                }
		            }
		        });

		}

		// Deal with any requests that are incoming, either from a bundle from the
		// app starting via notification, or from the inbox. Players should give
		// explicit approval to accept any gift or request, so we pop up a dialog.
		private void handleRequests(ArrayList<GameRequest> requests) {
		if (requests == null) {
		    return;
		}

		// Must have final for anonymous function
		final ArrayList<GameRequest> theRequests = requests;

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getRequestsString(requests))
		        .setPositiveButton("Absolutely!",
		                new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface dialog, int id) {
		                        acceptRequests(theRequests);
		                    }
		                })
		        .setNegativeButton("No thanks",
		                new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface dialog, int id) {
		                        // Do nothing---requests will remain un-created.
		                    }
		                });
		// Create the AlertDialog object and return it
		builder.create().show();

		}

		// Response to inbox check
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		    case SEND_REQUEST_CODE:
		        if (resultCode == GamesActivityResultCodes.RESULT_SEND_REQUEST_FAILED) {
		            Toast.makeText(this, "FAILED TO SEND REQUEST!",
		                    Toast.LENGTH_LONG).show();
		        }
		        break;
		    case SEND_GIFT_CODE:
		        if (resultCode == GamesActivityResultCodes.RESULT_SEND_REQUEST_FAILED) {
		            Toast.makeText(this, "FAILED TO SEND GIFT!", Toast.LENGTH_LONG)
		                    .show();
		        }
		        break;
		    case SHOW_INBOX:
		        if (resultCode == Activity.RESULT_OK && data != null) {
		            handleRequests(Games.Requests
		                    .getGameRequestsFromInboxResponse(data));
		        } else {
		            Log.e(TAG, "Failed to process inbox result: resultCode = "
		                    + resultCode + ", data = "
		                    + (data == null ? "null" : "valid"));
		        }
		        break;
		    case RC_SIGN_IN:
		        Log.d(TAG, "onActivityResult with requestCode == RC_SIGN_IN, responseCode="
		                + resultCode + ", intent=" + data);
		        mSignInClicked = false;
		        mResolvingConnectionFailure = false;
		        if (resultCode == RESULT_OK) {
		            mGoogleApiClient.connect();
		        } else {
		            BaseGameUtils.showActivityResultError(this, requestCode, resultCode, R.string.signin_other_error);
		        }
		        break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	
	public class LevelAdapter extends FragmentStatePagerAdapter{

		protected String[] ProductDetailPageAdapterTitles = new String[] { 
				getResources().getString(R.string.beginner),
				getResources().getString(R.string.intermediate),
				getResources().getString(R.string.expert)
		}; 
		protected int[] ICONS = new int[] {
            R.drawable.ic_launcher
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
