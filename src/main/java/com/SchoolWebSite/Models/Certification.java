package com.SchoolWebSite.Models;
import java.io.File;

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
public class Certification {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long certId;

	    private String certificat;

	    @ManyToOne
	    @JoinColumn(name = "student")
	    private Student student;
	
}
