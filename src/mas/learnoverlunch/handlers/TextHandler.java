package mas.learnoverlunch.handlers;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class TextHandler implements OnClickListener {
	@Override
	public void onClick(View v) {
		EditText e = (EditText) v;
		e.setText("");
	}
}