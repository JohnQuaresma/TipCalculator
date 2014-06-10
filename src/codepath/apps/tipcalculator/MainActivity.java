package codepath.apps.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final float FIFTEEN_PCT = .15f;
	public final float EIGHTEEN_PCT = .18f;
	public final float TWENTY_PCT = .2f;
	EditText checkAmount;
	RadioGroup tipOptions;
	Button calculateButton;
	TextView tipAmount;
	Float desiredTip;
	
	private String getFormattedDollarAmount(Float amount) {
		return String.format("%10.2f", amount); 
	}
	
	private Float getCheckAmount() throws NumberFormatException {
		return Float.valueOf(checkAmount.getText().toString());
	}
	
	private boolean validateCheckAmount() {
		if ("".equals(checkAmount.getText().toString())) {
			return false;
		}
		if (!checkAmount.getText().toString().matches("^([0-9]*\\.[0-9][0-9])$")) {
			return false;
		}
		try {
			Float value = getCheckAmount();
			double rounded = Math.round(value * 100.0) / 100.0;
			return Double.valueOf(value.toString()) == rounded;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		checkAmount = (EditText) findViewById(R.id.etCheckAmount);
		calculateButton = (Button) findViewById(R.id.btnCalc);
		tipAmount = (TextView) findViewById(R.id.tvTipAmount);
		tipOptions = (RadioGroup) findViewById(R.id.rgTipPct);
	}
	
	public void calculateTip(View v) {
		if (!validateCheckAmount()) {
			checkAmount.setError("Check amount must be in the format \"22.22\"");
			return;
		}
		// Check which radio button was clicked
	    switch(tipOptions.getCheckedRadioButtonId()) {
	        case R.id.rb15Pct:
	        	desiredTip = FIFTEEN_PCT;
	            break;
	        case R.id.rb18Pct:
	        	desiredTip = EIGHTEEN_PCT;
	            break;
	        case R.id.rb20Pct:
	        	desiredTip = TWENTY_PCT;
	            break;
	    }
	    String tipAmountStr = "$" + getFormattedDollarAmount(Float.valueOf(getCheckAmount() * desiredTip)).trim();
	    System.err.println(tipAmountStr);
		tipAmount.setText(tipAmountStr);
	}
}
