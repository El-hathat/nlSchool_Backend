package com.SchoolWebSite.Models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Admin{
	@Id
	private String email;
	@Column
	private String fullName;
	@Column
	private String address;
	@Column
	private String tel;
	@Column
	private String password;
	@Column
	private Date birthDate;
	@Column
	private String academy;
	@Column
	private String DP;
	@Column
String roleInAdministration;
	
	@OneToMany(mappedBy = "admin")
    private List<Bill> bills;
	
	 @ManyToOne
	    @JoinColumn(name = "school")
	    private School school;
}
