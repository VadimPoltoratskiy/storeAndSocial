package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.entity.CartItem;
import com.demo.entity.Order;
import com.demo.entity.Worker;
import com.demo.service.WorkerService;

@Controller
@SessionAttributes("cartItems")
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private WorkerService workerService;

	@RequestMapping(value = "/showItems", method = RequestMethod.GET)
	public String showItems(ModelMap model) {

		List<CartItem> items = (List<CartItem>) model.get("cartItems");

		if (items == null) {
			items = new ArrayList<>();
		}

		model.put("cartItems", items);

		return "cart";
	}

	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public String addItem(@RequestParam("workerId") int id, ModelMap model) {

		List<CartItem> items = (List<CartItem>) model.get("cartItems");

		if (items == null) {
			items = new ArrayList<>();
		}		 
		
		items.add(new CartItem(workerService.getWorkerById(id)));
		model.put("cartItems", items);

		return "redirect:/cart/showItems";

	}

	@RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
	public String deleteItem(@RequestParam("workerId") int id, ModelMap model) {

		List<CartItem> items = (List<CartItem>) model.get("cartItems");

		if (items != null) {
			Worker worker = workerService.getWorkerById(id);
			items.remove(new CartItem(worker));
			model.put("cartItems", items);			 
		}

		return "redirect:/cart/showItems";

	}
	
	@RequestMapping(value = "/updateCart", method = RequestMethod.GET)
	public String updateCart(@RequestParam("id") int id, @RequestParam("quantity") int quantity, ModelMap model) {

		List<CartItem> items = (List<CartItem>) model.get("cartItems");

		for (CartItem item : items) {
			Worker worker = item.getWorker();

			if (worker.getId() == id) {
				item.setQuantity(quantity);
			}
		}

		model.put("cartItem", items);
		return "redirect:/cart/showItems";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(ModelMap model) {
		
		/*Worker worker = workerService.getWorkerById(id);
		model.addAttribute("the_worker", worker);
		
		return "worker-form";*/
		
		List<CartItem> items = (List<CartItem>) model.get("cartItems");
		
		Order order = new Order();
		
		for (CartItem item : items) {
			int id = item.getWorker().getId();
			int qnt = item.getQuantity();
			
			order.addItem(id, qnt);			
		}
		
		model.put("order", order);
		
		return "order";
	}	
	
	 
	
	

}
