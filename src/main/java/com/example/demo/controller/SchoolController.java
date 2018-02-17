package com.example.demo.controller;

import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/rest/school")
class SchoolController {

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Student getStudentsInSchool(@PathVariable String id) {

        //Todo

        return null;
    }
}