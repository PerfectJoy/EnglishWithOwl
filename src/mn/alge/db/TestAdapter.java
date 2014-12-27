package mn.alge.db;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestAdapter {
	protected static final String TAG = "DataAdapter";

	private final Context mContext;
	private SQLiteDatabase mDb;
	private DataBaseHelper mDbHelper;

	ArrayList<Word> wordList = new ArrayList<Word>();

	public TestAdapter(Context context) {
		this.mContext = context;
		mDbHelper = new DataBaseHelper(mContext);
	}

	public TestAdapter createDatabase() throws SQLException {
		try {
			mDbHelper.createDataBase();
		} catch (IOException mIOException) {
			Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
			throw new Error("UnableToCreateDatabase");
		}
		return this;
	}

	public TestAdapter open() throws SQLException {
		try {
			mDbHelper.openDataBase();
			mDbHelper.close();
			mDb = mDbHelper.getReadableDatabase();
		} catch (SQLException mSQLException) {
			Log.e(TAG, "open >>" + mSQLException.toString());
			throw mSQLException;
		}
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public Cursor getTestDataContent() {
		try {
			String sql = "SELECT _id, english, mongol, picture FROM Content";

			Cursor mCur = mDb.rawQuery(sql, null);
			if (mCur != null) {
				mCur.moveToNext();
			}
			return mCur;
		} catch (SQLException mSQLException) {
			Log.e(TAG, "getTestData >>" + mSQLException.toString());
			throw mSQLException;
		}
	}

	// To get list of employee details
	public ArrayList<Word> retriveallWrdDetails() throws SQLException {
		Cursor cur = mDb.query(true, "Content", new String[] { "picture",
				"english", "mongol" }, null, null, null, null, null, null);
		if (cur.moveToFirst()) {
			do {
				byte[] blob = cur.getBlob(cur.getColumnIndex("picture"));
				String english = cur.getString(cur.getColumnIndex("english"));
				String mongol = cur.getString(cur.getColumnIndex("mongol"));
				wordList.add(new Word(Utility.getPhoto(blob), english, mongol));
			} while (cur.moveToNext());
		}
		return wordList;
	}
}
