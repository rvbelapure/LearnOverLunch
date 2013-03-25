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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListEventUsers extends ListActivity {

	Activity activity;
	Context context;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		context = getApplicationContext();
		adapter = new ArrayAdapter<String>(this, R.layout.activity_view_users, R.id.users_tv, masGlobal.userList);
		this.setListAdapter(adapter);
	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
		
		Intent mIntent = this.getIntent();
		Bundle extras = mIntent.getExtras();
		int value = extras.getInt("global_event_index", -1);
		int eventid = 0;

		JSONObject eventObject = new JSONObject();
		try {
			eventObject = masGlobal.globalMyEvents.getJSONObject(value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		try {
			eventid = eventObject.getInt("event_id");
		} catch (JSONException e1) {
			e1.printStackTrace();
			return;
		}

		String reply = ConnectionHandler.sendString(
				Constants.GET_EVENT_MEMBERS_URL, Integer.toString(eventid));
		JSONArray arr = new JSONArray();
		try {
			arr = new JSONArray(reply);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		masGlobal.userList = new String[arr.length()];
		masGlobal.isFeedbackScreen = true;
		try {
			DecimalFormat df = new DecimalFormat("0.0");
			for (int i = 0; i < arr.length(); i++) {
				JSONObject m = arr.getJSONObject(i);
				masGlobal.userList[i] = m.getString("fname") + " "
						+ m.getString("lname") + ":"
						+ df.format(m.getDouble("rating"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		adapter = new ArrayAdapter<String>(this, R.layout.activity_view_users, R.id.users_tv, masGlobal.userList);
		this.setListAdapter(adapter);
	}



	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		/* Do nothing if the screen is not feedback screen */
		if(!masGlobal.isFeedbackScreen)	
			return;
		
		String selected = masGlobal.userList[position];
		String parts[] =  selected.split(":");
		
		System.out.println("Selected = " + parts[0]);
		System.out.println("Self name = " + masGlobal.globalFullName);
		if(parts[0].equals(masGlobal.globalFullName))
		{
			System.out.println("Stop user from rating himself..");
			Toast.makeText(context, "You can not rate yourself!!", Toast.LENGTH_LONG).show();
			return;
		}
		String names[] = parts[0].split(" ");
		String fname = names[0];
		String lname = names[1];
		float prev_rating = Float.parseFloat(parts[1]);
    	
		Intent dialogIntent = new Intent(context, EnterFeedback.class);
    	Bundle extras = new Bundle();
    	extras.putString("fname", fname);
    	extras.putString("lname", lname);
    	extras.putFloat("prev_rating", prev_rating);
    	dialogIntent.putExtras(extras);
    	activity.startActivity(dialogIntent);
	}

}
