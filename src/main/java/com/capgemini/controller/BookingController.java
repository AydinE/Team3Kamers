package com.capgemini.controller;

import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.model.Room;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import com.capgemini.repository.BookingRepository;
import com.capgemini.repository.GuestRepository;
import com.capgemini.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomRepository roomRepository;

    private ArrayList<Booking> bookingList= new ArrayList<>();

    public BookingController(){

    }

    @GetMapping("/generateTestData")
    public String generateTestData() {

        Guest guest = new Guest();
        guest.setFirstName("Sinter");
        guest.setLastName("Klaas");
        guest.setAddress("Pietenlaan 10");
        guest.setPostalCode("1234AB");
        guest.setCountry("Spanje");
        guest.setEmail("sinterklaas@spanje.nl");

        guest = guestRepository.save(guest);

        Room room = new Room();
        room.setAvailability(true);
        room.setSizeOfRoom(RoomSize.FIVE_SIX_PERSONS);
        room.setTypeOfRoom(RoomType.LUXURY);

        room = roomRepository.save(room);

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setGuest(guest);
        booking.setCheckedIn(false);
        booking.setStartDate(LocalDateTime.of(2017,10,8, 16,00));
        booking.setEndDate(LocalDateTime.of(2017,10,10, 11,00));

        bookingRepository.save(booking);

        return "Database populated with test data";
    }

    @RequestMapping(value = "/addBooking", method = RequestMethod.POST)
    public Booking addBooking(@RequestBody Booking booking){

        Guest guest = guestRepository.findOne(booking.getGuest().getGuestNumber());

        Room room = roomRepository.findOne(booking.getRoom().getRoomNr());

        booking.setGuest(guest);
        booking.setRoom(room);

        return bookingRepository.save(booking);
    }

    @RequestMapping(value = "/getAllBookings", method = RequestMethod.GET)
    public Iterable<Booking> getAllBookings() {
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
                return bookingRepository.save(booking);
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
                bookingRepository.save(booking);
           }

        }
    }

}