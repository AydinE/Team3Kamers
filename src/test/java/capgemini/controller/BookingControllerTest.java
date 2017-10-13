package capgemini.controller;

import com.capgemini.controller.BookingController;
import com.capgemini.model.Booking;
import com.capgemini.model.Guest;
import com.capgemini.model.Room;
import com.capgemini.repository.BookingRepository;
import com.capgemini.repository.GuestRepository;
import com.capgemini.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private Booking booking;

    @Mock
    private Guest guest;

    @Mock
    private Room room;

    @InjectMocks
    private BookingController bookingController;

    @Test
    public void testAddBooking() {
        when(booking.getStartDate()).thenReturn(LocalDateTime.of(2017, 10, 17, 16, 00));
        when(booking.getEndDate()).thenReturn(LocalDateTime.of(2017, 10, 19, 11, 00));
        when(booking.getGuest()).thenReturn(guest);
        when(booking.getRoom()).thenReturn(room);
        bookingController.addBooking(booking);
        verify(bookingRepository, times(1)).save(booking);
    }
    @Test
    public void testAddBookingInvalid() {
        when(booking.getStartDate()).thenReturn(LocalDateTime.of(2017, 10, 17, 16, 00));
        when(booking.getEndDate()).thenReturn(LocalDateTime.of(2017, 10, 16, 11, 00));
        when(booking.getGuest()).thenReturn(guest);
        when(booking.getRoom()).thenReturn(room);
        bookingController.addBooking(booking);
        verify(bookingRepository, times(0)).save(booking);
    }
    @Test
    public void testFindAllBookingsEmpty() {
        when(bookingRepository.findAll()).thenReturn(new ArrayList<>());
        List<Booking> list = bookingController.getAllBookings();
        assertEquals(0, list.spliterator().getExactSizeIfKnown());
    }
    @Test
    public void testFindAllBookingsTwo(){
        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        bookings.add(booking);
        when(bookingRepository.findAll()).thenReturn(bookings);
        Iterable<Booking> result = bookingController.getAllBookings();
        assertEquals(2, result.spliterator().getExactSizeIfKnown());
    }
    @Test
    public void testFindOneBooking(){
        when(bookingRepository.findOne(1)).thenReturn(booking);
        bookingController.getBooking(1);
        verify(bookingRepository, times(1)).findOne(1);
    }
    @Test
    public void testChangeBooking(){
        when(bookingRepository.save(booking)).thenReturn(this.booking);
        Booking booking = bookingController.changeBooking(777, this.booking);
        verify(bookingRepository, times(1)).delete(777);
        verify(bookingRepository, times(1)).save(this.booking);
        assertEquals(this.booking, booking);
    }
    @Test
    public void testDeleteBooking(){
        bookingController.deleteBooking(booking);
        verify(bookingRepository, times(1)).delete(booking);
    }
    @Test
    public void testCheckIn() {
        when(bookingRepository.findOne(23456)).thenReturn(booking);
        when(bookingRepository.save(booking)).thenReturn(this.booking);
        when(booking.getBookingNr()).thenReturn(23456);
        Booking booking = bookingController.checkIn(this.booking);
        verify(bookingRepository, times(1)).findOne(23456);
        verify(this.booking, times(1)).setCheckedIn(true);
        verify(bookingRepository, times(1)).save(this.booking);
        assertEquals(this.booking, booking);
    }
}
