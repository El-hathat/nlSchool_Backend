package com.SchoolWebSite.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class School {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	String name;
	String addresse;
	String tel;
	String fax;
	
	@OneToMany(mappedBy = "school")
    private List<Student> students;
	
	@OneToMany(mappedBy = "school")
    private List<Teacher> teachers;
	
	@OneToMany(mappedBy = "school")
    private List<Employe> employes;
	
	@OneToMany(mappedBy = "school")
    private List<Admin> admins;

	public School(Long id, String name, String addresse, String tel, String fax) {
		super();
		this.id = id;
		this.name = name;
		this.addresse = addresse;
		this.tel = tel;
		this.fax = fax;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public School() {}
	
}
