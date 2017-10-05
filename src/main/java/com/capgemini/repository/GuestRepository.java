package com.capgemini.repository;

import com.capgemini.model.Guest;
import com.capgemini.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Integer> {

    
}
