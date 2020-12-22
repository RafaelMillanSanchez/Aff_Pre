package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * VentasRefClaveIntermediaId generated by hbm2java
 */
@Embeddable
public class VentasRefClaveIntermediaId  implements java.io.Serializable {


     private String customerCode;
     private Integer years;
     private Integer months;
     private String baseReference;
     private Double salesUnits;
     private Double salesKg;
     private String cadena;
     private String subcadena;
     private String accType;
     private String rdc;
     private String tam;
     private String billingcountry;
     private String herarchies;

    public VentasRefClaveIntermediaId() {
    }

    public VentasRefClaveIntermediaId(String customerCode, Integer years, Integer months, String baseReference, Double salesUnits, Double salesKg, String cadena, String subcadena, String accType, String rdc, String tam, String billingcountry, String herarchies) {
       this.customerCode = customerCode;
       this.years = years;
       this.months = months;
       this.baseReference = baseReference;
       this.salesUnits = salesUnits;
       this.salesKg = salesKg;
       this.cadena = cadena;
       this.subcadena = subcadena;
       this.accType = accType;
       this.rdc = rdc;
       this.tam = tam;
       this.billingcountry = billingcountry;
       this.herarchies = herarchies;
    }
   


    @Column(name="customer_code")
    public String getCustomerCode() {
        return this.customerCode;
    }
    
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }


    @Column(name="years")
    public Integer getYears() {
        return this.years;
    }
    
    public void setYears(Integer years) {
        this.years = years;
    }


    @Column(name="months")
    public Integer getMonths() {
        return this.months;
    }
    
    public void setMonths(Integer months) {
        this.months = months;
    }


    @Column(name="base_reference")
    public String getBaseReference() {
        return this.baseReference;
    }
    
    public void setBaseReference(String baseReference) {
        this.baseReference = baseReference;
    }


    @Column(name="sales_units", precision=17, scale=17)
    public Double getSalesUnits() {
        return this.salesUnits;
    }
    
    public void setSalesUnits(Double salesUnits) {
        this.salesUnits = salesUnits;
    }


    @Column(name="sales_kg", precision=17, scale=17)
    public Double getSalesKg() {
        return this.salesKg;
    }
    
    public void setSalesKg(Double salesKg) {
        this.salesKg = salesKg;
    }


    @Column(name="cadena")
    public String getCadena() {
        return this.cadena;
    }
    
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }


    @Column(name="subcadena")
    public String getSubcadena() {
        return this.subcadena;
    }
    
    public void setSubcadena(String subcadena) {
        this.subcadena = subcadena;
    }


    @Column(name="acc_type")
    public String getAccType() {
        return this.accType;
    }
    
    public void setAccType(String accType) {
        this.accType = accType;
    }


    @Column(name="rdc")
    public String getRdc() {
        return this.rdc;
    }
    
    public void setRdc(String rdc) {
        this.rdc = rdc;
    }


    @Column(name="tam")
    public String getTam() {
        return this.tam;
    }
    
    public void setTam(String tam) {
        this.tam = tam;
    }


    @Column(name="billingcountry")
    public String getBillingcountry() {
        return this.billingcountry;
    }
    
    public void setBillingcountry(String billingcountry) {
        this.billingcountry = billingcountry;
    }


    @Column(name="herarchies")
    public String getHerarchies() {
        return this.herarchies;
    }
    
    public void setHerarchies(String herarchies) {
        this.herarchies = herarchies;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VentasRefClaveIntermediaId) ) return false;
		 VentasRefClaveIntermediaId castOther = ( VentasRefClaveIntermediaId ) other; 
         
		 return ( (this.getCustomerCode()==castOther.getCustomerCode()) || ( this.getCustomerCode()!=null && castOther.getCustomerCode()!=null && this.getCustomerCode().equals(castOther.getCustomerCode()) ) )
 && ( (this.getYears()==castOther.getYears()) || ( this.getYears()!=null && castOther.getYears()!=null && this.getYears().equals(castOther.getYears()) ) )
 && ( (this.getMonths()==castOther.getMonths()) || ( this.getMonths()!=null && castOther.getMonths()!=null && this.getMonths().equals(castOther.getMonths()) ) )
 && ( (this.getBaseReference()==castOther.getBaseReference()) || ( this.getBaseReference()!=null && castOther.getBaseReference()!=null && this.getBaseReference().equals(castOther.getBaseReference()) ) )
 && ( (this.getSalesUnits()==castOther.getSalesUnits()) || ( this.getSalesUnits()!=null && castOther.getSalesUnits()!=null && this.getSalesUnits().equals(castOther.getSalesUnits()) ) )
 && ( (this.getSalesKg()==castOther.getSalesKg()) || ( this.getSalesKg()!=null && castOther.getSalesKg()!=null && this.getSalesKg().equals(castOther.getSalesKg()) ) )
 && ( (this.getCadena()==castOther.getCadena()) || ( this.getCadena()!=null && castOther.getCadena()!=null && this.getCadena().equals(castOther.getCadena()) ) )
 && ( (this.getSubcadena()==castOther.getSubcadena()) || ( this.getSubcadena()!=null && castOther.getSubcadena()!=null && this.getSubcadena().equals(castOther.getSubcadena()) ) )
 && ( (this.getAccType()==castOther.getAccType()) || ( this.getAccType()!=null && castOther.getAccType()!=null && this.getAccType().equals(castOther.getAccType()) ) )
 && ( (this.getRdc()==castOther.getRdc()) || ( this.getRdc()!=null && castOther.getRdc()!=null && this.getRdc().equals(castOther.getRdc()) ) )
 && ( (this.getTam()==castOther.getTam()) || ( this.getTam()!=null && castOther.getTam()!=null && this.getTam().equals(castOther.getTam()) ) )
 && ( (this.getBillingcountry()==castOther.getBillingcountry()) || ( this.getBillingcountry()!=null && castOther.getBillingcountry()!=null && this.getBillingcountry().equals(castOther.getBillingcountry()) ) )
 && ( (this.getHerarchies()==castOther.getHerarchies()) || ( this.getHerarchies()!=null && castOther.getHerarchies()!=null && this.getHerarchies().equals(castOther.getHerarchies()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCustomerCode() == null ? 0 : this.getCustomerCode().hashCode() );
         result = 37 * result + ( getYears() == null ? 0 : this.getYears().hashCode() );
         result = 37 * result + ( getMonths() == null ? 0 : this.getMonths().hashCode() );
         result = 37 * result + ( getBaseReference() == null ? 0 : this.getBaseReference().hashCode() );
         result = 37 * result + ( getSalesUnits() == null ? 0 : this.getSalesUnits().hashCode() );
         result = 37 * result + ( getSalesKg() == null ? 0 : this.getSalesKg().hashCode() );
         result = 37 * result + ( getCadena() == null ? 0 : this.getCadena().hashCode() );
         result = 37 * result + ( getSubcadena() == null ? 0 : this.getSubcadena().hashCode() );
         result = 37 * result + ( getAccType() == null ? 0 : this.getAccType().hashCode() );
         result = 37 * result + ( getRdc() == null ? 0 : this.getRdc().hashCode() );
         result = 37 * result + ( getTam() == null ? 0 : this.getTam().hashCode() );
         result = 37 * result + ( getBillingcountry() == null ? 0 : this.getBillingcountry().hashCode() );
         result = 37 * result + ( getHerarchies() == null ? 0 : this.getHerarchies().hashCode() );
         return result;
   }   


}


