package me.dbogda.additionallessonhogwartsschoolrestfulapi.repository;

import io.swagger.v3.oas.models.links.Link;
import me.dbogda.additionallessonhogwartsschoolrestfulapi.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findFacultiesByColor(String color);
}
