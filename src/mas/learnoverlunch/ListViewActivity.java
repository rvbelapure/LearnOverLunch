package mas.learnoverlunch;
 
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
 
public class ListViewActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
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
}
