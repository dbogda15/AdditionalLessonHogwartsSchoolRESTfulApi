package me.dbogda.additionallessonhogwartsschoolrestfulapi.service.impl;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.DTO.StudentDTO;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Student;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.repository.FacultyRepository;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.repository.StudentRepository;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public StudentDTO create(StudentDTO studentDTO) {
        studentRepository.save(toStudent(studentDTO));
        return studentDTO;
    }

    @Override
    public StudentDTO getStudentById(Long id) {
       Student student = studentRepository.findById(id).get();
       return fromStudent(student);
    }

    @Override
    public List<StudentDTO> getStudentsList() {
        List<Student> students =  studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students){
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        studentRepository.save(toStudent(studentDTO));
        return studentDTO;
    }

    @Override
    public String deleteStudentById(Long id) {
        studentRepository.deleteById(id);
        return "Student has been deleted!";
    }
    @Override
    public List <StudentDTO> getStudentsWithFilter(int age) {
        List<Student> students = studentRepository.findStudentByAge(age);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students){
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
    @Override
    public List <StudentDTO> findStudentByAgeBetween(int min, int max) {
        List<Student> students = studentRepository.findByAgeBetween(min, max);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students){
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public List<StudentDTO> findStudentsByFacultyId(Long id) {
        List<Student> students = studentRepository.findStudentByFaculty_Id(id);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    public static StudentDTO fromStudent (Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyId(student.getFaculty().getId());
        dto.setFacultyDTO(FacultyServiceImpl.fromFaculty(student.getFaculty()));
        return dto;
    }
    public Student toStudent(StudentDTO studentDTO){
        return new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAge(), facultyRepository.getReferenceById(studentDTO.getFacultyId()));
    }
}
