package com.SchoolWebSite.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Comment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentID;
	
	private String message;
	private Date creationDate;
	private int likeCount;
	private boolean sousComment;
	
	

	public Date getCreationDate() {
		return creationDate;
	}

	public boolean isSousComment() {
		return sousComment;
	}

	
	
	

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setSousComment(boolean sousComment) {
		this.sousComment = sousComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	public List<Comment> getReplies() {
		return replies;
	}

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}
	
	
	
	 public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}



	@ManyToOne
	    @JoinColumn(name = "cours")
	    private Cours cours;
	@ManyToOne
    @JoinColumn(name = "devoir")
    private Devoir devoir;
	 
	 @ManyToOne
	    @JoinColumn(name = "student")
	    private Student student;
	 
	 @ManyToOne
	    @JoinColumn(name = "teacher")
	    private Teacher teacher;
	 
	 @ManyToOne
	    @JoinColumn(name = "parent_id")
	    private Comment parentComment;

	    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Comment> replies;

	public Comment(Long commentID, String message, int likeCount, Cours cours) {
		
		this.commentID = commentID;
		this.message = message;
		this.likeCount = likeCount;
		this.cours = cours;
	}
	 
	 public Comment() {}

	public Long getCommentID() {
		return commentID;
	}

	public void setCommentID(Long commentID) {
		this.commentID = commentID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}


	public void setCours(Cours cours) {
		this.cours = cours;
	}
	 
	 
	 
	

}
