package capgemini.controller;

import com.capgemini.controller.GuestController;
import com.capgemini.model.Guest;
import com.capgemini.repository.GuestRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class GuestControllerTest {

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private Guest guest;

    @InjectMocks
    private GuestController guestController;


}
