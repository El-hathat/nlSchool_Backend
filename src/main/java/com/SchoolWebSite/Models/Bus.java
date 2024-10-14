package com.SchoolWebSite.Models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table
public class Bus {
@Id
String matricule;
@Column
String BusZone;

@OneToOne
@JoinColumn(name = "employee_id")
private Employe driver;

@OneToMany(mappedBy = "schoolBus")
private List<Student> students;
}
