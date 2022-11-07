package controller;
import enums.ShowStatus;
import enums.SortCriteria;

import java.util.ArrayList;
import model.Movie;
import utils.SalesUtils;
import enums.Advisory;
import enums.Genre;

/**
 * Account for a staff member.
 * Contains the staff id
 *
 @author Roy Leong
 @version 1.0
 @since 2022-10-30
*/
public class MovieManager {
  private ArrayList<Movie> moviesArr = new ArrayList<Movie>();

  public MovieManager() {}

  public void addMovie(Movie movie) {
    this.moviesArr.add(movie);
  }

  public void removeMovie(String movieTitle) throws Exception{
    Movie movie = this.getMovieByName(movieTitle);
    this.updateMovieShowingStatus(movie, ShowStatus.END_OF_SHOWING);
  }

  public void updateMovieShowingStatus(Movie movie, ShowStatus showStatus) {
    movie.setShowingStatus(showStatus);
  }

  public void updateMovie(Movie movie, String title, String synopsis, String director, ArrayList<String> cast, Advisory advisoryRating, Genre genre, ShowStatus showStatus) {
    if (title != null) {
      movie.setTitle(title);
    }
    if (synopsis != null) {
      movie.setSynopsis(synopsis);
    }
    if (director != null) {
      movie.setDirector(director);
    }
    if (cast != null) {
      movie.setCast(cast);
    }
    if (advisoryRating != null) {
      movie.setAdvisoryRating(advisoryRating);
    }
    if (genre != null) {
      movie.setGenre(genre);
    }
    if(showStatus != null) {
      movie.setShowingStatus(showStatus);
    }
  }

  public ArrayList<Movie> getMovies(SortCriteria sortingCriteria, ArrayList<ShowStatus> showStatuses) {
    ArrayList<Movie> movieLst = new ArrayList<Movie>();

    // First attempt to filter movies by show statuses if applicable else copies the full exsisting movie list
    if (showStatuses.isEmpty()) {
      for (Movie movie : this.moviesArr) {
        movieLst.add(movie);
      }
    }
    else {
      for (Movie movie : this.moviesArr) {
        for (ShowStatus status : showStatuses) {
          if (movie.getShowingStatus().equals(status)) {
            movieLst.add(movie);
          }
        }
      }
    }

    // Then sorts the list according to a specified sort criteria if applicable
    if (sortingCriteria.equals(SortCriteria.TITLE)) {
      movieLst.sort((m1, m2) -> m1.getTitle().compareTo(m2.getTitle()));
    }
    // Sorts movies by overall rating in descending order
    else if (sortingCriteria.equals(SortCriteria.RATING)) {
      movieLst.sort((m1, m2) -> ((Float) m2.getOverallRating()).compareTo((Float) m1.getOverallRating()));
    }
    else if (sortingCriteria.equals(SortCriteria.SALES)) {
      // Sorts movies by overall sales in descending order
      BookingManager bManager = new BookingManager();
      movieLst.sort((m1, m2) -> ((Float) SalesUtils.getSalesByMovie(bManager.getBookings(), m2.getTitle())).compareTo(
                                (Float) SalesUtils.getSalesByMovie(bManager.getBookings(), m1.getTitle())));
    }

    return movieLst;
  }

  public ArrayList<Movie> getMovies(){
    return moviesArr;
  }

  public ArrayList<Movie> getMovies(ArrayList<ShowStatus> showStatus){
    return getMovies(null, showStatus);
  }

  public ArrayList<Movie> getMovies(SortCriteria sortCriteria){
    return getMovies(sortCriteria, null);
  }

  public Movie getMovieByName(String title) throws Exception {
    for (Movie movie : moviesArr) {
      if (movie.getTitle().equals(title)) {
        return movie;
      }
    }
    
    throw new Exception("No movie with that name found");
  }
  
}