package com.capgemini.model;

import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;

import java.time.LocalDateTime;

public class Room {

    private int roomNr;
    private RoomType typeOfRoom;
    private RoomSize sizeOfRoom;
    private LocalDateTime createdOn;

    public Room(int identifier, RoomType typeOfRoom, RoomSize sizeOfRoom, LocalDateTime createdOn) {
        roomNr = identifier;
        this.typeOfRoom = typeOfRoom;
        this.sizeOfRoom = sizeOfRoom;
        this.createdOn = createdOn;

    }

    public int getRoomNr() {
        return roomNr;
    }

    public RoomType getTypeOfRoom() {
        return typeOfRoom;
    }

    public RoomSize getSizeOfRoom() {
        return sizeOfRoom;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setRoomNr(int roomNr) {
        this.roomNr = roomNr;
    }

    public void setTypeOfRoom(RoomType typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public void setSizeOfRoom(RoomSize sizeOfRoom) {
        this.sizeOfRoom = sizeOfRoom;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
