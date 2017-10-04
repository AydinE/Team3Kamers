package com.capgemini.model;

import com.sun.javafx.beans.IDProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private int bookingNr;
    private Guest guest;
    private Room room;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean checkedIn;

    public Booking() {}

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingNr=" + bookingNr +
                ", guest=" + guest +
                ", room=" + room +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", checkedIn=" + checkedIn +
                '}';
    }


    public int getBookingNr() {
        return bookingNr;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setBookingNr(int bookingNr) { this.bookingNr = bookingNr; }

    public void setGuest(Guest guest) { this.guest = guest; }

    public void setRoom(Room room) { this.room = room; }

    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public boolean isCheckedIn() { return checkedIn; }

    public void setCheckedIn(boolean checkedIn) { this.checkedIn = checkedIn; }
}
