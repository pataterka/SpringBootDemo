package com.example.demo.model;

import org.springframework.data.annotation.Id;

public class School {
    @Id
    private String id;
    private String name;
    private String schoolType;

    public School() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }
}
