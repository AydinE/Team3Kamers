package com.capgemini.controller;

import com.capgemini.model.Room;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import com.capgemini.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    private ArrayList<Room> roomList = new ArrayList<>();

    public RoomController() {

    }

    //http://localhost:8080/api/addRoom?roomNr=1&roomType=NORMAL&roomSize=ONE_PERSON
    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public Room addRoom(@RequestBody Room room) {
        roomRepository.save(room);
        return room;
    }

    @RequestMapping(value = "/getAllRooms", method = RequestMethod.GET)
    public Iterable<Room> getAllRooms() {return roomRepository.findAll();
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
        for (Room room : roomList) {
            if (room.getRoomNr() == roomNumber) {
                room.setAvailability(false);
                return room;
            }
        }
        return null;
    }

    @RequestMapping(value = "/unblockRoom", method = RequestMethod.POST)
    public Room unblockRoom(@RequestBody int roomNumber) {
        for (Room room : roomList) {
            if (room.getRoomNr() == roomNumber) {
                room.setAvailability(true);
                return room;
            }
        }
        return null;
    }

    @RequestMapping(value = "/deleteRoom", method = RequestMethod.POST)
    public void deleteRoom(@RequestBody Room room) {roomRepository.delete(room); }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

}