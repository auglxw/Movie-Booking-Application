package controller;

import java.util.ArrayList;
import java.util.HashMap;

import utils.DateTimeUtils;
import utils.Utils;
import enums.CinemaType;
import enums.SeatType;
import enums.SortCriteria;
import enums.FilterType;

enum SpecialDay {
  WEEKEND,
  HOLIDAY
}

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong, Augustine Lee
 @version 1.1
 @since 2022-10-30
*/
public class SystemManager {
  private ArrayList<String> holidaysArr = new ArrayList<String>();
  private HashMap<CinemaType, Float> cinemaMultMap = new HashMap<CinemaType, Float>();
  private HashMap<SeatType, Float> seatMultMap = new HashMap<SeatType, Float>();
  private ArrayList<FilterType> filtersApplied = Utils.asArrayList(FilterType.SALES, FilterType.RATING);
  private SortCriteria movieSortingCriteria = SortCriteria.SALES; // default

  public ArrayList<String> getHolidays() {
    return holidaysArr;
  }

  /*
   * @param CinemaType
   * @return Float The multiplier for the cinema type
   */
  public float getCinemaMultiplier(CinemaType ct) throws Exception {
    if (!cinemaMultMap.containsKey(ct)) {
      throw new Exception("Cinema type do not exists.");
    }
    return cinemaMultMap.get(ct);
  }

  // TODO: Add authorisation decorators for all admin methods. Do the same for other methods in other managers
  /*
   * @param CinemaType
   * @param Float The multiplier to set for the cinema type
   * @return void
   */
  public void setCinemaMultiplier(CinemaType ct, float mult) {
    cinemaMultMap.put(ct, mult);
  }

  public SortCriteria getSortingCriteria() {
    return this.movieSortingCriteria;
  }

  public void setSortingCriteria(SortCriteria sc) {
    this.movieSortingCriteria = sc;
  }

  /*
   * @param CinemaType
   * @return void
   */
  public void deleteCinemaMultipler(CinemaType ct) throws Exception {
    if (cinemaMultMap.containsKey(ct)) {
      cinemaMultMap.remove(ct);
    }
    throw new Exception("Cinema type do not exists.");
  }

  /*
   * @param SeatType
   * @return Float The multiplier for the seat type
   */
  public float getSeatMultiplier(SeatType st) throws Exception {
    if (!seatMultMap.containsKey(st)) {
      throw new Exception("Seat type do not exists.");
    }
    return seatMultMap.get(st);
  }

  /*
   * @param SeatType
   * @param Float The multiplier to set for the seat type
   * @return void
   */
  public void setSeatMultiplier(SeatType st, float multiplier) {
    seatMultMap.put(st, multiplier);
  }

  /*
   * @param SeatType
   * @return void
   */
  public void deleteSeatMultiplier(SeatType st) throws Exception {
    if (seatMultMap.containsKey(st)) {
      seatMultMap.remove(st);
    }
    throw new Exception("Seat type do not exists.");
  }

  /*
   * @param FilterType
   * @return void
   */
  public void addFilter(FilterType ft) throws Exception {
    if (filtersApplied.contains(ft)) {
      throw new Exception("Filter is already applied");
    }
    filtersApplied.add(ft);
  }

  /*
   * @param FilterType
   * @return void
   */
  public void deleteFilter(FilterType ft) throws Exception {
    if (filtersApplied.contains(ft)) {
      filtersApplied.remove(ft);
    }
    throw new Exception("Filter is not applied");
  }

  /*
   * @param Int year
   * @param Int month
   * @param Int day
   * @return void
   */
  public void deleteHoliday(int year, int month, int day) throws Exception {
    String dateString = day + "." + month + "." + year;
    if (!holidaysArr.contains(dateString)) {
      throw new Exception("Holiday do not exist in database.");
    }
    holidaysArr.removeIf(value -> dateString.equals(value));
  }

  /*
   * @param Int year
   * @param Int month
   * @param Int day
   * @return void
   */
  public void addHoliday(int year, int month, int day) throws Exception {
    String dateString = day + "." + month + "." + year;
    if (holidaysArr.contains(dateString)) {
      throw new Exception("Holiday already exists in database.");
    }
    holidaysArr.add(dateString);
  }

  /*
   * @param String dateString
   * @return Boolean whether the dateString is in the holidays array
   */
  public boolean isHoliday(String dateString) throws Exception {
    String reconstructedDateString = DateTimeUtils.dateTimeToDate(dateString) + "." + DateTimeUtils.dateTimeToMonth(dateString) + "." + DateTimeUtils.dateTimeToYear(dateString);
    return holidaysArr.contains(reconstructedDateString);
  }

}