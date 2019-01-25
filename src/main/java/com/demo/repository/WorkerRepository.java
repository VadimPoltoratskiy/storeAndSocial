package com.demo.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.entity.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Integer> {

	public List<Worker> findByName(String name);
	
	@Query("select w from Worker w where w.department.dep_id = :#{#depId}")
	public List<Worker> findUsersByDepId(@Param("depId") int depId);
}
