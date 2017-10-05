package com.capgemini.controller;

import com.capgemini.model.Booking;
import com.capgemini.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    private ArrayList<Booking> bookingList= new ArrayList<>();

    public BookingController(){

    }

    @RequestMapping(value = "/addBooking", method = RequestMethod.POST)
    public Booking addBooking(@RequestBody Booking booking){
        System.out.println("In booking");
        bookingRepository.save(booking);
        return booking;
    }

    @RequestMapping(value = "/getAllBooking", method = RequestMethod.GET)
    public Iterable<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @RequestMapping("/getBooking")
    public Booking getBooking(@RequestParam(value = "bookingNr", required = true) int bookingNr) {
        return bookingRepository.findOne(bookingNr);
    }

    @RequestMapping(value = "/changeBooking", method = RequestMethod.POST)
    public Booking changeBooking(@RequestBody int bookingNr, Booking booking){
        for (Booking oldBooking : bookingList){
            if (oldBooking.getBookingNr() == bookingNr) {
                oldBooking.setBookingNr(booking.getBookingNr());
                oldBooking.setGuest(booking.getGuest());
                oldBooking.setRoom(booking.getRoom());
                oldBooking.setStartDate(LocalDateTime.now());
                oldBooking.setEndDate(LocalDateTime.now());
                return booking;
            }
            System.out.println(booking);
        }
        return null;
    }

    @RequestMapping(value = "/deleteBooking", method = RequestMethod.POST)
    public void deleteBooking(@RequestBody Booking booking) {
        bookingRepository.delete(booking);
        }



    @RequestMapping(value = "/guestCheckIn", method = RequestMethod.POST)
    public void checkIn(@RequestBody Booking booking) {
        for (Booking booking1 : bookingList) {
            if (booking1.getBookingNr() == booking.getBookingNr()) {
                booking.setCheckedIn(true);
           }
        }
    }

}