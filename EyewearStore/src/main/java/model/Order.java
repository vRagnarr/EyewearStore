package model;

import java.sql.Date;

public class Order extends Product{
	private int orderId;
	private int userId;
	private int quantity;
	private String date;
	private String stato;
	private double valore;
	
	public Order() {
		
	}

	public Order(int orderId, int userId, int quantity, String date, String stato, double valore) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.quantity = quantity;
		this.date = date;
		this.stato = stato;
		this.valore = valore;
	}
	
	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public double getValore() {
		return valore;
	}
	
	public void setValore(double valore) {
		this.valore = valore;
	}
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
