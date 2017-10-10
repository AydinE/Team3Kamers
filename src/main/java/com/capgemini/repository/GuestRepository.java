package com.capgemini.repository;

import com.capgemini.model.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Integer> {

    Guest findOneByFirstNameAndLastName(String firstName, String lastName);

}
