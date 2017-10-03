package com.capgemini.controller;

import com.capgemini.GuestList;
import com.capgemini.model.Guest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class GuestController {

    private ArrayList<Guest> guestList = new ArrayList<>();

    public GuestController() {
        this.guestList = guestList;


        Guest guest1 = new Guest(2, "Pipo", "de Clown", "Clownstraat",
                "3321bp", "Clownstad", "NL", "06789", "pipo@slechteclown.nl");

        Guest guest2 = new Guest(1, "Charlie", "Straatman",
                "straat1", "d", "Sleeuwijk", "Nederland", "06123",
                "cstraatman@gmail.com");
        guestList.add(guest1);
        guestList.add(guest2);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/getGuestList")
    public ArrayList<Guest> getGuestList() {

        return guestList;
    }

        @RequestMapping(method = RequestMethod.GET, value = "/api/getGuest")
    public Guest getGuest(@RequestParam(value = "firstName", required = true)String firstName,
                          @RequestParam(value= "lastName", required = true)String lastName) {

        for (Guest guest: guestList) {

            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
               // if (guest.getLastName().equals(lastName)) {
               //   return guest;
              //  }
                return guest;
            }

        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/addGuest")
    public Guest addGuest(@RequestBody Guest guest) {

        guestList.add(guest);

        return guest;

    }

//    @RequestMapping(method = RequestMethod.GET, value = "/api/getGuest")
//    public Guest getGuest(@RequestParam(value="firstName", required = true)String firstName), @RequestParam(value="lastName", required = true)String lastName){
//
//        for(Guest guest: guestList){
//            if( guest.getFirstName().equals(firstName)&& guest.getLastName().equals(lastName)){
//                return guest;
//            }
//
//        }
//        return null;
//    }

    @RequestMapping("/api/alterGuest")
    // methode wijzigen adres. Elke andere methode voor wijzigen kan als deze opgebouwd worden.
    public void alterGuestAddress(Guest guest, String address){  // guest moet nog toegevoegd worden. String is nu key.
        guest.getAddress();
        guest.setAddress(address);                                            // op deze regel roepen we de setter aan.
        guestList.add(guest);
        System.out.println("Wijziging voltooid");
        System.out.println(guest);

    }
}
