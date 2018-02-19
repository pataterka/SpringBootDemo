package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class MongoDBStudentService implements StudentService {
    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MongoDBStudentService(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
//        this.bCryptPasswordEncoder = bCrypt;
    }

    @Override
    public Student create(Student student) {
        //uncomment if you don't allow student without any school assigned
//        if (student.getSchoolId()==null) {
//            List<School> schools = schoolRepository.findAll();
//            if (schools != null) {
//                String id = schools.get(0).getId();
//                student.setSchoolId(id);
//            }
//        }
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
            throw new Exception("Cannot deleteById a student " + id + " as he does not exist");
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
            updated.setSchoolId(student.getSchoolId());
            studentRepository.save(updated);
        } else throw new Exception("Cannot update a student " + id + " as he does not exist");
        return updated;
    }

    @Override
    public List<Student> updateYear() throws Exception {
        List<Student> updatedYear = studentRepository.findAll();
        if (updatedYear.isEmpty()) throw new Exception("No students to update");
        else {
            for (Student s : updatedYear) {
                int year = s.getYear();
                if (year < 3) s.setYear(year++);
            }

        }
        return updatedYear;
    }
}
