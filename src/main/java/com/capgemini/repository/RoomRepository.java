package com.capgemini.repository;
import java.util.List;
import com.capgemini.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer>{
    
}
