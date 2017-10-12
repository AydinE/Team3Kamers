package com.capgemini.controller;

import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.repository.BookingRepository;
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
    private GuestRepository guestRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public GuestController() { //voor het heen en weer sturen van info met de database, moet de constructor leeg zin.

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuestList")
    public List<Guest> getGuestList() {
        List<Guest> list = new ArrayList<>();
        guestRepository.findAll().forEach(list::add);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuest/")
    public Guest getGuest(@RequestParam(value = "id", required = true) int id) {
        return guestRepository.findOne(id);
    }

//    @RequestMapping(value="{id}/", method= RequestMethod.GET)
//    public Guest get(@PathVariable int id) {
//        return guestRepository.findOne(id);
//    }



    @RequestMapping(method = RequestMethod.GET, value = "/getGuestByName")
    public Guest getGuest(@RequestParam(value = "firstName", required = true) String firstName, @RequestParam(value = "lastName", required = true) String lastName) {
        return guestRepository.findOneByFirstNameAndLastName(firstName, lastName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addGuest")
    public Guest addGuest(@RequestBody Guest guest) {
        if (Validators.emailMatcher(guest.getEmail()) &&
            Validators.phoneMatcher(guest.getPhoneNumber()) &&
            Validators.nameMatcher(guest.getFirstName(),guest.getLastName()) &&
            Validators.countryCityMatcher(guest.getCountry(),guest.getCity()) &&
            Validators.postalCodeAdressMatcher(guest.getPostalCode(),guest.getAddress())) {
            return guestRepository.save(guest);
        }
        return null;
    }

    @RequestMapping(value = "/changeGuest", method = RequestMethod.PUT)
    public Guest changeGuest(@RequestBody Guest guest) {
        Validators.emailMatcher(guest.getEmail());
        Validators.phoneMatcher(guest.getPhoneNumber());
        Validators.nameMatcher(guest.getFirstName(),guest.getLastName());
        Validators.countryCityMatcher(guest.getCountry(),guest.getCity());
        Validators.postalCodeAdressMatcher(guest.getPostalCode(),guest.getAddress());
        return guestRepository.save(guest);
    }

    @RequestMapping(value = "/removeGuest/{id}", method = RequestMethod.DELETE)
    public void removeGuest(@PathVariable int id) {
        Iterable<Booking> bookings = bookingRepository.findByGuestId(id);
        bookings.forEach(b -> {
            b.setGuest(null);
            b.setRoom(null);
            bookingRepository.delete(b.getBookingNr());
        });
        guestRepository.delete(id);
    }

}

