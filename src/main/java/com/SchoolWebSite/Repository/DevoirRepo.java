package com.SchoolWebSite.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SchoolWebSite.Models.Devoir; 
import com.SchoolWebSite.Models.Soumission;
@Repository
public interface DevoirRepo extends JpaRepository<Devoir, Long> {
	@Query("SELECT d FROM Devoir d WHERE d.matiere.matID = :matiere")
	public List<Devoir> getDevoirsByMatiere( @Param("matiere") Long matiere);
	
	@Query("SELECT d FROM Devoir d WHERE d.matiere.matID = :matiere AND EXISTS (SELECT s FROM Soumission s WHERE s.devoir = d AND s.student.email = :student)")
	List<Devoir> findSubmittedDevoirsByMatiereAndStudent(
		@Param("student") String student,
	    @Param("matiere") Long matiere
	    
	);



}
