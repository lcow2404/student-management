package com.example.studentmanagement.models;

import jakarta.validation.constraints.*;

public class Student {
    private static int idCounter = 1;
    private Integer id;
    
    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Min(18)
    private Integer age;

    public Student(String name, String email, Integer age) {
        this.id = idCounter++;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Integer getAge() { return age; }
}
