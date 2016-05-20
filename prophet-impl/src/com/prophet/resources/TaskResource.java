package com.prophet.resources;

import com.prophet.services.DummyClass;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Named;


/**
 * This resource should be used when you want to use Prophet to help you with
 * certain tasks. She will not answer or ask personal questions in this avatar.
 * She will be equipped to help you with certain tasks.
 */
public class TaskResource {

  @Inject
  @Named
  DummyClass _dummyClass;


  public static void main(String args[]) throws IOException {
    while (true) {
      System.out.println("It's working! and the value is ");
    }
  }
}
