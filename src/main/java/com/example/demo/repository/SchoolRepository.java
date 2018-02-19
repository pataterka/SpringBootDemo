package com.example.demo.repository;

import com.example.demo.model.School;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "schools", path = "schools")

public interface SchoolRepository extends MongoRepository<School, String> {

    public List<School> findAll();

    public School findById(@Param("id") String id);

    public boolean existsById(@Param("id") String id);

    public School deleteById();

    public void deleteAll();

}
