package com.capgemini.controller;

import com.capgemini.model.Guest;
import com.capgemini.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GuestController {

    @Autowired
    private GuestRepository repository;

    public GuestController() {

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuestList")
    public List<Guest> getGuestList() {
        List<Guest> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getGuest/")
    public Guest getGuest(@RequestParam(value = "id", required = true) int id) {
        return repository.findOne(id);
    }

//    @RequestMapping(value="{id}/", method= RequestMethod.GET)
//    public Guest get(@PathVariable int id) {
//        return repository.findOne(id);
//    }



    @RequestMapping(method = RequestMethod.GET, value = "/getGuestByName")
    public Guest getGuest(@RequestParam(value = "firstName", required = true) String firstName, @RequestParam(value = "lastName", required = true) String lastName) {
        return repository.findOneByFirstNameAndLastName(firstName, lastName);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addGuest")
    public Guest addGuest(@RequestBody Guest guest) {
        return repository.save(guest);
    }

    @RequestMapping(value = "/changeGuest", method = RequestMethod.PUT)
    public void changeGuest(@RequestBody Guest guest) {
         repository.save(guest);
    }

    @RequestMapping(value = "/removeGuest/{id}", method = RequestMethod.DELETE)
    public void removeGuest(@PathVariable int id) {
        repository.delete(id);
    }

}

