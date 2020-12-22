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
 * ObjetivocuantitativoC generated by hbm2java
 */
@Entity
@Table(name="objetivocuantitativo__c"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class ObjetivocuantitativoC  implements java.io.Serializable {


     private Integer id;
     private String channelC;
     private String name;
     private String usernameC;
     private String lkpSubbrandC;
     private Boolean isdeleted;
     private Double goalC;
     private Date systemmodstamp;
     private Date startdateC;
     private String salesentityC;
     private Double achievementC;
     private Date createddate;
     private String userC;
     private Date enddateC;
     private String subbrandC;
     private String sfid;
     private String hcLastop;
     private String hcErr;
     private Date fechaFinCalculoC;
     private Date fechaInicioCalculoC;

    public ObjetivocuantitativoC() {
    }

	
    public ObjetivocuantitativoC(Integer id) {
        this.id = id;
    }
    public ObjetivocuantitativoC(Integer id, String channelC, String name, String usernameC, String lkpSubbrandC, Boolean isdeleted, Double goalC, Date systemmodstamp, Date startdateC, String salesentityC, Double achievementC, Date createddate, String userC, Date enddateC, String subbrandC, String sfid, String hcLastop, String hcErr, Date fechaFinCalculoC, Date fechaInicioCalculoC) {
       this.id = id;
       this.channelC = channelC;
       this.name = name;
       this.usernameC = usernameC;
       this.lkpSubbrandC = lkpSubbrandC;
       this.isdeleted = isdeleted;
       this.goalC = goalC;
       this.systemmodstamp = systemmodstamp;
       this.startdateC = startdateC;
       this.salesentityC = salesentityC;
       this.achievementC = achievementC;
       this.createddate = createddate;
       this.userC = userC;
       this.enddateC = enddateC;
       this.subbrandC = subbrandC;
       this.sfid = sfid;
       this.hcLastop = hcLastop;
       this.hcErr = hcErr;
       this.fechaFinCalculoC = fechaFinCalculoC;
       this.fechaInicioCalculoC = fechaInicioCalculoC;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="channel__c")
    public String getChannelC() {
        return this.channelC;
    }
    
    public void setChannelC(String channelC) {
        this.channelC = channelC;
    }

    
    @Column(name="name", length=80)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="username__c")
    public String getUsernameC() {
        return this.usernameC;
    }
    
    public void setUsernameC(String usernameC) {
        this.usernameC = usernameC;
    }

    
    @Column(name="lkp_subbrand__c", length=18)
    public String getLkpSubbrandC() {
        return this.lkpSubbrandC;
    }
    
    public void setLkpSubbrandC(String lkpSubbrandC) {
        this.lkpSubbrandC = lkpSubbrandC;
    }

    
    @Column(name="isdeleted")
    public Boolean getIsdeleted() {
        return this.isdeleted;
    }
    
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    
    @Column(name="goal__c", precision=17, scale=17)
    public Double getGoalC() {
        return this.goalC;
    }
    
    public void setGoalC(Double goalC) {
        this.goalC = goalC;
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
    @Column(name="startdate__c", length=13)
    public Date getStartdateC() {
        return this.startdateC;
    }
    
    public void setStartdateC(Date startdateC) {
        this.startdateC = startdateC;
    }

    
    @Column(name="salesentity__c")
    public String getSalesentityC() {
        return this.salesentityC;
    }
    
    public void setSalesentityC(String salesentityC) {
        this.salesentityC = salesentityC;
    }

    
    @Column(name="achievement__c", precision=17, scale=17)
    public Double getAchievementC() {
        return this.achievementC;
    }
    
    public void setAchievementC(Double achievementC) {
        this.achievementC = achievementC;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createddate", length=29)
    public Date getCreateddate() {
        return this.createddate;
    }
    
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    
    @Column(name="user__c", length=18)
    public String getUserC() {
        return this.userC;
    }
    
    public void setUserC(String userC) {
        this.userC = userC;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="enddate__c", length=13)
    public Date getEnddateC() {
        return this.enddateC;
    }
    
    public void setEnddateC(Date enddateC) {
        this.enddateC = enddateC;
    }

    
    @Column(name="subbrand__c")
    public String getSubbrandC() {
        return this.subbrandC;
    }
    
    public void setSubbrandC(String subbrandC) {
        this.subbrandC = subbrandC;
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

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_fin_calculo__c", length=13)
    public Date getFechaFinCalculoC() {
        return this.fechaFinCalculoC;
    }
    
    public void setFechaFinCalculoC(Date fechaFinCalculoC) {
        this.fechaFinCalculoC = fechaFinCalculoC;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="fecha_inicio_calculo__c", length=13)
    public Date getFechaInicioCalculoC() {
        return this.fechaInicioCalculoC;
    }
    
    public void setFechaInicioCalculoC(Date fechaInicioCalculoC) {
        this.fechaInicioCalculoC = fechaInicioCalculoC;
    }




}

