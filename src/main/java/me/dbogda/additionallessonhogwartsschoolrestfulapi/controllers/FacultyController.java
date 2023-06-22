package me.dbogda.additionallessonhogwartsschoolrestfulapi.controllers;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Faculty;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@RestController
@RequestMapping("/hogwarts/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody Faculty faculty) {
        if (faculty!=null) {
            String creationInfo = facultyService.create(faculty);
            return ResponseEntity.ok(creationInfo);
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<HashMap<Long, Faculty>> getAllFaculties() {
        HashMap<Long, Faculty> facultyHashMap = facultyService.getFacultiesMap();
        if (facultyHashMap.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyHashMap);
    }

    @GetMapping("/faculty-by-id")
    public ResponseEntity<Faculty> getFacultyById(@RequestParam Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty != null) {
            return ResponseEntity.ok(faculty);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFacultyById(@RequestBody Faculty faculty, @RequestParam Long id) {
        if (facultyService.getFacultiesMap().containsKey(id)) {
            Faculty faculty1 = facultyService.updateFacultyById(id, faculty);
            return ResponseEntity.ok(faculty1);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFacultyById(@RequestParam Long id) {
        String deletingInfo = facultyService.deleteFacultyById(id);
        if (deletingInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletingInfo);
    }

    @GetMapping("/filter")
    public ResponseEntity<HashMap<Long, Faculty>> getFacultiesMapWithFilter(@RequestParam String color) {
        HashMap<Long, Faculty> result = facultyService.getFacultiesWithFilter(color);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
