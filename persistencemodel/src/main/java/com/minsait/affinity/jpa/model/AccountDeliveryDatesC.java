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
 * AccountDeliveryDatesC generated by hbm2java
 */
@Entity
@Table(name="account_delivery_dates__c"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class AccountDeliveryDatesC  implements java.io.Serializable {


     private Integer id;
     private String lkpAccountC;
     private String name;
     private Date lastmodifieddate;
     private Boolean isdeleted;
     private Date systemmodstamp;
     private Date datIddpC;
     private Date createddate;
     private Date datUrgentDateC;
     private String sfid;
     private String hcLastop;
     private String hcErr;

    public AccountDeliveryDatesC() {
    }

	
    public AccountDeliveryDatesC(Integer id) {
        this.id = id;
    }
    public AccountDeliveryDatesC(Integer id, String lkpAccountC, String name, Date lastmodifieddate, Boolean isdeleted, Date systemmodstamp, Date datIddpC, Date createddate, Date datUrgentDateC, String sfid, String hcLastop, String hcErr) {
       this.id = id;
       this.lkpAccountC = lkpAccountC;
       this.name = name;
       this.lastmodifieddate = lastmodifieddate;
       this.isdeleted = isdeleted;
       this.systemmodstamp = systemmodstamp;
       this.datIddpC = datIddpC;
       this.createddate = createddate;
       this.datUrgentDateC = datUrgentDateC;
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

    
    @Column(name="lkp_account__c", length=18)
    public String getLkpAccountC() {
        return this.lkpAccountC;
    }
    
    public void setLkpAccountC(String lkpAccountC) {
        this.lkpAccountC = lkpAccountC;
    }

    
    @Column(name="name", length=80)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="lastmodifieddate", length=29)
    public Date getLastmodifieddate() {
        return this.lastmodifieddate;
    }
    
    public void setLastmodifieddate(Date lastmodifieddate) {
        this.lastmodifieddate = lastmodifieddate;
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

    @Temporal(TemporalType.DATE)
    @Column(name="dat_iddp__c", length=13)
    public Date getDatIddpC() {
        return this.datIddpC;
    }
    
    public void setDatIddpC(Date datIddpC) {
        this.datIddpC = datIddpC;
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
    @Column(name="dat_urgent_date__c", length=13)
    public Date getDatUrgentDateC() {
        return this.datUrgentDateC;
    }
    
    public void setDatUrgentDateC(Date datUrgentDateC) {
        this.datUrgentDateC = datUrgentDateC;
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


