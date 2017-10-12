package capgemini.controller;

import com.capgemini.controller.GuestController;
import com.capgemini.model.Guest;
import com.capgemini.repository.GuestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    private Guest guest;

    @InjectMocks
    private GuestController guestController;

    //    @Test
//    public void testDeleteGuest() {
//        guestController.removeGuest(1);
//        verify(guestRepository, times(1)).delete(1);
//    }
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
    }
    @Test
    public void testFindOne() {
        when(guestRepository.findOne(1)).thenReturn(guest);
        guestController.getGuest(1);
        verify(guestRepository, times(1)).findOne(1);
    }
}

//    @RequestMapping(method = RequestMethod.GET, value = "/getGuest/")
//    public Guest getGuest(@RequestParam(value = "id", required = true) int id) {
//        return guestRepository.findOne(id);
//    }