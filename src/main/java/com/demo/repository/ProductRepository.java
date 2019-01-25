package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	@Query("select p from Product p where p.category = :#{#category_id}")
	List<Product> getProductsByCategoryId(@Param("category_id") int id);

}


