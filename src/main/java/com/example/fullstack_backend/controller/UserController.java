package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.exception.UserNotFoundException;
import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepo userRep;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRep.save(user);
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers(){
        return userRep.findAll();
    }


    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id){
        return userRep.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
        return  userRep.findById(id)
                .map(user ->{
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRep.save(user);
                })
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id){
        if(!userRep.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRep.deleteById(id);
        return "User "+id+" has been deleted";
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public User getMyProfile(org.springframework.security.core.Authentication authentication) {
        String email = authentication.getName();   // comes from JWT
        return userRep.findByEmail(email);
    }


}
