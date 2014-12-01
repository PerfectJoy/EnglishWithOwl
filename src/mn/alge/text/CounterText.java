package mn.alge.text;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CounterText extends TextView {
	public CounterText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CounterText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CounterText(Context context) {
		super(context);
		init();
	}

	private void init() {
		if (!isInEditMode()) {
			Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
					"fonts/font_counter.ttf");
			setTypeface(tf);
		}
	}
	
}