package com.SchoolWebSite.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SchoolWebSite.Models.SessionAbsence;

@Repository
public interface AbsenceRepo extends JpaRepository<SessionAbsence, Long> {
	@Query("SELECT sa FROM SessionAbsence sa WHERE sa.day = :day AND sa.mounth = :mounth AND sa.year = :year AND sa.student.id = :studentId")
    List<SessionAbsence> findByDayMonthYearAndStudent(
        @Param("day") int day, 
        @Param("mounth") int mounth, 
        @Param("year") int year, 
        @Param("studentId") String studentId
    );
}
