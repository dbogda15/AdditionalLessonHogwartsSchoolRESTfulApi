package me.dbogda.additionallessonhogwartsschoolrestfulapi.service.impl;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Student;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.repository.StudentRepository;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
       return studentRepository.findById(id).get();
    }

    @Override
    public List<Student> getStudentsList() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public String deleteStudentById(Long id) {
        studentRepository.deleteById(id);
        return "Student has been deleted!";
    }

    @Override
    public List <Student> getStudentsWithFilter(int age) {
        return studentRepository.findStudentByAge(age);
    }
}
