package com.nelioalves.workshop_springboot_mongodb.resources;

import com.nelioalves.workshop_springboot_mongodb.domain.User;
import com.nelioalves.workshop_springboot_mongodb.dto.UserDto;
import com.nelioalves.workshop_springboot_mongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findAll(){
        List<User> list = service.findAll();
        List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findById(@PathVariable String id){
        User obj = service.findById(id);

        return ResponseEntity.ok().body(new UserDto(obj));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDto> insert(@RequestBody UserDto objDto){
        User obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
