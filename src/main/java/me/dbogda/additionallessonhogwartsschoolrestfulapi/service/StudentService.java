package me.dbogda.additionallessonhogwartsschoolrestfulapi.service;

import org.springframework.stereotype.Service;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Student;

import java.util.HashMap;
import java.util.List;

@Service
public interface StudentService {
    Student create(Student student);
    Student getStudentById(Long id);
    List<Student> getStudentsList();
    Student updateStudent(Student student);
    String deleteStudentById(Long id);
    List<Student> getStudentsWithFilter(int age);
}
