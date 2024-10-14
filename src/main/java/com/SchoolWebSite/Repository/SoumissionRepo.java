package com.SchoolWebSite.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SchoolWebSite.Models.Matiere;
import com.SchoolWebSite.Models.Soumission;
@Repository
public interface SoumissionRepo extends JpaRepository<Soumission, Long> {
	  @Query("SELECT s FROM Soumission s WHERE s.student.email = :student AND s.devoir.matiere.matID = :matiere")
	    List<Soumission> findSoumissionsByStudentAndMatiere(
	        @Param("student") String student,
	        @Param("matiere") Long matiere
	    );
}
