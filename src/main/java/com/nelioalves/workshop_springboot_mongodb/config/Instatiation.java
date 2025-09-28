package com.nelioalves.workshop_springboot_mongodb.config;

import com.nelioalves.workshop_springboot_mongodb.domain.Post;
import com.nelioalves.workshop_springboot_mongodb.domain.User;
import com.nelioalves.workshop_springboot_mongodb.dto.AuthorDto;
import com.nelioalves.workshop_springboot_mongodb.repositories.PostRepository;
import com.nelioalves.workshop_springboot_mongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class Instatiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, LocalDate.parse("21/03/2018",fmt), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",new AuthorDto(maria));
        Post post2 = new Post(null, LocalDate.parse("23/03/2018",fmt), "Bom dia", "Acordei feliz hoje!",new AuthorDto(maria));


        postRepository.saveAll(Arrays.asList(post1,post2));
    }
}
