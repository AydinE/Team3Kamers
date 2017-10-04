package com.capgemini.model;

import java.time.LocalDateTime;

public class Booking {

    private int bookingNr;
    private Guest guest;
    private Room room;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean checkedIn;

    public Booking() {}

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
