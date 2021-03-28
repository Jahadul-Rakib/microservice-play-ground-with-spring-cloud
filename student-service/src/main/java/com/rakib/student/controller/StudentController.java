package com.rakib.student.controller;

import com.rakib.student.domain.Student;
import com.rakib.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
//@RefreshScope //used if read value from property file upto date
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> postStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.saveStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable(value = "id") Long id) throws IOException {
        return ResponseEntity.ok().body(studentService.getStudent(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllStudent() {
        return ResponseEntity.ok().body(studentService.getAllStudent());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student,
                                           @PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.ok().body(studentService.updateStudent(student, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(studentService.deleteStudent(id));
    }
}
