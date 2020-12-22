package com.minsait.affinity.jpa.model;
// Generated 30-mar-2020 20:13:03 by Hibernate Tools 5.0.6.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * PgaException generated by hbm2java
 */
@Entity
@Table(name="pga_exception"
    ,schema="pgagent"
    , uniqueConstraints = @UniqueConstraint(columnNames={"jexdate", "jextime"}) 
)
public class PgaException  implements java.io.Serializable {


     private Integer jexid;
     private PgaSchedule pgaSchedule;
     private Date jexdate;
     private Date jextime;

    public PgaException() {
    }

	
    public PgaException(Integer jexid, PgaSchedule pgaSchedule) {
        this.jexid = jexid;
        this.pgaSchedule = pgaSchedule;
    }
    public PgaException(Integer jexid, PgaSchedule pgaSchedule, Date jexdate, Date jextime) {
       this.jexid = jexid;
       this.pgaSchedule = pgaSchedule;
       this.jexdate = jexdate;
       this.jextime = jextime;
    }
   
     @Id 

    
    @Column(name="jexid", unique=true, nullable=false)
    public Integer getJexid() {
        return this.jexid;
    }
    
    public void setJexid(Integer jexid) {
        this.jexid = jexid;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="jexscid", nullable=false)
    public PgaSchedule getPgaSchedule() {
        return this.pgaSchedule;
    }
    
    public void setPgaSchedule(PgaSchedule pgaSchedule) {
        this.pgaSchedule = pgaSchedule;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="jexdate", length=13)
    public Date getJexdate() {
        return this.jexdate;
    }
    
    public void setJexdate(Date jexdate) {
        this.jexdate = jexdate;
    }

    @Temporal(TemporalType.TIME)
    @Column(name="jextime", length=15)
    public Date getJextime() {
        return this.jextime;
    }
    
    public void setJextime(Date jextime) {
        this.jextime = jextime;
    }




}

