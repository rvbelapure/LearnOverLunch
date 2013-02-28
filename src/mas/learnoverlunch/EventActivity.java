package mas.learnoverlunch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_event);
	    
	    //change hardcoding to fetching value from server and change datatype
	    String topic = "Hypnotism";
	    String date = "12th Jan 2014";
	    String time = "2:00 pm";
	    String venue = "Piedmont Room";
	    String going = "2";
	    String capacity = "5";
	    String dateandtime  = date + " " + time;
	    TextView tv5 = (TextView) findViewById(R.id.textView5);
	    tv5.setText(topic);
	    TextView tv7 = (TextView) findViewById(R.id.textView7);
	    tv7.setText(dateandtime);
	    TextView tv8 = (TextView) findViewById(R.id.textView8);
	    tv8.setText(venue);
	    TextView tv9 = (TextView) findViewById(R.id.textView9);
	    tv9.setText(going);
	    TextView tv10 = (TextView) findViewById(R.id.textView10);
	    tv10.setText(capacity);
	    
	 }

	public EventActivity() {
		// TODO Auto-generated constructor stub
	}

}
