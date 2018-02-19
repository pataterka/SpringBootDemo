package com.example.demo.model;

import org.springframework.data.annotation.Id;

public class School {
    @Id
    private String id;

    private String name;
    private SchoolType schoolType;

    public School() {
    }

    public School(String name, SchoolType schoolType) {
        this.name = name;
        this.schoolType = schoolType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
    }

    public String getId() {
        return id;
    }

}
