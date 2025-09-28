package com.nelioalves.workshop_springboot_mongodb.services;

import com.nelioalves.workshop_springboot_mongodb.domain.Post;
import com.nelioalves.workshop_springboot_mongodb.domain.User;
import com.nelioalves.workshop_springboot_mongodb.dto.UserDto;
import com.nelioalves.workshop_springboot_mongodb.repositories.PostRepository;
import com.nelioalves.workshop_springboot_mongodb.repositories.UserRepository;
import com.nelioalves.workshop_springboot_mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {


    @Autowired
    private PostRepository repo;

    public Post findById(String id){
        Optional<Post> user = repo.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    public List<Post> findByTitle(String text){
        return repo.findByTitleContainingIgnoreCase(text);
    }

}
