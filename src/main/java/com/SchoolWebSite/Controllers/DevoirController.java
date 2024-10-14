package com.SchoolWebSite.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolWebSite.Models.Devoir;
import com.SchoolWebSite.Models.Soumission;
import com.SchoolWebSite.Services.DevoirService;
import com.SchoolWebSite.Services.SoumissionService;

@RestController
@RequestMapping("/devoir")
public class DevoirController {
	
	@Autowired
	SoumissionService serv;
	
	@Autowired
	DevoirService service;
	
	
	@GetMapping("/soumissions/{student}/{devoir}")
	public List<Soumission> getSoumissionsByStudent(@PathVariable String student,@PathVariable Long devoir){
		return serv.getSoumissionsByStudent(student, devoir);
		
	}
	  
	@GetMapping("/devoirSoumis/{student}/{matiere}")
	public List<Devoir> getSoumissionsOfDevoir(@PathVariable String student,@PathVariable Long matiere){

		return service.getSoumissionsOfDevoir(student,matiere);
		
	}
	
	@GetMapping("devoirById/{devoir}")
	public Optional<Devoir> getDevoir(@PathVariable Long devoir){

		return service.getDevoirById(devoir);
		
	}
	
	@GetMapping("/{matiere}")
	public List<Devoir> getCountOfDevoir(@PathVariable Long matiere){

		return service.getDevoirs(matiere);
		
	}

}
