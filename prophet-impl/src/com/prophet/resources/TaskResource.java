package com.prophet.resources;

import com.prophet.Context.TravelContext;
import com.prophet.enums.TaskType;
import com.prophet.services.TravelService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static com.prophet.dictionary.Constants.EXIT;
import static org.apache.commons.lang.StringUtils.EMPTY;


/**
 * This resource should be used when you want to use Prophet to help you with
 * certain tasks. She will not answer or ask personal questions in this avatar.
 * She will be equipped to help you with certain tasks.
 */
public class TaskResource {
  private static final TravelService _travelService = new TravelService();

  //TODO: this can be part of the main resource. For now leaving it standalone for easy usage.

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String taskType;

    while (true) {
      System.out.println("Hi How can I help you? I'm currently equipped with performing the following tasks:");
      printAllTaskOptions();
      System.out.println("Pick any one that you want. ");
      taskType = br.readLine();

      if (EXIT.equalsIgnoreCase(taskType)) {
        break;
      }

      System.out.println(String.format("Your chosen option is: %s. Finding flights from LAX to SFO for 30th may. ", taskType));
      _travelService.processQuery(EMPTY, new TravelContext());
    }
  }

  /**
   * Prints all tasks that Prophet can work on.
   */
  private static void printAllTaskOptions() {
    Stream.of(TaskType.values())
        .map(TaskType::toString)
        .forEach(System.out::println);
  }
}
