package mas.learnoverlunch;

import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;

import org.json.JSONObject;

import mas.comm.ConnectionHandler;
import mas.commons.Constants;
import mas.commons.masGlobal;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.view.MenuItem;


public class EventActivity extends ListActivity {
	
	Activity activity;
	Context context;
	String[] theEvents = new String[5];
	private static final int JOIN = 0;
    private static final int FEEDBACK = 1;
    Bundle extras;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_event);
	    activity=this;
	    context = getApplicationContext();
	    Intent mIntent = this.getIntent();
	    extras = mIntent.getExtras();
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
	    
		theEvents[0]=topic;
		theEvents[1]=dateandtime;
		theEvents[2]=venue;
		theEvents[3]=going;
		theEvents[4]=capacity;
		
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, theEvents));

	    
	    
	 }

	@Override
    public boolean onCreateOptionsMenu(Menu menu){ 

        menu.add(0,JOIN,0,"Join");
        menu.add(0,FEEDBACK,1,"Rate"); 
        
        return true; 
    }
	
	public boolean onOptionsItemSelected (MenuItem item)
    { 
        switch(item.getItemId())
        {
           case(JOIN):
        	   JSONObject o = new JSONObject();
           		int id=extras.getInt("event_id");
				try {
					o.put("uname", extras.getString("uname"));
					o.put("event_id", new String(Integer.toString(id)));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				ConnectionHandler.sendString(Constants.JOIN_EVENT, "todo");
				break;
           case(FEEDBACK):
        	   JSONObject k = new JSONObject();
	           int eventid = extras.getInt("event_id");
				String reply = ConnectionHandler.sendString(Constants.GET_EVENT_MEMBERS_URL, new String(Integer.toString(eventid)));
				JSONArray arr = new JSONArray();
				try {
					arr = new JSONArray(reply);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				masGlobal.userList = new String[arr.length()];
				masGlobal.isFeedbackScreen = true;
				try {
					DecimalFormat df= new DecimalFormat("0.0");
					for(int i = 0 ; i < arr.length() ; i++)
					{
						JSONObject m = arr.getJSONObject(i);
						masGlobal.userList[i] = m.getString("fname") + " " + m.getString("lname") + ":" + df.format(m.getDouble("rating"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				activity.startActivity(new Intent(context, ListEventUsers.class));

				try {
					k.put("uname", extras.getString("uname"));
					k.put("event_id", extras.getString("event_id"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
        	   ConnectionHandler.sendString(Constants.GIVE_FEEDBACK, "todo");
               break; 
         } 
        return false; 
    } 
	
	public EventActivity() {
		// TODO Auto-generated constructor stub
	}
	
	

}
