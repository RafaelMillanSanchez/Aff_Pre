package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * ListOfProductsAssortmentC generated by hbm2java
 */
@Entity
@Table(name="list_of_products_assortment__c"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class ListOfProductsAssortmentC  implements java.io.Serializable {


     private Integer id;
     private String lkpProductC;
     private String lkpAssortmentC;
     private String frmBaseReferenceC;
     private String name;
     private Boolean isdeleted;
     private Date systemmodstamp;
     private Date createddate;
     private String lkpBaseReferenceC;
     private Double numQuantityC;
     private String sfid;
     private String hcLastop;
     private String hcErr;

    public ListOfProductsAssortmentC() {
    }

	
    public ListOfProductsAssortmentC(Integer id) {
        this.id = id;
    }
    public ListOfProductsAssortmentC(Integer id, String lkpProductC, String lkpAssortmentC, String frmBaseReferenceC, String name, Boolean isdeleted, Date systemmodstamp, Date createddate, String lkpBaseReferenceC, Double numQuantityC, String sfid, String hcLastop, String hcErr) {
       this.id = id;
       this.lkpProductC = lkpProductC;
       this.lkpAssortmentC = lkpAssortmentC;
       this.frmBaseReferenceC = frmBaseReferenceC;
       this.name = name;
       this.isdeleted = isdeleted;
       this.systemmodstamp = systemmodstamp;
       this.createddate = createddate;
       this.lkpBaseReferenceC = lkpBaseReferenceC;
       this.numQuantityC = numQuantityC;
       this.sfid = sfid;
       this.hcLastop = hcLastop;
       this.hcErr = hcErr;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="lkp_product__c", length=18)
    public String getLkpProductC() {
        return this.lkpProductC;
    }
    
    public void setLkpProductC(String lkpProductC) {
        this.lkpProductC = lkpProductC;
    }

    
    @Column(name="lkp_assortment__c", length=18)
    public String getLkpAssortmentC() {
        return this.lkpAssortmentC;
    }
    
    public void setLkpAssortmentC(String lkpAssortmentC) {
        this.lkpAssortmentC = lkpAssortmentC;
    }

    
    @Column(name="frm_base_reference__c", length=1300)
    public String getFrmBaseReferenceC() {
        return this.frmBaseReferenceC;
    }
    
    public void setFrmBaseReferenceC(String frmBaseReferenceC) {
        this.frmBaseReferenceC = frmBaseReferenceC;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createddate", length=29)
    public Date getCreateddate() {
        return this.createddate;
    }
    
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    
    @Column(name="lkp_base_reference__c", length=18)
    public String getLkpBaseReferenceC() {
        return this.lkpBaseReferenceC;
    }
    
    public void setLkpBaseReferenceC(String lkpBaseReferenceC) {
        this.lkpBaseReferenceC = lkpBaseReferenceC;
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


