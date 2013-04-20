package zl.goods.android.test.helloworld;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button = (Button) findViewById(R.id.submit);
		button.setOnClickListener(calcBMI);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private OnClickListener calcBMI = new OnClickListener() {
		@Override
		public void onClick(View v) {
			DecimalFormat nf = new DecimalFormat("0.00");
			EditText fieldHeight = (EditText) findViewById(R.id.height);
			EditText fieldWeight = (EditText) findViewById(R.id.weight);

			double height = Double
					.parseDouble(fieldHeight.getText().toString())/100;
			double weight = Double
					.parseDouble(fieldWeight.getText().toString());

			double BMI = weight / (height * height);
			
			TextView fieldResult = (TextView) findViewById(R.id.result);
			fieldResult.setText("Your BMI is "+nf.format(BMI));
			
			TextView fieldSuggest = (TextView) findViewById(R.id.suggest);
			fieldSuggest.setText(
					BMI>25
					?R.string.advice_heavy
					:(BMI<20
							?R.string.advice_light
							:R.string.advice_average));
			
		}
	};

}
