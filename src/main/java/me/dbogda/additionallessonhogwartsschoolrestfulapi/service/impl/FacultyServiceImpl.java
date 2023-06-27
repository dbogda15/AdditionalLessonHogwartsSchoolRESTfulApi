package me.dbogda.additionallessonhogwartsschoolrestfulapi.service.impl;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.DTO.FacultyDTO;
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
    public FacultyDTO create(FacultyDTO facultyDTO) {
         facultyRepository.save(toFaculty(facultyDTO));
         return facultyDTO;
    }
    @Override
    public FacultyDTO getFacultyById(Long id) {
       Faculty faculty = facultyRepository.findById(id).get();
       return fromFaculty(faculty);
    }

    @Override
    public List<FacultyDTO> getFacultiesList() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty faculty : faculties){
            FacultyDTO facultyDTO = fromFaculty(faculty);
            facultyDTOS.add(facultyDTO);
        }
        return facultyDTOS;
    }

    @Override
    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        facultyRepository.save(toFaculty(facultyDTO));
        return facultyDTO;
    }

    @Override
    public String deleteFacultyById(Long id) {
        facultyRepository.deleteById(id);
        return "Faculty has been deleted!";
    }

    @Override
    public List<FacultyDTO> getFacultiesWithFilter(String color) {
        List<Faculty> faculties = facultyRepository.findFacultiesByColorIgnoreCase(color);
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty faculty : faculties){
            FacultyDTO facultyDTO = fromFaculty(faculty);
            facultyDTOS.add(facultyDTO);
        }
        return facultyDTOS;
    }

    @Override
    public List<FacultyDTO> findFacultyByName(String name) {
        List<Faculty> faculties = facultyRepository.findFacultiesByNameIgnoreCase(name);
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty faculty : faculties){
            FacultyDTO facultyDTO = fromFaculty(faculty);
            facultyDTOS.add(facultyDTO);
        }
        return facultyDTOS;
    }

    public static FacultyDTO fromFaculty(Faculty faculty){
        FacultyDTO dto = new FacultyDTO();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setColor(faculty.getColor());
        return dto;
    }

    public Faculty toFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty.setId(facultyDTO.getId());
        faculty.setName(facultyDTO.getName());
        faculty.setColor(facultyDTO.getColor());
        return faculty;
    }
}
