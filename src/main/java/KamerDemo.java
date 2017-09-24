import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class KamerDemo {

    public static void main(String[] args) {

        // Starting point of the application.



        Guest guest = new Guest(1, "Tijs","Straatman",
                "straat1","d","Sleeuwijk","Nedelrand","06123",
                "tstraatman@gmail.com");

        GuestList guestList = new GuestList();

        HashMap<String, Guest> hList = guestList.getGuestList();

        guest.getLastName();
        guest.getAddress();
        guest.getFirstName();
        guest.getCity();
        guest.getCountry();
        guest.getEmail();
        guest.getGuestNumber();
        guest.getPhoneNumber();
        guest.getPostalCode();

        guestList.addGuest("Tijs"+ ' '+ "Straatman",guest);

        System.out.println(guestList);


        // Logging example
//        Logger logger = LogManager.getRootLogger();
//        logger.info("Hi");

    }

}
