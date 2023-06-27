package me.dbogda.additionallessonhogwartsschoolrestfulapi.DTO;

public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private Long facultyId;
    private FacultyDTO facultyDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public FacultyDTO getFacultyDTO() {
        return facultyDTO;
    }
    public void setFacultyDTO(FacultyDTO facultyDTO) {
        this.facultyDTO = facultyDTO;
    }
}
