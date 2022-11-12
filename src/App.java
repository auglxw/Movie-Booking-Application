import enums.LoginStatus;
import model.Booking;
import model.Account.AdminAccount;
import model.Account.MovieGoerAccount;
import model.Account.GuestAccount;
import model.Ticket;

import java.util.ArrayList;

import controller.BookingManager;
import controller.CineplexManager;
import controller.LoginManager;
import controller.MovieManager;
import controller.ReviewManager;
import controller.ScreeningManager;
import controller.SystemManager;
import view.AdminConsole;
import view.LoginConsole;
import view.MovieGoerConsole;
import view.GuestConsole;
import view.ParentConsole;
import utils.Utils;
import utils.IOUtils;
import java.util.Scanner;

/**
 * @author Roy Leong
 * @version 1.0
 * @since 2022-10-30
 */
public class App {
  /**
   * Contains stateful details about the user's log in status
   * currentAccount object can be obtained from loginManager and passed to other
   * class methods
   */
  static final private LoginManager loginManager = new LoginManager();

  static private BookingManager bookingManager = new BookingManager();
  static final private CineplexManager cineplexManager = new CineplexManager();
  static final private MovieManager movieManager = new MovieManager();
  static final private ReviewManager reviewManager = new ReviewManager();
  static final private ScreeningManager screeningManager = new ScreeningManager();
  static final private SystemManager systemManager = new SystemManager();

  /**
   * Contains the possible consoles that can be selected
   */
  private final static ParentConsole[] consolesArr = {
      new LoginConsole(loginManager),
      new MovieGoerConsole(loginManager, bookingManager, cineplexManager, movieManager, reviewManager, screeningManager,
          systemManager),
      new AdminConsole(loginManager, bookingManager, cineplexManager, movieManager, reviewManager, screeningManager,
          systemManager),
      new GuestConsole(loginManager, bookingManager, cineplexManager, movieManager, reviewManager, screeningManager,
          systemManager)
  };

  /**
   * Contains the main method that runs the program
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Welcome to the MOBLIMA app!");
    Scanner sc = new Scanner(System.in);
		try	{
        loginManager.hydrateLoginManager(IOUtils.accountsDeserialiser(), IOUtils.usedStaffIdsDeserialiser());
        bookingManager.hydrateBookings(IOUtils.bookingsDeserialiser());
        cineplexManager.hydrateCineplexManager(IOUtils.cineplexesDeserialiser(), IOUtils.cinemasDeserialiser());
        movieManager.hydrateMovieManager(IOUtils.moviesDeserialiser());
        screeningManager.hydrateScreeningManager(IOUtils.screeningsDeserialiser());
        systemManager.hydrateSystemManager(IOUtils.holidaysDeserialiser(), IOUtils.cinemaMultDeserialiser(), IOUtils.seatMultDeserialiser(), IOUtils.sortCriteriaDeserialiser());
        ArrayList<Booking> arr = bookingManager.getBookings();

		} catch ( Exception e ) {
					System.out.println( "Exception >> " + e.getMessage() );
		}

    while (true) {
      System.out.println("Select your choice : \n 1. Just Browsing \n 2. Login to make booking/staff \n 3. Quit");
      Integer choice = Integer.parseInt(sc.nextLine());
      if (choice != 1 && choice != 2 && choice != 3) {
        System.out.println("Invalid input. Please try again.");
        continue;
      }
      if (choice == 1) {
        consolesArr[3].display(new GuestAccount());
      } else if (choice == 2) {
        while (true) {
          LoginStatus loginStatus = loginManager.getLoginStatus();
          System.out.println(loginStatus + " page\n");
          // matches the index of this.consolesArr
          int consolesArrIndex = loginStatus.equals(LoginStatus.LOGIN) ? 0
              : loginStatus.equals(LoginStatus.MOVIE_GOER) ? 1 : loginStatus.equals(LoginStatus.ADMIN) ? 2 : 3;
          if (consolesArrIndex == 3) {
            break;
          }
          consolesArr[consolesArrIndex].display(loginManager.getCurrentAccount());
        }
      } else {
        IOUtils.accountsSerialiser(loginManager);
        IOUtils.usedStaffIdsSerialiser(loginManager);
        IOUtils.bookingsSerialiser(bookingManager);
        IOUtils.cinemasSerialiser(cineplexManager);
        IOUtils.cineplexesSerialiser(cineplexManager);
        IOUtils.moviesSerialiser(movieManager);
        IOUtils.screeningsSerialiser(screeningManager);
        IOUtils.cinemaMultSerialiser(systemManager);
        IOUtils.holidaysSerialiser(systemManager);
        IOUtils.seatMultSerialiser(systemManager);
        IOUtils.sortCriteriaSerialiser(systemManager);
        sc.close();
        System.exit(0);
      }
    }
  }
}
