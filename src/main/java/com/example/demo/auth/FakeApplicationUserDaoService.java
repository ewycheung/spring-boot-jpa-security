package com.example.demo.auth;

import java.util.List;
import java.util.Optional;

import com.example.demo.security.ApplicationUserRole;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {        
        return getApplicationUsers()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
            new ApplicationUser(
                "echeung",                
                passwordEncoder.encode("password"),
                ApplicationUserRole.STUDENT.getGrantedAuthority(),
                true,
                true,
                true,
                true),
            new ApplicationUser(
                "admin",                
                passwordEncoder.encode("password"),
                ApplicationUserRole.ADMIN.getGrantedAuthority(),
                true,
                true,
                true,
                true),
            new ApplicationUser(
                "admin_trainee",                
                passwordEncoder.encode("password"),
                ApplicationUserRole.ADMINTRAINEE.getGrantedAuthority(),
                true,
                true,
                true,
                true)
        );

        return applicationUsers;
    }
}
