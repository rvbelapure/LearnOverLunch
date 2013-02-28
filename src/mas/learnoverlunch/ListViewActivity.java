package mas.learnoverlunch;
 
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class ListViewActivity extends ListActivity {
	
	Activity activity;
	Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        context = getApplicationContext();
        
        // storing string resources into Array
        String[] theEvents = new String[] {"The Universe, 2/28, 1pm", "Python for Dummies, 2/28, 1pm", "Caricature Discussions, 2/29, 2pm"}; 
 
        // Binding resources Array to ListAdapter
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, theEvents));
        
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
    	activity.startActivity(new Intent(context, EventActivity.class));
    }
}
