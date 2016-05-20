package com.prophet.http;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.prophet.dictionary.HttpConstants;
import com.prophet.utils.HttpUtils;
import java.util.List;
import java.util.Optional;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import static com.prophet.dictionary.HttpConstants.REQUEST;
import static com.prophet.dictionary.HttpConstants.SLICE;
import static org.apache.commons.lang.StringUtils.EMPTY;


/**
 * This class is responsible for fetching client related data from google API. Uses the
 * {@link HttpClient} internally
 */
public class TravelClient {
  private static final String GOOGLE_API_URL = "https://www.googleapis.com/qpxExpress/v1/trips/search?key=AIzaSyCT10NlXF9456QVfC9icTlhSYyDQ6nwp10";

  private final HttpClient _httpClient;

  public TravelClient() {
    _httpClient = new HttpClient();
  }

  // return the http response for now.
  public String findFlight() {
    return Optional.ofNullable(requestParams())
        .map(requestParams -> _httpClient.post(GOOGLE_API_URL, requestParams))
        .map(HttpUtils::responseEntityToString)
        .orElse(EMPTY);
  }

  private List<NameValuePair> requestParams() {
    // TODO: FIX ME!!! Need to pass the json object via post, currently only being able to pass name value pairs.
    List<NameValuePair> requestParams = Lists.newArrayList();
    NameValuePair request = new BasicNameValuePair(REQUEST, createRequest());
    //NameValuePair slice = new BasicNameValuePair()

    return requestParams;
  }

/*  private String createRequest() {
    JsonObject request = new JsonObject();
  }*/
}