package mas.learnoverlunch;

import mas.learnoverlunch.handlers.SignupHandler;
import mas.learnoverlunch.handlers.TextHandler;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity {
	Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        context = getApplicationContext();          
        
        EditText uname = (EditText) findViewById(R.id.signup_uname);
        EditText passwd = (EditText) findViewById(R.id.signup_passwd);
        EditText fname = (EditText) findViewById(R.id.signup_fname);
        EditText lname = (EditText) findViewById(R.id.signup_lname);
        Button submit = (Button) findViewById(R.id.signup_submit);
        
        uname.setOnClickListener(new TextHandler());
        passwd.setOnClickListener(new TextHandler());
        fname.setOnClickListener(new TextHandler());
        lname.setOnClickListener(new TextHandler());
        submit.setOnClickListener(new SignupHandler(context,this,uname, passwd, fname, lname));
    }
}
