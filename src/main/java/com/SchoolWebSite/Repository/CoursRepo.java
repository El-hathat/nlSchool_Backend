package com.SchoolWebSite.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SchoolWebSite.Models.Cours;

@Repository
public interface CoursRepo extends JpaRepository<Cours, Long> {

}
