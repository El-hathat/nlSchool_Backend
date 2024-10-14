package com.nlschool.nlschoolProject.Models;

import java.util.Date;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends Person{
	private Date enterSchoolDate;
	private Date exitSchoolDate;
	private Double baseSalary;
	

}
