package me.dbogda.additionallessonhogwartsschoolrestfulapi.service.impl;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Student;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    HashMap<Long, Student> studentHashMap = new HashMap<>();

    @Override
    public String create(Student student) {
        if (student != null && !studentHashMap.containsKey(student.getId())) {
            studentHashMap.put(student.getId(), student);
            return student.getName() + " has been created!";
        }
        return null;
    }

    @Override
    public Student getStudentById(Long id) {
       if(!studentHashMap.isEmpty()) {
           return studentHashMap.get(id);
       } return null;
    }

    @Override
    public HashMap<Long, Student> getStudentsMap() {
        return studentHashMap;
    }

    @Override
    public Student updateStudentById(Long id, Student student) {
        if (studentHashMap.containsKey(id)) {
            return studentHashMap.put(id, student);
        }
        return null;
    }

    @Override
    public String deleteStudentById(Long id) {
        if (studentHashMap.containsKey(id)) {
            studentHashMap.remove(id);
            return "Student has been deleted!";
        }
        return null;
    }

    @Override
    public HashMap<Long, Student> getStudentsWithFilter(int age) {
        return studentHashMap.entrySet()
                .stream()
                .filter(s -> Objects.equals(age, s.getValue().getAge()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x, y) -> x,
                        HashMap::new
                ));
    }
}
