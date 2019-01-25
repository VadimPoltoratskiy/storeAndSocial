package com.demo.entity;

import java.util.LinkedHashMap;

public class Order {

	private LinkedHashMap<Integer, Integer> items = new LinkedHashMap<>();
	
	public void addItem(int id, int qnt) {
		items.put(id, qnt);
	}

	public LinkedHashMap<Integer, Integer> getItems() {
		return items;
	}

	public void setItems(LinkedHashMap<Integer, Integer> items) {
		this.items = items;
	}
	
	
	
	
}
