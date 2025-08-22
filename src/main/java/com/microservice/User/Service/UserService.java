package com.microservice.User.Service;

import com.microservice.User.Models.Entities.User;
import com.microservice.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException());
    }

    public User saveUser(User user){
        return  userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public User update(Long id, User user){
        User newUser = userRepository.getReferenceById(id);
        updateUser(newUser,user);
        return userRepository.save(newUser);
    }


    private void updateUser(User newUser, User user){
        newUser.setEmail(user.getEmail());
        newUser.setSenha(user.getSenha());
    }
}
