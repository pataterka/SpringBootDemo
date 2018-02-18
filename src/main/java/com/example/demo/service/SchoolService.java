package com.example.demo.service;

import com.example.demo.model.School;
import com.example.demo.model.Student;

import java.util.List;

public interface SchoolService {

    School create(School school);

    School findById(String id);

    List<School> findAllSchools();

    List<Student> findAllStudentsInSchool();

    School delete(String id) throws Exception;

    School update(String id, School student) throws Exception;

}
