package capgemini.controller;

import com.capgemini.controller.RoomController;
import com.capgemini.model.Room;
import com.capgemini.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class RoomControllerTest {

    @Mock
    private RoomRepository roomRepository;

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
        roomController.deleteRoom(1);
        verify(roomRepository, times(1)).delete(1);
    }
    @Test
    public void testaddRoom() {
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

        Room room = roomController.changeRoom(777, this.room);

        verify(roomRepository, times(1)).delete(777);
        verify(roomRepository, times(1)).save(this.room);
        assertEquals(this.room, room);
    }
}