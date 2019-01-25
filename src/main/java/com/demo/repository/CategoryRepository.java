package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Category;
import com.demo.entity.Message;
import com.demo.entity.Product;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	

}
