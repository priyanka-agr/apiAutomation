package com.tajawal.apiactivator;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class BaseApiActivator {

    // Constant values

    protected final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    // Abstract Methods

    protected abstract String getApiUrl();

    // Base Methods

    /**
     * @param requestBody
     * @return
     * @throws JSONException
     */
    public Response sendPostRequest(JSONObject requestBody) throws JSONException {
        return sendPostRequest(requestBody, null, null);
    }

    /**
     * @param requestBody
     * @param headerParameters
     * @param queryParameters
     * @return
     * @throws JSONException
     */
    public Response sendPostRequest(JSONObject requestBody, Map<String, String> headerParameters, Map<String, String> queryParameters) throws JSONException {


        // Create RequestSpecification
        RequestSpecification requestSpecification;

        if (requestBody == null) {
            requestSpecification = RestAssured.given();
        } else {
            requestSpecification = RestAssured.given().body(requestBody.toString()).with().contentType(CONTENT_TYPE_APPLICATION_JSON);
        }


        // Build Header parameters into RequestSpecification
        if (headerParameters != null) {
            for (Map.Entry<String, String> headerParameter : headerParameters.entrySet()) {
                requestSpecification.header(headerParameter.getKey(), headerParameter.getValue());
            }
        }

        // Add default header of user agent
        requestSpecification.header("x-user-agent", "qa_automation");

        // Send Post Request. Adding "src=qa_automation" to url in order to differentiate automation requests
        if (queryParameters != null) {
            requestSpecification.queryParams(queryParameters);
        }

        String apiUrl = getApiUrl();

        // Send Post Request
      // Response response = requestSpecification.when().log().all().post(getApiUrl() + "?src=qa_automation");
        Response response = requestSpecification.when().log().all().post(apiUrl + "?src=qa_automation");

        System.out.println("Response: " + response.asString());
        return response;
    }

    /**
     * @param requestBody
     * @param extendUrl
     * @return
     * @throws JSONException
     */
    public Response sendPostRequestWithExtendUrl(JSONObject requestBody, String extendUrl) throws JSONException {
        return sendPostRequestWithExtendUrl(requestBody, null, null, extendUrl);
    }

    /**
     * @param requestBody
     * @param headerParameters
     * @param queryParameters
     * @param extendUrl
     * @return
     * @throws JSONException
     */
    public Response sendPostRequestWithExtendUrl(JSONObject requestBody, Map<String, String> headerParameters, Map<String, String> queryParameters, String extendUrl) throws JSONException {
        String apiUrl = extendUrl == null ? getApiUrl() : getApiUrl() + extendUrl;

        // Create RequestSpecification
        RequestSpecification requestSpecification;

        if (requestBody == null) {
            requestSpecification = RestAssured.given();
        } else {
            requestSpecification = RestAssured.given().baseUri(extendUrl).body(requestBody.toString()).with().contentType(CONTENT_TYPE_APPLICATION_JSON);
        }

        // Build Header parameters into RequestSpecification
        if (headerParameters != null) {
            for (Map.Entry<String, String> headerParameter : headerParameters.entrySet()) {
                requestSpecification.header(headerParameter.getKey(), headerParameter.getValue());
            }
        }

        // Add default header of user agent
        requestSpecification.header("x-user-agent", "qa_automation");

       // Send Post Request. Adding "src=qa_automation" to url in order to differentiate automation requests
        if (queryParameters != null) {
            requestSpecification.queryParams(queryParameters);
        }
        // Send Post Request
        Response response = requestSpecification.when().log().all().post(apiUrl + "?src=qa_automation");

        System.out.println("Response: " + response.asString());
        return response;
    }

    /**
     * @param extendUrl
     * @return
     */
    public Response sendGetRequest(String extendUrl) {

        return sendGetRequest(null, null, extendUrl);
    }


    public Response sendGetRequest() {

        return sendGetRequest(null, null, null);
    }

    /**
     * @param queryParameters
     * @return
     */
    public Response sendGetRequest(Map<String, String> queryParameters) {
        return sendGetRequest(queryParameters, null, null);
    }

    /**
     * @param queryParameters
     * @param headerParameters
     * @return
     */
    public Response sendGetRequest(Map<String, String> queryParameters, Map<String, String> headerParameters ) {
        return sendGetRequest(queryParameters, headerParameters, null);
    }

    public Response sendGetRequest(Map<String, String> queryParameters,String extendUrl) {
        return sendGetRequest(queryParameters, null, extendUrl);
    }
    /**
     * @param queryParameters
     * @param headerParameters
     * @param extendUrl
     * @return
     */
    public Response sendGetRequest(Map<String, String> queryParameters, Map<String, String> headerParameters, String extendUrl) {
        // Create RequestSpecification
        String apiUrl = extendUrl == null ? getApiUrl() : getApiUrl() + extendUrl;
        RestAssured.baseURI = apiUrl  + "?src=qa_automation";

        RequestSpecification requestSpecification = RestAssured.given();
        // Build Header parameters into RequestSpecification
        if (queryParameters != null)  {
            requestSpecification.queryParams(queryParameters);
        }

        // Build Header parameters into RequestSpecification
        if (headerParameters != null) {
            for (Map.Entry<String, String> headerParameter : headerParameters.entrySet()) {
                requestSpecification.header(headerParameter.getKey(), headerParameter.getValue());
            }
        }

        // Send Get Request
        Response response = requestSpecification.log().all().request(Method.GET);

        System.out.println("Response: " + response.asString());
        return response;
    }

    // Send PUT request

    public Response sendPutRequest(JSONObject requestBody,String extendUrl )
    {
        return sendPUTRequest(requestBody, null, null,extendUrl);
    }

    public Response sendPutRequest( )
    {
        return sendPUTRequest(null, null, null,null);
    }

    /**
     * @param requestBody
     * @return
     */
    public Response sendPutRequest(JSONObject requestBody) {
        return sendPUTRequest(requestBody, null, null,null);
    }

    public Response sendPUTRequest(JSONObject requestBody, Map<String, String> headerParameters, Map<String, String> queryParameters,String extendUrl)
    {

        String apiUrl = extendUrl == null ? getApiUrl() : getApiUrl() + extendUrl;

        // Create RequestSpecification
        RequestSpecification requestSpecification;

        if (requestBody == null) {
            requestSpecification = RestAssured.given();
        } else {
            requestSpecification = RestAssured.given().body(requestBody.toString()).with().contentType(CONTENT_TYPE_APPLICATION_JSON);
        }


        // Build Header parameters into RequestSpecification
        if (headerParameters != null) {
            for (Map.Entry<String, String> headerParameter : headerParameters.entrySet()) {
                requestSpecification.header(headerParameter.getKey(), headerParameter.getValue());
            }
        }

        // Send Post Request
        Response response = requestSpecification.when().log().all().put(apiUrl+"?src=qa_automation");

        System.out.println("Response: " + response.asString());
        return response;

    }

    /**
     *
     * @return
     */
    public Response sendDeleteRequest() {

        // Send Delete Request
        Response response = given().when().log().all().delete(getApiUrl());
        return response;
    }
    /**
     *
     * @param extendUrl
     * @return
     */
    public Response sendDeleteRequest(String extendUrl) {
        // Send Delete Request
        String apiUrl = extendUrl == null ? getApiUrl() : getApiUrl() + extendUrl;
        RestAssured.baseURI = apiUrl  + "?src=qa_automation";

        Response response = given().when().log().all().request(Method.DELETE);
        System.out.println("Response: " + response.asString());

        return response;
    }

    /**
     * @param response
     * @param parameterPath
     * @return
     */
    public Object getParameterFromResponse(Response response, String parameterPath) {
        return response.getBody().jsonPath().get(parameterPath);
    }

    /**
     * @param response
     */
    public void assertThatStatusCodeIsOK(Response response) {
        assertThatStatusCodeEquals(response, 200);
    }

    public void assertThatStatusCodeEquals(Response response, int expectedStatusCode) {
        assertThatResponseIsNotNull(response);
        Assert.assertEquals("The Status code of the Response is different than expected", expectedStatusCode, response.getStatusCode());
    }

    public void assertThatResponseParameterEquals(Response response, String parameterPath, Object expectedParameterValue) {
        assertThatResponseParameterExists(response, parameterPath);
        Assert.assertEquals(String.format("The value of '%s' parameter is different than expected", parameterPath), expectedParameterValue, getParameterFromResponse(response, parameterPath));
    }

    public void assertThatResponseParameterContains(Response response, String parameterPath, Object expectedParameterValue) {
        assertThatResponseParameterExists(response, parameterPath);

        String responseParameter = getParameterFromResponse(response, parameterPath).toString();
        String expectedParameterText = expectedParameterValue.toString();

        Assert.assertThat(String.format("The response parameter '%s' does not contain the expected text '%s'", responseParameter, expectedParameterText),
                responseParameter, CoreMatchers.containsString(expectedParameterText));
    }

    public void assertThatResponseParameterContains(Response response, String parameterPath, String expectedParameterValue) {
        assertThatResponseParameterExists(response, parameterPath);
        Assert.assertTrue(String.format("The value of '%s' parameter does not contain the value '%s'", parameterPath, expectedParameterValue), response.getBody().jsonPath().get(parameterPath).toString().contains(expectedParameterValue));
    }

    public void assertThatResponseParameterExists(Response response, String parameterPath) {
        assertThatResponseBodyIsNotNull(response);
        Assert.assertNotNull(String.format("The '%s' parameter is missing from the Response", parameterPath), getParameterFromResponse(response, parameterPath));
    }

    public void assertThatResponseBodyIsNotNull(Response response) {
        assertThatResponseIsNotNull(response);
        Assert.assertNotNull("The Body of the Response object is null", response.getBody());
        Assert.assertNotNull("The Body's JSON path of the Response object is null", response.getBody().jsonPath());
    }

    public void assertThatResponseIsNotNull(Response response) {
        Assert.assertNotNull("The Response object is null", response);
    }



}
