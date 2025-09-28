package com.nelioalves.workshop_springboot_mongodb.dto;

import com.nelioalves.workshop_springboot_mongodb.domain.User;

import java.io.Serial;
import java.io.Serializable;

public class AuthorDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public AuthorDto(){}

    public AuthorDto(User obj){
        id = obj.getId();
        name = obj.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
