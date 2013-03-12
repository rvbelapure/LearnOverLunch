package mas.learnoverlunch;

import mas.comm.ConnectionHandler;
import mas.commons.Constants;
import mas.commons.masGlobal;

import org.json.JSONArray;
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

public class HomeScreenActivity extends ListActivity {

	Activity activity;
	Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] options = new String[] { "Create Event", "View My Events",
				"View Events by Categories", "View Events by Places" };
		activity = this;
		context = getApplicationContext();

		// Binding resources Array to ListAdapter
		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_homescreen, R.id.home_tv, options));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String item = (String) l.getItemAtPosition(position);
		
		
		if (item.equals("Create Event"))
			activity.startActivity(new Intent(context, AddEvent.class));
			// Testing launch for feedback rating bar
			//activity.startActivity(new Intent(context, EnterFeedback.class));

		
		else if (item.equals("View My Events")) {
			String reply = null;
			reply = ConnectionHandler.sendString(Constants.GET_MY_EVENTS_URL,
					masGlobal.globalUname);
			try {
				masGlobal.globalMyEvents = new JSONArray(reply);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (reply.length() > 0) {
				activity.startActivity(new Intent(context,
						ListViewActivity.class));

			} else {
				Toast.makeText(context, "No events Registered",
						Toast.LENGTH_LONG).show();
			}

		
		} else if (item.equals("View Events by Categories")) {
			masGlobal.itemOnclickActionUrl = Constants.GET_EVENT_BY_CATEGORY_URL;
	        String reply = ConnectionHandler.sendString(Constants.GET_CATEGORIES, "dummyarg");
	        JSONArray arr = new JSONArray();
	        try {
				arr = new JSONArray(reply);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        masGlobal.globalItems = new String [arr.length()];
	        try {
				for(int i = 0 ; i < arr.length() ; i++)
				{
					JSONObject o = arr.getJSONObject(i);
					masGlobal.globalItems[i] = o.getString("cat_name");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			activity.startActivity(new Intent(context, ShowItemsActivity.class));
		} else if(item.equals("View Events by Places"))
		{
			masGlobal.itemOnclickActionUrl = Constants.GET_EVENT_BY_LOCATION_URL;
	        String reply = ConnectionHandler.sendString(Constants.GET_LOCATIONS_URL, "dummyarg");
	        JSONArray arr = new JSONArray();
	        try {
				arr = new JSONArray(reply);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        masGlobal.globalItems = new String [arr.length()];
	        try {
				for(int i = 0 ; i < arr.length() ; i++)
				{
					JSONObject o = arr.getJSONObject(i);
					masGlobal.globalItems[i] = o.getString("event_place");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			activity.startActivity(new Intent(context, ShowItemsActivity.class));
		}
	}
}
