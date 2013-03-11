package mas.learnoverlunch;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment
                            implements DatePickerDialog.OnDateSetListener {
	
	
	String sdate;
	private Context context;
	private AddEvent activity;
	
    public DatePickerFragment(Context context, AddEvent activity) {
		this.context = context;
		this.activity = (AddEvent) activity;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
    	sdate=(month+1) + "/" + day + "/" + year;
    	
    	Button dateButton = (Button) activity.findViewById(R.id.event_date_button);
    	dateButton.setText( "Event date: "+ sdate);
    	
    	activity.year = year;
    	activity.month = month;
    	activity.day = day;
    }
}