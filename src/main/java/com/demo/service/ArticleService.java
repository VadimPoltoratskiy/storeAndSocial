package com.demo.service;

import java.util.List;

import com.demo.entity.Article;

public interface ArticleService {

	public List<Article> getArticlesByProfileId(Long id);

	public Article getArticleById(Long id);
	
	
}
