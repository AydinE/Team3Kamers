package com.capgemini.controller;

import com.capgemini.model.Room;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static java.time.LocalTime.now;

@RestController
public class RoomController {

    private ArrayList<Room> roomsList= new ArrayList<>();

    public RoomController(){

        roomsList.add(new Room(1, RoomType.BUDGET, RoomSize.ONE_PERSON, LocalDateTime.now()));
    }

    //http://localhost:8080/api/addRoom?roomNr=1&roomType=NORMAL&roomSize=ONE_PERSON
    @RequestMapping("/api/addRoom")
    public Room addRoom(@RequestParam(value="roomNr", required = true) int roomNr, @RequestParam(value="roomType", required = true) RoomType roomType, @RequestParam(value="roomSize", required = true) RoomSize roomSize) {

        Room room = new Room(roomNr, roomType, roomSize, LocalDateTime.now());

        roomsList.add(room);
        return room;
    }

    public Room changeRoom(int currentRoomNumber, int newRoomNumber, RoomType newRoomType, RoomSize newRoomSize, LocalDateTime newTime){
        for (Room room : roomsList) {
            if (currentRoomNumber == room.getRoomNr()){
                room.setRoomNr(newRoomNumber);
                room.setTypeOfRoom(newRoomType);
                room.setSizeOfRoom(newRoomSize);
                room.setCreatedOn(newTime);
            }
            System.out.println(room);
            return room;
        }
        return null;
    }

    public void blockRoom(){
    }

    public void deleteRoom(){
    }

    public ArrayList<Room> getRoomsList() {
        return roomsList;
    }

    public void setRoomsList(ArrayList<Room> roomsList) {
        this.roomsList = roomsList;
    }

}