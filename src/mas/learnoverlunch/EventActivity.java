package mas.learnoverlunch;

import java.text.DecimalFormat;
import java.util.Date;

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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class EventActivity extends ListActivity {

	Activity activity;
	Context context;
	String[] theEvents;
	private static final int JOIN = 0;
	private static final int FEEDBACK = 1;
	private static final int VIEW_MEMBERS = 2;
	Bundle extras;
	JSONObject eventObject;
	int value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_event);

		activity = this;
		context = getApplicationContext();
		Intent mIntent = this.getIntent();
		extras = mIntent.getExtras();
		value = extras.getInt("global_event_index", -1);

		eventObject = new JSONObject();
		try {
			eventObject = masGlobal.globalMyEvents.getJSONObject(value);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String topic = "";
		try {
			topic = " TOPIC : " + eventObject.getString("topic_name");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String dateandtime = "";
		try {
			dateandtime = " DATE & TIME : "
					+ eventObject.getString("event_date");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String venue = "";
		try {
			venue = " VENUE : " + eventObject.getString("event_place");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String description = "";
		try {
			description = " DESC : " + eventObject.getString("topic_desc");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String category = "";
		try {
			category = " CATEGORY : " + eventObject.getString("topic_category");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String capacity = "";
		try {
			capacity = " CAPACITY : "
					+ eventObject.getInt("max_allowed_members");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		theEvents = new String[6];
		for (int i = 0; i < theEvents.length; i++)
			theEvents[i] = "";

		theEvents[0] = topic;
		theEvents[1] = description;
		theEvents[2] = category;
		theEvents[3] = dateandtime;
		theEvents[4] = venue;
		theEvents[5] = capacity;

		System.out.println(theEvents);

		this.setListAdapter(new ArrayAdapter<String>(this,
				R.layout.activity_event, R.id.event_tv, theEvents));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(0, JOIN, 0, "Join");
		menu.add(0, FEEDBACK, 1, "Rate");
		menu.add(0, VIEW_MEMBERS, 2, "View Members");

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (JOIN):
			JSONObject o = new JSONObject();
			int id;
			try {
				id = eventObject.getInt("event_id");
				o.put("uname", masGlobal.globalUname);
				o.put("event_id", new String(Integer.toString(id)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			String oldreply = ConnectionHandler.sendString(Constants.ISMEMBER_URL, o.toString());
			
			String reply1 = ConnectionHandler.sendString(Constants.JOIN_EVENT,
					o.toString());
			System.out.println(oldreply);
			if (oldreply.equals("no")) {
				try {
					calendar_event();
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			Toast.makeText(context, reply1, Toast.LENGTH_LONG).show();

			break;
		case (FEEDBACK):
			int eventid = 0;

			try {
				eventid = eventObject.getInt("event_id");
			} catch (JSONException e1) {
				e1.printStackTrace();
				return true;
			}
			;

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
			
			Intent eventIntent = new Intent(context, ListEventUsers.class);
	    	Bundle extras = new Bundle();
	    	extras.putInt("global_event_index", value);
	    	eventIntent.putExtras(extras);
	    	activity.startActivity(eventIntent);

			break;
		case (VIEW_MEMBERS):
			int eventid2 = 0;

			try {
				eventid2 = eventObject.getInt("event_id");
			} catch (JSONException e1) {
				e1.printStackTrace();
				return true;
			}
			;

			String reply2 = ConnectionHandler
					.sendString(Constants.GET_EVENT_MEMBERS_URL,
							Integer.toString(eventid2));
			JSONArray arr2 = new JSONArray();
			try {
				arr2 = new JSONArray(reply2);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			masGlobal.userList = new String[arr2.length()];
			masGlobal.isFeedbackScreen = false;
			try {
				DecimalFormat df = new DecimalFormat("0.0");
				for (int i = 0; i < arr2.length(); i++) {
					JSONObject m = arr2.getJSONObject(i);
					masGlobal.userList[i] = m.getString("fname") + " "
							+ m.getString("lname") + ":"
							+ df.format(m.getDouble("rating"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			activity.startActivity(new Intent(context, ListEventUsers.class));

			break;
		}
		return false;
	}

	private void calendar_event() throws JSONException {

		Date d = new Date(eventObject.getString("event_date"));

		// Calendar cal = Calendar.getInstance();
		Intent intent = new Intent(Intent.ACTION_EDIT);
		intent.setType("vnd.android.cursor.item/event");
		intent.putExtra("beginTime", d.getTime());
		intent.putExtra("allDay", false);
		intent.putExtra("endTime", d.getTime() + 60 * 60 * 1000);
		intent.putExtra("title", eventObject.getString("topic_name"));
		intent.putExtra("eventLocation", eventObject.getString("event_place"));
		startActivity(intent);
	}

	public EventActivity() {
		// TODO Auto-generated constructor stub
	}

}
