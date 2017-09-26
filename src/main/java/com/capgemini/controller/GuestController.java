package com.capgemini.controller;

import com.capgemini.GuestList;
import com.capgemini.model.Guest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GuestController {

    private ArrayList<Guest> guestList = new ArrayList<>();

    public GuestController() {
        this.guestList = guestList;


        Guest guest1 = new Guest(2, "Pipo", "de Clown", "Clownstraat",
                "3321bp", "Clownstad", "NL", "06789", "pipo@slechteclown.nl");

        Guest guest2 = new Guest(1, "Charlie", "Straatman",
                "straat1", "d", "Sleeuwijk", "Nedelrand", "06123",
                "tstraatman@gmail.com");
        guestList.add(guest1);
        guestList.add(guest2);
    }

    @RequestMapping("/api/getGuestList")
    public ArrayList<Guest> getGuestList() {
        return guestList;
    }



    @RequestMapping("/api/addGuest")
    public Guest addGuest(@RequestParam(value="guestNumber", required = true) int guestNumber, @RequestParam(value="firstName", required = true)
            String firstName, @RequestParam(value="lastName", required = true) String lastName, @RequestParam(value="address", required = true)String address,
                          @RequestParam(value="postalCode", required = true)String postalCode,@RequestParam(value="city", required = true)String city, @RequestParam(value="country",
            required = true)String country, @RequestParam(value="phoneNumber", required = true)String phoneNumber, @RequestParam(value="email", required = true)String email) {

        Guest guest = new Guest(guestNumber,firstName,lastName,address,postalCode,city,country,phoneNumber,email);

        guestList.add(guest);

        return guest;

    }
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
