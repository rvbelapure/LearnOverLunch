package mas.learnoverlunch;
 
import org.json.JSONArray;
import org.json.JSONException;

import mas.comm.ConnectionHandler;
import mas.commons.Constants;
import mas.commons.masGlobal;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
 
public class HomeScreenActivity extends ListActivity {
	
	Activity activity;
	Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] options = new String[] {"Create Event", "View My Events", "View By Category"};
        activity = this;
        context = getApplicationContext();
 
        // Binding resources Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_homescreen, R.id.home_tv, options));
        
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
    	String item = (String) l.getItemAtPosition(position);
    	if(item.equals("Create Event"))
    		activity.startActivity(new Intent(context, AddEvent.class));
    	else if(item.equals("View My Events"))
    	{
    		String reply = null;
    		reply = ConnectionHandler.sendString(Constants.GET_MY_EVENTS_URL, masGlobal.globalUname);
    		try {
				masGlobal.globalMyEvents = new JSONArray(reply);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(reply.length() >0)
    		{
    			
    			activity.startActivity(new Intent(context, ListViewActivity.class));
    			
    		}
    		else
    		{
    			Toast.makeText(context, "No events Registered", Toast.LENGTH_LONG).show();
    			//Log.d("SERVER", "Unable to get My Events from Server");
    		}
    		
    	}
    	else if(item.equals("View By Category"))
    	{
    		String reply = null;
    		reply = ConnectionHandler.sendString(Constants.GET_EVENTS_BY_CATEGORY, masGlobal.globalUname);
    		try {
				masGlobal.globalMyEvents = new JSONArray(reply);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		if(reply.length() >0)
    		{
    			
    			activity.startActivity(new Intent(context, ListViewActivity.class));
    			
    		}
    		else
    		{
    			Toast.makeText(context, "No events Registered", Toast.LENGTH_LONG).show();
    			//Log.d("SERVER", "Unable to get My Events from Server");
    		}
    	}
    }
}
