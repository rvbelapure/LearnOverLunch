package mas.learnoverlunch;

import mas.comm.ConnectionHandler;
import mas.commons.masGlobal;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ShowItemsActivity extends ListActivity {

	Context context;
	Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		context = getApplicationContext();
		this.setListAdapter(new ArrayAdapter<String>(this
				.getApplicationContext(), R.layout.itemized_list_activity,
				R.id.items_textview, masGlobal.globalItems));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String selected = masGlobal.globalItems[position];
		String reply = ConnectionHandler.sendString(masGlobal.itemOnclickActionUrl, selected);
		try {
			masGlobal.globalMyEvents = new JSONArray(reply);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		if (reply.length() > 0) {
			activity.startActivity(new Intent(context,
					ListViewActivity.class));

		} else {
			Toast.makeText(context, "No events exist !",
					Toast.LENGTH_LONG).show();
		}
	}

}
