package com.capgemini.model;

import com.capgemini.model.enums.RoomSize;
import com.capgemini.model.enums.RoomType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nameOfRoom;
    private RoomType typeOfRoom;
    private RoomSize sizeOfRoom;
    private LocalDateTime createdOn;
    private boolean availability;

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                " nameOfRoom=" + nameOfRoom +
                ", typeOfRoom=" + typeOfRoom +
                ", sizeOfRoom=" + sizeOfRoom +
                ", createdOn=" + createdOn +
                ", availability=" + availability +
                '}';
    }

    public RoomType getTypeOfRoom() {
        return typeOfRoom;
    }

    public RoomSize getSizeOfRoom() {
        return sizeOfRoom;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public void setNameOfRoom(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
    }
}
