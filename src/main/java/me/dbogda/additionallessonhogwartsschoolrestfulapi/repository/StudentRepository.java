package me.dbogda.additionallessonhogwartsschoolrestfulapi.repository;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByAge(int age);
    List<Student> findByAgeBetween(int min, int max);
    List<Student> findStudentByFaculty_Id(Long id);
}
