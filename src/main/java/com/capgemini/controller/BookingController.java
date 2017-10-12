package com.capgemini.controller;

import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.model.Room;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import com.capgemini.repository.BookingRepository;
import com.capgemini.repository.GuestRepository;
import com.capgemini.repository.RoomRepository;
import org.apache.tomcat.jni.Local;
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

    private ArrayList<Booking> bookingList = new ArrayList<>();

    public BookingController() {

    }

    @GetMapping("/generateTestData")
    public String generateTestData() {

        Guest guest = new Guest();
        guest.setFirstName("Sinter");
        guest.setLastName("Klaas");
        guest.setAddress("Pietenlaan 10");
        guest.setPostalCode("1234AB");
        guest.setCity("Barcelona");
        guest.setCountry("Spanje");
        guest.setPhoneNumber("112");
        guest.setEmail("sinterklaas@spanje.nl");

        guest = guestRepository.save(guest);

        Room room = new Room();
        room.setNameOfRoom("Room 1");
        room.setAvailability(true);
        room.setSizeOfRoom(RoomSize.FIVE_SIX_PERSONS);
        room.setTypeOfRoom(RoomType.LUXURY);
        room.setCreatedOn(LocalDateTime.now());

        room = roomRepository.save(room);

        Room room2 = new Room();
        room2.setNameOfRoom("Room 2");
        room2.setAvailability(false);
        room2.setSizeOfRoom(RoomSize.THREE_FOUR_PERSONS);
        room2.setTypeOfRoom(RoomType.BUDGET);
        room2.setCreatedOn(LocalDateTime.now());

        room2 = roomRepository.save(room2);

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setGuest(guest);
        booking.setCheckedIn(false);
        booking.setStartDate(LocalDateTime.of(2017, 10, 8, 16, 00));
        booking.setEndDate(LocalDateTime.of(2017, 10, 10, 11, 00));

        bookingRepository.save(booking);

        return "Database populated with test data";
    }

    @GetMapping("/freerooms")
    public List<Room> getFreeRooms() {
        Booking booking = new Booking();

        List<Room> freeRoomsList = new ArrayList<>();
        roomRepository.findAll().forEach(freeRoomsList::add);

        List<Booking> allBookinglist = new ArrayList<>();
        bookingRepository.findAll().forEach(allBookinglist::add);

        for (Booking registeredBooking : allBookinglist) {

            // Als nieuwe startdatum tussen bestaande booking start en einddatum ligt, gooi kamer uit lijst..
            if (registeredBooking.getStartDate().isAfter(booking.getStartDate()) && registeredBooking.getStartDate().isBefore(booking.getEndDate())) {

                freeRoomsList.remove(registeredBooking.getRoom());
            }

            // Als nieuwe einddatum tussen bestaande booking start en einddatum ligt, gooi kamer uit lijst..
            if (registeredBooking.getEndDate().isAfter(booking.getStartDate()) && registeredBooking.getEndDate().isBefore(booking.getEndDate())) {

                freeRoomsList.remove(registeredBooking.getRoom());

            }

        }
        return freeRoomsList;
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

    @RequestMapping(value = "/guestCheckOut", method = RequestMethod.POST)
    public Booking checkOut(@RequestBody Booking bookingId) {
        Booking booking = bookingRepository.findOne(bookingId.getBookingNr());
        booking.setCheckedIn(false);
        return bookingRepository.save(booking);
    }


}