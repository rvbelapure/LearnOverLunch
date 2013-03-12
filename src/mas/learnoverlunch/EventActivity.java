package mas.learnoverlunch;

import org.json.JSONException;
import org.json.JSONObject;

import mas.commons.masGlobal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EventActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_event);
	    
	    Intent mIntent = new Intent(this, EventActivity.class);
	    Bundle extras = mIntent.getExtras();
	    int value = extras.getInt("global_event_index", -1);
	    
	    JSONObject temp = new JSONObject();
	    try {
			temp = masGlobal.globalMyEvents.getJSONObject(value);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //change hardcoding to fetching value from server and change datatype
	    String topic=null;
		try {
			topic = " TOPIC : " + temp.getString("topic_name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dateandtime=null; 
		try {
			dateandtime  = " DATE n TIME : " + temp.getString("event_date");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    String venue=null;
		try {
			venue = " VENUE : " + temp.getString("event_place");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String going=null;
		try {
			going = " PEOPLE JOINING : " + temp.getString("event_members");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String capacity=null;
		try {
			capacity = " CAPACITY : " + temp.getString("max_allowed_members");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	  
		TextView tv5 = (TextView) findViewById(R.id.textView5);
	    tv5.setText(topic);
	    TextView tv7 = (TextView) findViewById(R.id.textView7);
	    tv7.setText( dateandtime);
	    TextView tv8 = (TextView) findViewById(R.id.textView8);
	    tv8.setText( venue);
	    TextView tv9 = (TextView) findViewById(R.id.textView9);
	    tv9.setText( going);
	    TextView tv10 = (TextView) findViewById(R.id.textView10);
	    tv10.setText(capacity);
	    
	 }

	public EventActivity() {
		// TODO Auto-generated constructor stub
	}

}
