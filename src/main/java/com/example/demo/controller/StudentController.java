package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.MongoDBStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/students")
@Api(value = "onlineStudentManagement", description = "Operations to manage students in the School System")
class StudentController {

    @Autowired
    private MongoDBStudentService mongoDBStudentService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Get all the students", response = Student.class)
    public List<Student> getAllStudents() {
        return mongoDBStudentService.findAllStudents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get a student with the certain id", response = Student.class)
    public Student getStudent(@PathVariable String id) throws Exception {
        return mongoDBStudentService.findById(id);
    }

    @RequestMapping(value = "/byLastName/{lastName}", method = RequestMethod.GET)
    @ApiOperation(value = "Get students with the certain last names")
    public List<Student> getStudentsByLastName(@PathVariable String lastName) throws Exception {
        return mongoDBStudentService.findByLastName(lastName);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "Create a student", response = Student.class)
    public Student addStudent(
//            @RequestParam(value = "username") String username,
//                              @RequestParam(value = "password") String password,
                              @RequestParam(value = "firstName") String firstName,
                              @RequestParam(value = "lastName") String lastName,
                              @RequestParam(value = "year", required = false, defaultValue = "1") int year,
                              @RequestParam(value = "schoolId", required = false) String schoolId) {
        Student student = new Student();
//        student.setUsername(username);
//        student.setPassword(password);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setYear(year);
        student.setSchoolId(schoolId);
        return mongoDBStudentService.create(student);

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update a  certain student", response = Student.class)
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) throws Exception {

        student = mongoDBStudentService.update(id, student);
        return student;
    }

    @RequestMapping(value = "/updateyear", method = RequestMethod.PUT)
    @ApiOperation(value = "Update a year for all the students")
    private List<Student> updateYear() throws Exception {
        return mongoDBStudentService.updateYear();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a certain student", response = Student.class)
    public Student deleteStudent(@PathVariable String id) throws Exception {

        Student student = mongoDBStudentService.delete(id);
        return student;
    }
}
