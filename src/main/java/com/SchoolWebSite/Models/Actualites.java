package com.SchoolWebSite.Models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Actualites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	String actualite;
	Date date;
	
	@ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;
	
	@ManyToOne
    @JoinColumn(name = "classe")
    private Classe classe;

	public Actualites(Long id, String actualite, Date date, Teacher teacher) {
		super();
		this.id = id;
		this.actualite = actualite;
		this.date = date;
		this.teacher = teacher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActualite() {
		return actualite;
	}

	public void setActualite(String actualite) {
		this.actualite = actualite;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public Actualites() {}
	

}
