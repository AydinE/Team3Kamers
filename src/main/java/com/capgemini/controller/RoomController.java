package com.capgemini.controller;

import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.model.Room;
import com.capgemini.repository.BookingRepository;
import com.capgemini.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BookingRepository bookingRepository;

    public RoomController() {
    }

    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public Room addRoom(@RequestBody Room room) {
        room.setCreatedOn(LocalDateTime.now());
        room.setAvailability(true);
        return roomRepository.save(room);
    }

    @RequestMapping(value = "/getRoomList", method = RequestMethod.GET)
    public Iterable<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        roomRepository.findAll().forEach(list::add);
        return list;
    }

    @RequestMapping("/getRoom")
    public Room getRoom(@RequestParam(value = "id", required = true) int id) {
        return roomRepository.findOne(id);
    }

    @RequestMapping(value = "/changeRoom", method = RequestMethod.PUT)
    public Room changeRoom(@RequestBody Room room) {
        Room existing = roomRepository.findOne(room.getId());
        room.setCreatedOn(existing.getCreatedOn());
        return roomRepository.save(room);
    }

    @RequestMapping(value = "/blockRoom", method = RequestMethod.POST)
    public Room blockRoom(@RequestBody int roomNumber) {
        Room room = roomRepository.findOne(roomNumber);
        room.setAvailability(false);
        return roomRepository.save(room);
    }

    @RequestMapping(value = "/unblockRoom", method = RequestMethod.POST)
    public Room unblockRoom(@RequestBody int roomNumber) {
        Room room = roomRepository.findOne(roomNumber);
        room.setAvailability(true);
        return roomRepository.save(room);
    }

    @RequestMapping(value = "/deleteRoom/{id}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable int id) {
        Iterable<Booking> bookings = bookingRepository.findByRoomId(id);
        bookings.forEach(b -> {
            b.setGuest(null);
            b.setRoom(null);
            bookingRepository.delete(b.getBookingNr());
        });
        roomRepository.delete(id);
    }

}