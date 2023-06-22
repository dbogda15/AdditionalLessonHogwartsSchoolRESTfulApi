package me.dbogda.additionallessonhogwartsschoolrestfulapi.service;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public interface FacultyService {
    String create(Faculty faculty);
    Faculty getFacultyById(Long id);
    HashMap<Long, Faculty> getFacultiesMap();
    Faculty updateFacultyById(Long id, Faculty faculty);
    String deleteFacultyById(Long id);
    HashMap<Long, Faculty> getFacultiesWithFilter(String color);
}

