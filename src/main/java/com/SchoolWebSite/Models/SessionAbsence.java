package com.SchoolWebSite.Models;
import java.util.Date;
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
public class SessionAbsence {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateAbsence;
    
    private boolean justify;
    
    int day;
    int mounth;
    int year;
    
	@ManyToOne
    @JoinColumn(name = "matiere")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

	public SessionAbsence(Long id, Date dateAbsence, boolean justify, int day, int mounth, int year, Matiere matiere,
			Student student) {
		super();
		this.id = id;
		this.dateAbsence = dateAbsence;
		this.justify = justify;
		this.day = day;
		this.mounth = mounth;
		this.year = year;
		this.matiere = matiere;
		this.student = student;
	}

	public SessionAbsence() {}

	public boolean isJustify() {
		return justify;
	}



	public void setJustify(boolean justify) {
		this.justify = justify;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateAbsence() {
		return dateAbsence;
	}

	public void setDateAbsence(Date dateAbsence) {
		this.dateAbsence = dateAbsence;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMounth() {
		return mounth;
	}

	public void setMounth(int mounth) {
		this.mounth = mounth;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
    
    
}
