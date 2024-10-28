package com.SchoolWebSite.Controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SchoolWebSite.Models.Comment;
import com.SchoolWebSite.Models.Devoir;
import com.SchoolWebSite.Services.CommentService;
import com.SchoolWebSite.Services.CoursService;
import com.SchoolWebSite.Services.DevoirService;
import com.SchoolWebSite.Services.StudentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService serv;
	@Autowired
	StudentService serv1;
	@Autowired
	DevoirService dvs;
	@Autowired
	CoursService crs;
	

	
	@GetMapping("/addLike/{cmt}/{count}")
	public Comment addComments(@PathVariable Long cmt,@PathVariable int count) {
		return serv.updateCmt(cmt,count);
	}
	
	@PostMapping("/addCoursComment/{cours}/{email}/{parent}")
	public Comment addComment2Cours(@PathVariable Long cours,@PathVariable String email,@PathVariable String parent,@RequestBody Comment cmt) {
		System.out.println(cmt.getMessage());
		cmt.setStudent(serv1.getByEmail(email));
		if(!parent.equals("null")) {cmt.setParentComment(serv.getCommentById(Long.parseLong(parent+"")));}
		cmt.setCours(crs.getCours(cours).get());
		return serv.addComment(cmt);
	}
	
	
	@PostMapping("/add/{dv}/{email}/{parent}")
	public Comment addComments(@PathVariable Long dv,@PathVariable String email,@PathVariable String parent,@RequestBody Comment cmt) {
		System.out.println(email);
		cmt.setStudent(serv1.getByEmail(email));
		if(!parent.equals("null")) {cmt.setParentComment(serv.getCommentById(Long.parseLong(parent+"")));}
		cmt.setDevoir(dvs.getDv(dv));
		return serv.addComment(cmt);
	}
	
	@DeleteMapping("/delete/{cmt}")
	public boolean delComments(@PathVariable Long cmt) {
	 serv.delComment(cmt);
	 return serv.getCommentById(cmt)==null;
	}
	
	@GetMapping("/all")
	public List<Comment> allComments() {
		return serv.getAllCmt();
	}
}
