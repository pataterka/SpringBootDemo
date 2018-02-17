package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Autowired
    private StudentRepository repository;

//    private static void init(){
//        repository.deleteAll();
//
//        dave = repository.save(new Student("Dave", "Matthews"));
//        oliver = repository.save(new Student("Oliver", "Matthews"));
//        carter = repository.save(new Student("Carter", "Beauford"));
//    }


	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Student("Alice", "Smith"));
        repository.save(new Student("Bob", "Smith"));

    }
}
