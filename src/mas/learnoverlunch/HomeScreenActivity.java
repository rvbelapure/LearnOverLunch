package mas.learnoverlunch;
 
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class HomeScreenActivity extends ListActivity {
	
	Activity activity;
	Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] options = new String[] {"Create Event", "View Events"};
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
    	else if(item.equals("View Events"))
    		activity.startActivity(new Intent(context, ListViewActivity.class));
    }
}
