package com.demo.entity;

public class CartItem {

	private Worker worker;
	private int quantity;
	
	public CartItem(Worker worker, int quantity) {
		this.worker = worker;
		this.quantity = quantity;
	}
	
	public CartItem(Worker worker) {
		this(worker, 1);
	}
	
	public CartItem() {
		
	}
	
	public void addItem(Worker worker) {
		this.worker = worker;
	}
	
	public double getTotalPrice() {
		return worker.getSalary() * quantity;
	}
	
	public String getQuantityIdName() {
		return "quantity" + worker.getId();
	}
	
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((worker == null) ? 0 : worker.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (worker == null) {
			if (other.worker != null)
				return false;
		} else if (!worker.equals(other.worker))
			return false;
		return true;
	}
	
	
	
	
}
