package com.capgemini.controller;

import com.capgemini.model.Guest;
import com.capgemini.repository.RepositoryGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestController {

    @Autowired
    private RepositoryGuest repository;

    public GuestController() {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuestList")
    public List<Guest> getGuestList() {
        List<Guest> list = new ArrayList<>();
        for (Guest guest : repository.findAll()) {
            list.add(guest);
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuest")
    public Guest getGuest(@RequestParam(value = "guestNumber", required = true) int guestNumber) {
        return repository.findOne(guestNumber);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuestByName")
    public Guest getGuest(@RequestParam(value = "firstName", required = true) String firstName, @RequestParam(value = "lastName", required = true) String lastName) {

        Guest guest = new Guest();
        guest.setFirstName("Pipo");
        guest.setLastName("de Clown");
        guest.setAddress("Clownstraat");
        guest.setPostalCode("3321bp");
        guest.setCity("Clownstad");
        guest.setCountry("NL");
        guest.setPhoneNumber("06789");
        guest.setEmail("pip@slechteclown.nl");
        repository.save(guest);

        return repository.findOneByFirstNameAndLastName(firstName, lastName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addGuest")
    public Guest addGuest(@RequestBody Guest guest) {
        return repository.save(guest);
    }

    @RequestMapping(value = "/changeGuest", method = RequestMethod.POST)
    public Guest changeGuest(@RequestBody int guestNumber, Guest guest) {
        repository.delete(guestNumber);
        return repository.save(guest);
    }

    @RequestMapping(value = "/removeguest", method = RequestMethod.POST)
    public void removeGuest(@RequestBody Guest guest) {
        repository.delete(guest);
    }

}
