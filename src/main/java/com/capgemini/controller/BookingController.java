package com.capgemini.controller;

import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import com.capgemini.model.Guest;
import com.capgemini.model.Room;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BookingController {

    private ArrayList<Booking> bookingList= new ArrayList<>();

    public BookingController(){


    }

    @RequestMapping("/api/addBooking")
    public Booking addBooking(@RequestParam(value="bookingNr", required = true) int bookingNr, @RequestParam(value = "guest", required = true)Guest guest, @RequestParam(value = "room", required = true)Room room, @RequestParam(value = "startDate", required = true)LocalDateTime startDate, @RequestParam(value = "endDate", required = true)LocalDateTime endDate, @RequestParam(value = "checkedIn", required = true) boolean checkedIn) {

        Booking booking = new Booking(bookingNr, guest, room, startDate, endDate, checkedIn);

        bookingList.add(booking);
        return booking;
    }

    @RequestMapping("/api/getAllBooking")
    public ArrayList<Booking> getAllBooking(@RequestBody Booking booking) {
        return bookingList;
    }

    @RequestMapping("/api/getBooking")
    public Booking getBooking(@RequestParam(value = "bookingNr", required = true) int bookingNr) {
        for (Booking requiredBooking : bookingList){
            if (requiredBooking.getBookingNr() == bookingNr){
                return requiredBooking;
            }
        }
        return null;
    }

    @RequestMapping(value = "/api/changeBooking", method = RequestMethod.POST) //does not return anything yet VOID
    public void changeBooking(@RequestBody Booking booking){

        for (Booking changedBooking : bookingList){

            if (changedBooking.getBookingNr() == booking.getBookingNr()) {

                changedBooking.setBookingNr(booking.getBookingNr());
                changedBooking.setGuest(booking.getGuest());
                changedBooking.setRoom(booking.getRoom());
                changedBooking.setStartDate(LocalDateTime.now());
                changedBooking.setEndDate(LocalDateTime.now());
            }
            System.out.println(booking);
        }

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