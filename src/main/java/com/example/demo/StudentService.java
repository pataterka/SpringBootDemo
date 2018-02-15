package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/rest/student")
class StudentService{

    @RequestMapping(value="/",method = RequestMethod.GET)
    public List<Student> getAllStudents(){
        return DemoApplication.studentRepository.findAll();
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Student addStudent(@RequestParam(value="firstName") String firstName
            ,@RequestParam(value="lastName") String lastName
            ,@RequestParam(value = "year", defaultValue = "1") int year){

        Student student=new Student(firstName,lastName);
        DemoApplication.studentRepository.save(student);
//        DemoApplication.hmStudent.put(new Long(student.getId()),student);
        return student;

    }

    @RequestMapping(value="/update",method = RequestMethod.PUT)
    public Student updateStudent(@RequestBody Student student) throws Exception {
        if(DemoApplication.
                hmStudent.containsKey(new Long(student.getId()))){
            DemoApplication.hmStudent.put(new Long(student.getId()),student);
        }else{
            throw new Exception("Student "+student.getId()+" does not exists");
        }
//        if(DemoApplication.hmStudent.containsKey(new Long(student.getId()))){
//            DemoApplication.hmStudent.put(new Long(student.getId()),student);
//        }else{
//            throw new Exception("Student "+student.getId()+" does not exists");
//        }

        return student;
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public Student deleteStudent(@PathVariable long id) throws Exception {

        Student student;

        if(DemoApplication.hmStudent.containsKey(new Long(id))){
            student=DemoApplication.hmStudent.get(new Long(id));
            DemoApplication.hmStudent.remove(new Long(id));
        }else{
            throw new Exception("Student "+id+" does not exists");
        }
        return student;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Student getStudent(@PathVariable long id){
        return DemoApplication.hmStudent.get(new Long(id));
    }
}
