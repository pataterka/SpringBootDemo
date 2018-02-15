package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "students", path = "students")

public interface StudentRepository extends MongoRepository<Student, String> {
    public Student findById(long id);

    public Student findByFirstName(@Param("firstName") String firstName);

    public List<Student> findByLastName(@Param("lastName") String lastName);

    public List<Student> findByYear(@Param("year") int year);

}

