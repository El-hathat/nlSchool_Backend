package com.SchoolWebSite.Models;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Salle {
    @Id
    private String salleNum;
    
    private String salleEtage;

    @OneToMany(mappedBy = "salle")
    private List<Matiere> matieres;
}
