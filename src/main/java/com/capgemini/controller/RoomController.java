package com.capgemini.controller;

import com.capgemini.model.Room;
import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
public class RoomController {

    private ArrayList<Integer> roomID= new ArrayList<Integer>();

    public RoomController(){

//        roomID.add(new Room(1));
//        roomID.add(new Room(2));
//        roomID.add(new Room(3));
//        roomID.add(new Room(5));
//        roomID.add(new Room(6));
//        roomID.add(new Room(7));
//        roomID.add(new Room(8));
//        roomID.add(new Room(9));
//        roomID.add(new Room(10));
//        roomID.add(new Room(12));
    }

//    public void addRoom(){
//
//        roomID.add(objt.Data(name, address, contact))
//        System.out.println(("Roomnumber "), + Room + ,("has been added to the list"));
//
//    }

    //http://localhost:8080/api/addRoom?roomNr=1&roomType=NORMAL&roomSize=ONE_PERSON
    @RequestMapping("/api/addRoom")
    public Room addRoom(@RequestParam(value="roomNr", required = true) int roomNr, @RequestParam(value="roomType", required = true) RoomType roomType, @RequestParam(value="roomSize", required = true) RoomSize roomSize) {

        Room room = new Room(roomNr, roomType, roomSize, LocalDateTime.now());
        System.out.println("Created new room: " + roomNr);
        return room;

    }

    public void change(){

    }

    public void block(){

    }

    public void delete(){

    }
}