package mas.learnoverlunch.handlers;

import mas.comm.ConnectionHandler;
import mas.commons.Constants;
import mas.commons.ServerConstants;
import mas.learnoverlunch.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class SignupHandler implements OnClickListener {

	
	private Context context;
	private EditText uname;
	private EditText passwd;
	private EditText fname;
	private EditText lname;
	private Activity activity;

	public SignupHandler(Context context, Activity activity, EditText uname, EditText passwd,
			EditText fname, EditText lname) {
		this.context = context;
		this.uname = uname;
		this.passwd = passwd;
		this.fname = fname;
		this.lname = lname;
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		if(Constants.BYPASS)
		{
			Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show();
			activity.startActivity(new Intent(context, LoginActivity.class));
		}
		else
		{
			JSONObject o = null;
			try {
				o = new JSONObject();
				o.put("uname", uname.getText().toString());
				o.put("passwd", passwd.getText().toString());
				o.put("fname", fname.getText().toString());
				o.put("lname", lname.getText().toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if(o != null)
			{
				String reply = ConnectionHandler.sendString(Constants.SIGNUP_URL, o.toString());
				Toast.makeText(context, reply, Toast.LENGTH_LONG).show();
				if(reply.equals(ServerConstants.ERR_SUCCESS))
					activity.startActivity(new Intent(context, LoginActivity.class));
			}
		}
	}

}
