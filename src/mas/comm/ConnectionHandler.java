package mas.comm;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import mas.commons.Constants;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;

import android.util.Log;


public class ConnectionHandler {

	static String beginningOfInputBoundary = "\\A";
	static String encoding = "utf-8";

	public static String sendString(String url, String data) {

		Log.d(Constants.TAG, url);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		BasicHttpResponse httpResponse = null;

		StringEntity se = null;

		try {
			se = new StringEntity(data);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		se.setContentType("text/plain");

		httpPost.setEntity(se);
		try {
			httpResponse = (BasicHttpResponse) httpClient.execute(httpPost);
			Log.d(Constants.TAG, httpResponse.toString());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String reply = Constants.SERVER_ERROR;
		if (httpResponse != null) {
			
			try {
				InputStream is = httpResponse.getEntity().getContent();
				Scanner sc = new Scanner(is, encoding);
				reply = sc.useDelimiter(beginningOfInputBoundary).next();
				sc.close();
				is.close();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reply;
	}
}
