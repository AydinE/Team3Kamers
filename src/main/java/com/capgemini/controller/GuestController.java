package com.capgemini.controller;

import com.capgemini.model.Guest;
import com.capgemini.repository.RepositoryGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class GuestController {

    private ArrayList<Guest> guestList = new ArrayList<>();

    @Autowired
    private RepositoryGuest repository;

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
    public Guest getGuest(@RequestParam(value = "guestNumber", required = true) int guestNumber) {

        return repository.findOne(guestNumber); // eigenlijk staat hier: Guest guest = repository.findOne(guestnumber);
                                                // return guest
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addGuest")
    public Guest addGuest(@RequestBody Guest guest) {
        guestList.add(guest);
        return repository.save(guest);
    }

    @RequestMapping(value = "/changeGuest", method = RequestMethod.POST)
    public Guest changeGuest(@RequestBody int guestNumber, Guest guest) {
        for (int i = 0; i < guestList.size(); i++) {
            if (guestList.get(i).getGuestNumber() == guestNumber) {
                guestList.set(i, guest);
                return repository.save(guest);
            }
        }
        return null;
    }

    @RequestMapping(value = "/removeGuest", method = RequestMethod.POST)
    public void removeGuest(@RequestBody Guest guest) {
        for (Guest removeGuest1 : guestList) {
            if (removeGuest1.getGuestNumber() == (guest.getGuestNumber())) {
                guestList.remove(guest);
                repository.delete(guest);
            }
        }

    }

}
