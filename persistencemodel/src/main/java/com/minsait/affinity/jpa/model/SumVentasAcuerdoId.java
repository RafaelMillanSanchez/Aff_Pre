package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SumVentasAcuerdoId generated by hbm2java
 */
@Embeddable
public class SumVentasAcuerdoId  implements java.io.Serializable {


     private Integer customerCode;
     private String subbrand;
     private Double sumKg;
     private Date fechaInicio;
     private Date fechaFin;
     private String sfidCustomer;
     private String sfidSubbrand;

    public SumVentasAcuerdoId() {
    }

    public SumVentasAcuerdoId(Integer customerCode, String subbrand, Double sumKg, Date fechaInicio, Date fechaFin, String sfidCustomer, String sfidSubbrand) {
       this.customerCode = customerCode;
       this.subbrand = subbrand;
       this.sumKg = sumKg;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.sfidCustomer = sfidCustomer;
       this.sfidSubbrand = sfidSubbrand;
    }
   


    @Column(name="customer_code")
    public Integer getCustomerCode() {
        return this.customerCode;
    }
    
    public void setCustomerCode(Integer customerCode) {
        this.customerCode = customerCode;
    }


    @Column(name="subbrand")
    public String getSubbrand() {
        return this.subbrand;
    }
    
    public void setSubbrand(String subbrand) {
        this.subbrand = subbrand;
    }


    @Column(name="sum_kg", precision=17, scale=17)
    public Double getSumKg() {
        return this.sumKg;
    }
    
    public void setSumKg(Double sumKg) {
        this.sumKg = sumKg;
    }


    @Column(name="fecha_inicio", length=13)
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    @Column(name="fecha_fin", length=13)
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }


    @Column(name="sfid_customer")
    public String getSfidCustomer() {
        return this.sfidCustomer;
    }
    
    public void setSfidCustomer(String sfidCustomer) {
        this.sfidCustomer = sfidCustomer;
    }


    @Column(name="sfid_subbrand")
    public String getSfidSubbrand() {
        return this.sfidSubbrand;
    }
    
    public void setSfidSubbrand(String sfidSubbrand) {
        this.sfidSubbrand = sfidSubbrand;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SumVentasAcuerdoId) ) return false;
		 SumVentasAcuerdoId castOther = ( SumVentasAcuerdoId ) other; 
         
		 return ( (this.getCustomerCode()==castOther.getCustomerCode()) || ( this.getCustomerCode()!=null && castOther.getCustomerCode()!=null && this.getCustomerCode().equals(castOther.getCustomerCode()) ) )
 && ( (this.getSubbrand()==castOther.getSubbrand()) || ( this.getSubbrand()!=null && castOther.getSubbrand()!=null && this.getSubbrand().equals(castOther.getSubbrand()) ) )
 && ( (this.getSumKg()==castOther.getSumKg()) || ( this.getSumKg()!=null && castOther.getSumKg()!=null && this.getSumKg().equals(castOther.getSumKg()) ) )
 && ( (this.getFechaInicio()==castOther.getFechaInicio()) || ( this.getFechaInicio()!=null && castOther.getFechaInicio()!=null && this.getFechaInicio().equals(castOther.getFechaInicio()) ) )
 && ( (this.getFechaFin()==castOther.getFechaFin()) || ( this.getFechaFin()!=null && castOther.getFechaFin()!=null && this.getFechaFin().equals(castOther.getFechaFin()) ) )
 && ( (this.getSfidCustomer()==castOther.getSfidCustomer()) || ( this.getSfidCustomer()!=null && castOther.getSfidCustomer()!=null && this.getSfidCustomer().equals(castOther.getSfidCustomer()) ) )
 && ( (this.getSfidSubbrand()==castOther.getSfidSubbrand()) || ( this.getSfidSubbrand()!=null && castOther.getSfidSubbrand()!=null && this.getSfidSubbrand().equals(castOther.getSfidSubbrand()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCustomerCode() == null ? 0 : this.getCustomerCode().hashCode() );
         result = 37 * result + ( getSubbrand() == null ? 0 : this.getSubbrand().hashCode() );
         result = 37 * result + ( getSumKg() == null ? 0 : this.getSumKg().hashCode() );
         result = 37 * result + ( getFechaInicio() == null ? 0 : this.getFechaInicio().hashCode() );
         result = 37 * result + ( getFechaFin() == null ? 0 : this.getFechaFin().hashCode() );
         result = 37 * result + ( getSfidCustomer() == null ? 0 : this.getSfidCustomer().hashCode() );
         result = 37 * result + ( getSfidSubbrand() == null ? 0 : this.getSfidSubbrand().hashCode() );
         return result;
   }   


}


