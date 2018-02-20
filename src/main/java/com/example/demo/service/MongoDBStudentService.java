package com.example.demo.service;

import com.example.demo.model.School;
import com.example.demo.model.Student;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class MongoDBStudentService implements StudentService {
    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //    @Autowired
    public MongoDBStudentService(StudentRepository studentRepository
            , SchoolRepository schoolRepository
//            ,BCryptPasswordEncoder bCrypt
    ) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
//        this.bCryptPasswordEncoder = bCrypt;
    }

    @Override
    public Student create(Student student) {
        //uncomment if you allow student without the school assigned
        if (student.getSchoolId() == null) {
            List<School> schools = schoolRepository.findAll();
            if (schools != null) {
                String id = schools.get(0).getId();
                student.setSchoolId(id);
            }
        }
//        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student findById(String id) throws Exception {
        Student student = studentRepository.findById(id);
        if (student == null) throw new Exception("No student with id: " + id);
        return student;
    }

    @Override
    public List<Student> findByLastName(String lastName) throws Exception {
        List<Student> students = studentRepository.findByLastName(lastName);
        if (students.isEmpty()) throw new Exception("No students with the last name: " + lastName);
        return students;
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
            throw new Exception("Cannot deleteById a student " + id + " as he does not exist");
        }
        return deleted;
    }

    @Override
    public Student update(String id, Student student) throws Exception {
        Student updated = studentRepository.findById(id);
        if (updated != null) {
            if (student.getFirstName() != null) updated.setFirstName(student.getFirstName());
            if (student.getLastName() != null) updated.setLastName(student.getLastName());
            //todo why can't I check if "year" is not null?
            updated.setYear(student.getYear());
            if (student.getSchoolId() != null) updated.setSchoolId(student.getSchoolId());
            studentRepository.save(updated);
        } else throw new Exception("Cannot update a student " + id + " as he does not exist");
        return updated;
    }

    @Override
    public List<Student> updateYear() throws Exception {
        List<Student> toUpdate = studentRepository.findAll();
        if (toUpdate.isEmpty()) throw new Exception("No students to update");
        else {
            for (Student s : toUpdate) {
                int year = s.getYear();
                if (year < 3) {
                    s.setYear(year + 1);
                }
                studentRepository.save(s);
            }

        }
        return toUpdate;
    }
}
