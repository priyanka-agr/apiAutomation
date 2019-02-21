package com.tajawal.hotels.unittest.search.hotelsearchquery;

import com.tajawal.common.utilities.DateUtility;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import static com.tajawal.apiactivator.hotelactivators.HotelSearchQueryApiActivator.*;
import com.tajawal.apiactivator.hotelactivators.*;
import java.util.HashMap;

public class HotelsSearchQueryPositiveTests {

    @Rule
    public TestName name = new TestName();

    private HotelSearchQueryApiActivator searchQueryApiActivator;

    @Before
    public void setUp() {
        System.out.println("************" + name.getMethodName() + "************");
        searchQueryApiActivator = new HotelSearchQueryApiActivator();
    }
    /**
     * Search by Country, Valid Dates and within the allowed guest limit of 4 per room
     * @throws JSONException
     */
    @Test
    public void HotelsSearchQueryByCountryAndFutureDates() throws JSONException {
        String checkInDate = DateUtility.getFormattedDateFromToday(37, DateUtility.DATE_FORMAT_PATTERN_DD_MM_YYYY);
        String checkOutDate = DateUtility.getFormattedDateFromToday(40, DateUtility.DATE_FORMAT_PATTERN_DD_MM_YYYY);
        String destination = "United-Kingdom";
        int adult = 1;
        int child = 1;

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

        String query = destination.toLowerCase() + "/" + checkInDate + "/" + checkOutDate + "/"
                + String.valueOf(adult) + "_adult" + "," + String.valueOf(child) + "_child,_age";

        System.out.println(query);

        // HashMap creation to verify data in the response
        HashMap<String, String> mapData = new HashMap<>();
        mapData.put("type", "location");
        mapData.put("query", query);

        //Assertions
        searchQueryApiActivator.verifySuccessResponse(response, mapData);
        searchQueryApiActivator.verifyResponseParametersExist(response);
        searchQueryApiActivator.assertThatStatusCodeIsOK(response);

    }

}