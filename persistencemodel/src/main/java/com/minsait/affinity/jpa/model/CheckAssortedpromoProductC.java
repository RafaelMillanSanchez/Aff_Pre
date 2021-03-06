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
 * CheckAssortedpromoProductC generated by hbm2java
 */
@Entity
@Table(name="check_assortedpromo_product__c"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class CheckAssortedpromoProductC  implements java.io.Serializable {


     private Integer id;
     private String accountAssproC;
     private String recordtypeid;
     private String accountPromoC;
     private String name;
     private Boolean isdeleted;
     private Date systemmodstamp;
     private Date createddate;
     private Date fechaFinC;
     private Date fechaInicioC;
     private String sfid;
     private String hcLastop;
     private String hcErr;

    public CheckAssortedpromoProductC() {
    }

	
    public CheckAssortedpromoProductC(Integer id) {
        this.id = id;
    }
    public CheckAssortedpromoProductC(Integer id, String accountAssproC, String recordtypeid, String accountPromoC, String name, Boolean isdeleted, Date systemmodstamp, Date createddate, Date fechaFinC, Date fechaInicioC, String sfid, String hcLastop, String hcErr) {
       this.id = id;
       this.accountAssproC = accountAssproC;
       this.recordtypeid = recordtypeid;
       this.accountPromoC = accountPromoC;
       this.name = name;
       this.isdeleted = isdeleted;
       this.systemmodstamp = systemmodstamp;
       this.createddate = createddate;
       this.fechaFinC = fechaFinC;
       this.fechaInicioC = fechaInicioC;
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

    
    @Column(name="account_asspro__c", length=18)
    public String getAccountAssproC() {
        return this.accountAssproC;
    }
    
    public void setAccountAssproC(String accountAssproC) {
        this.accountAssproC = accountAssproC;
    }

    
    @Column(name="recordtypeid", length=18)
    public String getRecordtypeid() {
        return this.recordtypeid;
    }
    
    public void setRecordtypeid(String recordtypeid) {
        this.recordtypeid = recordtypeid;
    }

    
    @Column(name="account_promo__c", length=18)
    public String getAccountPromoC() {
        return this.accountPromoC;
    }
    
    public void setAccountPromoC(String accountPromoC) {
        this.accountPromoC = accountPromoC;
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

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_fin__c", length=13)
    public Date getFechaFinC() {
        return this.fechaFinC;
    }
    
    public void setFechaFinC(Date fechaFinC) {
        this.fechaFinC = fechaFinC;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_inicio__c", length=13)
    public Date getFechaInicioC() {
        return this.fechaInicioC;
    }
    
    public void setFechaInicioC(Date fechaInicioC) {
        this.fechaInicioC = fechaInicioC;
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


