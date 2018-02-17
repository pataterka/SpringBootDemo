package com.example.demo.controller;

import com.example.demo.model.School;
import com.example.demo.model.Student;
import com.example.demo.service.MongoDBStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value="/rest/school")
class SchoolController {
    @Autowired
    private MongoDBStudentService mongoDBSchoolService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<School> getAllSchools() {
        return mongoDBSchoolService.findAll();
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Student getStudentsInSchool(@PathVariable String id) {

        //Todo

        return null;
    }
}