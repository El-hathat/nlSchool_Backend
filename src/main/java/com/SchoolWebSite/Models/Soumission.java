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
public class Soumission {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Date dateSoumission;
	    private Double note;
	    private String remarque;

	    
	    public Soumission(Long id, Date dateSoumission, Double note, String remarque, Devoir devoir, Student student) {
			super();
			this.id = id;
			this.dateSoumission = dateSoumission;
			this.note = note;
			this.remarque = remarque;
			this.devoir = devoir;
			this.student = student;
		}
public Soumission() {}
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getDateSoumission() {
			return dateSoumission;
		}

		public void setDateSoumission(Date dateSoumission) {
			this.dateSoumission = dateSoumission;
		}

		public Double getNote() {
			return note;
		}

		public void setNote(Double note) {
			this.note = note;
		}

		public String getRemarque() {
			return remarque;
		}

		public void setRemarque(String remarque) {
			this.remarque = remarque;
		}

		

		public void setDevoir(Devoir devoir) {
			this.devoir = devoir;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		@ManyToOne
	    @JoinColumn(name = "devoir")
	    private Devoir devoir;

	    @ManyToOne
	    @JoinColumn(name = "student")
	    private Student student;
}
