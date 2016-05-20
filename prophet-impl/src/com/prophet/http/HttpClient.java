package com.prophet.http;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * Primary client which is responsible for making GET and POST requests to the web.
 */
public class HttpClient {
  private static final String UTF_8 = "UTF-8";

  /**
   * Makes a post request to the given uri with the params.
   *
   * @param uri
   * @param requestParams
   * @return
   */
  public HttpEntity post(String uri, List<NameValuePair> requestParams) {
    Preconditions.checkNotNull(uri);
    Preconditions.checkNotNull(requestParams);

    // create the http request
    org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(uri);
    try {
      httpPost.setEntity(new UrlEncodedFormEntity(requestParams, UTF_8));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    // execute the http request
    try {
      HttpResponse httpResponse = httpClient.execute(httpPost);
      return httpResponse.getEntity();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }
}
