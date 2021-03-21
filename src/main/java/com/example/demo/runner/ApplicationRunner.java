package com.example.demo.runner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import com.example.demo.security.ApplicationUserRole;
import com.example.demo.security.Role;
import com.example.demo.security.RoleRepository;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public ApplicationRunner(StudentRepository studentRepository, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        Student mariam = new Student(              
            "Mariam",
            "mariam.jamal@gmail.com",
            LocalDate.of(2000, Month.JANUARY, 5)
        );

        Student alex = new Student(                
            "Alex",
            "alex@gmail.com",
            LocalDate.of(2004, Month.JANUARY, 5)
        );

        studentRepository.saveAll(Arrays.asList(mariam, alex));

        Role admin = new Role(       
            ApplicationUserRole.ADMIN
        );

        Role admin_trainee = new Role(       
            ApplicationUserRole.ADMINTRAINEE
        );

        Role student = new Role(       
            ApplicationUserRole.STUDENT
        );

        roleRepository.saveAll(Arrays.asList(admin, admin_trainee, student));
        
    }    
}
