package com.tajawal.apiactivator.hotelactivators;
import com.tajawal.apiactivator.BaseApiActivator;
import com.tajawal.common.ConfigProperties;
import com.tajawal.common.utilities.DateUtility;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;



public class HotelSearchQueryApiActivator extends BaseApiActivator {

    public static final String ADULT = "ADT";
    public static final String CHILD = "CHD";
    public static final String CHECK_IN_DATE = "checkin";
    public static final String CHECK_OUT_DATE = "checkout";
    public static final String REQUEST_BODY_DATES = "dates";
    public static final String REQUEST_BODY_GUEST = "guest";
    public static final String REQUEST_BODY_ROOM = "room";
    public static final String REQUEST_BODY_DESTINATION = "destination";
    public static final String REQUEST_BODY_NEARME = "nearme";
    public static final String REQUEST_BODY_SOURCE = "source";
    public static final String REQUEST_BODY_PLACE_ID = "placeId";
    public static final String REQUEST_BODY_TYPE = "type";
    public static final String DESTINATION_VALUE = "Dubai";
    public static final String PLACE_ID_VALUE = "ChIJRcbZaklDXz4RYlEphFBu5r0";
    public static final String SOURCE_VALUE = "g";
    public static final String QUERY = "query";
    public static final String RESPONSE_BODY_STATUS = "status";
    public static final String RESPONSE_BODY_TITLE = "title";
    public static final String RESPONSE_BODY_DETAIL = "detail";
    public static final String RESPONSE_BODY_TYPE = "type";
    public static final String RESPONSE_BODY_QUERYPARAM = "queryParameters";

    // Override Abstract Methods

    @Override
    protected String getApiUrl() {

        String apiUrl = "/hotel/ahs/search/request";
        String mobileAPI = System.getProperty("mobileAPI") == "yes" ? "/m" + apiUrl
                : apiUrl;

        return ConfigProperties.getPublicUrl() + mobileAPI;
    }

    //  Methods of Hotel Search Query API Activator Class
    public JSONObject buildHotelSearchRequest(boolean hotelsNearMe,
                                              String destination,
                                              String source,
                                              String googlePlaceId,
                                              String checkInDateText,
                                              String checkOutDateText,
                                              int child,
                                              int adult) throws JSONException {

        JSONObject requestBody = new JSONObject();
        JSONObject dates = new JSONObject();
        dates.put(CHECK_IN_DATE, checkInDateText);
        dates.put(CHECK_OUT_DATE, checkOutDateText);


        JSONArray roomsArray = new JSONArray();
        JSONObject roomObject = new JSONObject();
        roomObject.put(REQUEST_BODY_GUEST, getRoomGuests(adult, child));
        roomsArray.put(roomObject);

        // Other important params to be passed
        requestBody.put(REQUEST_BODY_DATES, dates);
        requestBody.put(REQUEST_BODY_ROOM, roomsArray);
        requestBody.put(REQUEST_BODY_DESTINATION, destination);
        requestBody.put(REQUEST_BODY_NEARME, hotelsNearMe);
        requestBody.put(REQUEST_BODY_SOURCE, source);
        requestBody.put(REQUEST_BODY_PLACE_ID, googlePlaceId);
        return requestBody;
    }

    // Get Room with guests
    private JSONArray getRoomGuests(int adult, int child) throws JSONException {
        JSONArray guestsArray = new JSONArray();

        for (int i = 0; i < adult; i++) {
            guestsArray.put(getGuest(ADULT));
        }

        for (int i = 0; i < child; i++) {
            guestsArray.put(getGuest(CHILD));
        }

        return guestsArray;
    }

    // Get guests with adults and children
    private JSONObject getGuest(String type) throws JSONException {
        JSONObject guest = new JSONObject();
        guest.put(REQUEST_BODY_TYPE, type);
        return guest;
    }


    public void verifyResponseParametersExist(Response response)
    {
        assertThatResponseParameterExists(response, RESPONSE_BODY_TYPE);
        assertThatResponseParameterExists(response, QUERY);
        assertThatResponseParameterExists(response, RESPONSE_BODY_QUERYPARAM);
        assertThatResponseParameterExists(response, "queryParametersObj.lat");
        assertThatResponseParameterExists(response, "queryParametersObj.lng");
        assertThatResponseParameterExists(response, "queryParametersObj.isGeo");
        assertThatResponseParameterExists(response, "queryParametersObj.sortBy");
        assertThatResponseParameterExists(response, "queryParametersObj.types");


    }

    public void verifyErrorResponseParametersExist(Response response)
    {
        assertThatResponseParameterExists(response, RESPONSE_BODY_STATUS);
        assertThatResponseParameterExists(response, RESPONSE_BODY_TITLE);
        assertThatResponseParameterExists(response, RESPONSE_BODY_DETAIL);
        assertThatResponseParameterExists(response, RESPONSE_BODY_TYPE);

    }

    public void verifySuccessResponse (Response response, HashMap mapData)
    {
        assertThatResponseParameterEquals(response,RESPONSE_BODY_TYPE,mapData.get("type"));
        assertThatResponseParameterEquals(response,QUERY,mapData.get("query"));
    }

    public void verifyErrorResponse (Response response, HashMap mapData,int statusCode)
    {

        assertThatResponseParameterEquals(response,RESPONSE_BODY_TYPE,mapData.get("type"));
        assertThatResponseParameterEquals(response,RESPONSE_BODY_STATUS,statusCode);

        String output = response.getBody().asString();
        String input = mapData.get("message").toString();
      //  String path = "detail." + mapData.get("tagName");
        assertThat("check message" , output,containsString(input));
       // assertThatResponseParameterEquals(response,path,mapData.get("message"));

    }
}
