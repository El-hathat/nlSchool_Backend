package com.SchoolWebSite.Models;

public class MatResponse {
String mat;
double note;
public MatResponse(String mat, double note) {
	super();
	this.mat = mat;
	this.note = note;
}
public String getMat() {
	return mat;
}
public void setMat(String mat) {
	this.mat = mat;
}
public double getNote() {
	return note;
}
public void setNote(double note) {
	this.note = note;
}
public MatResponse() {}
}
