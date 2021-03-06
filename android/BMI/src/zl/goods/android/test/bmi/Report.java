package zl.goods.android.test.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Report extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);

		findViews();
		setListeners();
		showResult();
	}

	private TextView viewResult;
	private TextView viewSuggest;
	private Button btBack;

	private void findViews() {
		viewResult = (TextView) findViewById(R.id.result);
		viewSuggest = (TextView) findViewById(R.id.suggest);
		btBack = (Button) findViewById(R.id.report_back);
	}

	private void setListeners() {
		btBack.setOnClickListener(backMain);
	}

	private Button.OnClickListener backMain = new Button.OnClickListener() {
		public void onClick(View v) {
			Report.this.finish();
		}
	};

	private void showResult() {
		Bundle bundle = this.getIntent().getExtras();
		double height = Double.parseDouble(bundle.getString("KEY_HEIGHT")) / 100;
		double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));

		double BMI = weight / (height * height);

		DecimalFormat nf = new DecimalFormat("0.00");
		viewResult.setText(getString(R.string.bmi_result) + nf.format(BMI));

		viewSuggest.setText(BMI > 25 ? R.string.advice_heavy
				: (BMI < 20 ? R.string.advice_light : R.string.advice_average));
	}

}
