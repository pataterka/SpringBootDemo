package com.example.demo.service;

import com.example.demo.model.School;
import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {

    Student create(Student student);

    Student findById(String id);

    List<School> findAll();

    Student delete(String id) throws Exception;

    Student update(String id, Student student) throws Exception;
}
