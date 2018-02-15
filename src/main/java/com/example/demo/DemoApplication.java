package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class DemoApplication {
	public static HashMap<Long, Student> hmStudent;
	@Autowired
    static StudentRepository studentRepository;

	public static void main(String[] args) {


//		hmStudent = new HashMap<Long, Student>();
//
//		Student one = new Student("John", "math");
//		hmStudent.put(new Long(one.getId()), one);



//	Student two = new Student("Jane", "history");
//      hmStudent.put(new
//
//	Long(two.getId()),two);
        SpringApplication.run(DemoApplication.class, args);
    }
}
