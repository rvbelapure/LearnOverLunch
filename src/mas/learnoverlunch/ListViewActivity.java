package mas.learnoverlunch;
 
import java.util.Date;

import mas.commons.masGlobal;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class ListViewActivity extends ListActivity {
	
	Activity activity;
	Context context;
	public String[] jsonArray;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        context = getApplicationContext();
        // storing string resources into Array
        //String[] theEvents = new String[] {"The Universe, 2/28, 1pm", "Python for Dummies, 2/28, 1pm", "Caricature Discussions, 2/29, 2pm"}; 
        String[] theEvents = new String[masGlobal.globalMyEvents.length()];
        JSONObject temp = new JSONObject();
        for(int p=0; p<masGlobal.globalMyEvents.length(); p++)
        {
        	try {
				temp = masGlobal.globalMyEvents.getJSONObject(p);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	try {
        		
        		Date d;
        		d = new Date(temp.getString("event_date"));
				theEvents[p] = d.getMonth()+"/"+d.getDate()+", "+temp.getString("topic_name");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
      
        if(theEvents.length == 0)
        {
        	Toast.makeText(context, "No events Registered", Toast.LENGTH_LONG).show();
        }
        	
        // Binding resources Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, theEvents));
        
        //Button addButton = (Button) findViewById(R.id.button_add)
        //addButton.setOnClickListener (new View.OnClickListener() {
			/*
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListViewActivity.this, AddEvent.class);
				startActivity(intent);
			}
		});*/
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
    	Intent eventIntent = new Intent(context, EventActivity.class);
    	Bundle extras = eventIntent.getExtras();
    	extras.putInt("global_event_index", position);
    	activity.startActivity(eventIntent);
    }
}
