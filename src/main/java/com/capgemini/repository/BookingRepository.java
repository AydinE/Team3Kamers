package com.capgemini.repository;

import com.capgemini.model.Booking;
import com.capgemini.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

}
