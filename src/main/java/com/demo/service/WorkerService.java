package com.demo.service;

import java.util.List;
import com.demo.entity.Worker;

public interface WorkerService {
	
	public List<Worker> getWorkers();
	
	public void addWorker(Worker worker);

	public Worker getWorkerById(int id);

	public void delete(int id);
	
	public List<Worker> getWorkersByName(String name);
	
	public List<Worker> getWorkersByDepId(int depId);

	

}
