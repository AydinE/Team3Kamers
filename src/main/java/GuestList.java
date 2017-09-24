import java.util.HashMap;
import java.util.List;

public class GuestList {

    private HashMap<String, Guest> guestList;

    public GuestList() {
        this.guestList = guestList;
    }



    public void searchGuest(int guestNumber){
        //toevoegen method zoeken in lijst.

    }

    public HashMap<String, Guest> getGuestList() {
        return guestList;
    }

    public void addGuest(String s, Guest guest){
        guestList.put(s ,guest);
    }


    public void alterGuestAddress(Guest guest, String address){  // guest moet nog toegevoegd worden. String is nu key.
        guest.getAddress();
        guest.setAddress(address);                                            // op deze regel roepen we de setter aan.
        guestList.put(guest.getFirstName() + ' '+ guest.getLastName(), guest);

    }



    @Override
    public String toString() {
        return "GuestList{" +
                "guestList=" + guestList +
                '}';
    }
}
