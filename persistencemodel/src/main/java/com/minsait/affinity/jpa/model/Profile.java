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
 * Profile generated by hbm2java
 */
@Entity
@Table(name="profile"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class Profile  implements java.io.Serializable {


     private Integer id;
     private Date createddate;
     private String name;
     private Date systemmodstamp;
     private String sfid;
     private String hcLastop;
     private String hcErr;

    public Profile() {
    }

	
    public Profile(Integer id) {
        this.id = id;
    }
    public Profile(Integer id, Date createddate, String name, Date systemmodstamp, String sfid, String hcLastop, String hcErr) {
       this.id = id;
       this.createddate = createddate;
       this.name = name;
       this.systemmodstamp = systemmodstamp;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createddate", length=29)
    public Date getCreateddate() {
        return this.createddate;
    }
    
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="systemmodstamp", length=29)
    public Date getSystemmodstamp() {
        return this.systemmodstamp;
    }
    
    public void setSystemmodstamp(Date systemmodstamp) {
        this.systemmodstamp = systemmodstamp;
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

