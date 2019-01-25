package com.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entity.User;
import com.demo.entity.Worker;
import com.demo.service.CustomUserDetailsService;
import com.demo.service.FileService;
import com.demo.service.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerController {

	@Autowired
	private WorkerService workerService;

	@Autowired
	private CustomUserDetailsService userService;
	

	/*@Value("${photo.upload.directory}")
	private String photoUploadDirectory;
	
	@Autowired
	private FileService fileService;*/
	

	@RequestMapping(value = "/list/dep/{depId}", method = RequestMethod.GET)
	public String listWorkersDep(@PathVariable(value = "depId") int depId, Model model) {

		List<Worker> workers = workerService.getWorkersByDepId(depId);
		model.addAttribute("workers_list", workers);

		return "home_dep";
	}

	@RequestMapping(value = "/showJson", method = RequestMethod.GET)
	@ResponseBody
	public List<Worker> showJson() {

		return workerService.getWorkers();
	}

	@RequestMapping(value = "/resultJson", method = RequestMethod.GET)
	public String resultJson() {
		return "result-json";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "main";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {

		User user = new User();
		model.addAttribute("the_user", user);

		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("the_user")  User user, 
			BindingResult result) {

		if (!result.hasErrors()) {
			userService.register(user);			
			return "redirect:/worker/list";
		}
		
		return "register";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String page403() {
		return "403";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listWorkers(Model model) {

		List<Worker> workers = workerService.getWorkers();
		model.addAttribute("workers_list", workers);
		
		return "home";
		
	}
	
	/*@RequestMapping(value = "/upload-profile-photo", method = RequestMethod.POST) 
	public String photoUploads(Model model, @RequestParam("file") MultipartFile file) {		
		
		try {
			fileService.saveImageFile(file, photoUploadDirectory, "photo", "profile");
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return "redirect:/worker/list";
	}*/

	@RequestMapping(value = "/showFormAdd", method = RequestMethod.GET)
	public String showFormAdd(Model model) {

		Worker worker = new Worker();
		model.addAttribute("the_worker", worker);

		return "worker-form";
	}

	@RequestMapping(value = "/addWorker", method = RequestMethod.POST)
	public String addWorker(@ModelAttribute("the_worker") Worker worker) {

		workerService.addWorker(worker);
		return "redirect:/worker/list";
	}

	@RequestMapping(value = "/showFormUpdate", method = RequestMethod.GET)
	public String showFormUpdate(@RequestParam("workerId") int id, Model model) {

		Worker worker = workerService.getWorkerById(id);
		model.addAttribute("the_worker", worker);

		return "worker-form";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("workerId") int id) {

		workerService.delete(id);
		return "redirect:/worker/list";
	}
	
	

}
