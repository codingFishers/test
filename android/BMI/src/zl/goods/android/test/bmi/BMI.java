package zl.goods.android.test.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BMI extends Activity {
	private static final int MENU_ABOUT = Menu.FIRST;
	private static final int MENU_QUIT = Menu.FIRST + 1;

	private static final String TAG = "BMI";
	private static final String PREF = "BMI_PREF";
	private static final String PREF_HEIGHT = "PREF_HEIGHT";

	private EditText fieldHeight;
	private EditText fieldWeight;
	private Button calcButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViews();
		restorePref();
		setListeners();
	}

	@Override
	protected void onStop() {
		super.onStop();
		SharedPreferences settings = getSharedPreferences(PREF, 0);
		String height = fieldHeight.getText().toString();
		settings.edit().putString(PREF_HEIGHT, height).commit();
		Log.d(TAG, "Store Height: " + height);
	}

	private void findViews() {
		fieldHeight = (EditText) findViewById(R.id.height);
		fieldWeight = (EditText) findViewById(R.id.weight);
		calcButton = (Button) findViewById(R.id.submit);
	}

	private void restorePref() {
		SharedPreferences settings = getSharedPreferences(PREF, 0);
		String height = settings.getString(PREF_HEIGHT, "");
		if (!"".equals(height)) {
			fieldHeight.setText(height);
			fieldWeight.requestFocus();
		} else {
			fieldHeight.requestFocus();
		}
	}

	private void setListeners() {
		calcButton.setOnClickListener(calcBMI);
	}

	private Button.OnClickListener calcBMI = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				Intent intent = new Intent(BMI.this, Report.class);
				Bundle bundle = new Bundle();
				bundle.putString("KEY_HEIGHT", fieldHeight.getText().toString());
				bundle.putString("KEY_WEIGHT", fieldWeight.getText().toString());
				intent.putExtras(bundle);
				startActivity(intent);
			} catch (Exception ex) {
				Toast.makeText(BMI.this, R.string.input_error,
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	private void openOptionsDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.about_title)
				.setMessage(R.string.about_msg)
				.setPositiveButton(R.string.ok_label,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

							}
						})
				.setNegativeButton(R.string.homepage_label,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Uri uri = Uri
										.parse(getString(R.string.homepage_uri));
								Intent intent = new Intent(Intent.ACTION_VIEW,
										uri);
								startActivity(intent);
							}
						}).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_ABOUT, 0, "关于");
		menu.add(0, MENU_QUIT, 1, "结束");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_ABOUT:
			openOptionsDialog();
			break;
		case MENU_QUIT:
			finish();
			break;
		}

		return true;
	}

}
