package com.nelioalves.workshop_springboot_mongodb.config;

import com.nelioalves.workshop_springboot_mongodb.domain.Post;
import com.nelioalves.workshop_springboot_mongodb.domain.User;
import com.nelioalves.workshop_springboot_mongodb.dto.AuthorDto;
import com.nelioalves.workshop_springboot_mongodb.dto.CommentDto;
import com.nelioalves.workshop_springboot_mongodb.repositories.PostRepository;
import com.nelioalves.workshop_springboot_mongodb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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

        CommentDto c1 = new CommentDto("Boa viagem mano",LocalDate.parse("21/03/2018",fmt),new AuthorDto(alex));
        CommentDto c2 = new CommentDto("Aproveite!",LocalDate.parse("22/03/2018",fmt),new AuthorDto(bob));
        CommentDto c3 = new CommentDto("Tenha um ótimo dia!",LocalDate.parse("23/03/2018",fmt),new AuthorDto(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().add(c3);

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2));

        userRepository.save(maria);
    }
}
