package com.kh.cart.model.vo;

import java.sql.Date;

public class Order {
	
	private int orderNo;
	private String orderPro;
	private int orderPrice;
	private int orderAmount;
	private Date orderDate;
	private String deliveryStatus;
	private String userId;
	
	public Order() {
		super();
	}

	public Order(int orderNo, String orderPro, int orderPrice, int orderAmount, Date orderDate, String deliveryStatus,
			String userId) {
		super();
		this.orderNo = orderNo;
		this.orderPro = orderPro;
		this.orderPrice = orderPrice;
		this.orderAmount = orderAmount;
		this.orderDate = orderDate;
		this.deliveryStatus = deliveryStatus;
		this.userId = userId;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderPro() {
		return orderPro;
	}

	public void setOrderPro(String orderPro) {
		this.orderPro = orderPro;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", orderPro=" + orderPro + ", orderPrice=" + orderPrice + ", orderAmount="
				+ orderAmount + ", orderDate=" + orderDate + ", deliveryStatus=" + deliveryStatus + ", userId=" + userId
				+ "]";
	}
	
	
	
	
	
	

}
