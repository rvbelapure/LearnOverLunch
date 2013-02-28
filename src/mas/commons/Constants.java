package mas.commons;

public interface Constants {

	boolean BYPASS = false;
	String SERVER_ERROR = "Server not reachable!";
	String SERVER_IP = "192.168.1.78";
	String URL_PREFIX = "http://"+SERVER_IP+":8080/LearnOverLunchService/services";
	String AUTH_URL = URL_PREFIX+"/userservice/auth";
	String SIGNUP_URL = URL_PREFIX + "/userservice/signup";
	String TAG = "LEARN";
}
