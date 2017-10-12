package capgemini.controller;

import com.capgemini.controller.GuestController;
import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.repository.BookingRepository;
import com.capgemini.repository.GuestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class GuestControllerTest {

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private Guest guest;

    @InjectMocks
    private GuestController guestController;


    @Test
    public void testGetGuestListEmpty() {
        when(guestRepository.findAll()).thenReturn(new ArrayList<>());
        List<Guest> list = guestController.getGuestList();
        assertEquals(0, list.spliterator().getExactSizeIfKnown());
    }
    @Test
    public void testGetGuestListTwoGuests() {
        ArrayList<Guest> guests = new ArrayList<>();
        guests.add(guest);
        guests.add(guest);
        when(guestRepository.findAll()).thenReturn(guests);
        List<Guest> list = guestController.getGuestList();
        assertEquals(2, list.spliterator().getExactSizeIfKnown());
        verify(guestRepository, times(1)).findAll();
    }
    @Test
    public void testFindOne() {
        when(guestRepository.findOne(1)).thenReturn(guest);
        guestController.getGuest(1);
        verify(guestRepository, times(1)).findOne(1);
    }
    @Test
    public void testaddGuest() {
        when(guest.getEmail()).thenReturn("1a@2b.nl");
        when(guest.getPhoneNumber()).thenReturn("1234567890");
        when(guest.getFirstName()).thenReturn("chaouki");
        when(guest.getLastName()).thenReturn("Machinelearning");
        when(guest.getCountry()).thenReturn("Nederland");
        when(guest.getCity()).thenReturn("Voldemortdatabase");
        when(guest.getPostalCode()).thenReturn("2347GO");
        when(guest.getAddress()).thenReturn("Hyper Engine 6");
        guestController.addGuest(guest);
        verify(guestRepository, times(1)).save(guest);
    }
    @Test
    public void testaddInvalidGuest() {
        when(guest.getEmail()).thenReturn("1a2b.nl");
        when(guest.getPhoneNumber()).thenReturn("1234567890");
        when(guest.getFirstName()).thenReturn("chaouki");
        when(guest.getLastName()).thenReturn("Machinelearning");
        when(guest.getCountry()).thenReturn("Nederland");
        when(guest.getCity()).thenReturn("Voldemortdatabase");
        when(guest.getPostalCode()).thenReturn("2347GO");
        when(guest.getAddress()).thenReturn("Hyper Engine 6");
        guestController.addGuest(guest);
        verify(guestRepository, times(0)).save(guest);
    }
    @Test
    public void testChangeGuest() {
        when(guest.getEmail()).thenReturn("1a@2b.nl");
        when(guest.getPhoneNumber()).thenReturn("1234567890");
        when(guest.getFirstName()).thenReturn("chaouki");
        when(guest.getLastName()).thenReturn("Machinelearning");
        when(guest.getCountry()).thenReturn("Nederland");
        when(guest.getCity()).thenReturn("Voldemortdatabase");
        when(guest.getPostalCode()).thenReturn("2347GO");
        when(guest.getAddress()).thenReturn("Hyper Engine 6");
        when(guestRepository.save(guest)).thenReturn(this.guest);
        Guest guest = guestController.changeGuest(this.guest);
        verify(guestRepository, times(1)).save(this.guest);
        assertEquals(this.guest, guest);
    }
    @Test
        public void testDeleteGuest() {
        List<Booking> bookings= new ArrayList<>();
        Booking booking = new Booking();
        booking.setBookingNr(53);
        bookings.add(booking);
        when(bookingRepository.findByGuestId(78)).thenReturn(bookings);
        guestController.removeGuest(78);
        verify(bookingRepository, times(1)).delete(53);
        verify(guestRepository, times(1)).delete(78);
    }
}
