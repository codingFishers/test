package zl.goods.android.test.sharpphoneapp;

import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btCall = (Button) findViewById(R.id.btCall);
		btCall.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText text = (EditText)findViewById(R.id.phoneno);
				String phoneno  = text.getText().toString();
				if(phoneno!=null && !"".equals(phoneno)){
					Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneno));
					startActivity(intent);
				}
				String message = "你好！";
				SmsManager mgr = SmsManager.getDefault();
				List<String> messages = mgr.divideMessage(message);
				for(String msg : messages){
					mgr.sendTextMessage(phoneno, null, msg, null, null);
					Toast.makeText(MainActivity.this, "Send Message Success", Toast.LENGTH_SHORT);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
