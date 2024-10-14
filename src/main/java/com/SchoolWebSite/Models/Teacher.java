package com.SchoolWebSite.Models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Teacher{
	@Id
	private String email;
	@Column
	private String fullName;
	@Column
	private String address;
	@Column
	private String tel;
	@Column
	private String password;
	@Column
	private Date birthDate;
	@Column
	private String academy;
	@Column
	private String DP;
	@Column
float basesalary;
	
	String profil;
	
	
	
	  public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}
	
	@OneToMany(mappedBy = "teacher")
    private List<Matiere> matieres;
	 
	 @OneToMany(mappedBy = "teacher")
	    private List<Salary> salaries;
	 
	 @OneToMany(mappedBy = "teacher")
	    private List<Actualites> actualites;
	 
	 @OneToMany(mappedBy = "teacher")
	    private List<Comment> comments;
	 
	    @ManyToOne
	    @JoinColumn(name = "school")
	    private School school;



	public Teacher(String email, String fullName, String address, String tel, String password, Date birthDate,
			String academy, String dP, float basesalary, String profil, List<Matiere> matieres,
			List<Salary> salaries, List<Comment> comments) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.address = address;
		this.tel = tel;
		this.password = password;
		this.birthDate = birthDate;
		this.academy = academy;
		DP = dP;
		this.basesalary = basesalary;
		this.profil = profil;
		this.matieres = matieres;
		this.salaries = salaries;
		this.comments = comments;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getDP() {
		return DP;
	}

	public void setDP(String dP) {
		DP = dP;
	}

	public float getBasesalary() {
		return basesalary;
	}

	public void setBasesalary(float basesalary) {
		this.basesalary = basesalary;
	}

	public List<Salary> getSalaries() {
		return salaries;
	}

	public void setSalaries(List<Salary> salaries) {
		this.salaries = salaries;
	}
	 
	 
	 public Teacher() {}
}
