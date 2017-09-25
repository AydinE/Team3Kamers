package model;

import model.enums.RoomSize;
import model.enums.RoomType;

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
}
