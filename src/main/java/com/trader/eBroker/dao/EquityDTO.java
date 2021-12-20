package com.trader.eBroker.dao;

public class EquityDTO {
	
	private String name;
	private double perStockPrice;
	private int quantity;
	private String orderType;
    public EquityDTO() {
		
	}
	
	public EquityDTO(String name, double perStockPrice, int quantity, String orderType) {
		super();
		this.name = name;
		this.perStockPrice = perStockPrice;
		this.quantity = quantity;
		this.orderType = orderType;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPerStockPrice() {
		return perStockPrice;
	}
	public void setPerStockPrice(double perStockPrice) {
		this.perStockPrice = perStockPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	
	
}
