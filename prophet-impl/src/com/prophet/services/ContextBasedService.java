package com.prophet.services;

import com.prophet.Context.Context;


/**
 * Created by rshendye on 5/20/16.
 */
public interface ContextBasedService extends Service {

  //TODO: consider returning a complex response later.
  String processQuery(String query, Context context);
}
