package com.SchoolWebSite.Models;

public class ResetPassword {
String email;
String oldPassword;
String newPassword;

public ResetPassword() {}

public ResetPassword(String email, String oldPassword, String newPassword) {
	super();
	this.email = email;
	this.oldPassword = oldPassword;
	this.newPassword = newPassword;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getOldPassword() {
	return oldPassword;
}

public void setOldPassword(String oldPassword) {
	this.oldPassword = oldPassword;
}

public String getNewPassword() {
	return newPassword;
}

public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
}



}
