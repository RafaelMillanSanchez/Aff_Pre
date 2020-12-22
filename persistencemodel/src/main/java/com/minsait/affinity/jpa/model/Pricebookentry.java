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
 * Pricebookentry generated by hbm2java
 */
@Entity
@Table(name="pricebookentry"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class Pricebookentry  implements java.io.Serializable {


     private Integer id;
     private Double unitprice;
     private Boolean usestandardprice;
     private String name;
     private Boolean isarchived;
     private Boolean isdeleted;
     private Boolean isactive;
     private Date systemmodstamp;
     private Date createddate;
     private String pricebook2id;
     private String productcode;
     private String product2id;
     private String sfid;
     private String hcLastop;
     private String hcErr;

    public Pricebookentry() {
    }

	
    public Pricebookentry(Integer id) {
        this.id = id;
    }
    public Pricebookentry(Integer id, Double unitprice, Boolean usestandardprice, String name, Boolean isarchived, Boolean isdeleted, Boolean isactive, Date systemmodstamp, Date createddate, String pricebook2id, String productcode, String product2id, String sfid, String hcLastop, String hcErr) {
       this.id = id;
       this.unitprice = unitprice;
       this.usestandardprice = usestandardprice;
       this.name = name;
       this.isarchived = isarchived;
       this.isdeleted = isdeleted;
       this.isactive = isactive;
       this.systemmodstamp = systemmodstamp;
       this.createddate = createddate;
       this.pricebook2id = pricebook2id;
       this.productcode = productcode;
       this.product2id = product2id;
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

    
    @Column(name="unitprice", precision=17, scale=17)
    public Double getUnitprice() {
        return this.unitprice;
    }
    
    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }

    
    @Column(name="usestandardprice")
    public Boolean getUsestandardprice() {
        return this.usestandardprice;
    }
    
    public void setUsestandardprice(Boolean usestandardprice) {
        this.usestandardprice = usestandardprice;
    }

    
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="isarchived")
    public Boolean getIsarchived() {
        return this.isarchived;
    }
    
    public void setIsarchived(Boolean isarchived) {
        this.isarchived = isarchived;
    }

    
    @Column(name="isdeleted")
    public Boolean getIsdeleted() {
        return this.isdeleted;
    }
    
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    
    @Column(name="isactive")
    public Boolean getIsactive() {
        return this.isactive;
    }
    
    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
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

    
    @Column(name="pricebook2id", length=18)
    public String getPricebook2id() {
        return this.pricebook2id;
    }
    
    public void setPricebook2id(String pricebook2id) {
        this.pricebook2id = pricebook2id;
    }

    
    @Column(name="productcode")
    public String getProductcode() {
        return this.productcode;
    }
    
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    
    @Column(name="product2id", length=18)
    public String getProduct2id() {
        return this.product2id;
    }
    
    public void setProduct2id(String product2id) {
        this.product2id = product2id;
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

