package com.SchoolWebSite.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolWebSite.Models.Devoir;
import com.SchoolWebSite.Repository.DevoirRepo;

@Service
public class DevoirService {
	
	@Autowired
	DevoirRepo rep;
	
	public List<Devoir> getDevoirs(Long matiere) {
		return rep.getDevoirsByMatiere(matiere);
	}
	
	public Optional<Devoir> getDevoirById(Long id) {
		return rep.findById(id);
	}
	public List<Devoir> getSoumissionsOfDevoir(String student,Long matiere) {
		return rep.findSubmittedDevoirsByMatiereAndStudent(student,matiere);
	}

}
