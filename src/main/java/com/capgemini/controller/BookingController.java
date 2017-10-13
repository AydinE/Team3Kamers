package com.capgemini.controller;

import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.model.Room;
import com.capgemini.repository.BookingRepository;
import com.capgemini.repository.GuestRepository;
import com.capgemini.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomRepository roomRepository;

    public BookingController() {
    }

    @RequestMapping(value = "/addBooking", method = RequestMethod.POST)
    public Booking addBooking(@RequestBody Booking booking) {
        if (booking.getStartDate().isAfter(LocalDateTime.now()) && booking.getEndDate().isAfter(booking.getStartDate())) {
            Guest guest = guestRepository.findOne(booking.getGuest().getId());
            Room room = roomRepository.findOne(booking.getRoom().getId());
            booking.setGuest(guest);
            booking.setRoom(room);
            return bookingRepository.save(booking);
        }
        return null;
    }

    @RequestMapping(value = "/getBookingList", method = RequestMethod.GET)
    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        bookingRepository.findAll().forEach(list::add);
        return list;
    }

    @RequestMapping("/getBooking")
    public Booking getBooking(@RequestParam(value = "bookingNr", required = true) int bookingNr) {
        return bookingRepository.findOne(bookingNr);
    }

    @RequestMapping(value = "/changeBooking", method = RequestMethod.POST)
    public Booking changeBooking(@RequestBody int bookingNr, Booking booking) {
        bookingRepository.delete(bookingNr);
        return bookingRepository.save(booking);
    }

    @RequestMapping(value = "/deleteBooking", method = RequestMethod.POST)
    public void deleteBooking(@RequestBody Booking booking) {
        bookingRepository.delete(booking);
    }

    @RequestMapping(value = "/guestCheckIn", method = RequestMethod.POST)
    public Booking checkIn(@RequestBody Booking bookingId) {
        Booking booking = bookingRepository.findOne(bookingId.getBookingNr());
        booking.setCheckedIn(true);
        return bookingRepository.save(booking);
    }
}