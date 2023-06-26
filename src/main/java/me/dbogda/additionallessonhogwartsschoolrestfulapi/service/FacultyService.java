package me.dbogda.additionallessonhogwartsschoolrestfulapi.service;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface FacultyService {
    Faculty create(Faculty faculty);
    Faculty getFacultyById(Long id);
    List<Faculty> getFacultiesList();
    Faculty updateFaculty(Faculty faculty);
    String deleteFacultyById(Long id);
    List<Faculty> getFacultiesWithFilter(String color);
}

