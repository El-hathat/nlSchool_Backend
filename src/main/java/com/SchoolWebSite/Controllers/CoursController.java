package com.SchoolWebSite.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.SchoolWebSite.Models.Cours;
import com.SchoolWebSite.Models.Student;
import com.SchoolWebSite.Services.CloudinaryService;
import com.SchoolWebSite.Services.CoursService;

@RestController
@RequestMapping("/cours") 
public class CoursController {
	
	@Autowired
	CoursService serv;
	@Autowired
	CloudinaryService cloudinaryService;
	
	 @GetMapping("/{courID}")
	    public Optional<Cours> getCours(@PathVariable Long courID) {
	    	System.out.println("dazt dazt");
	        return serv.getCours(courID);
	    }
	 
	 
	 @PostMapping(value="/add", consumes = "multipart/form-data")
		@ResponseBody
		public Optional<Cours> addStudent(@ModelAttribute Cours cours,
		        @RequestParam("file") MultipartFile file) throws IOException {
			if (file.isEmpty()) {
		        throw new RuntimeException("File is empty");
		    }
		    Cours st = serv.saveCours(cours);
		    st.setFile(cloudinaryService.uploadImage(file));
		    return Optional.of(serv.saveCours(st));
		}

}
