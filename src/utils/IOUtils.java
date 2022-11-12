package utils;

import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import model.Booking;
import model.Cinema;
import model.Cineplex;
import model.DateTime;
import model.Movie;
import model.Screening;
import model.Account.Account;
import controller.BookingManager;
import controller.CineplexManager;
import controller.LoginManager;
import controller.MovieManager;
import controller.ScreeningManager;
import controller.SystemManager;
import enums.CinemaType;
import enums.SeatType;
import enums.SortCriteria;

public class IOUtils {
    public static ArrayList bookingsDeserialiser() {
        ArrayList<Booking> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/bookings.ser");
			in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void bookingsSerialiser(BookingManager bm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/bookings.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(bm.getBookings());
			out.close();
			System.out.println("Bookings Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }

    public static ArrayList cineplexesDeserialiser() {
        ArrayList<Cineplex> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/cineplexes.ser");
			in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void cineplexesSerialiser(CineplexManager cm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/cineplexes.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(cm.getCineplexes());
			out.close();
			System.out.println("Cineplexes Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }

    public static ArrayList cinemasDeserialiser() {
        ArrayList<Cinema> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/cinemas.ser");
			in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void cinemasSerialiser(CineplexManager cm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/cinemas.ser");
			out = new ObjectOutputStream(fos);
            ArrayList<Cinema> cinemasArr = new ArrayList<Cinema>();
            for (Cineplex cineplex : cm.getCineplexes()) {
                for (Cinema cinema : cm.getCinemas(cineplex)) {
                    cinemasArr.add(cinema);
                }
            }
			out.writeObject(cinemasArr);
			out.close();
			System.out.println("Cinemas Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static ArrayList moviesDeserialiser() {
        ArrayList<Movie> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/movies.ser");
			in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void moviesSerialiser(MovieManager mm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/movies.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(mm.getMovies());
			out.close();
			System.out.println("Movies Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static ArrayList screeningsDeserialiser() {
        ArrayList<Screening> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/screenings.ser");
			in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void screeningsSerialiser(ScreeningManager sm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/screenings.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(sm.getScreenings());
			out.close();
			System.out.println("Screenings Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static ArrayList holidaysDeserialiser() {
        ArrayList<DateTime> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/holidays.ser");
			in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void holidaysSerialiser(SystemManager sysm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/holidays.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(sysm.getHolidays());
			out.close();
			System.out.println("Holidays Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static HashMap cinemaMultDeserialiser() {
        HashMap<CinemaType, Float> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/cinemaMult.ser");
			in = new ObjectInputStream(fis);
            pDetails = (HashMap) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void cinemaMultSerialiser(SystemManager sysm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/cinemaMult.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(sysm.getCinemaMultMap());
			out.close();
			System.out.println("Cinemas Multipliers Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static HashMap seatMultDeserialiser() {
        HashMap<SeatType, Float> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/seatMult.ser");
			in = new ObjectInputStream(fis);
            pDetails = (HashMap) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void seatMultSerialiser(SystemManager sysm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/seatMult.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(sysm.getSeatMultMap());
			out.close();
			System.out.println("Seats Multipliers Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static SortCriteria sortCriteriaDeserialiser() {
        SortCriteria pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/sortingCriteria.ser");
			in = new ObjectInputStream(fis);
            pDetails = (SortCriteria) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void sortCriteriaSerialiser(SystemManager sysm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/sortingCriteria.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(sysm.getSortingCriteria());
			out.close();
			System.out.println("Sorting Criteria Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static ArrayList accountsDeserialiser() {
        ArrayList<Account> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/accounts.ser");
			in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void accountsSerialiser(LoginManager lm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/accounts.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(lm.getAccounts());
			out.close();
			System.out.println("Accounts Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public static ArrayList usedStaffIdsDeserialiser() {
        ArrayList<String> pDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
        try {
			fis = new FileInputStream("src/storage/usedStaffID.ser");
			in = new ObjectInputStream(fis);
            pDetails = (ArrayList) in.readObject();
			in.close();
		} catch (EOFException e) {
			return null;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
    }

    public static void usedStaffIdsSerialiser(LoginManager lm) {
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("src/storage/usedStaffIds.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(lm.getUsedStaffIds());
			out.close();
			System.out.println("Used Staff IDs Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

}
