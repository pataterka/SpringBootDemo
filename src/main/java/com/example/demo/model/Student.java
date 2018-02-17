package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {
    @Id
    public String id;

    private String firstName;
    private String lastName;


    private int year;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName= lastName;
        this.year = getYear();
    }


    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
