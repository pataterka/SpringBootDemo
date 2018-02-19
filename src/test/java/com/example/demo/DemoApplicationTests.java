package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.MongoDBStudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    StudentRepository repository;

    @Autowired
    MongoDBStudentService mongoDBStudentService;

    Student dave, oliver, carter;

    @Before
    public void setUp() {

        repository.deleteAll();

        dave = repository.save(new Student("Dave", "Matthews"));
        oliver = repository.save(new Student("Oliver", "Matthews"));
        carter = repository.save(new Student("Carter", "Beauford"));
    }

    @Test
    public void savesNewStudent() {
        Student puppero = new Student("Fluffy", "Puppero");
        mongoDBStudentService.create(puppero);

        assertTrue(repository.existsById(puppero.id));
    }

    @Test
    public void setsIdOnSave() {

        Student peter = repository.save(new Student("Peter", "Pan"));

        assertNotNull(peter.id);
        //assertTrue(repository.findById(peter.id).equals(peter));
    }

    @Test
    public void setsYearOnSave() {

        Student pati = repository.save(new Student("Pati", "Taterka"));

        assertNotNull(pati.getYear());
    }

//    @Test
//    private void setsSchoolIdOnSave{
//
//    }

    @Test
    public void findsByFirstName() {

        Student result = repository.findByFirstName("Oliver");

        assertTrue(result.getLastName().equals("Matthews"));
    }

    @Test
    public void findsByLastName() {

        List<Student> result = repository.findByLastName("Beauford");

        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getFirstName().equals("Carter"));
//        extracting("firstName").contains("Carter");

        List<Student> result2 = repository.findByLastName("Matthews");

        assertTrue(result2.size() == 2);
//        assertTrue(result.get(0).getFirstName().equals(""));
    }

    @Test
    public void deletesStudent() {
        repository.delete(carter);
        assertFalse(repository.existsByFirstName("Carter"));
    }

//    @Test
//    public void findsByExample() {
//
//        Customer probe = new Customer(null, "Matthews");
//
//        List<Customer> result = repository.findAll(Example.of(probe));
//
//        assertThat(result).hasSize(2).extracting("firstName").contains("Dave", "Oliver August");
//    }

}
