package com.minsait.affinity.jpa.model;

import java.util.Date;

public class ObjetivoGlobal {
	
	private Double goalSum;
	private Double achivSum;
	private String channelC;
    private Date startdateC;
    private String salesentityC;
    private Date enddateC;
    private String subbrandC;
    
    
    public ObjetivoGlobal(Double goalSum, Double achivSum, String channelC, 
    		Date startdateC, String salesentityC, Date enddateC, String subbrandC) {
    	
    	this.setGoalSum(goalSum);
    	this.setAchivSum(achivSum);
    	this.setChannelC(channelC);
    	this.setStartdateC(startdateC);
    	this.setSalesentityC(salesentityC);
    	this.setEnddateC(enddateC);
    	this.setSubbrandC(subbrandC);
    }
    
    
    
    
	public Double getGoalSum() {
		return goalSum;
	}
	public void setGoalSum(Double goalSum) {
		this.goalSum = goalSum;
	}
	public Double getAchivSum() {
		return achivSum;
	}
	public void setAchivSum(Double achivSum) {
		this.achivSum = achivSum;
	}
	public String getChannelC() {
		return channelC;
	}
	public void setChannelC(String channelC) {
		this.channelC = channelC;
	}
	public Date getStartdateC() {
		return startdateC;
	}
	public void setStartdateC(Date startdateC) {
		this.startdateC = startdateC;
	}
	public String getSalesentityC() {
		return salesentityC;
	}
	public void setSalesentityC(String salesentityC) {
		this.salesentityC = salesentityC;
	}
	public Date getEnddateC() {
		return enddateC;
	}
	public void setEnddateC(Date enddateC) {
		this.enddateC = enddateC;
	}
	public String getSubbrandC() {
		return subbrandC;
	}
	public void setSubbrandC(String subbrandC) {
		this.subbrandC = subbrandC;
	}

}
