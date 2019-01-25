package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Article;
import com.demo.entity.Message;
import com.demo.entity.Profile;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long>, JpaRepository<Article, Long> {

	@Query("select a from Article a where a.authorProfile.id = :#{#authorProfileId}")
	List<Article> findArticlesByProfileId(@Param("authorProfileId") Long authorProfileId);
	
	@Query("select a from Article a where a.authorProfile.id = :#{#authorProfileId}")
	Article getArticleByProfileId(@Param("authorProfileId") Long authorProfileId);
	
}
