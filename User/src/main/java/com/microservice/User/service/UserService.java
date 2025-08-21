package com.microservice.User.service;


import com.microservice.User.models.entities.User;
import com.microservice.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new RuntimeException());
    }

    public List<User> findAll (){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        User user = userRepository.getReferenceById(id);
        userRepository.delete(user);
    }

    public User atualizarUser(Long id, User user){
        User newUser = userRepository.getReferenceById(id);
        updateData(newUser, user);
        return userRepository.save(user);
    }


    private void updateData(User newUser, User user){
        newUser.setNome(user.getNome());
        newUser.setSenha(user.getSenha());
    }
}
