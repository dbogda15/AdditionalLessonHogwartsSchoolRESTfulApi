package me.dbogda.additionallessonhogwartsschoolrestfulapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Student;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.StudentService;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/hogwarts/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create new student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (!student.getName().isEmpty() || student.getAge() >= 6) {
            Student newStudent = studentService.create(student);
            return ResponseEntity.ok(newStudent);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-all")
    @Operation(summary = "There you can get info about all students")
    public ResponseEntity<List<Student>> getAllStudents() {
       List <Student> students = studentService.getStudentsList();
       if (students.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(students);
    }

    @GetMapping("/student-by-id")
    @Operation(summary = "Find a student by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found!"
            )
    })
    public ResponseEntity<Student> getStudentById(@RequestParam Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Operation(summary = "Update a student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
            Student student1 = studentService.updateStudent(student);
            if (student1 != null) {
            return ResponseEntity.ok(student1);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete a student by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found!"
            )
    })
    public ResponseEntity<String> deleteStudentById(@RequestParam Long id) {
        String deletingInfo = studentService.deleteStudentById(id);
        return ResponseEntity.ok(deletingInfo);
    }

    @GetMapping("/filter")
    @Operation(summary = "List of students sorted by age")
    public ResponseEntity<List<Student>> getStudentsMApWithFilter (@RequestParam int age) {
        List <Student> result = studentService.getStudentsWithFilter(age);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
