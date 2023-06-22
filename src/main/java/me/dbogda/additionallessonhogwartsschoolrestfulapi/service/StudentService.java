package me.dbogda.additionallessonhogwartsschoolrestfulapi.service;

import org.springframework.stereotype.Service;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Student;

import java.util.HashMap;

@Service
public interface StudentService {
    String create(Student student);
    Student getStudentById(Long id);
    HashMap<Long, Student> getStudentsMap();
    Student updateStudentById(Long id, Student student);
    String deleteStudentById(Long id);
    HashMap<Long, Student> getStudentsWithFilter(int age);
}
