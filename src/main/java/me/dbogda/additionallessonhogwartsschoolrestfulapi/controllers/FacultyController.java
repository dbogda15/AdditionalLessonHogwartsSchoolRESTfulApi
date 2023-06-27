package me.dbogda.additionallessonhogwartsschoolrestfulapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.DTO.FacultyDTO;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.DTO.StudentDTO;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.FacultyService;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hogwarts/faculties")
public class FacultyController {
    private final FacultyService facultyService;
    private final StudentService studentService;

    public FacultyController(FacultyService facultyService, StudentService studentService) {
        this.facultyService = facultyService;
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(summary = "Create a new faculty")
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO facultyDTO) {
            FacultyDTO newFacultyDTO = facultyService.create(facultyDTO);
            return ResponseEntity.ok(newFacultyDTO);
    }

    @GetMapping("/all")
    @Operation(summary = "List of faculties")
    public ResponseEntity<List<FacultyDTO>> getAllFaculties() {
        List<FacultyDTO> faculties = facultyService.getFacultiesList();
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("/find-by-id")
    @Operation(summary = "Find a faculty by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty not found!"
            )
    })
    public ResponseEntity<FacultyDTO> getFacultyById(@RequestParam Long id) {
        FacultyDTO faculty = facultyService.getFacultyById(id);
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    @Operation(summary = "Update information about the faculty")
    public ResponseEntity<FacultyDTO> updateFaculty(@RequestBody FacultyDTO facultyDTO) {
            FacultyDTO updatedFacultyDTO = facultyService.updateFaculty(facultyDTO);
            if (updatedFacultyDTO != null) {
            return ResponseEntity.ok(updatedFacultyDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete faculty by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty not found!"
            )
    })
    public ResponseEntity<String> deleteFacultyById(@RequestParam Long id) {
        String deletingInfo = facultyService.deleteFacultyById(id);
        return ResponseEntity.ok(deletingInfo);
    }

    @GetMapping("/color-filter")
    @Operation(summary = "The list of faculties sorted by color")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty not found!"
            )
    })
    public ResponseEntity<List<FacultyDTO>> getFacultiesMapWithFilter(@RequestParam String color) {
        List <FacultyDTO> result = facultyService.getFacultiesWithFilter(color);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/name-filter")
    @Operation(summary = "The list of faculties sorted by name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty not found!"
            )
    })
    public ResponseEntity<List<FacultyDTO>> getFacultiesSortedByName(@RequestParam String name) {
        List<FacultyDTO> result = facultyService.findFacultyByName(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/students-from-faculty")
    @Operation(summary = "Here you can get a list of students from a certain faculty")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty not found!"
            )
    })
    public ResponseEntity<List<StudentDTO>> getStudentsFromCurrentFaculty (@RequestParam Long id) {
        List<StudentDTO> students = studentService.findStudentsByFacultyId(id);
        return ResponseEntity.ok(students);
    }
}
