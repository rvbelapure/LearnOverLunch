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
	    TextView tv2 = (TextView) findViewById(R.id.textView2);
	    tv2.setText(topic);
	    TextView tv6 = (TextView) findViewById(R.id.textView6);
	    tv6.setText(dateandtime);
	    TextView tv4 = (TextView) findViewById(R.id.textView4);
	    tv4.setText(venue);
	    TextView tv3 = (TextView) findViewById(R.id.textView3);
	    tv3.setText(going);
	    TextView tv1 = (TextView) findViewById(R.id.textView1);
	    tv1.setText(capacity);
	    
	 }

	public EventActivity() {
		// TODO Auto-generated constructor stub
	}

}
