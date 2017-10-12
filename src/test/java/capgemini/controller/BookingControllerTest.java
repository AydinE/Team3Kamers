package capgemini.controller;

import com.capgemini.controller.BookingController;
import com.capgemini.model.Booking;
import com.capgemini.repository.BookingRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private Booking booking;

    @InjectMocks
    private BookingController bookingController;


}
