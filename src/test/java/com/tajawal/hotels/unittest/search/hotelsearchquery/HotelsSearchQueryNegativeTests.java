package com.tajawal.hotels.unittest.search.hotelsearchquery;

import com.tajawal.apiactivator.hotelactivators.*;
import com.tajawal.common.utilities.DateUtility;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import java.util.HashMap;
import static com.tajawal.apiactivator.hotelactivators.HotelSearchQueryApiActivator.*;

public class HotelsSearchQueryNegativeTests {

    @Rule
    public TestName name = new TestName();

    private HotelSearchQueryApiActivator searchQueryApiActivator;


    @Before
    public void setUp() {
        System.out.println("************" + name.getMethodName() + "************");
        searchQueryApiActivator = new HotelSearchQueryApiActivator();
    }

    /**
     * Search by empty destination, Valid Dates and within the allowed guest limit of 4 per room
     *
     * @throws JSONException
     */

    @Test
    public void HotelsSearchQueryByEmptyDestination() throws JSONException {
        String checkInDate = DateUtility.getFormattedDateFromToday(365, DateUtility.DATE_FORMAT_PATTERN_DD_MM_YYYY);
        String checkOutDate = DateUtility.getFormattedDateFromToday(366, DateUtility.DATE_FORMAT_PATTERN_DD_MM_YYYY);
        String destination = "";
        int adult = 0;
        int child = 2;

        JSONObject requestBody = searchQueryApiActivator.buildHotelSearchRequest(
                false,
                destination,
                SOURCE_VALUE,
                PLACE_ID_VALUE,
                checkInDate,
                checkOutDate,
                child,
                adult);

        //Actions
        Response response = searchQueryApiActivator.sendPostRequest(requestBody);

        // HashMap creation to verify data in the response
        HashMap<String, String> mapData = new HashMap<>();
        mapData.put("status", "400");
        mapData.put("title", "[Gateway:``] Bad Request");
        mapData.put("type", "https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html");
        mapData.put("tagName", "destination[0]");
        mapData.put("message", "The destination field is required.");

        //Assertions
        searchQueryApiActivator.assertThatStatusCodeEquals(response, 400);
        searchQueryApiActivator.verifyErrorResponseParametersExist(response);
        searchQueryApiActivator.verifyErrorResponse(response, mapData, 400);

    }

    /**
     * Search by City, Invalid order of checkin and checkout dates and within the allowed guest limit of 4 per room
     *
     * @throws JSONException
     */
    @Test
    public void HotelsSearchQueryByWrongOrderOfDates() throws JSONException {
        String checkInDate = DateUtility.getFormattedDateFromToday(2, DateUtility.DATE_FORMAT_PATTERN_DD_MM_YYYY);
        String checkOutDate = DateUtility.getFormattedDateFromToday(1, DateUtility.DATE_FORMAT_PATTERN_DD_MM_YYYY);
        String destination = "London";
        int adult = 2;
        int child = 2;

        JSONObject requestBody = searchQueryApiActivator.buildHotelSearchRequest(
                false,
                destination,
                SOURCE_VALUE,
                PLACE_ID_VALUE,
                checkInDate,
                checkOutDate,
                child,
                adult);

        //Actions
        Response response = searchQueryApiActivator.sendPostRequest(requestBody);

        // HashMap creation to verify data in the response
        HashMap<String, String> mapData = new HashMap<>();
        mapData.put("status", "400");
        mapData.put("title", "[Gateway:``] Bad Request");
        mapData.put("type", "https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html");
        mapData.put("tagName", "dates.checkout");
        mapData.put("message", "The dates.checkout must be a date after dates.checkin.");

        //Assertions
        searchQueryApiActivator.assertThatStatusCodeEquals(response, 400);
        searchQueryApiActivator.verifyErrorResponseParametersExist(response);
        searchQueryApiActivator.verifyErrorResponse(response, mapData, 400);

    }
}
