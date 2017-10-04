package com.capgemini.controller;

import com.capgemini.model.Guest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class GuestController {

    private ArrayList<Guest> guestList = new ArrayList<>();

    public GuestController() {
        this.guestList = guestList;
        Guest guest = new Guest();
        guest.setFirstName("Pipo");
        guest.setLastName("de Clown");
        guest.setAddress("Clownstraat");
        guest.setPostalCode("3321bp");
        guest.setCity("Clownstad");
        guest.setCountry("NL");
        guest.setPhoneNumber("06789");
        guest.setEmail("pip@slechteclown.nl");
        guestList.add(guest);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuestList")
    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuest")
    public Guest getGuest(@RequestParam(value = "firstName", required = true) String firstName,
                          @RequestParam(value = "lastName", required = true) String lastName) {
        for (Guest guest : guestList) {
            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
                return guest;
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addGuest")
    public Guest addGuest(@RequestBody Guest guest) {
        guestList.add(guest);
        return guest;
    }

    @RequestMapping(value = "/changeGuest", method = RequestMethod.POST)
    public Guest changeGuest(@RequestBody int guestNumber, Guest guest) {
        for (int i = 0; i < guestList.size(); i++) {
            if (guestList.get(i).getGuestNumber() == guestNumber) {
                guestList.set(i, guest);
                return guest;
            }
        }
        return null;
    }

    @RequestMapping(value = "/removeguest", method = RequestMethod.POST)
    public void removeGuest(@RequestBody Guest guest) {
        for (Guest removeGuest1 : guestList) {
            if (removeGuest1.getGuestNumber() == (guest.getGuestNumber())) {
                guestList.remove(guest);
            }
        }

    }

}
