package mas.learnoverlunch;

import mas.comm.ConnectionHandler;
import mas.commons.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class EnterFeedback extends Activity implements OnClickListener{

	Context context;
	Activity activity;
	RatingBar bar;
	Bundle b;
	float rating;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.feedback);
	    activity = this;
	    context = getApplicationContext();
	    b = this.getIntent().getExtras();
	    
	    bar = (RatingBar) findViewById(R.id.reviewStars);
	    bar.setOnTouchListener(new RatingBarTouchHandler());
	    Button submit = (Button) findViewById(R.id.reviewWriteAccept);
	    Button cancel = (Button) findViewById(R.id.reviewWriteCancel);
	    
	    submit.setTag("submit");
	    cancel.setTag("cancel");
	    
	    submit.setOnClickListener(this);
	    cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getTag().equals("submit"))
		{
			JSONObject o = new JSONObject();
			try {
				o.put("fname", b.getString("fname"));
				o.put("lname", b.getString("lname"));
				o.put("prev_rating", Float.toString((b.getFloat("prev_rating"))));
				o.put("curr_rating", Float.toString(rating));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			String reply = ConnectionHandler.sendString(Constants.REPORT_RATING_URL, o.toString());
			Toast.makeText(context, reply, Toast.LENGTH_LONG).show();
			activity.finish();
		}
		else if (v.getTag().equals("cancel"))
		{
			activity.finish();
		}
	}

	class RatingBarTouchHandler implements View.OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			RatingBar b = (RatingBar) v;
			rating = b.getRating();
			return false;
		}
		
	}
}
