package capgemini.controller;

import com.capgemini.controller.RoomController;
import com.capgemini.model.Booking;
import com.capgemini.model.Room;
import com.capgemini.repository.BookingRepository;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class RoomControllerTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private Room room;

    @InjectMocks
    private RoomController roomController;

    @Test
    public void testFindAllEmpty(){
        when(roomRepository.findAll()).thenReturn(new ArrayList<>());
        Iterable<Room> result = roomController.getAllRooms();
        assertEquals(0, result.spliterator().getExactSizeIfKnown());

    }

    @Test
    public void testFindAllTwo(){
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(room);
        when(roomRepository.findAll()).thenReturn(rooms);
        Iterable<Room> result = roomController.getAllRooms();
        assertEquals(2, result.spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testFindOne(){
        when(roomRepository.findOne(1)).thenReturn(room);
        roomController.getRoom(1);
        verify(roomRepository, times(1)).findOne(1);
    }
    @Test
    public void testDeleteRoom(){
        List<Booking> bookings= new ArrayList<>();
        Booking booking = new Booking();
        booking.setBookingNr(53);
        bookings.add(booking);
        when(bookingRepository.findByRoomId(78)).thenReturn(bookings);
        roomController.deleteRoom(78);
        verify(bookingRepository, times(1)).delete(53);
        verify(roomRepository, times(1)).delete(78);
    }
    @Test
    public void testAddRoom() {
        roomController.addRoom(room);
        verify(roomRepository, times(1)).save(room);
    }
    @Test
    public void testUnblockRoom() {
        // Given - precondities
        when(roomRepository.findOne(23456)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(this.room);

        // Test actie
        Room room = roomController.unblockRoom(23456);

        // Expectations
        verify(roomRepository, times(1)).findOne(23456);
        verify(this.room, times(1)).setAvailability(true);
        verify(roomRepository, times(1)).save(this.room);
        assertEquals(this.room, room);
    }
    @Test
    public void testBlockRoom(){
        when(roomRepository.findOne(34567)).thenReturn(room);
        when(roomRepository.save(room)).thenReturn(this.room);
        Room room = roomController.blockRoom(34567);
        verify(roomRepository, times(1)).findOne(34567);
        verify(this.room, times(1)).setAvailability(false);
        verify(roomRepository, times(1)).save(this.room);
        assertEquals(this.room, room);
    }
    @Test
    public void testChangeRoom(){
        when(roomRepository.save(room)).thenReturn(this.room);
        when(roomRepository.findOne(anyInt())).thenReturn(room);
        when(room.getCreatedOn()).thenReturn(LocalDateTime.now());
        doNothing().when(room).setCreatedOn(anyObject());
        Room room = roomController.changeRoom(this.room);
        verify(roomRepository, times(1)).save(this.room);
        assertEquals(this.room, room);
    }
}