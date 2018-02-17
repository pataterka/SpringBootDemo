package com.example.demo.service;

import com.example.demo.model.School;
import com.example.demo.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDBSchoolService implements SchoolService {
    private final SchoolRepository schoolRepository;

    @Autowired
    public MongoDBSchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public School create(School school) {
        schoolRepository.save(school);
        return school;
    }

    @Override
    public School findById(String id) {
        return schoolRepository.findById(id);
    }

    @Override
    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    @Override
    public School delete(String id) throws Exception {
        School deleted = schoolRepository.findById(id);
        if (deleted != null) {
            schoolRepository.delete(deleted);

        } else {
            throw new Exception("Cannot delete a school " + id + " as it does not exist");
        }
        return deleted;
    }

    @Override
    public School update(String id, School school) throws Exception {
        School updated = schoolRepository.findById(id);
        if (updated != null) {
            updated.setName(school.getName());
            updated.setSchoolType(school.getSchoolType());
            schoolRepository.save(updated);
        } else throw new Exception("Cannot update a school " + id + " as it does not exist");
        return updated;
    }
}
