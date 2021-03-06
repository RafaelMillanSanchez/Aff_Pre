package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ResumenVentasYear generated by hbm2java
 */
@Entity
@Table(name="resumen_ventas_year"
    ,schema="salesforce"
)
public class ResumenVentasYear  implements java.io.Serializable {


     private long id;
     private String years;
     private String customer;
     private String brand;
     private Double ventas;
     private Double ventasKg;
     private String brandName;
     private Boolean fullYear;
     private String subbrandName;

    public ResumenVentasYear() {
    }

	
    public ResumenVentasYear(long id) {
        this.id = id;
    }
    public ResumenVentasYear(long id, String years, String customer, String brand, Double ventas, Double ventasKg, String brandName, Boolean fullYear, String subbrandName) {
       this.id = id;
       this.years = years;
       this.customer = customer;
       this.brand = brand;
       this.ventas = ventas;
       this.ventasKg = ventasKg;
       this.brandName = brandName;
       this.fullYear = fullYear;
       this.subbrandName = subbrandName;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    
    @Column(name="years")
    public String getYears() {
        return this.years;
    }
    
    public void setYears(String years) {
        this.years = years;
    }

    
    @Column(name="customer")
    public String getCustomer() {
        return this.customer;
    }
    
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    
    @Column(name="brand")
    public String getBrand() {
        return this.brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }

    
    @Column(name="ventas", precision=17, scale=17)
    public Double getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Double ventas) {
        this.ventas = ventas;
    }

    
    @Column(name="ventas_kg", precision=17, scale=17)
    public Double getVentasKg() {
        return this.ventasKg;
    }
    
    public void setVentasKg(Double ventasKg) {
        this.ventasKg = ventasKg;
    }

    
    @Column(name="brand_name")
    public String getBrandName() {
        return this.brandName;
    }
    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    
    @Column(name="full_year")
    public Boolean getFullYear() {
        return this.fullYear;
    }
    
    public void setFullYear(Boolean fullYear) {
        this.fullYear = fullYear;
    }

    
    @Column(name="subbrand_name")
    public String getSubbrandName() {
        return this.subbrandName;
    }
    
    public void setSubbrandName(String subbrandName) {
        this.subbrandName = subbrandName;
    }




}


