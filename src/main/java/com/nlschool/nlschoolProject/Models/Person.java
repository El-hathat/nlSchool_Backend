package com.nlschool.nlschoolProject.Models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Table
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name="personFunction")
@Data
@AllArgsConstructor
public abstract class Person implements Serializable {
	@Id
	private String email;
	private String fullName;
	private String address;
	private String tel;
	private String password;
	private Date birthDate;
	private String school;
	private String academy;
	private String DP;
	

}
