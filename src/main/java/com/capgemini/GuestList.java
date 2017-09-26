package com.capgemini;

import java.util.HashMap;

public class GuestList {

    private HashMap<String, Guest> guestList = new HashMap<>();

    public GuestList() {
        this.guestList = guestList;
    }


    //toevoegen method zoeken in lijst.
    public void searchGuest(int guestNumber){
            }

    public HashMap<String, Guest> getGuestList() {
        return guestList;
    }


    // methode wijzigen adres. Elke andere methode voor wijzigen kan als deze opgebouwd worden.
    public void alterGuestAddress(Guest guest, String address){  // guest moet nog toegevoegd worden. String is nu key.
        guest.getAddress();
        guest.setAddress(address);                                            // op deze regel roepen we de setter aan.
        guestList.put(guest.getFirstName() +' '+ guest.getLastName(), guest);
        System.out.println("Wijziging voltooid");
        System.out.println(guest);

    }

    //methode voor toevoegen nieuwe gast
    public Guest addGuest(int guestNumber, String firstName, String lastName, String address, String postalCode,
                          String city, String country, String phoneNumber, String email) {

        Guest guest = new Guest(guestNumber, firstName, lastName, address, postalCode, city, country, phoneNumber, email);

        guestList.put(guest.getFirstName() + ' ' + guest.getLastName(), guest);

        return guest;
    }
    //Methode versimpeld voor addguest
//
//    public void addGuest(Guest guest) {
//        guestList.put(guest.getFirstName() + ' ' + guest.getLastName(), guest);
//    }

    // methode voor remove guest
    public void removeGuest(String guest, Guest guest1){
        guestList.remove(guest);
        guest1.getFirstName();
        guest1.setFirstName(null);
        guest1.getLastName();
        guest1.setLastName(null);
        guest1.getPostalCode();
        guest1.setPostalCode(null);
        guest1.getPhoneNumber();
        guest1.setPhoneNumber(null);
        guest1.getGuestNumber();
        guest1.setGuestNumber(0);
        guest1.getEmail();
        guest1.setEmail(null);
        guest1.getCountry();
        guest1.setCountry(null);
        guest1.getCity();
        guest1.setCity(null);
        guest1.getAddress();
        guest1.setAddress(null);



    }





}
