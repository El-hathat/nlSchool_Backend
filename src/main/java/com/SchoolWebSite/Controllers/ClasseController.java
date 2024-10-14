package com.SchoolWebSite.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolWebSite.Models.Cours;
import com.SchoolWebSite.Models.Matiere;
import com.SchoolWebSite.Services.ClasseService;

@RestController
@RequestMapping("/classes") 
public class ClasseController {

    @Autowired 
    private ClasseService classeService;

    @GetMapping("/{classeID}/matieres")
    public List<Matiere> getMatieres(@PathVariable Long classeID) {
        return classeService.getMatieresForClasse(classeID);
    }
    
   
    
    
}
