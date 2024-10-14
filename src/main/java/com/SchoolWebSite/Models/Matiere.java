package com.SchoolWebSite.Models;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Matiere {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long matID;

	    private String matName;
	    private Date pubDate;
	    
	    public Matiere() {}

	    
	    public Long getMatID() {
			return matID;
		}

		public void setMatID(Long matID) {
			this.matID = matID;
		}

		public String getMatName() {
			return matName;
		}

		public void setMatName(String matName) {
			this.matName = matName;
		}

		public Date getPubDate() {
			return pubDate;
		}

		public void setPubDate(Date pubDate) {
			this.pubDate = pubDate;
		}

	/*	public List<Classe> getClasses() {
			return classes;
		}*/

		public void setClasses(List<Classe> classes) {
			this.classes = classes;
		}

		public Salle getSalle() {
			return salle;
		}

		public void setSalle(Salle salle) {
			this.salle = salle;
		}

		public Teacher getTeacher() {
			return teacher;
		}

		public void setTeacher(Teacher teacher) {
			this.teacher = teacher;
		}

		public List<Devoir> getDevoirs() {
			return devoirs;
		}

		public void setDevoirs(List<Devoir> devoirs) {
			this.devoirs = devoirs;
		}

		public List<Cours> getCours() {
			return cours;
		}

		public void setCours(List<Cours> cours) {
			this.cours = cours;
		}

		@ManyToMany(mappedBy = "matieres")
	    private List<Classe> classes;
	    

	    public Matiere(Long matID, String matName, Date pubDate, List<Classe> classes, Salle salle, Teacher teacher,
				List<Devoir> devoirs, List<Cours> cours) {
			super();
			this.matID = matID;
			this.matName = matName;
			this.pubDate = pubDate;
			this.classes = classes;
			this.salle = salle;
			this.teacher = teacher;
			this.devoirs = devoirs;
			this.cours = cours;
		}

		@ManyToOne
	    @JoinColumn(name = "salle_id")
	    private Salle salle;
		
		@OneToMany(mappedBy = "matiere")
	    private List<SessionAbsence> absences;
		
		@OneToMany(mappedBy = "matiere")
	    private List<Examen> exams;
		
	
	    
	    @ManyToOne
	    @JoinColumn(name = "teacher_id")
	    private Teacher teacher;
	    
	    @OneToMany(mappedBy = "matiere")
	    private List<Devoir> devoirs;
	    
	    @OneToMany(mappedBy = "matiere")
	    private List<Cours> cours;
	    
	    
}
