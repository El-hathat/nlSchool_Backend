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
public class Filiere {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long filiereID;

	    private String filiereName;
	    private String schoolLevel;

	    @OneToMany(mappedBy = "filiere")
	    private List<Classe> classes;
}
