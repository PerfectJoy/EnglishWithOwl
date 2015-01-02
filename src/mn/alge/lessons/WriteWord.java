package mn.alge.lessons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import mn.alge.db.DBAdapter;
import mn.alge.db.Word;
import mn.alge.english.R;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WriteWord extends Activity {
	ArrayList<Word> wordList = new ArrayList<Word>();
	ArrayList<Word> wordList1 = new ArrayList<Word>(1);
	ArrayList<String> wordLetterArray = new ArrayList<String>();
	ArrayList<String> wordLetterArrayTaslalt = new ArrayList<String>();
	ArrayList<String> wordLetterArray2 = new ArrayList<String>();
	ArrayList<String> wordLetterArray3 = new ArrayList<String>();
	ArrayList<String> wordLetterArray4 = new ArrayList<String>();
	ArrayList<String> wordLetterArray5 = new ArrayList<String>();
	ArrayList<String> missWrdLetterArray = new ArrayList<String>();
	ArrayList<String> buttons = new ArrayList<String>();

	private TextToSpeech tts;
	Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	ImageButton btnHelp, btnDugar;
	ImageView ivWriteWord;
	TextView tvWriteWord;

	Button[] numButtons = new Button[15];
	Integer[] numBtnIDs = { R.id.btnWrite0, R.id.btnWrite1, R.id.btnWrite2,
			R.id.btnWrite3, R.id.btnWrite4, R.id.btnWrite5, R.id.btnWrite6,
			R.id.btnWrite7, R.id.btnWrite8, R.id.btnWrite9 };
	String btnLetter;
	String listString1 = "", listString2 = "", listString3 = "",
			listString4 = "", listString5 = "";
	char emptyLetter1, emptyLetter2, emptyLetter3;
	char orluul;
	int nextL = 0;
	int i = 0, n1, n2, n3;

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_word);
		tts = new TextToSpeech(getApplicationContext(),
				new TextToSpeech.OnInitListener() {
					@Override
					public void onInit(int status) {
						if (status != TextToSpeech.ERROR) {
							tts.setLanguage(Locale.ENGLISH);
						}
					}
				});
		DBAdapter mDbHelper = new DBAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();

		wordList = mDbHelper.retrieveWords1();
		Collections.shuffle(wordList);
		wordList1.add(wordList.get(0));

		mDbHelper.close();

		ivWriteWord = (ImageView) findViewById(R.id.ivWriteWord);
		ivWriteWord.setImageBitmap(wordList1.get(0).getBitmap());

		final int length = wordList1.get(0).getEnglish().length();

		tvWriteWord = (TextView) findViewById(R.id.tvWriteWord);
		tvWriteWord.setText(wordList1.get(0).getEnglish());
		// ugnii urtaar _ ene temdegt tawij bn
		// String repeated = new String(new char[length]).replace("\0", "_");
		// tvListenWrite.setText(repeated);

		// ugiig usgeer ni salgaj uur array ruu hiij bn
		for (int i = 0; i < length; i++) {
			// wordList1.get(0).getEnglish().toString().toCharArray();
			wordLetterArray.add(Character.toString(wordList1.get(0)
					.getEnglish().toString().charAt(i)));
		}

		// sanamsargui usgiig alga bolgoh 3 ba tuunees doosh usegtei ug
		if (length <= 4) {
			n1 = randInt(0, 3);
			missWrdLetterArray.add(wordLetterArray.get(n1));
			for (int i = 0; i < n1; i++) {
				wordLetterArray2.add(Character.toString(wordList1.get(0)
						.getEnglish().toString().charAt(i)));
			}
			for (String s : wordLetterArray2) {
				listString1 += s;
			}
			for (int j = n1 + 1; j < length; j++) {
				wordLetterArray3.add(Character.toString(wordList1.get(0)
						.getEnglish().toString().charAt(j)));
			}
			for (String s : wordLetterArray3) {
				listString2 += s;
			}
			tvWriteWord.setText(listString1 + "_" + listString2);
		}
		// 4 ba tuunees deesh, 7s baga usegtei ug
		if (length > 4 && length < 7) {
			n1 = randInt(0, 2);
			n2 = randInt(3, 5);
			missWrdLetterArray.add(wordLetterArray.get(n1));
			missWrdLetterArray.add(wordLetterArray.get(n2));
			for (int j = 0; j < n1; j++) {
				wordLetterArray2.add(Character.toString(wordList1.get(0)
						.getEnglish().toString().charAt(j)));
			}
			for (String s : wordLetterArray2) {
				listString1 += s;
			}
			for (int j = n1 + 1; j < n2; j++) {
				wordLetterArray3.add(Character.toString(wordList1.get(0)
						.getEnglish().toString().charAt(j)));
			}
			for (String s : wordLetterArray3) {
				listString2 += s;
			}
			for (int j = n2 + 1; j < length; j++) {
				wordLetterArray4.add(Character.toString(wordList1.get(0)
						.getEnglish().toString().charAt(j)));
			}
			for (String s : wordLetterArray4) {
				listString3 += s;
			}
			tvWriteWord.setText(listString1 + "_" + listString2 + "_"
					+ listString3);
		}
		// 7 ba tuunees deesh usegtei ug
		if (length >= 7) {
			n1 = randInt(0, 2);
			n2 = randInt(3, 4);
			n3 = randInt(5, 6);
			// ehnii hooson hurtelh usegnuud
			missWrdLetterArray.add(wordLetterArray.get(n1));
			missWrdLetterArray.add(wordLetterArray.get(n2));
			missWrdLetterArray.add(wordLetterArray.get(n3));
			for (int j = 0; j < n1; j++) {
				wordLetterArray2.add(Character.toString(wordList1.get(0)
						.getEnglish().toString().charAt(j)));
			}
			for (String s : wordLetterArray2) {
				listString1 += s;
			}

			// daraagiin hooson hurtelh usegnuud
			for (int j = n1 + 1; j < n2; j++) {
				wordLetterArray3.add(Character.toString(wordList1.get(0)
						.getEnglish().toString().charAt(j)));
			}
			for (String s : wordLetterArray3) {
				listString2 += s;
			}

			// daraagiin hooson hurtelh usegnuud
			for (int j = n2 + 1; j < n3; j++) {
				wordLetterArray4.add(Character.toString(wordList1.get(0)
						.getEnglish().toString().charAt(j)));
			}
			for (String s : wordLetterArray4) {
				listString3 += s;
			}

			// uldsen usegnuud
			for (int j = n3 + 1; j < length; j++) {
				wordLetterArray5.add(Character.toString(wordList1.get(0)
						.getEnglish().toString().charAt(j)));
			}
			for (String s : wordLetterArray5) {
				listString4 += s;
			}
			tvWriteWord.setText(listString1 + "_" + listString2 + "_"
					+ listString3 + "_" + listString4);
		}

		for (String s : wordLetterArray) {
			listString5 += s;
		}

		// ugnii ehnii 7n usgiig huulj awii hooson usegnuud tend baigaa uchir
		for (int j = 2; j < 20; j++) {
			if (listString5.length() == j) {
				Log.d(listString5, "onCreated called");
				if (j < 7) {
					for (int k = 0; k < j; k++) {
						wordLetterArrayTaslalt.add(wordLetterArray.get(k));
					}
				} else {
					for (int k = 0; k < 7; k++) {
						wordLetterArrayTaslalt.add(wordLetterArray.get(k));
					}
				}
			}
		}
		Log.d(wordLetterArrayTaslalt.toString(), "anhnii tasalsan ug");
		// usegnuudee holii
		Collections.shuffle(wordLetterArrayTaslalt);
		// if(length <= )

		for (i = 0; i < numBtnIDs.length; i++) {
			numButtons[i] = (Button) findViewById(numBtnIDs[i]);
		}
		if (length < 5) {
			i = 0;
			for (int j = 0; j < length; j++) {
				numButtons[j].setText(wordLetterArrayTaslalt.get(i));
				i++;
			}
			Collections.shuffle(wordLetterArrayTaslalt);
			i = 0;
			for (int j = length; j < length * 2; j++) {
				numButtons[j].setText(wordLetterArrayTaslalt.get(i));
				i++;
			}
			Collections.shuffle(wordLetterArrayTaslalt);
			i = 0;
			for (int j = length * 2; j < numBtnIDs.length; j++) {
				numButtons[j].setText(wordLetterArrayTaslalt.get(i));
				i++;
			}
		}
		if (length > 4 && length < 8) {
			i = 0;
			for (int j = 0; j < length; j++) {
				numButtons[j].setText(wordLetterArrayTaslalt.get(i));
				i++;
			}
			Collections.shuffle(wordLetterArrayTaslalt);
			i = 0;
			for (int j = length; j < numBtnIDs.length; j++) {
				numButtons[j].setText(wordLetterArrayTaslalt.get(i));
				i++;
			}
		}
		if (length < 11 && length > 7) {
			i = 0;
			for (int j = 0; j < 7; j++) {
				numButtons[j].setText(wordLetterArrayTaslalt.get(i));
				i++;
			}
			Collections.shuffle(wordLetterArrayTaslalt);
			i = 0;
			for (int j = 7; j < numBtnIDs.length; j++) {
				numButtons[j].setText(wordLetterArrayTaslalt.get(i));
				i++;
			}
		}
		i = 0;
		if (length > 10) {
			i = 0;
			for (int j = 0; j < 7; j++) {
				numButtons[j].setText(wordLetterArrayTaslalt.get(i));
				i++;
			}
			Collections.shuffle(wordLetterArrayTaslalt);
			i = 0;
			for (int j = 7; j < numBtnIDs.length; j++) {
				numButtons[j].setText(wordLetterArrayTaslalt.get(i));
				i++;
			}
		}

		for (i = 0; i < numBtnIDs.length; i++) {
			final int index;
			index = i;
			numButtons[index].setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					// if
					// (wordList1.get(0).getEnglish().toString().charAt(nextL)
					// == numButtons[index]
					// .getText().charAt(0)) {
					// btnLetter = tvWriteWord.getText().toString()
					// + numButtons[index].getText().toString();
					// tvWriteWord.setText(btnLetter);
					// nextL++;
					// numButtons[index].setVisibility(View.INVISIBLE);
					// }
					if (length <= 4) {
						if (missWrdLetterArray.get(0) == numButtons[index]
								.getText()) {
							emptyLetter1 = wordList1.get(0).getEnglish()
									.toString().charAt(n1);
							tvWriteWord.setText(listString1
									+ Character.toString(emptyLetter1)
									+ listString2);
							tts.speak("very good", TextToSpeech.QUEUE_FLUSH,
									null);
							Toast.makeText(getApplicationContext(),
									"+1 оноо авлаа", 0).show();
							finish();
						}
					}
					if (length > 4 && length < 7) {
						if (missWrdLetterArray.get(0) == numButtons[index]
								.getText()) {
							emptyLetter1 = wordList1.get(0).getEnglish()
									.toString().charAt(n1);
							tvWriteWord.setText(listString1
									+ Character.toString(emptyLetter1)
									+ listString2 + "_" + listString3);
						}
						if (tvWriteWord
								.getText()
								.toString()
								.equals(listString1
										+ Character.toString(emptyLetter1)
										+ listString2 + "_" + listString3)) {
							if (missWrdLetterArray.get(1) == numButtons[index]
									.getText()) {
								emptyLetter2 = wordList1.get(0).getEnglish()
										.toString().charAt(n2);
								tvWriteWord.setText(listString1
										+ Character.toString(emptyLetter1)
										+ listString2
										+ Character.toString(emptyLetter2)
										+ listString3);
								tts.speak("excellent",
										TextToSpeech.QUEUE_FLUSH, null);
								Toast.makeText(getApplicationContext(),
										"+1 оноо авлаа", 0).show();
								finish();
							}
						}

					}
					if (length >= 7) {
						if (missWrdLetterArray.get(0) == numButtons[index]
								.getText()) {
							emptyLetter1 = wordList1.get(0).getEnglish()
									.toString().charAt(n1);
							tvWriteWord.setText(listString1
									+ Character.toString(emptyLetter1)
									+ listString2 + "_" + listString3 + "_"
									+ listString4);
						}
						if (tvWriteWord
								.getText()
								.toString()
								.equals(listString1
										+ Character.toString(emptyLetter1)
										+ listString2 + "_" + listString3 + "_"
										+ listString4)) {
							if (missWrdLetterArray.get(1) == numButtons[index]
									.getText()) {
								emptyLetter2 = wordList1.get(0).getEnglish()
										.toString().charAt(n2);
								tvWriteWord.setText(listString1
										+ Character.toString(emptyLetter1)
										+ listString2
										+ Character.toString(emptyLetter2)
										+ listString3 + "_" + listString4);
							}

						}
						if (tvWriteWord
								.getText()
								.toString()
								.equals(listString1
										+ Character.toString(emptyLetter1)
										+ listString2
										+ Character.toString(emptyLetter2)
										+ listString3 + "_" + listString4)) {
							if (missWrdLetterArray.get(2) == numButtons[index]
									.getText()) {
								emptyLetter3 = wordList1.get(0).getEnglish()
										.toString().charAt(n3);
								tvWriteWord.setText(listString1
										+ Character.toString(emptyLetter1)
										+ listString2
										+ Character.toString(emptyLetter2)
										+ listString3
										+ Character.toString(emptyLetter3)
										+ listString4);
								tts.speak("you did it",
										TextToSpeech.QUEUE_FLUSH, null);
								Toast.makeText(getApplicationContext(),
										"+1 оноо авлаа", 0).show();
								finish();
							}
						}

					}
				}
			});
		}

		btnHelp = (ImageButton) findViewById(R.id.btnWriteWordHelp);
		btnHelp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

	}

	public int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}
