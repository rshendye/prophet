package com.prophet.Context;

import com.prophet.enums.Airport;
import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;


/**
 * Context containing travel related information. Should be used only by
 * {@link }
 */
public class TravelContext implements Context {
  private Airport _origin;
  private Airport _destination;
  private Passengers _passengers;
  private Date _date;     // date for beginning travel

  private class Passengers {
    private int adults;
    private int children;
    private int seniorCitizen;

    public Passengers(int n) {
      adults = n;
    }

    public  Passengers(int adults, int children, int seniorCitizen) {
      checkArgument(adults >= 0 && children >= 0 && seniorCitizen >= 0);

      this.adults = adults;
      this.children = children;
      this.seniorCitizen = seniorCitizen;
    }

    public int getAdults() {
      return adults;
    }

    public void setAdults(int adults) {
      this.adults = adults;
    }

    public int getChildren() {
      return children;
    }

    public void setChildren(int children) {
      this.children = children;
    }

    public int getSeniorCitizen() {
      return seniorCitizen;
    }

    public void setSeniorCitizen(int seniorCitizen) {
      this.seniorCitizen = seniorCitizen;
    }
  }
}
