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
public class Examen {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long examID;

	    private String examNum;
	    private Date examDate;
	    private String examFile;

	    @OneToMany(mappedBy = "exam") 
	    private List<ExamNote> examNotes;
	    
	    @ManyToOne
	    @JoinColumn(name = "matiere")
	    private Matiere matiere;

		public Long getExamID() {
			return examID;
		}

		public void setExamID(Long examID) {
			this.examID = examID;
		}

		public String getExamNum() {
			return examNum;
		}

		public void setExamNum(String examNum) {
			this.examNum = examNum;
		}

		public Date getExamDate() {
			return examDate;
		}

		public void setExamDate(Date examDate) {
			this.examDate = examDate;
		}

		public String getExamFile() {
			return examFile;
		}

		public void setExamFile(String examFile) {
			this.examFile = examFile;
		}

		public List<ExamNote> getExamNotes() {
			return examNotes;
		}

		public void setExamNotes(List<ExamNote> examNotes) {
			this.examNotes = examNotes;
		}

		public Matiere getMatiere() {
			return matiere;
		}

		public void setMatiere(Matiere matiere) {
			this.matiere = matiere;
		}

		public Examen(Long examID, String examNum, Date examDate, String examFile, List<ExamNote> examNotes,
				Matiere matiere) {
			super();
			this.examID = examID;
			this.examNum = examNum;
			this.examDate = examDate;
			this.examFile = examFile;
			this.examNotes = examNotes;
			this.matiere = matiere;
		}
	    
	    public Examen() {}
}
