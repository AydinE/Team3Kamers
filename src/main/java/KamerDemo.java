import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class KamerDemo {

    public static void main(String[] args) {

        // Starting point of the application.

        GuestList guestList = new GuestList();

       Guest guest = guestList.addGuest(2, "Pipo", "de Clown", "Clownstraat",
               "3321bp", "Clownstad", "NL", "06789", "pipo@slechteclown.nl");

       Guest guest2 = guestList.addGuest(1, "Tijs","Straatman",
               "straat1","d","Sleeuwijk","Nedelrand","06123",
               "tstraatman@gmail.com") ;

        guestList.alterGuestAddress(guest,"Straat3");
        guestList.removeGuest("Pipo"+' '+"de Clown",guest);

        System.out.println(guestList);

        // Logging example
//        Logger logger = LogManager.getRootLogger();
//        logger.info("Hi");

    }

}
