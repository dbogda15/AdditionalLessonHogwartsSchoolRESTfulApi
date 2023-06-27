package me.dbogda.additionallessonhogwartsschoolrestfulapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.DTO.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/hogwarts/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(summary = "Create new student")
    public ResponseEntity<StudentDTO> createStudent(StudentDTO studentDTO) {
        if (!studentDTO.getName().isEmpty() || studentDTO.getAge() >= 6) {
            StudentDTO newStudent = studentService.create(studentDTO);
            return ResponseEntity.ok(newStudent);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    @Operation(summary = "There you can get info about all students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
       List <StudentDTO> students = studentService.getStudentsList();
       return ResponseEntity.ok(students);
    }

    @GetMapping("/by-id")
    @Operation(summary = "Find a student by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found!"
            )
    })
    public ResponseEntity<StudentDTO> getStudentById(@RequestParam Long id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    @Operation(summary = "Update a student")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
            StudentDTO updatedStudentDTO = studentService.updateStudent(studentDTO);
            if (updatedStudentDTO != null) {
            return ResponseEntity.ok(updatedStudentDTO);
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

    @GetMapping("/by-age")
    @Operation(summary = "List of students sorted by age")
    public ResponseEntity<List<StudentDTO>> getStudentsListWithFilter (@RequestParam int age) {
        List <StudentDTO> result = studentService.getStudentsWithFilter(age);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/between-ages")
    @Operation(summary = "List of students sorted by age restriction")
    public ResponseEntity<List<StudentDTO>> getStudentsByAgeBetween(@RequestParam int min, @RequestParam int max) {
        List <StudentDTO> result = studentService.findStudentByAgeBetween(min, max);
        return ResponseEntity.ok(result);
    }
}
