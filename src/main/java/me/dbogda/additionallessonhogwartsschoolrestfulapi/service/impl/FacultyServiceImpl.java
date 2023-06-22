package me.dbogda.additionallessonhogwartsschoolrestfulapi.service.impl;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Faculty;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.FacultyService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    HashMap<Long, Faculty> facultyHashMap = new HashMap<>();
    @Override
    public String create(Faculty faculty) {
        if (faculty!=null && !facultyHashMap.containsKey(faculty.getId())) {
            facultyHashMap.put(faculty.getId(), faculty);
            return faculty.getName() + " has been created!";
        }
        return null;
    }
    @Override
    public Faculty getFacultyById(Long id) {
        if (facultyHashMap.isEmpty()) {
            return null;
        }
        return facultyHashMap.get(id);
    }

    @Override
    public HashMap<Long, Faculty> getFacultiesMap() {
        return facultyHashMap;
    }

    @Override
    public Faculty updateFacultyById(Long id, Faculty faculty) {
        if (facultyHashMap.containsKey(id)){
            facultyHashMap.put(id, faculty);
        }
        return null;
    }

    @Override
    public String deleteFacultyById(Long id) {
        if (facultyHashMap.containsKey(id)) {
            facultyHashMap.remove(id);
            return "Faculty has been deleted!";
        }
        return null;
    }

    @Override
    public HashMap<Long, Faculty> getFacultiesWithFilter(String color) {
        return facultyHashMap.entrySet()
                .stream()
                .filter(s -> Objects.equals(color.toLowerCase(Locale.ROOT), s.getValue().getColor()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x, y) -> x,
                        HashMap::new
                ));
    }
}
