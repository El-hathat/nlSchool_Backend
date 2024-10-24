package com.SchoolWebSite.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SchoolWebSite.Models.Classe;
import com.SchoolWebSite.Models.Matiere;

public interface ClasseRepo extends JpaRepository<Classe, Long> {

    @Query("SELECT c.matieres FROM Classe c WHERE c.classID = :classID")
    List<Matiere> findMatieresByClasseId(@Param("classID") Long classID);
    
    @Query("SELECT c.classID FROM Student s JOIN s.classes c WHERE s.id = :studentId AND c.schoolYear = :currentYear")
    Long findClassesForCurrentYear(@Param("studentId") String studentId, @Param("currentYear") String currentYear);
    
    @Query("SELECT c.timeTable FROM Student s JOIN s.classes c WHERE s.id = :studentId AND c.schoolYear = :currentYear")
    String findTimeTable(@Param("studentId") String studentId, @Param("currentYear") String currentYear);
   
}
