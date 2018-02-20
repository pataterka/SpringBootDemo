package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {

    Student create(Student student);

    List<Student> findAllStudents();

    Student findById(String id) throws Exception;

    List<Student> findByLastName(String lastName) throws Exception;

    Student delete(String id) throws Exception;

    Student update(String id, Student student) throws Exception;

    List<Student> updateYear() throws Exception;
}
