package com.SchoolWebSite.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolWebSite.Models.Matiere;
import com.SchoolWebSite.Repository.ClasseRepo;

@Service
public class ClasseService {

    @Autowired
    private ClasseRepo classeRepository;

    public List<Matiere> getMatieresForClasse(Long classeID) {
        return classeRepository.findMatieresByClasseId(classeID);
    }
}
