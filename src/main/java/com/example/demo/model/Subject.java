package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lecturerFullName;
    private Integer semester;

    public Subject(String name, String lecturerFullName, Integer semester) {
        this.name = name;
        this.lecturerFullName = lecturerFullName;
        this.semester = semester;
    }

    public Subject() {
    }

    public String getName() {
        return name;
    }

    public String getLecturerFullName() {
        return lecturerFullName;
    }

    public Integer getSemester() {
        return semester;
    }
}
