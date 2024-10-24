package com.SchoolWebSite.Models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
@Entity
@Table
public class Notification {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String message;
	    private Date messageDate;
	    private String subject;
	    private boolean reading;
	    
	    @ManyToOne
	    @JoinColumn(name = "student")
	    private Student student;
	     
	    
	    

		


public Notification(Long id, String message, Date messageDate, String subject,boolean reading, Student student) {
			super();
			this.id = id;
			this.message = message;
			this.messageDate = messageDate;
			this.subject = subject;
			this.reading=reading;
			this.student = student;
		}
public Notification() {}
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Date getMessageDate() {
			return messageDate;
		}

		public void setMessageDate(Date messageDate) {
			this.messageDate = messageDate;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		

		public void setStudent(Student student) {
			this.student = student;
		}
		
		 @PrePersist
		    protected void onCreate() {
		        this.messageDate = new Date();
		    }
		
		public boolean isreading() {
			return reading;
		}
		public void setReading(boolean reading) {
			this.reading = reading;
		}
	    
	    
}
