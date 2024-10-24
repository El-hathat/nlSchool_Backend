package com.SchoolWebSite.Models;
import java.io.File;
import java.util.Date;

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
	 	private File cetFile;
	    private String certificat;
	    private Date date;

	    @ManyToOne
	    @JoinColumn(name = "student")
	    private Student student;

		public Long getCertId() {
			return certId;
		}

		public void setCertId(Long certId) {
			this.certId = certId;
		}

		public File getCetFile() {
			return cetFile;
		}

		public void setCetFile(File cetFile) {
			this.cetFile = cetFile;
		}

		public String getCertificat() {
			return certificat;
		}

		public void setCertificat(String certificat) {
			this.certificat = certificat;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Certification(){}
		public Certification(Long certId, File cetFile, String certificat, Date date, Student student) {
			super();
			this.certId = certId;
			this.cetFile = cetFile;
			this.certificat = certificat;
			this.date = date;
			this.student = student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		
	    
	    
	
}
