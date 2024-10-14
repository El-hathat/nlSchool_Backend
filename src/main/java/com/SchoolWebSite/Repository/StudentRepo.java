package com.SchoolWebSite.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SchoolWebSite.Models.Actualites;
import com.SchoolWebSite.Models.Bill;
import com.SchoolWebSite.Models.Certification;
import com.SchoolWebSite.Models.Classe;
import com.SchoolWebSite.Models.Etat;
import com.SchoolWebSite.Models.NoteFinale;
import com.SchoolWebSite.Models.Semestre;
import com.SchoolWebSite.Models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
	
	Student findByEmail(String email);
	
	@Query("SELECT s, sl.name, cls.className,s.schoolBus.matricule " +
	           "FROM Student s " +
	           "JOIN s.school sl " +
	           "JOIN s.classes cls " +
	           "WHERE s.email = :email")
	    Object[] findStudentDetailsByEmail(@Param("email") String email);
	    
	    
	
	@Query("SELECT n FROM NoteFinale n " +
		       "JOIN n.classe c " +
		       "JOIN c.filiere f " +
		       "JOIN c.students s " +
		       "WHERE n.student = :student " +
		       "AND s = :student " +
		       "AND c.schoolYear = :schoolyear AND n.semestre= :semestre")
		NoteFinale getNoteFinale(
		    @Param("student") Student student,
		    @Param("schoolyear") String schoolyear,
		    @Param("semestre") Semestre semestre
		);
	
	@Query("SELECT n FROM NoteFinale n " +
		       "JOIN n.classe c " +
		       "JOIN c.filiere f " +
		       "JOIN c.students s " +
		       "WHERE c.schoolYear = :schoolyear AND n.semestre= :semestre AND c=classe"
		       + " order by n.NF")
		List<NoteFinale> getNoteFinaleSort(
		  
		    @Param("schoolyear") String schoolyear,
		    @Param("semestre") Semestre semestre,
		    @Param("semestre") Classe classe
		);

    @Query("SELECT c " +
            "FROM Classe c " +
            "JOIN c.students s " +
            "WHERE s.email = :email")
    Classe findClasseByStudent(@Param("email") String email);
    
    
    @Query("SELECT c FROM Classe c JOIN c.students s WHERE s.email = :email AND c.schoolYear = :schoolYear")
    Classe findClasseByStudentAndSchoolYear(@Param("email") String email, @Param("schoolYear") String schoolYear);

	
	@Query("SELECT DISTINCT e.note, e.semestre, e.remarque, m.matName, c.className, c.schoolYear " +
		       "FROM ExamNote e " +
		       "JOIN e.exam ex " +
		       "JOIN ex.matiere m " +
		       "JOIN m.classes c " +
		       "JOIN e.student ss " +
		       "JOIN NoteFinale n ON n.classe.id = c.id " +
		       "WHERE c.schoolYear = :schoolYear " +
		       "AND e.semestre = :semestre " +
		       "AND m.matName = :matName "+
		       "AND ss.email= :email")
		List<Object[]> findExamNotes(
		    @Param("schoolYear") String schoolYear, 
		    @Param("semestre") Semestre semestre, 
		    @Param("matName") String matName,
		    @Param("email") String email
		);
		
		
		 @Query("SELECT f.filiereName, f.schoolLevel, s.name, c.className, COUNT(st) " +
		           "FROM Filiere f " +
		           "JOIN f.classes c " +
		           "JOIN c.students st " +
		           "JOIN st.school s " +
		           "WHERE st.email = :email " +
		           "AND c.schoolYear = :year " +
		           "GROUP BY f.filiereName, f.schoolLevel, s.name, c.className")
		    List<Object[]> findClassInfoByStudentEmail(@Param("email") String email,@Param("year") String year);

		    @Query("select b from Bill b,Student s where b.student=s AND b.etat= :etat and s= :student")
		    List<Bill> findAllBillsByStudent(Etat etat,Student student);
		    
		    
		    @Query("select c from Certification c where c.student= :student")
		    List<Certification> findCertificatsByStudent(@Param("student") Student student);
		    
		    @Query("SELECT count(d) "
		    		+ "FROM Devoir d "
		    		+ "LEFT JOIN Soumission s ON d.devoirID = s.devoir.devoirID "
		    		+ "cross join Student st "
		    		+ "WHERE s.devoir IS NULL "
		    		+ "and st.email= :email")
		    int devoirNonRemis(@Param("email") String email);
		    
		    @Query("select count(s) from SessionAbsence s where s.student= :student")
		    int getCountOfSessionAbsenceByStudent(@Param("student") Student student);
		    
		    @Query("SELECT a FROM Actualites a " +
		            "JOIN a.classe c " +
		            "JOIN a.teacher t " +
		            "WHERE c.classID = :classid")
		     List<Actualites> findActualitesByClassId(@Param("classid") Long classid);

}
