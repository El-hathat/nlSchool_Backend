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
public class Cours {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long coursID;

	    private String matName;
	    private List<String> files;
	    private String description;
	    private Date pubDate;
	    
	    @ManyToOne
	    @JoinColumn(name = "matiere")
	    private Matiere matiere;

	    @OneToMany(mappedBy = "cours")
	    private List<Comment> comments;

		

		public Cours(Long coursID, String matName, List<String> files, String description, Date pubDate,
				Matiere matiere, List<Comment> comments) {
			super();
			this.coursID = coursID;
			this.matName = matName;
			this.files = files;
			this.description = description;
			this.pubDate = pubDate;
			this.matiere = matiere;
			this.comments = comments;
		}

		public Long getCoursID() {
			return coursID;
		}

		public List<Comment> getComments() {
			return comments;
		}

		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}

		public void setCoursID(Long coursID) {
			this.coursID = coursID;
		}

		public String getMatName() {
			return matName;
		}

		public void setMatName(String matName) {
			this.matName = matName;
		}

		public List<String> getFiles() {
			return files;
		}

		public void setFiles(List<String> files) {
			this.files = files;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getPubDate() {
			return pubDate;
		}

		public void setPubDate(Date pubDate) {
			this.pubDate = pubDate;
		}

		

		public void setMatiere(Matiere matiere) {
			this.matiere = matiere;
		}
	    
	    public Cours() {}


}
