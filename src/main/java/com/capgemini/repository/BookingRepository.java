package com.capgemini.repository;

import com.capgemini.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

    Iterable<Booking> findByRoomId(int id);
    Iterable<Booking> findByGuestId(int id);

}
