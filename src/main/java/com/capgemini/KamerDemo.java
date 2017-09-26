package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//public class KamerDemo {

@SpringBootApplication
public class KamerDemo {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(KamerDemo.class, args);
    }
}

//    public static void main(String[] args) {
//
//        // Starting point of the application.
//
//        GuestList guestList = new GuestList();
//
//       Guest guest1 = guestList.addGuest(2, "Pipo", "de Clown", "Clownstraat",
//               "3321bp", "Clownstad", "NL", "06789", "pipo@slechteclown.nl");
//
//       Guest guest2 = guestList.addGuest(1, "Charlie","Straatman",
//               "straat1","d","Sleeuwijk","Nedelrand","06123",
//               "tstraatman@gmail.com") ;
//
//        guestList.alterGuestAddress(guest1,"Straat3");
//        guestList.removeGuest("Pipo"+' '+"de Clown",guest);
//
//        System.out.println(guestList);
//
//        // Logging example
////        Logger logger = LogManager.getRootLogger();
////        logger.info("Hi");
//
//    }

//}
