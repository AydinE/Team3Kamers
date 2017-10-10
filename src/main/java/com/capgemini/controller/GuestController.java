package com.capgemini.controller;

import com.capgemini.model.Guest;
import com.capgemini.repository.GuestRepository;
import com.capgemini.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestController {

    @Autowired
    private GuestRepository repository;

    public GuestController() {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuestList")
    public List<Guest> getGuestList() {
        List<Guest> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuest")
    public Guest getGuest(@RequestParam(value = "guestNumber", required = true) int guestNumber) {
        return repository.findOne(guestNumber);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuestByName")
    public Guest getGuest(@RequestParam(value = "firstName", required = true) String firstName, @RequestParam(value = "lastName", required = true) String lastName) {
        return repository.findOneByFirstNameAndLastName(firstName, lastName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addGuest")
    public Guest addGuest(@RequestBody Guest guest) {
        Validators.emailMatcher(guest.getEmail());
        Validators.phoneMatcher(guest.getPhoneNumber());
        Validators.nameMatcher(guest.getFirstName(),guest.getLastName());
        return repository.save(guest);
    }

    @RequestMapping(value = "/changeGuest", method = RequestMethod.POST)
    public Guest changeGuest(@RequestBody int guestNumber, Guest guest) {
        repository.delete(guestNumber);
        return repository.save(guest);
    }

    @RequestMapping(value = "/removeGuest/{id}", method = RequestMethod.DELETE)
    public void removeGuest(@PathVariable int id) {
        repository.delete(id);
    }

}
