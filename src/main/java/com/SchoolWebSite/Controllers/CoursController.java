package com.SchoolWebSite.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolWebSite.Models.Cours;
import com.SchoolWebSite.Services.CoursService;

@RestController
@RequestMapping("/cours") 
public class CoursController {
	
	@Autowired
	CoursService serv;
	
	 @GetMapping("/{courID}")
	    public Optional<Cours> getCours(@PathVariable Long courID) {
	    	System.out.println("dazt dazt");
	        return serv.getCours(courID);
	    }

}
