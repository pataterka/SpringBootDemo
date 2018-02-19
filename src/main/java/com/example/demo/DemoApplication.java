package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
//    @Autowired
//    private StudentRepository repository;


	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        repository.deleteAll();
//
//        // save a couple of customers
//        repository.save(new Student("Alice", "Smith"));
//        repository.save(new Student("Bob", "Smith"));

    }
}
