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

      //  try{ (int)firstName, nog afmaken.

        for (Guest guest: guestList) {

            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
                // if (guest.getLastName().equals(lastName)) {
                //   return guest;
                //  }

                return guest;
            }
            }

       // }
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

//

    @RequestMapping(value = "/api/changeGuest", method = RequestMethod.POST)
    public Guest changeGuest (@RequestBody Guest guest){
        for (Guest changedGuest : guestList){
            if (changedGuest.getGuestNumber() == guest.getGuestNumber()) {
                changedGuest.setFirstName(guest.getFirstName());
                changedGuest.setLastName(guest.getLastName());
                changedGuest.setAddress(guest.getAddress());
                changedGuest.setPostalCode(guest.getPostalCode());
                changedGuest.setCity(guest.getCity());
                changedGuest.setCountry(guest.getCountry());
                changedGuest.setPhoneNumber(guest.getPhoneNumber());
                changedGuest.setEmail(guest.getEmail());
            }
            System.out.println(guest);
            return guest;
        }
        return null;
    }

}
