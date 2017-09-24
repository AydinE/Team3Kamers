import java.util.HashMap;
import java.util.List;

public class GuestList {

    private HashMap<String, Guest> guestList = new HashMap<>();

    public GuestList() {
        this.guestList = guestList;
    }



    public void searchGuest(int guestNumber){
        //toevoegen method zoeken in lijst.

    }
    public HashMap<String, Guest> getGuestList() {
        return guestList;
    }

    
    // methode wijzigen adres. Elke andere methode voor wijzigen kan als deze opgebouwd worden.
    public void alterGuestAddress(Guest guest, String address){  // guest moet nog toegevoegd worden. String is nu key.
        guest.getAddress();
        guest.setAddress(address);                                            // op deze regel roepen we de setter aan.
        guestList.put(guest.getFirstName() +' '+ guest.getLastName(), guest);

    }

    //methode voor toevoegen nieuwe gast
    public Guest addGuest(int guestNumber,String firstName, String lastName, String address, String postalCode,
                         String city, String country, String phoneNumber, String email){

        Guest guest = new Guest(guestNumber,firstName,lastName,address,postalCode,city,country,phoneNumber, email);

        guestList.put(guest.getFirstName()+' '+ guest.getLastName(),guest);

        return guest;
    }




}
