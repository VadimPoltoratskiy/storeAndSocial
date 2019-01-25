package com.demo.service;

import java.util.List;
import com.demo.entity.Comment;

public interface CommentService {

	public void add(Comment comment);
	
	public Comment getCommentById(Long id);

	public List<Comment> getComments();
	
	public List<Comment> getCommentsByArticleId(Long id);
	 
}


