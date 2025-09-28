package com.nelioalves.workshop_springboot_mongodb.repositories;

import com.nelioalves.workshop_springboot_mongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {

}
