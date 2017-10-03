package com.capgemini.controller;

import com.capgemini.model.Booking;
import com.capgemini.model.Room;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingController {

    private ArrayList<Booking> bookingList= new ArrayList<>();



    @RequestMapping(value = "/api/deleteBooking", method = RequestMethod.POST)
    public void deleteBooking(@RequestBody Booking booking) {
        for (Booking excistingBooking : bookingList) {
            if (excistingBooking.getBookingNr() == booking.getBookingNr()) {
                bookingList.remove(excistingBooking);
            }
        }
    }
}