package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDBStudentService implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public MongoDBStudentService(StudentRepository repository) {
        this.studentRepository = repository;
    }

    @Override
    public Student create(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student findById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student delete(String id) throws Exception {
        Student deleted = studentRepository.findById(id);
        if (deleted != null) {
            studentRepository.delete(deleted);

        } else {
            throw new Exception("Cannot delete a student " + id + " as he does not exist");
        }
        return deleted;
    }

    @Override
    public Student update(String id, Student student) throws Exception {
        Student updated = studentRepository.findById(id);
        if (updated != null) {
            updated.setFirstName(student.getFirstName());
            updated.setLastName(student.getLastName());
            updated.setYear(student.getYear());
            studentRepository.save(updated);
        } else throw new Exception("Cannot update a student " + id + " as he does not exist");
        return updated;
    }
}
