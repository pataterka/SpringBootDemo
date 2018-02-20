package com.example.demo.service;

import com.example.demo.model.School;
import com.example.demo.model.Student;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDBSchoolService implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public MongoDBSchoolService(SchoolRepository schoolRepository, StudentRepository studentRepository) {
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public School create(School school) {
        schoolRepository.save(school);
        return school;
    }

    @Override
    public School findById(String id) throws Exception {
        School school = schoolRepository.findById(id);
        if (school == null) throw new Exception("No school with id: " + id);

        return school;
    }

    @Override
    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public List<Student> findAllStudentsInSchool(String id) throws Exception {
        School school = schoolRepository.findById(id);
        String name = school.getName();
        List<Student> studentList = studentRepository.findBySchoolId(id);
        if (studentList.isEmpty()) {
            throw new Exception("No students in the school: " + name);
        }
        return studentList;
    }

    @Override
    public List<Student> findAllStudentsInSchoolYear(String id, int year) throws Exception {
        School school = schoolRepository.findById(id);
        String name = school.getName();
        List<Student> studentList = studentRepository.findBySchoolIdAndYear(id, year);
        if (studentList.isEmpty()) {
            throw new Exception("No students in the school: " + name + "and year: " + year);
        }
        return studentList;
    }

    @Override
    public School deleteById(String id) throws Exception {
        School deleted = schoolRepository.findById(id);
        if (deleted != null) {
            schoolRepository.delete(deleted);
            List<Student> students = studentRepository.findBySchoolId(id);
            for (Student s : students) {
                s.setSchoolId(null);
                studentRepository.save(s);
            }


        } else {
            throw new Exception("Cannot deleteById a school " + id + " as it does not exist");
        }
        return deleted;
    }

    @Override
    public List<School> deleteAll() throws Exception {
        List<School> deletedSchools = schoolRepository.findAll();
        if (deletedSchools.isEmpty()) throw new Exception("There are no schools to delete");
        else schoolRepository.deleteAll();

        return deletedSchools;
    }

    @Override
    public School update(String id, School school) throws Exception {
        School updated = schoolRepository.findById(id);
        if (updated != null) {
            if (school.getName() != null) updated.setName(school.getName());
            if (school.getSchoolType() != null) updated.setSchoolType(school.getSchoolType());
            schoolRepository.save(updated);
        } else throw new Exception("Cannot update a school " + id + " as it does not exist");
        return updated;
    }
}
