package com.SchoolWebSite.Controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.SchoolWebSite.Models.Cours;
import com.SchoolWebSite.Models.Devoir;
import com.SchoolWebSite.Models.Soumission;
import com.SchoolWebSite.Services.CloudinaryService;
import com.SchoolWebSite.Services.DevoirService;
import com.SchoolWebSite.Services.SoumissionService;
import com.SchoolWebSite.Services.StudentService;

@RestController
@RequestMapping("/devoir")
public class DevoirController {
	
	@Autowired
	SoumissionService serv;
	
	@Autowired
	DevoirService service;
	
	@Autowired
	StudentService ser;
	@Autowired
	CloudinaryService cloudinaryService;
	
	@PostMapping(value="/upload/{email}/{dv}", consumes = "multipart/form-data")
    public Soumission uploadFile(@PathVariable String email,@PathVariable Long dv,@RequestParam("file") MultipartFile file) {
        try {
            // Get file details
            String fileName = file.getOriginalFilename();
            System.out.println("Received file: " + fileName);

            if (file.isEmpty()) {
		        throw new RuntimeException("File is empty");
		    }
           
		    return  serv.creerSoumission(email, dv, cloudinaryService.uploadImage(file));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	
	@GetMapping("/getSoumission/{email}/{dv}")
	public Soumission getSm(@PathVariable String email,@PathVariable Long dv) {
		System.out.println("daz");
		return serv.getSoumissionBySdAndDv(email, dv);
	}
	
	
	@PostMapping("/sm/{student}/{dv}/{file}")
	public Soumission setSoumission(@PathVariable String student,@PathVariable Long devoir,@PathVariable String file){

		return serv.creerSoumission(student, devoir, file);
		
	}
	
	@GetMapping("/soumissions/{student}/{devoir}")
	public List<Soumission> getSoumissionsByStudent(@PathVariable String student,@PathVariable Long devoir){
		return serv.getSoumissionsByStudent(student, devoir);
		
	}
	  
	@GetMapping("/devoirSoumis/{student}/{matiere}")
	public List<Devoir> getSoumissionsOfDevoir(@PathVariable String student,@PathVariable Long matiere){

		return service.getSoumissionsOfDevoir(student,matiere);
		
	}
	
	@GetMapping("/devoirById/{devoir}")
	public Optional<Devoir> getDevoir(@PathVariable Long devoir){

		return service.getDevoirById(devoir);
		
	}
	
	@GetMapping("/{matiere}")
	public List<Devoir> getCountOfDevoir(@PathVariable Long matiere){

		return service.getDevoirs(matiere);
		
	}

}
