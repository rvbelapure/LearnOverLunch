package mas.commons;

public interface Constants {

	boolean BYPASS = false;
	String SERVER_ERROR = "Server not reachable!";
	
	String SERVER_IP = "10.0.0.39";
	String URL_PREFIX = "http://"+SERVER_IP+":8080/LearnOverLunchService/services";
	String AUTH_URL = URL_PREFIX+"/userservice/auth";
	String GET_MY_EVENTS_URL = URL_PREFIX+"/eventservice/get/event/byuser";
	String SIGNUP_URL = URL_PREFIX + "/userservice/signup";
	String GET_EVENTS_BY_CATEGORY = URL_PREFIX+"/eventservice/get/event/bycategory";
	String TAG = "LEARN";
	String ADD_EVENT = URL_PREFIX + "/eventservice/create";
}
