package com.minsait.affinity.jpa.model;

import java.util.Date;

public class OrderSummary {
	
	private String orderId;
	private String orderNum;
	private Date deliveryDate;
	private String shiptoName;
	private String userName;
	private Double totalEuros;
	private Double totalKG;
	private String address;
	private String state;
	private Double totalDiscount;
	private Boolean urgent;
	private Boolean collect;
	private Double conditionTypeValueC;
	
	public OrderSummary (String orderId, String orderNum, Date deliveryDate, String shiptoName, 
						String userName, Double totalEuros, Double totalKG, String address, String state, 
						Double totalDiscount, Boolean urgent, Boolean collect, Double conditionTypeValueC) {
		
		this.setOrderId(orderId);
		this.setOrderNum(orderNum);
		this.setDeliveryDate(deliveryDate);
		this.setShiptoName(shiptoName);
		this.setUserName(userName);
		this.setTotalEuros(totalEuros);
		this.setTotalKG(totalKG);
		this.setAddress(address);
		this.setState(state);
		this.setTotalDiscount(totalDiscount);
		this.setUrgent(urgent);
		this.setCollect(collect);
		this.setConditionTypeValueC(conditionTypeValueC);
	}
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getShiptoName() {
		return shiptoName;
	}
	public void setShiptoName(String shiptoName) {
		this.shiptoName = shiptoName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Double getTotalEuros() {
		return totalEuros;
	}
	public void setTotalEuros(Double totalEuros) {
		this.totalEuros = totalEuros;
	}
	public Double getTotalKG() {
		return totalKG;
	}
	public void setTotalKG(Double totalKG) {
		this.totalKG = totalKG;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	public Double getTotalDiscount() {
		return totalDiscount;
	}


	public void setTotalDiscount(Double totalDiscount2) {
		this.totalDiscount = totalDiscount2;
	}


	public Boolean getUrgent() {
		return urgent;
	}


	public void setUrgent(Boolean urgent) {
		this.urgent = urgent;
	}


	public Boolean getCollect() {
		return collect;
	}


	public void setCollect(Boolean collect) {
		this.collect = collect;
	}


	public Double getConditionTypeValueC() {
		return conditionTypeValueC;
	}


	public void setConditionTypeValueC(Double conditionTypeValueC) {
		this.conditionTypeValueC = conditionTypeValueC;
	}

}
