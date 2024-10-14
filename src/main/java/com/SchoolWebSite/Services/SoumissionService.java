package com.SchoolWebSite.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolWebSite.Models.Soumission;
import com.SchoolWebSite.Repository.SoumissionRepo;

@Service
public class SoumissionService{
	
	@Autowired 
	SoumissionRepo repo;
	public List<Soumission> getSoumissionsByStudent(String email,Long devoir){
		return repo.findSoumissionsByStudentAndMatiere(email, devoir);
	}

}
