package com.example.demo.service;

import com.example.demo.model.School;
import com.example.demo.model.Student;

import java.util.List;

public interface SchoolService {

    School create(School school);

    School findById(String id) throws Exception;

    List<School> findAllSchools();

    List<Student> findAllStudentsInSchool(String id) throws Exception;

    List<Student> findAllStudentsInSchoolYear(String id, int year) throws Exception;

    School deleteById(String id) throws Exception;

    List<School> deleteAll() throws Exception;

    School update(String id, School school) throws Exception;

}
