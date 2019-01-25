package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Article;
import com.demo.repository.ArticleRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	
	@Override
	public List<Article> getArticlesByProfileId(Long id) {		
		return articleRepository.findArticlesByProfileId(id);
	}


	@Override
	public Article getArticleById(Long id) {
		
		return articleRepository.findById(id).orElseThrow(null);
	}

}
