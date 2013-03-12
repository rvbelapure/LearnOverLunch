package mas.commons;

public interface Constants {

	boolean BYPASS = false;
	String SERVER_ERROR = "Server not reachable!";
	
	String SERVER_IP = "192.168.1.6";
	String URL_PREFIX = "http://"+SERVER_IP+":8080/LearnOverLunchService/services";
	
	String SIGNUP_URL = URL_PREFIX + "/userservice/signup";
	String AUTH_URL = URL_PREFIX+"/userservice/auth";
	String ADD_EVENT = URL_PREFIX + "/eventservice/create";
	String GET_MY_EVENTS_URL = URL_PREFIX+"/eventservice/get/event/byuser";
	String GET_EVENT_BY_CATEGORY_URL = URL_PREFIX + "/eventservice/get/event/bycategory";
	String GET_EVENT_BY_LOCATION_URL = URL_PREFIX + "/eventservice/get/event/bylocation";
	String GET_CATEGORIES = URL_PREFIX+"/categoryservice/get";
	String GET_LOCATIONS_URL = URL_PREFIX+"/locationservice/get";
	
	String TAG = "LEARN";
}
