package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

public class School {
    @Id
    @ApiModelProperty(notes = "Autogenerated school id", required = true)
    private String id;
    @ApiModelProperty(notes = "The name of the school", required = true)
    private String name;
    @ApiModelProperty(notes = "Type of school: HIGH_SCHOOL or UNIVERSITY", required = true)
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
