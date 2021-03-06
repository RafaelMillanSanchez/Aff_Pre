package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * LightStockC generated by hbm2java
 */
@Entity
@Table(name="light_stock__c"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class LightStockC  implements java.io.Serializable {


     private Integer id;
     private String lkpItemC;
     private String pckWarehouseC;
     private String name;
     private Boolean isdeleted;
     private Date systemmodstamp;
     private Double numQuantitySfUrgentC;
     private Double numQuantitySfC;
     private Date createddate;
     private Double frmlRealStockC;
     private Double numQuantityC;
     private String sfid;
     private String hcLastop;
     private String hcErr;

    public LightStockC() {
    }

	
    public LightStockC(Integer id) {
        this.id = id;
    }
    public LightStockC(Integer id, String lkpItemC, String pckWarehouseC, String name, Boolean isdeleted, Date systemmodstamp, Double numQuantitySfUrgentC, Double numQuantitySfC, Date createddate, Double frmlRealStockC, Double numQuantityC, String sfid, String hcLastop, String hcErr) {
       this.id = id;
       this.lkpItemC = lkpItemC;
       this.pckWarehouseC = pckWarehouseC;
       this.name = name;
       this.isdeleted = isdeleted;
       this.systemmodstamp = systemmodstamp;
       this.numQuantitySfUrgentC = numQuantitySfUrgentC;
       this.numQuantitySfC = numQuantitySfC;
       this.createddate = createddate;
       this.frmlRealStockC = frmlRealStockC;
       this.numQuantityC = numQuantityC;
       this.sfid = sfid;
       this.hcLastop = hcLastop;
       this.hcErr = hcErr;
    }
   
     @Id 

     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="lkp_item__c", length=18)
    public String getLkpItemC() {
        return this.lkpItemC;
    }
    
    public void setLkpItemC(String lkpItemC) {
        this.lkpItemC = lkpItemC;
    }

    
    @Column(name="pck_warehouse__c")
    public String getPckWarehouseC() {
        return this.pckWarehouseC;
    }
    
    public void setPckWarehouseC(String pckWarehouseC) {
        this.pckWarehouseC = pckWarehouseC;
    }

    
    @Column(name="name", length=80)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="isdeleted")
    public Boolean getIsdeleted() {
        return this.isdeleted;
    }
    
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="systemmodstamp", length=29)
    public Date getSystemmodstamp() {
        return this.systemmodstamp;
    }
    
    public void setSystemmodstamp(Date systemmodstamp) {
        this.systemmodstamp = systemmodstamp;
    }

    
    @Column(name="num_quantity_sf_urgent__c", precision=17, scale=17)
    public Double getNumQuantitySfUrgentC() {
        return this.numQuantitySfUrgentC;
    }
    
    public void setNumQuantitySfUrgentC(Double numQuantitySfUrgentC) {
        this.numQuantitySfUrgentC = numQuantitySfUrgentC;
    }

    
    @Column(name="num_quantity_sf__c", precision=17, scale=17)
    public Double getNumQuantitySfC() {
        return this.numQuantitySfC;
    }
    
    public void setNumQuantitySfC(Double numQuantitySfC) {
        this.numQuantitySfC = numQuantitySfC;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createddate", length=29)
    public Date getCreateddate() {
        return this.createddate;
    }
    
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    
    @Column(name="frml_real_stock__c", precision=17, scale=17)
    public Double getFrmlRealStockC() {
        return this.frmlRealStockC;
    }
    
    public void setFrmlRealStockC(Double frmlRealStockC) {
        this.frmlRealStockC = frmlRealStockC;
    }

    
    @Column(name="num_quantity__c", precision=17, scale=17)
    public Double getNumQuantityC() {
        return this.numQuantityC;
    }
    
    public void setNumQuantityC(Double numQuantityC) {
        this.numQuantityC = numQuantityC;
    }

    
    @Column(name="sfid", unique=true, length=18)
    public String getSfid() {
        return this.sfid;
    }
    
    public void setSfid(String sfid) {
        this.sfid = sfid;
    }

    
    @Column(name="_hc_lastop", length=32)
    public String getHcLastop() {
        return this.hcLastop;
    }
    
    public void setHcLastop(String hcLastop) {
        this.hcLastop = hcLastop;
    }

    
    @Column(name="_hc_err")
    public String getHcErr() {
        return this.hcErr;
    }
    
    public void setHcErr(String hcErr) {
        this.hcErr = hcErr;
    }




}


