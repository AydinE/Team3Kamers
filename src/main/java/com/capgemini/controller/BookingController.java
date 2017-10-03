package com.capgemini.controller;

import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingController {

    private ArrayList<Booking> bookingList= new ArrayList<>();

    public BookingController(){

//       bookingList.add(new Booking(1, "guest1", ));
//        bookingList.add(new Booking (2, "guest2"));
    }


    @RequestMapping(value = "/api/deleteBooking", method = RequestMethod.POST)
    public void deleteBooking(@RequestBody Booking booking) {
        for (Booking excistingBooking : bookingList) {
            if (excistingBooking.getBookingNr() == booking.getBookingNr()) {
                bookingList.remove(excistingBooking);
            }
        }
    }

    @RequestMapping(value = "/api/guestCheckIn", method = RequestMethod.POST)
    public void checkIn(@RequestBody Booking booking) {
        for (Booking booking1 : bookingList) {
            if (booking1.getBookingNr() == booking.getBookingNr()) {
                if (booking.isCheckedIn() == false) {
                    booking.setCheckedIn(true);

                }
            }
        }
    }

}