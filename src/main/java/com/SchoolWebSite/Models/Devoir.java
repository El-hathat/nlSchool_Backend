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
public class Devoir {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long devoirID;

	    private String devoirTitle;
	    private String description;
	    private String devoirFile;
	    private Date devoirDate;
	    private Date limiteDate;

	    @OneToMany(mappedBy = "devoir")
	    private List<Soumission> soumissions;
	    
	    @ManyToOne
	    @JoinColumn(name = "matiere")
	    private Matiere matiere;

	    @OneToMany(mappedBy = "devoir")
	    private List<Comment> comments;

		public Devoir(Long devoirID, String devoirTitle, String description, String devoirFile, Date devoirDate,
				Date limiteDate, List<Soumission> soumissions, Matiere matiere) {
			super();
			this.devoirID = devoirID;
			this.devoirTitle = devoirTitle;
			this.description = description;
			this.devoirFile = devoirFile;
			this.devoirDate = devoirDate;
			this.limiteDate = limiteDate;
			this.soumissions = soumissions;
			this.matiere = matiere;
		}

		
		public List<Comment> getComments() {
			return comments;
		}


		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}


		public Date getLimiteDate() {
			return limiteDate;
		}


		public void setLimiteDate(Date limiteDate) {
			this.limiteDate = limiteDate;
		}


		public Long getDevoirID() {
			return devoirID;
		}

		public void setDevoirID(Long devoirID) {
			this.devoirID = devoirID;
		}

		public String getDevoirTitle() {
			return devoirTitle;
		}

		public void setDevoirTitle(String devoirTitle) {
			this.devoirTitle = devoirTitle;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getDevoirFile() {
			return devoirFile;
		}

		public void setDevoirFile(String devoirFile) {
			this.devoirFile = devoirFile;
		}

		public Date getDevoirDate() {
			return devoirDate;
		}

		public void setDevoirDate(Date devoirDate) {
			this.devoirDate = devoirDate;
		}

		public List<Soumission> getSoumissions() {
			return soumissions;
		}

		public void setSoumissions(List<Soumission> soumissions) {
			this.soumissions = soumissions;
		}

		

		public void setMatiere(Matiere matiere) {
			this.matiere = matiere;
		}
	    
		public Devoir() {}
	    
}
