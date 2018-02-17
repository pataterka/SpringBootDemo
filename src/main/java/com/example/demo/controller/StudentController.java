package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.MongoDBStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/student")
class StudentController {

    @Autowired
    private MongoDBStudentService mongoDBStudentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Student> getAllStudents() {
        return mongoDBStudentService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Student addStudent(@RequestParam(value = "firstName") String firstName
            , @RequestParam(value = "lastName") String lastName
            , @RequestParam(value = "year", required = false, defaultValue = "1") int year) {
        Student student = new Student(firstName, lastName);
        return mongoDBStudentService.create(student);

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) throws Exception {

        student = mongoDBStudentService.update(id, student);
        return student;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Student deleteStudent(@PathVariable String id) throws Exception {

        Student student = mongoDBStudentService.delete(id);
        return student;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable String id) {
        return mongoDBStudentService.findById(id);
    }
}
