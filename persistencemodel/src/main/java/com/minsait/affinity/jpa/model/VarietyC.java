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
 * VarietyC generated by hbm2java
 */
@Entity
@Table(name="variety__c"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class VarietyC  implements java.io.Serializable {


     private Integer id;
     private Date createddate;
     private String frmDescriptionC;
     private String frmHierarchyC;
     private String frmVarietyLineC;
     private String hcErr;
     private String hcLastop;
     private Boolean isdeleted;
     private String lineC;
     private String name;
     private Double numLineOrderC;
     private String sfid;
     private Date systemmodstamp;
     private String txtDescriptionC;

    public VarietyC() {
    }

	
    public VarietyC(Integer id) {
        this.id = id;
    }
    public VarietyC(Integer id, Date createddate, String frmDescriptionC, String frmHierarchyC, String frmVarietyLineC, String hcErr, String hcLastop, Boolean isdeleted, String lineC, String name, Double numLineOrderC, String sfid, Date systemmodstamp, String txtDescriptionC) {
       this.id = id;
       this.createddate = createddate;
       this.frmDescriptionC = frmDescriptionC;
       this.frmHierarchyC = frmHierarchyC;
       this.frmVarietyLineC = frmVarietyLineC;
       this.hcErr = hcErr;
       this.hcLastop = hcLastop;
       this.isdeleted = isdeleted;
       this.lineC = lineC;
       this.name = name;
       this.numLineOrderC = numLineOrderC;
       this.sfid = sfid;
       this.systemmodstamp = systemmodstamp;
       this.txtDescriptionC = txtDescriptionC;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createddate", length=29)
    public Date getCreateddate() {
        return this.createddate;
    }
    
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    
    @Column(name="frm_description__c", length=1300)
    public String getFrmDescriptionC() {
        return this.frmDescriptionC;
    }
    
    public void setFrmDescriptionC(String frmDescriptionC) {
        this.frmDescriptionC = frmDescriptionC;
    }

    
    @Column(name="frm_hierarchy__c", length=1300)
    public String getFrmHierarchyC() {
        return this.frmHierarchyC;
    }
    
    public void setFrmHierarchyC(String frmHierarchyC) {
        this.frmHierarchyC = frmHierarchyC;
    }

    
    @Column(name="frm_variety_line__c", length=1300)
    public String getFrmVarietyLineC() {
        return this.frmVarietyLineC;
    }
    
    public void setFrmVarietyLineC(String frmVarietyLineC) {
        this.frmVarietyLineC = frmVarietyLineC;
    }

    
    @Column(name="_hc_err")
    public String getHcErr() {
        return this.hcErr;
    }
    
    public void setHcErr(String hcErr) {
        this.hcErr = hcErr;
    }

    
    @Column(name="_hc_lastop", length=32)
    public String getHcLastop() {
        return this.hcLastop;
    }
    
    public void setHcLastop(String hcLastop) {
        this.hcLastop = hcLastop;
    }

    
    @Column(name="isdeleted")
    public Boolean getIsdeleted() {
        return this.isdeleted;
    }
    
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    
    @Column(name="line__c", length=18)
    public String getLineC() {
        return this.lineC;
    }
    
    public void setLineC(String lineC) {
        this.lineC = lineC;
    }

    
    @Column(name="name", length=80)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="num_line_order__c", precision=17, scale=17)
    public Double getNumLineOrderC() {
        return this.numLineOrderC;
    }
    
    public void setNumLineOrderC(Double numLineOrderC) {
        this.numLineOrderC = numLineOrderC;
    }

    
    @Column(name="sfid", unique=true, length=18)
    public String getSfid() {
        return this.sfid;
    }
    
    public void setSfid(String sfid) {
        this.sfid = sfid;
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




}

