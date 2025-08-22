package com.microservice.User.Config;

import com.microservice.User.Models.Entities.User;
import com.microservice.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig  implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User u1 = new User(null, "joao@example.com", "123456");
        User u2 = new User(null, "maria@example.com", "senhaSegura");
        User u3 = new User(null, "pedro@example.com", "pedro2025");
        User u4 = new User(null, "ana@example.com", "ana@123");
        User u5 = new User(null, "lucas@example.com", "lucas321");


        userRepository.saveAll(Arrays.asList(u1,u2,u3,u4,u5));
    }
}
