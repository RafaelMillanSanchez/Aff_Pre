package com.minsait.affinity.jpa.model;


import java.time.*;
import java.time.zone.*;
import java.time.Month;
import java.time.LocalDate;



public class VentasGeneral {
	
	
	private String cust_code;
	private String base_ref_code;
	private Integer month;
	private Integer years;
	
	
	public VentasGeneral (String customercode, String basereference)  {
		this.cust_code = customercode;
		this.base_ref_code = basereference.substring(14);
	}
	
	public String getcust_code() {
		return cust_code;
	}
	
	public void setcust_code(String cust_code) {
		this.cust_code = cust_code;
	}
		
	public String getbase_ref_code() {
		return base_ref_code;
	}
	
	public void setbase_ref_code(String base_ref) {
		this.base_ref_code = base_ref;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}
}
