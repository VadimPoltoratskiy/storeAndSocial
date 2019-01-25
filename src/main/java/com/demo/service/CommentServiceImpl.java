package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Comment;
import com.demo.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;	
	
	@Override
	@Transactional
	public void add(Comment comment) {
		 commentRepository.save(comment);		
	}

	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.findById(id).orElseThrow(null);
	}

	@Override
	public List<Comment> getComments() {
		return (List<Comment>) commentRepository.findAll();
	}

	@Override
	public List<Comment> getCommentsByArticleId(Long id) {
		return commentRepository.findCommentsByArticleId(id);
	}

}
