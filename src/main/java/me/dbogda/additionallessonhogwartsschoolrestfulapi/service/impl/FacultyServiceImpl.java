package me.dbogda.additionallessonhogwartsschoolrestfulapi.service.impl;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Faculty;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.repository.FacultyRepository;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.FacultyService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty create(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
    @Override
    public Faculty getFacultyById(Long id) {
       return facultyRepository.findById(id).get();
    }

    @Override
    public List<Faculty> getFacultiesList() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public String deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
        return "Faculty has been deleted!";
    }

    @Override
    public List<Faculty> getFacultiesWithFilter(String color) {
        return facultyRepository.findFacultiesByColor(color.toLowerCase());
    }
}
