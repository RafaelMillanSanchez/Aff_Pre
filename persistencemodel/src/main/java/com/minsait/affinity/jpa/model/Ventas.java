package com.minsait.affinity.jpa.model;


import java.time.*;
import java.time.zone.*;
import java.time.Month;
import java.time.LocalDate;



public class Ventas implements Comparable<Ventas> {
	
	
	private String cust_code;
	private Long num_ventas;
	private String base_ref_code;
	private Integer month;
	private Integer years;
	private Integer st_month;
	private Integer	st_year;
	private Integer fs_month;
	private Integer fs_year;
	private Integer mes1;
	private Integer mes2;
	private Integer mes3;
	private Integer mes4;
	private Integer mes5;
	
	public Ventas (String customercode, Long ventas, String basereference, Integer month, Integer years)  {
		
		LocalDate today = LocalDate.now();
		Integer month_num = today.getMonth().getValue();
		Integer year_num = today.getYear();
		Integer month_num_ult;
		if(month_num >= 5 ) {
			month_num_ult = month_num - 4;
			if(month <= month_num && month >= month_num_ult && year_num.equals(years)) {
				this.cust_code = customercode;
				this.num_ventas = ventas ;
				this.base_ref_code = basereference.substring(14);
				this.month = month;
				this.years = years;	
				this.st_month = month_num;
				this.fs_month = month_num_ult;
				this.st_year = years;
				this.fs_year = years;
				
				if(month.equals(month_num)) {
					//mes 1
					mes1 = num_ventas.intValue(); 
				}else if(month_num.equals(month +1)) {
					//mes 2
					mes2 = num_ventas.intValue(); 
				}else if(month_num.equals(month +2)) {
					//mes 3
					mes3 = num_ventas.intValue(); 
				}else if(month_num.equals(month +3)) {
					//mes 4
					mes4 = num_ventas.intValue(); 
				}else {
					//mes 5
					mes5 = num_ventas.intValue(); 
				}
			}
		}else {
			month_num_ult = 12 - (5 -month_num);
			
			if((month <= month_num) && years.equals(year_num)){
				this.cust_code = customercode;
				this.num_ventas = ventas ;
				this.base_ref_code = basereference.substring(14);
				this.month = month;
				this.years = years;
				this.st_month = month_num;
				this.fs_month = month_num_ult;
				this.st_year = years;
				this.fs_year = years-1;
				if(month.equals(month_num)) {
					//mes 1
					mes1 = num_ventas.intValue(); 
				}else if(month_num.equals(month +1)) {
					//mes 2
					mes2 = num_ventas.intValue(); 
				}else if(month_num.equals(month +2)) {
					//mes 3
					mes3 = num_ventas.intValue(); 
				}else if(month_num.equals(month +3)) {
					// mes 4
					mes4 = num_ventas.intValue(); 
				}
				
			}else if ((month > month_num_ult && years.equals(year_num -1))) {
				this.cust_code = customercode;
				this.num_ventas = ventas ;
				this.base_ref_code = basereference.substring(14);
				this.month = month;
				this.years = years;
				this.st_month = month_num;
				this.fs_month = month_num_ult;
				this.st_year = years;
				this.fs_year = years-1;
				if(month.equals(month_num_ult + 1)) {
					//mes 5
					mes5 = num_ventas.intValue(); 
				}else if(month.equals(month_num_ult + 2)) {
					//mes 4
					mes4 = num_ventas.intValue(); 
				}else if(month.equals(month_num_ult + 3)) {
					//mes 3
					mes3 = num_ventas.intValue(); 
				}else if(month.equals(month_num_ult + 4)) {
					// mes 2
					mes2 = num_ventas.intValue(); 
				}
			}
		}
		
			
	}
	
	public String getcust_code() {
		return cust_code;
	}
	
	public void setcust_code(String cust_code) {
		this.cust_code = cust_code;
	}
	
	public Long getnum_ventas() {
		return num_ventas;
	}
	
	public void setnum_ventas(Long ventas) {
		this.num_ventas = ventas;
	}
	
	public String getbase_ref_code() {
		return base_ref_code;
	}
	
	public void setbase_ref_code(String base_ref) {
		this.base_ref_code = base_ref;
	}
	
	public Integer getmonth() {
		return month;
	}
	
	public void setmonth(Integer month) {
		this.month = month;
	}
	
	public Integer getyear() {
		return years;
	}
	
	public void setyear(Integer year) {
		this.years = years;
	}
	
	public Integer gettst_year() {
		return st_year;
	}
	
	public void setst_year(Integer year) {
		this.st_year = years;
	}
	
	
	public Integer gettst_month() {
		return st_month;
	}
	
	public void setst_month(Integer month) {
		this.st_month = month;
	}
	
	
	public Integer gettfs_year() {
		return fs_year;
	}
	
	public void setfs_year(Integer year) {
		this.fs_year = years;
	}
	
	public Integer gettfs_month() {
		return fs_month;
	}
	
	public void setfs_month(Integer month) {
		this.fs_month = month;
	}

	@Override
	public int compareTo(Ventas V) {
		if (getmonth() == null || V.getmonth() == null) {
		      return 0;
		    }
		    return getmonth().compareTo(V.getmonth());
	}
	public Integer getmes1() {
		return mes1;
	}

	public void setmes1(Integer mes) {
		this.mes1 = mes;
	}
	
	public Integer getmes2() {
		return mes2;
	}

	public void setmes2(Integer mes) {
		this.mes2 = mes;
	}
	public Integer getmes3() {
		return mes3;
	}

	public void setmes3(Integer mes) {
		this.mes3 = mes;
	}
	
	public Integer getmes4() {
		return mes4;
	}

	public void setmes4(Integer mes) {
		this.mes4 = mes;
	}
	
	public Integer getmes5() {
		return mes5;
	}

	public void setmes5(Integer mes) {
		this.mes5 = mes;
	}

}
