package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "students", path = "students")

public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findAll();

    Student findById(String id);

    List<Student> findBySchoolId(@Param("schoolId") String schoolId);

    List<Student> findBySchoolIdAndYear(@Param("schoolId") String schoolId, @Param("year") int year);

    List<Student> findByLastName(@Param("lastName") String lastName);

    boolean existsById(@Param("id") String id);

    boolean existsByFirstName(@Param("firstName") String firstName);

}

