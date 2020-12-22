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
 * BrandC generated by hbm2java
 */
@Entity
@Table(name="brand__c"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class BrandC  implements java.io.Serializable {


     private Integer id;
     private String frmDescriptionC;
     private String name;
     private String frmHierarchyC;
     private Boolean isdeleted;
     private String segmentC;
     private Date systemmodstamp;
     private String txtDescriptionC;
     private Date createddate;
     private String sfid;
     private String hcLastop;
     private String hcErr;

    public BrandC() {
    }

	
    public BrandC(Integer id) {
        this.id = id;
    }
    public BrandC(Integer id, String frmDescriptionC, String name, String frmHierarchyC, Boolean isdeleted, String segmentC, Date systemmodstamp, String txtDescriptionC, Date createddate, String sfid, String hcLastop, String hcErr) {
       this.id = id;
       this.frmDescriptionC = frmDescriptionC;
       this.name = name;
       this.frmHierarchyC = frmHierarchyC;
       this.isdeleted = isdeleted;
       this.segmentC = segmentC;
       this.systemmodstamp = systemmodstamp;
       this.txtDescriptionC = txtDescriptionC;
       this.createddate = createddate;
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

    
    @Column(name="frm_description__c", length=1300)
    public String getFrmDescriptionC() {
        return this.frmDescriptionC;
    }
    
    public void setFrmDescriptionC(String frmDescriptionC) {
        this.frmDescriptionC = frmDescriptionC;
    }

    
    @Column(name="name", length=80)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="frm_hierarchy__c", length=1300)
    public String getFrmHierarchyC() {
        return this.frmHierarchyC;
    }
    
    public void setFrmHierarchyC(String frmHierarchyC) {
        this.frmHierarchyC = frmHierarchyC;
    }

    
    @Column(name="isdeleted")
    public Boolean getIsdeleted() {
        return this.isdeleted;
    }
    
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    
    @Column(name="segment__c", length=18)
    public String getSegmentC() {
        return this.segmentC;
    }
    
    public void setSegmentC(String segmentC) {
        this.segmentC = segmentC;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="systemmodstamp", length=29)
    public Date getSystemmodstamp() {
        return this.systemmodstamp;
    }
    
    public void setSystemmodstamp(Date systemmodstamp) {
        this.systemmodstamp = systemmodstamp;
    }

    
    @Column(name="txt_description__c", length=40)
    public String getTxtDescriptionC() {
        return this.txtDescriptionC;
    }
    
    public void setTxtDescriptionC(String txtDescriptionC) {
        this.txtDescriptionC = txtDescriptionC;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createddate", length=29)
    public Date getCreateddate() {
        return this.createddate;
    }
    
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
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


