package com.capgemini.model;

import java.time.LocalDateTime;

public class Booking {

    private int bookingNr;
    private Guest guest;
    private Room room;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Booking(int bookingNr, Guest guest, Room room, LocalDateTime startDate, LocalDateTime endDate) {
        this.bookingNr = bookingNr;
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void CreateBooking() {

    }

    public void cancelBooking() {

    }

    public void guestRegisteryCheck() {

    }

    public void guestCheckin() {

    }

    public void guestCheckout() {

    }

    public void paymentCheck() {

    }

    public void RoomNrCoupling() {

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
}