package mas.learnoverlunch;

import mas.learnoverlunch.handlers.LoginHandler;
import mas.learnoverlunch.handlers.TextHandler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	Context context = null;
	Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();
        activity = this;
        
        EditText uname = (EditText) findViewById(R.id.EditText01);
        uname.setOnClickListener(new TextHandler());
        EditText passwd = (EditText) findViewById(R.id.editText1);
        passwd.setOnClickListener(new TextHandler());
    
        Button loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(new LoginHandler(context, this, uname, passwd));
        
        Button signupButton = (Button) findViewById(R.id.button_signup);
        signupButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activity.startActivity(new Intent(context, SignupActivity.class));	
			}
		});
        
        
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }   
    
}