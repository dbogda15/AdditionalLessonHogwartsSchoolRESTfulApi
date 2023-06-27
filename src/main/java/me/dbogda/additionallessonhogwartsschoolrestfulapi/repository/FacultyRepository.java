package me.dbogda.additionallessonhogwartsschoolrestfulapi.repository;

import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultiesByColorIgnoreCase(String color);
    List<Faculty> findFacultiesByNameIgnoreCase (String name);
}
