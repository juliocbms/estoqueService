package com.microservice.User.Config;


import com.microservice.User.models.entities.User;
import com.microservice.User.repository.UserRepository;
import com.microservice.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration

public class TestConfig  implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {




        userRepository.deleteAll();


        User user1 = new User(null, "Julio", "123456");
        User user2 = new User(null, "Maria", "senhaSegura");
        User user3 = new User(null, "Carlos", "abc123");
        User user4 = new User(null, "Ana", "teste@2025");


        userRepository.saveAll(Arrays.asList(user1,user2,user3,user4));
    }
}
