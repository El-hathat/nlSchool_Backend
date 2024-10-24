package com.SchoolWebSite.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolWebSite.Models.Classe;
import com.SchoolWebSite.Models.Matiere;
import com.SchoolWebSite.Repository.ClasseRepo;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepo classeRepository;

    public List<Matiere> getMatieresForClasse(Long classeID) {
        return classeRepository.findMatieresByClasseId(classeID);
    }
    
    public Long getClassesForCurrentYear(String studentId) {
    	int month=new Date().getMonth();
        String currentYear = month>=9 && month<=12 ?(new Date().getYear()+1900)+"-"+(new Date().getYear()+1901):(new Date().getYear()+1899)+"-"+(new Date().getYear()+1900);
    	 // Vous pouvez dynamiquement récupérer l'année actuelle
        return classeRepository.findClassesForCurrentYear(studentId, currentYear);
    }
    
    
    public String gettimeTablee(String studentId) {
    	int month=new Date().getMonth();
        String currentYear = month>=9 && month<=12 ?(new Date().getYear()+1900)+"-"+(new Date().getYear()+1901):(new Date().getYear()+1899)+"-"+(new Date().getYear()+1900);
    	 // Vous pouvez dynamiquement récupérer l'année actuelle
        return classeRepository.findTimeTable(studentId, currentYear);
    }
}
