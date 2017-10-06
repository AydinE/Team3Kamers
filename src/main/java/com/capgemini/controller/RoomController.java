package com.capgemini.controller;

import com.capgemini.model.Room;
import com.capgemini.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    public RoomController() {}

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
    public Room getRoom(@RequestParam(value = "roomNr", required = true) int roomNr) {
        return roomRepository.findOne(roomNr);
    }

    @RequestMapping(value = "/changeRoom", method = RequestMethod.POST)
    public Room changeRoom(@RequestBody int roomNumber, Room room) {
        roomRepository.delete(roomNumber);
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
        roomRepository.delete(id);
    }

}