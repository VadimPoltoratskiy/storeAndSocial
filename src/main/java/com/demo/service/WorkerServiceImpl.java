package com.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Worker;
import com.demo.repository.WorkerRepository;

@Service
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerRepository workerRepository;
	
	
	@Override
	@Transactional
	public List<Worker> getWorkers() {		
		return (List<Worker>) workerRepository.findAll();
	}
	
	@Override
	@Transactional
	public void addWorker(Worker worker) {
		workerRepository.save(worker);
	}

	@Override
	@Transactional
	public Worker getWorkerById(int id) {		
		return workerRepository.findById(id).orElseThrow(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		workerRepository.deleteById(id);
		
	}
	
	@Override
	@Transactional
	public List<Worker> getWorkersByName(String name) {
		return (List<Worker>) workerRepository.findByName(name);
	}

	@Override
	@Transactional
	public List<Worker> getWorkersByDepId(int depId) {		
		return workerRepository.findUsersByDepId(depId);
	}

	


	

}
