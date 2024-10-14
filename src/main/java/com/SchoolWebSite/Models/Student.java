package com.SchoolWebSite.Models;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table
public class Student implements UserDetails{
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
	private String fatherName;
	@Column
	private String motherName;
	@Column
	private String guardianTel;
	
	@Enumerated(EnumType.STRING)
    @Column(name="Genre")
    private Genre genre;
	
	String profil;
	
	
	
	  public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@ManyToMany(mappedBy = "students")
    private List<Classe> classes;

	    @OneToMany(mappedBy = "student")
	    private List<Soumission> soumissions;

	    @OneToMany(mappedBy = "student")
	    private List<SessionAbsence> absences;
	    
	    @OneToMany(mappedBy = "student")
	    private List<NoteFinale> noteFinale;
	    
	    @ManyToOne
	    @JoinColumn(name = "bus_id")
	    private Bus schoolBus;
	    
	    @ManyToOne
	    @JoinColumn(name = "school")
	    private School school;
	    
	    @OneToMany(mappedBy = "student")
	    private List<Certification> certifications;
	    
	    @OneToMany(mappedBy = "student")
	    private List<Bill> bills;
	    
	    @OneToMany(mappedBy = "student")
	    private List<Comment> comments;
	    
	    

		

		public Student(String email, String fullName, String address, String tel, String password, Date birthDate,
				String school, String academy, String dP, String fatherName, String motherName, String guardianTel,
				Genre genre, String profil, List<Soumission> soumissions, List<SessionAbsence> absences,
				Bus schoolBus, List<Certification> certifications, List<Bill> bills, List<Comment> comments) {
			super();
			this.email = email;
			this.fullName = fullName;
			this.address = address;
			this.tel = tel;
			this.password = password;
			this.birthDate = birthDate;
			this.academy = academy;
			DP = dP;
			this.fatherName = fatherName;
			this.motherName = motherName;
			this.guardianTel = guardianTel;
			this.genre = genre;
			this.profil = profil;
			
			this.soumissions = soumissions;
			this.absences = absences;
			this.schoolBus = schoolBus;
			this.certifications = certifications;
			this.bills = bills;
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

		public String getFatherName() {
			return fatherName;
		}

		public void setFatherName(String fatherName) {
			this.fatherName = fatherName;
		}

		public String getMotherName() {
			return motherName;
		}

		public void setMotherName(String motherName) {
			this.motherName = motherName;
		}

		public String getGuardianTel() {
			return guardianTel;
		}

		public void setGuardianTel(String guardianTel) {
			this.guardianTel = guardianTel;
		}

		

	//	public List<Soumission> getSoumissions() {
		//	return soumissions;
		//}

		public void setSoumissions(List<Soumission> soumissions) {
			this.soumissions = soumissions;
		}

	/*	public List<SessionAbsence> getAbsences() {
			return absences;
		}

		public void setAbsences(List<SessionAbsence> absences) {
			this.absences = absences;
		}*/

		public Bus getSchoolBus() {
			return schoolBus;
		}

		public void setSchoolBus(Bus schoolBus) {
			this.schoolBus = schoolBus;
		}

		public List<Certification> getCertifications() {
			return certifications;
		}

		public void setCertifications(List<Certification> certifications) {
			this.certifications = certifications;
		}

		public List<Bill> getBills() {
			return bills;
		}

		public void setBills(List<Bill> bills) {
			this.bills = bills;
		}
	    
	    public Student() {}

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        // Return the user's roles or authorities
	        return List.of(new SimpleGrantedAuthority("ROLE_USER")); // or whatever roles you want to assign
	    }

	    @Override
	    public String getPassword() {
	        return this.password;
	    }

	    @Override
	    public String getUsername() {
	        return this.email;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true; // Or implement your logic
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true; // Or implement your logic
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true; // Or implement your logic
	    }

	    @Override
	    public boolean isEnabled() {
	        return true; // Or implement your logic
	    }    
}
