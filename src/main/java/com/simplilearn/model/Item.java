package com.simplilearn.model;

public class Item {
	String name;
	String price;
	int quantity;
	String subTotal;
	public Item(String name, String price, int quantity, String subTotal) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getSubTotal() {
		return subTotal;
	}

}
