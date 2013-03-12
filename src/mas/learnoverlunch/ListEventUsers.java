package mas.learnoverlunch;

import mas.commons.masGlobal;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListEventUsers extends ListActivity {

	Activity activity;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		context = getApplicationContext();
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_view_users, R.id.users_tv, masGlobal.userList));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		/* Do nothing if the screen is not fedback screen */
		if(!masGlobal.isFeedbackScreen)	
			return;
		String selected = masGlobal.userList[position];
		String parts[] =  selected.split(":");
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