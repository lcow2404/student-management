package com.example.studentmanagement.controller;

import com.example.studentmanagement.models.Student;
import com.example.studentmanagement.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final ObjectMapper objectMapper;

    public StudentController(StudentService studentService, ObjectMapper objectMapper) {
        this.studentService = studentService;
        this.objectMapper = objectMapper;
    }

    // Display list of students
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    // Show form to add a new student
    @GetMapping("/new")
    public String showStudentForm(Model model) {
        model.addAttribute("student", new Student("", "", 18));
        return "new-student";
    }

    // Save a new student
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "new-student";
        }
        studentService.addStudent(student);
        return "redirect:/students";
    }

    // Delete a student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        if (!studentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    // Export students as JSON
    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<String> exportStudentsAsJson() {
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseEntity.ok(objectMapper.writeValueAsString(students));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error generating JSON output");
        }
    }
}
