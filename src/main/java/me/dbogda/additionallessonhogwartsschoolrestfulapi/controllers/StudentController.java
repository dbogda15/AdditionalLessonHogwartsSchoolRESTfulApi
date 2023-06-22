package me.dbogda.additionallessonhogwartsschoolrestfulapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Student;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.StudentService;

import java.util.HashMap;

@RestController
@RequestMapping("/hogwarts/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        if (student!=null) {
            String creationInfo = studentService.create(student);
            return ResponseEntity.ok(creationInfo);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<HashMap<Long, Student>> getAllStudents() {
       HashMap<Long, Student> studentHashMap = studentService.getStudentsMap();
       if (studentHashMap.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(studentHashMap);
    }

    @GetMapping("/student-by-id")
    public ResponseEntity<Student> getStudentById(@RequestParam Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Student> updateStudentById(@RequestBody Student student, @RequestParam Long id) {
        if (studentService.getStudentsMap().containsKey(id)) {
            Student student1 = studentService.updateStudentById(id, student);
            return ResponseEntity.ok(student1);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudentById(@RequestParam Long id) {
        String deletingInfo = studentService.deleteStudentById(id);
        if (deletingInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletingInfo);
    }

    @GetMapping("/filter")
    public ResponseEntity<HashMap<Long, Student>> getStudentsMApWithFilter (@RequestParam int age) {
        HashMap<Long, Student> result = studentService.getStudentsWithFilter(age);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
