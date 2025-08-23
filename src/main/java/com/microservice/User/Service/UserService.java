package com.microservice.User.Service;

import com.microservice.User.Models.DTO.RegisterDTO;
import com.microservice.User.Models.Entities.User;
import com.microservice.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        String encryptedPassowrd = new BCryptPasswordEncoder().encode(user.getSenha());
        User newUser = new User(null, user.getEmail(), encryptedPassowrd, user.getRole());
       return userRepository.save(newUser);
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

    public User fromDTO(RegisterDTO dto){
        return new User(dto.id(),dto.email(), dto.senha(), dto.role());
    }
}
