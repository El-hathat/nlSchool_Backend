package com.SchoolWebSite.Models;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class ExamNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date noteDate;
    private Double note;
    private String remarque;
    @Enumerated(EnumType.STRING)
    @Column(name="Semestre")
    private Semestre semestre;

    @ManyToOne
    @JoinColumn(name = "exam")
    private Examen exam;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getNoteDate() {
		return noteDate;
	}

	public void setNoteDate(Date noteDate) {
		this.noteDate = noteDate;
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

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public Examen getExam() {
		return exam;
	}

	public void setExam(Examen exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ExamNote(Long id, Date noteDate, Double note, String remarque, Semestre semestre, Examen exam,
			Student student) {
		super();
		this.id = id;
		this.noteDate = noteDate;
		this.note = note;
		this.remarque = remarque;
		this.semestre = semestre;
		this.exam = exam;
		this.student = student;
	}
    
    public ExamNote() {}

    // Getters and Setters
}
