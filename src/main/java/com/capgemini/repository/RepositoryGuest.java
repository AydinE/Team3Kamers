package com.capgemini.repository;

import com.capgemini.model.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositoryGuest extends CrudRepository<Guest, Integer> {

    List<Guest> findByBrand(String brand);

}
