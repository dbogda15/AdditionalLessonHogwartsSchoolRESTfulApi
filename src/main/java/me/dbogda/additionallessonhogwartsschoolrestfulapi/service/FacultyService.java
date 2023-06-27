package me.dbogda.additionallessonhogwartsschoolrestfulapi.service;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.DTO.FacultyDTO;

import java.util.List;

public interface FacultyService {
    FacultyDTO create(FacultyDTO facultyDTO);
    FacultyDTO getFacultyById(Long id);
    List<FacultyDTO> getFacultiesList();
    FacultyDTO updateFaculty(FacultyDTO facultyDTO);
    String deleteFacultyById(Long id);
    List<FacultyDTO> getFacultiesWithFilter(String color);
    List<FacultyDTO> findFacultyByName(String name);
}

