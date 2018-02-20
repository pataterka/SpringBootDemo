package com.example.demo;

import com.example.demo.model.School;
import com.example.demo.model.Student;
import com.example.demo.repository.SchoolRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.MongoDBSchoolService;
import com.example.demo.service.MongoDBStudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.demo.model.SchoolType.HIGH_SCHOOL;
import static com.example.demo.model.SchoolType.UNIVERSITY;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    MongoDBStudentService mongoDBStudentService;
    @Autowired
    MongoDBSchoolService mongoDBSchoolService;

    Student dave, oliver, carter;
    School uep, upc, itu;

    @Before
    public void setUp() {

        studentRepository.deleteAll();
        schoolRepository.deleteAll();
        uep = new School("Uniwersytet Ekonomiczny w Poznaniu", UNIVERSITY);
        mongoDBSchoolService.create(uep);
        upc = new School("Universitat Politecnica de Catalunya", UNIVERSITY);
        mongoDBSchoolService.create(upc);
        itu = new School("IT University of Copenhagen", UNIVERSITY);
        mongoDBSchoolService.create(itu);

        dave = new Student("Dave", "Matthews");
        dave.setYear(2);
        mongoDBStudentService.create(dave);
        oliver = new Student("Oliver", "Matthews");
        mongoDBStudentService.create(oliver);
        carter = new Student("Carter", "Beauford");
        mongoDBStudentService.create(carter);

    }

    @Test
    public void setsIdOnSave() {

        assertNotNull(dave.id);
    }

    @Test
    public void savesNewStudent() {
        Student puppero = new Student("Fluffy", "Puppero");
        mongoDBStudentService.create(puppero);

        assertTrue(studentRepository.existsById(puppero.id));
    }

    @Test
    public void savesNewSchool() {
        School highschool = new School("Orestad Gymnasium", HIGH_SCHOOL);
        mongoDBSchoolService.create(highschool);

        assertTrue(schoolRepository.existsById(highschool.getId()));
    }

    @Test
    public void setsYearOnSave() {

        assertNotNull(carter.getYear());
    }

    @Test
    public void setsSchoolIdOnSave() {
        Student peter = new Student("Peter", "Pan");
        mongoDBStudentService.create(peter);
        assertNotNull(peter.getSchoolId());
//        assertTrue(peter.getSchoolId() == uep.getId());
    }

    @Test
    public void findsAllStudents() {
        List<Student> stu = mongoDBStudentService.findAllStudents();
        assertFalse(stu.isEmpty());
        assertTrue(stu.size() == 3);

    }

    @Test
    public void findsAllSchools() {
        List<School> schools = mongoDBSchoolService.findAllSchools();
        assertFalse(schools.isEmpty());
    }

    @Test
    public void findsStudentByLastName() throws Exception {

        List<Student> result = mongoDBStudentService.findByLastName("Beauford");

        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getFirstName().equals("Carter"));

        List<Student> result2 = mongoDBStudentService.findByLastName("Matthews");

        assertTrue(result2.size() == 2);
    }

    @Test
    public void findsAllStudentsInSchool() throws Exception {
        List<Student> result = mongoDBSchoolService.findAllStudentsInSchool(uep.getId());
        assertFalse(result.isEmpty());
        assertTrue(result.size() == 3);
    }

    @Test
    public void findsAllStudentsInSchoolYear() throws Exception {
        List<Student> result = mongoDBSchoolService.findAllStudentsInSchoolYear(uep.getId(), 2);
        assertTrue(result.size() == 1);
//        assertTrue(result.get(0).getFirstName()== "Dave");
    }

    @Test
    public void deletesStudent() throws Exception {

        mongoDBStudentService.delete(carter.getId());
        assertFalse(studentRepository.existsByFirstName("Carter"));
    }

    @Test
    public void deletesSchool() throws Exception {
        String id = uep.getId();
        mongoDBSchoolService.deleteById(id);
        assertNull(schoolRepository.findById(id));
        assertTrue(studentRepository.findBySchoolId(id).isEmpty());
    }

    @Test
    public void deletesAllSchools() throws Exception {
        mongoDBSchoolService.deleteAll();
        assertTrue(schoolRepository.findAll().isEmpty());
        assertTrue(studentRepository.findById(dave.id).getSchoolId() == null);
    }

    @Test
    public void updatesStudent() throws Exception {
        dave.setFirstName("David");
        dave.setYear(3);
        mongoDBStudentService.update(dave.id, dave);

        assertTrue(dave.getFirstName() == "David");
        assertTrue(dave.getYear() == 3);

    }

    @Test
    public void updatesSchool() throws Exception {
        uep.setName("Poznan University of Economics");
        mongoDBSchoolService.update(uep.getId(), uep);

        assertTrue(uep.getName() == "Poznan University of Economics");
    }

    @Test
    public void updatesYear() throws Exception {
        int before = dave.getYear();
        mongoDBStudentService.updateYear();
        assertTrue(before >= dave.getYear());
    }

}
