package mas.learnoverlunch;

import java.util.Date;

import mas.comm.ConnectionHandler;
import mas.commons.Constants;
import mas.learnoverlunch.handlers.TextHandler;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddEvent extends FragmentActivity {
	
	Context context;
	AddEvent activity;
	public int year, month, day, hr, min;
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
	
		this.context = getApplicationContext();
		this.activity = this;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		
		final EditText title = (EditText) findViewById(R.id.event_title);
        title.setOnClickListener(new TextHandler());
        
        final Spinner spinner = (Spinner) findViewById(R.id.events_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.event_categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        
        final EditText desc = (EditText) findViewById(R.id.event_desc);
        desc.setOnClickListener(new TextHandler());
    
        final EditText loc = (EditText) findViewById(R.id.event_loc);
        loc.setOnClickListener(new TextHandler());
        
        final EditText count = (EditText) findViewById(R.id.event_max_people);
        count.setOnClickListener(new TextHandler());
    
        Button add_button = (Button) findViewById(R.id.event_add_button);
        add_button.setOnClickListener(new OnClickListener() {
        		
			@Override
			public void onClick(View v) {
				String category = spinner.getSelectedItem().toString();   
				JSONObject json = null;
				try {
					json = new JSONObject();
					json.put("event_date", (new Date((year-1900), month, day, (hr-1), min)).toString());
					json.put("topic_desc", desc.getText().toString());
					json.put("event_place", loc.getText().toString());
					json.put("topic_name", title.getText().toString());
					json.put("topic_category", category.toString());
					json.put("max_allowed_members", Integer.parseInt(count.getText().toString()));
					json.put("username", "abhi");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String status = ConnectionHandler.sendString(Constants.ADD_EVENT, json.toString());
				//String status = ConnectionHandler.sendString(Constants.ADD_EVENT, "{{\"event_date\": \"2013-02-14\", \"event_place\":\"Atlanta\", \"topic_name\":\"Physics\", \"topic_category\":\"Science\", \"max_allowed_members\":\"5\"}, \"event_members\":\"yatish\"}");
				Toast.makeText(context, status, Toast.LENGTH_LONG).show();
			}
		});
	}

	
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment(context,activity);
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	public void showTimePickerDialog(View v) {
	    DialogFragment newFragment = new TimePickerFragment(context, activity);
	    newFragment.show(getSupportFragmentManager(), "timePicker");
	}
}



