package me.dbogda.additionallessonhogwartsschoolrestfulapi.service;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.DTO.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO create(StudentDTO studentDTO);
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getStudentsList();
    StudentDTO updateStudent(StudentDTO studentDTO);
    String deleteStudentById(Long id);
    List<StudentDTO> getStudentsWithFilter(int age);
    List<StudentDTO> findStudentByAgeBetween(int min, int max);
    List<StudentDTO> findStudentsByFacultyId(Long id);
}
