package com.SchoolWebSite.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolWebSite.Models.Devoir;
import com.SchoolWebSite.Models.Soumission;
import com.SchoolWebSite.Repository.SoumissionRepo;

@Service
public class SoumissionService{
	
	@Autowired 
	SoumissionRepo repo;
	
	
	@Autowired 
	StudentService rep;
	
	@Autowired 
	DevoirService dev;
	
	public List<Soumission> getSoumissionsByStudent(String email,Long devoir){
		return repo.findSoumissionsByStudentAndMatiere(email, devoir);
	}

	public Soumission creerSoumission(String std,Long dv,String file) {
		Soumission sm=this.getSoumissionBySdAndDv(std, dv);
		sm.setFile(file);
		sm.setStudent(rep.getByEmail(std));
		sm.setDevoir(dev.getDv(dv));
		sm.setDateSoumission(LocalDate.now());
		return repo.save(sm);
	}
	
	
	public Soumission getSoumissionBySdAndDv(String st,Long dv) {
		return repo.findSoumissionByStudentAndDevoir(rep.getByEmail(st), dev.getDv(dv));
	}
}
