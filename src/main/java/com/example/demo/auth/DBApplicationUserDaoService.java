package com.example.demo.auth;

import java.util.Optional;

import com.example.demo.security.UserRepository;
import com.example.demo.security.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("postgre")
public class DBApplicationUserDaoService implements ApplicationUserDao {    
    private final UserRepository repository;

    @Autowired
    public DBApplicationUserDaoService(UserRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        ApplicationUser applicationUser = null;  
        Optional<User> user = repository.findByUsername(username);

        if (user.isPresent()) {
            applicationUser = ApplicationUser.build(user.get());
        }

        return Optional.ofNullable(applicationUser);                
    }    
}
