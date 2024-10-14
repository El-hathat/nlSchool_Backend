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
public class Notification {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String message;
	    private Date messageDate;
	    private String subject;

	    @ManyToOne
	    @JoinColumn(name = "student")
	    private Student student;
}
