package com.prophet.services;

import com.prophet.Context.Context;
import com.prophet.Context.TravelContext;
import com.prophet.http.TravelClient;
import java.util.List;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * Service for performing travel related tasks. It is limited to finding the best flights for now
 */
public class TravelService implements ContextBasedService {
  private final TravelClient _travelClient;

  public TravelService() {
    _travelClient = new TravelClient();
  }

  // TODO: Passing empty params for now. Should mark it as non null later.
  @Override
  public String processQuery(String query, Context context) {
    checkArgument(context instanceof TravelContext);

    return _travelClient.findFlight();
  }

  /**
   * Process the input query and return an output.
   * @param query
   * @return
   */
  @Override
  public String processQuery(String query) {
    return _travelClient.findFlight();
  }
}
