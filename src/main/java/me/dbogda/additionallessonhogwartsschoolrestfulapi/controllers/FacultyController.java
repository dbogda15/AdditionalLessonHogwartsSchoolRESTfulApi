package me.dbogda.additionallessonhogwartsschoolrestfulapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Faculty;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/hogwarts/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new faculty")
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        if (!faculty.getName().isEmpty()||!faculty.getColor().isEmpty()) {
            Faculty newFaculty = facultyService.create(faculty);
            return ResponseEntity.ok(newFaculty);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-all")
    @Operation(summary = "List of faculties")
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getFacultiesList();
        if (faculties.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("/faculty-by-id")
    @Operation(summary = "Find a faculty by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    description = "Faculty not found!"
            )
    })
    public ResponseEntity<Faculty> getFacultyById(@RequestParam Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Operation(summary = "Update information about the faculty")
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
            Faculty faculty1 = facultyService.updateFaculty(faculty);
            if (faculty1 != null) {
            return ResponseEntity.ok(faculty1);
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

    @GetMapping("/filter")
    @Operation(summary = "List of faculties sorted by color")
    public ResponseEntity<List<Faculty>> getFacultiesMapWithFilter(@RequestParam String color) {
        List <Faculty> result = facultyService.getFacultiesWithFilter(color);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
