package model;

import java.sql.Date;

public class Order extends Product{
	private int orderId;
	private String userId;
	private int quantity;
	private Date date;
	
	public Order() {
		
	}

	public Order(int orderId, String userId, int quantity, Date date) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.quantity = quantity;
		this.date = date;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
