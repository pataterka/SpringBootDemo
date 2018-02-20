//package com.example.demo.service;
//
//import com.example.demo.model.Student;
//import com.example.demo.repository.StudentRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import static java.util.Collections.emptyList;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private StudentRepository studentRepository;
//
//    public UserDetailsServiceImpl(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Student student = studentRepository.findByUsername(username);
//        if (student == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new User(student.getUsername(), student.getPassword(), emptyList());
//    }
//}
