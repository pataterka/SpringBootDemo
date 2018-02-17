package com.example.demo.service;

import com.example.demo.model.School;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDBStudentService implements StudentService {
    private final StudentRepository repository;

    @Autowired
    public MongoDBStudentService(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student create(Student student) {
        repository.save(student);
        return student;
    }

    @Override
    public Student findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<School> findAll() {
        return repository.findAll();
    }

    @Override
    public Student delete(String id) throws Exception {
        Student deleted = repository.findById(id);
        if (deleted != null) {
            repository.delete(deleted);

        } else {
            throw new Exception("Cannot delete a student " + id + " as he does not exist");
        }
        return deleted;
    }

    @Override
    public Student update(String id, Student student) throws Exception {
        Student updated = repository.findById(id);
        if (updated != null) {
            updated.setFirstName(student.getFirstName());
            updated.setLastName(student.getLastName());
            updated.setYear(student.getYear());
            repository.save(updated);
        } else throw new Exception("Cannot update a student " + id + " as he does not exist");
        return updated;
    }
}
