package com.SchoolWebSite.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SchoolWebSite.Models.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
Comment getCommentByCommentID(Long id);
}
