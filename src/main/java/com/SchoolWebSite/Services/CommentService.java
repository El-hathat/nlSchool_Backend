package com.SchoolWebSite.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SchoolWebSite.Models.Comment;
import com.SchoolWebSite.Repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository repo;
	
	public Comment addComment(Comment cmt) {
		return repo.save(cmt);
	}
	
	public List<Comment> getAllCmt(){
		return repo.findAll();
	}
	
	public Comment getCommentById(Long id){
		return repo.getCommentByCommentID(id);
	}
	
	public Comment updateCmt(Long id,int like){
		Comment cmt=repo.getCommentByCommentID(id);
		cmt.setLikeCount(like);
		return repo.save(cmt);
	}

}
