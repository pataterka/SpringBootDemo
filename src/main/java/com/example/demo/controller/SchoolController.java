package com.example.demo.controller;

import com.example.demo.model.School;
import com.example.demo.model.SchoolType;
import com.example.demo.model.Student;
import com.example.demo.service.MongoDBSchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/rest/school")
@Api(value = "onlineSchoolManagement", description = "Operations to manage the School System")
class SchoolController {
    @Autowired
    private MongoDBSchoolService mongoDBSchoolService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Get all the schools")
    private List<School> getAllSchools() {
        return mongoDBSchoolService.findAllSchools();
    }

    @RequestMapping(value = "/{id}/students", method = RequestMethod.GET)
    @ApiOperation(value = "Get all the students in a school")
    private List<Student> getStudentsInSchool(@PathVariable String id) throws Exception {

        return mongoDBSchoolService.findAllStudentsInSchool(id);

    }

    @RequestMapping(value = "/{id}/{year}/students", method = RequestMethod.GET)
    @ApiOperation(value = "Get all the students in a school, on a certain year")
    private List<Student> getStudentsInSchoolYear(@PathVariable String id, int year) throws Exception {
        return mongoDBSchoolService.findAllStudentsInSchoolYear(id, year);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "Create a school", response = School.class)
    private School addSchool(@RequestParam(value = "name") String name,
                             @RequestParam(value = "schoolType", defaultValue = "UNIVERSITY") SchoolType schoolType) {
        School school = new School(name, schoolType);
        return mongoDBSchoolService.create(school);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a school", response = School.class)
    private School deleteSchool(@PathVariable String id) throws Exception {
        return mongoDBSchoolService.deleteById(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete all the schools")
    private List<School> deleteAllSchools() throws Exception {
        return mongoDBSchoolService.deleteAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update a school", response = School.class)
    private School updateSchool(@PathVariable String id, @RequestBody School school) throws Exception {
        school = mongoDBSchoolService.update(id, school);
        return school;
    }
}