package com.SchoolWebSite.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolWebSite.Models.Cours;
import com.SchoolWebSite.Repository.CoursRepo;

@Service
public class CoursService {

	@Autowired
	CoursRepo repo;
	
	public Optional<Cours> getCours(Long id) {
		return repo.findById(id);
	}
	
	
}
