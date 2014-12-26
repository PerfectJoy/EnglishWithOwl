package mn.alge.db;

import android.database.Cursor;

public class Utility {

 	public static String GetColumnValueWord(Cursor cur, String ColumnName) {
		try {
			return cur.getString(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return "";
		}
	}
 	
 	public static byte[] GetColumnValuePicture(Cursor cur, String ColumnName) {
		try {
			//byte[] blob = cur.getBlob(cur.getColumnIndex(ColumnName));
			return cur.getBlob(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return null;
		}
	}
}
