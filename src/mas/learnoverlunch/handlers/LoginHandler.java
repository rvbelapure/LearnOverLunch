package mas.learnoverlunch.handlers;

import mas.comm.ConnectionHandler;
import mas.commons.Constants;
import mas.learnoverlunch.HomeScreenActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;


public class LoginHandler implements OnClickListener {

	Context context;
	Activity activity;
	
	EditText uname;
	EditText passwd;
	
	
	public LoginHandler(Context context, Activity activity, EditText uname, EditText passwd) {
		super();
		this.context = context;
		this.activity = activity;
		
		this.uname = uname;
		this.passwd = passwd;

	}


	@Override
	public void onClick(View v) {
		//activity.startActivity(new Intent(context, HomeActivity.class));
		//Toast.makeText(context, "Finally!!", 3).show();
		
		String local_uname = uname.getText().toString();
		String local_passwd = passwd.getText().toString();
		
		Log.d(Constants.TAG, local_uname+"::"+local_passwd);
		
		if(Constants.BYPASS)
			activity.startActivity(new Intent(context, HomeScreenActivity.class));
		else
		{
			String reply = ConnectionHandler.sendString(Constants.AUTH_URL, local_uname+"::"+local_passwd);
			if (reply.equals("yes")) {
				Intent homeIntent = new Intent(context, HomeScreenActivity.class);
				Bundle b = homeIntent.getExtras();
				activity.startActivity (homeIntent);	
			} else if(reply.equals("no")) {
				Toast.makeText(context, "Login failed!", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(context, reply, Toast.LENGTH_LONG).show();
			}
		}
	}
	

}
