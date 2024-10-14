package com.SchoolWebSite.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class NoteFinale {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nfID;
	double NEN;
	double NCC;
	double NF;
	double NC;
	 @Enumerated(EnumType.STRING)
	    @Column(name="Semestre")
	    private Semestre semestre;
	
	@ManyToOne
    @JoinColumn(name = "student")
    private Student student;
	
	@ManyToOne
    @JoinColumn(name = "classe")
    private Classe classe;

	public Long getNfID() {
		return nfID;
	}

	public void setNfID(Long nfID) {
		this.nfID = nfID;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	

	

	

	public Student getStudent() {
		return student;
	}

	public double getNEN() {
		return NEN;
	}

	public void setNEN(double nEN) {
		NEN = nEN;
	}

	public double getNCC() {
		return NCC;
	}

	public void setNCC(double nCC) {
		NCC = nCC;
	}

	public double getNF() {
		return NF;
	}

	public void setNF(double nF) {
		NF = nF;
	}

	public double getNC() {
		return NC;
	}

	public void setNC(double nC) {
		NC = nC;
	}

	

	public void setStudent(Student student) {
		this.student = student;
	}

	
	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	
	
	public NoteFinale(Long nfID, double nEN, double nCC, double nF, double nC, Semestre semestre, Student student,
			Classe classe) {
		super();
		this.nfID = nfID;
		NEN = nEN;
		NCC = nCC;
		NF = nF;
		NC = nC;
		this.semestre = semestre;
		this.student = student;
		this.classe = classe;
	}

	public NoteFinale() {}
}
