package com.SchoolWebSite.Models;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table
public class Salary {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
int salaryId;
@Column
Double salary;
@Column
int month;
@Column
Date date;
@Column
int absenceInMonth;

@ManyToOne
@JoinColumn(name = "employee_id")
private Employe employee;

@ManyToOne
@JoinColumn(name = "teacher_id")
private Teacher teacher;

}
