package com.SchoolWebSite.Models;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Classe {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long classID;

	    private String className;
	    private String schoolYear;
	    private String timeTable;

	    @ManyToOne
	    @JoinColumn(name = "filiere_id")
	    private Filiere filiere;

	 
	    
	    @OneToMany(mappedBy = "classe")
	    private List<NoteFinale> noteFinale;
	    
	    
	    @OneToMany(mappedBy = "classe")
	    private List<Actualites> actualites;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "classe_student",
	        joinColumns = @JoinColumn(name = "classe_id"),
	        inverseJoinColumns = @JoinColumn(name = "student_id")
	    )
	    private List<Student> students;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "classe_matiere",
	        joinColumns = @JoinColumn(name = "classe_id"),
	        inverseJoinColumns = @JoinColumn(name = "matiere_id")
	    )
	    private List<Matiere> matieres;

		public Classe(Long classID, String className, String schoolYear, String timeTable, Filiere filiere,
				List<Student> students, List<Matiere> matieres) {
			super();
			this.classID = classID;
			this.className = className;
			this.schoolYear = schoolYear;
			this.timeTable = timeTable;
			this.filiere = filiere;
			this.students = students;
			this.matieres = matieres;
		}

		public Long getClassID() {
			return classID;
		}

		public void setClassID(Long classID) {
			this.classID = classID;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getSchoolYear() {
			return schoolYear;
		}

		public void setSchoolYear(String schoolYear) {
			this.schoolYear = schoolYear;
		}

		public String getTimeTable() {
			return timeTable;
		}

		public void setTimeTable(String timeTable) {
			this.timeTable = timeTable;
		}

		public Filiere getFiliere() {
			return filiere;
		}

		public void setFiliere(Filiere filiere) {
			this.filiere = filiere;
		}

	/*	public List<Student> getStudents() {
			return students;
		}*/

		public void setStudents(List<Student> students) {
			this.students = students;
		}

		public List<Matiere> getMatieres() {
			return matieres;
		}

		public void setMatieres(List<Matiere> matieres) {
			this.matieres = matieres;
		}
		
		public Classe() {}
	    
}
