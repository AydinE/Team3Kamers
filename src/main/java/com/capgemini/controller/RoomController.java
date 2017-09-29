package com.capgemini.controller;

import com.capgemini.model.Room;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
public class RoomController {

    private ArrayList<Room> roomsList= new ArrayList<>();

    public RoomController(){

        roomsList.add(new Room(1, RoomType.BUDGET, RoomSize.ONE_PERSON, LocalDateTime.now(), true));
        roomsList.add(new Room(13, RoomType.BUDGET, RoomSize.ONE_PERSON, LocalDateTime.now(), true));
    }

    //http://localhost:8080/api/addRoom?roomNr=1&roomType=NORMAL&roomSize=ONE_PERSON
    @RequestMapping("/api/addRoom")
    public Room addRoom(@RequestParam(value="roomNr", required = true) int roomNr, @RequestParam(value="roomType", required = true) RoomType roomType, @RequestParam(value="roomSize", required = true) RoomSize roomSize, @RequestParam(value="availability", required = true) boolean availability)  {

        Room room = new Room(roomNr, roomType, roomSize, LocalDateTime.now(), true);

        roomsList.add(room);
        return room;
    }

    @RequestMapping("/api/getRoom")
    public Room getRoom(@RequestParam(value = "roomNr", required = true) int roomNr) {
        for (Room requiredRoom : roomsList){
            if (requiredRoom.getRoomNr() == roomNr){
                return requiredRoom;
            }
        }
        return null;
    }


    @RequestMapping(value = "/api/changeRoom", method = RequestMethod.POST)
    public Room changeRoom(@RequestBody Room room){
        for (Room changedRoom : roomsList){
            if (changedRoom.getRoomNr() == room.getRoomNr()) {
                changedRoom.setRoomNr(room.getRoomNr());
                changedRoom.setTypeOfRoom(room.getTypeOfRoom());
                changedRoom.setSizeOfRoom(room.getSizeOfRoom());
                changedRoom.setCreatedOn(LocalDateTime.now());
            }
            System.out.println(room);
            return room;
        }
        return null;
    }

    @RequestMapping(value = "/api/blockRoom", method = RequestMethod.POST)
    public Room blockRoom(@RequestBody Room room){
        for (Room blockedRoom : roomsList){
            if (blockedRoom.getRoomNr() == room.getRoomNr()) {
                blockedRoom.setAvailability(room.isAvailability());
            }
        }
        return room;
    }

    @RequestMapping(value = "/api/deleteRoom", method = RequestMethod.POST)
    public void deleteRoom(@RequestBody Room room) {
        for (Room excistingRoom : roomsList){
            if (excistingRoom.getRoomNr() == room.getRoomNr()) {
                roomsList.remove(excistingRoom);
            }
        }
    }

    public void setRoomsList(ArrayList<Room> roomsList) {
        this.roomsList = roomsList;
    }

}