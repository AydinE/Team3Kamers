package com.capgemini.controller;

import com.capgemini.model.Room;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class RoomController {

    private ArrayList<Room> roomList = new ArrayList<>();

    public RoomController() {
        Room room = new Room();
        room.setRoomNr(1);
        room.setTypeOfRoom(RoomType.BUDGET);
        room.setSizeOfRoom(RoomSize.ONE_PERSON);
        room.setCreatedOn(LocalDateTime.now());
        roomList.add(room);
    }

    //http://localhost:8080/api/addRoom?roomNr=1&roomType=NORMAL&roomSize=ONE_PERSON
    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public Room addRoom(@RequestBody Room room) {
        roomList.add(room);
        return room;
    }

    @RequestMapping(value = "/getAllRooms", method = RequestMethod.GET)
    public ArrayList<Room> getAllRooms() {
        return roomList;
    }

    @RequestMapping("/getRoom")
    public Room getRoom(@RequestParam(value = "roomNr", required = true) int roomNr) {
        for (Room requiredRoom : roomList) {
            if (requiredRoom.getRoomNr() == roomNr) {
                return requiredRoom;
            }
        }
        return null;
    }

    @RequestMapping(value = "/changeRoom", method = RequestMethod.POST)
    public Room changeRoom(@RequestBody int roomNumber, Room room) {
        for (Room oldRoom : roomList) {
            if (oldRoom.getRoomNr() == roomNumber) {
                oldRoom.setRoomNr(room.getRoomNr());
                oldRoom.setTypeOfRoom(room.getTypeOfRoom());
                oldRoom.setSizeOfRoom(room.getSizeOfRoom());
                oldRoom.setCreatedOn(room.getCreatedOn());
                return room;
            }
            System.out.println(room);
        }
        return null;
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
    public void deleteRoom(@RequestBody Room room) {
        for (Room existingRoom : roomList) {
            if (existingRoom.getRoomNr() == room.getRoomNr()) {
                roomList.remove(existingRoom);
            }
        }
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

}