package mas.learnoverlunch;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment
                            implements TimePickerDialog.OnTimeSetListener {
	private Context context;
	private AddEvent activity;
	String stime;
	
    public TimePickerFragment(Context context, AddEvent activity) {
    	this.context = context;
		this.activity = activity;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
    	activity.hr = hourOfDay;
    	activity.min = minute;
    	Button b = (Button) activity.findViewById(R.id.event_time_button);
    	b.setText( "Event Time: " + hourOfDay + ":" + minute);
    }
    
}

