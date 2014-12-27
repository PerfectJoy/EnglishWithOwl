package mn.alge.db;

import android.graphics.Bitmap;

public class Word {
	private Bitmap bmp;
	private String english;
	private String mongol;

	public Word(Bitmap b, String e, String m) {
		bmp = b;
		english = e;
		mongol = m;
	}

	public Bitmap getBitmap() {
		return bmp;
	}

	public String getEnglish() {
		return english;
	}

	public String getMongol() {
		return mongol;
	}

}
