package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> { 

	@Query("select c from Comment c where c.article.id = :#{#articleId}")
	List<Comment> findCommentsByArticleId(@Param("articleId") Long articleId);
}


/*
package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entity.Message;



public interface MessageRepository extends CrudRepository<Message, Long> {

	@Query("select m from Message m where m.profile.id = :#{#profileId} and recipientDeleted = 0")
	List<Message> findMessagesByProfileId(@Param("profileId") Long profileId);
	
	@Query("select m from Message m where m.fromProfile.id = :#{#profileId}")
	List<Message> findSentMessagesByProfileId(@Param("profileId") Long profileId);
	
	@Modifying
	@Query("UPDATE Message m SET m.recipientDeleted = 1 WHERE m.id = :#{#messageId}")
	void deleteMessage(@Param("messageId") Long id);

	@Modifying
	@Query("UPDATE Message m SET m.opened = 1 WHERE m.id = :#{#messageId}")
	void openMessage(@Param("messageId") Long id);
		
	
	@Query("select count(*) from Message m where m.profile.id = :#{#profileId} and recipientDeleted = 0 and opened = 0")
	int getUnreadCount(@Param("profileId") Long profileId);
	
	
}




*/