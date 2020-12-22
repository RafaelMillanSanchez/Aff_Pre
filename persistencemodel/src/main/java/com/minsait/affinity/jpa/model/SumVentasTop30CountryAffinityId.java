package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SumVentasTop30CountryAffinityId generated by hbm2java
 */
@Embeddable
public class SumVentasTop30CountryAffinityId  implements java.io.Serializable {


     private String country;
     private String subBrand;
     private String baseReference;
     private Double sumEuros;
     private Double sumKg;
     private String cadena;
     private String accType;
     private String productName;

    public SumVentasTop30CountryAffinityId() {
    }

    public SumVentasTop30CountryAffinityId(String country, String subBrand, String baseReference, Double sumEuros, Double sumKg, String cadena, String accType, String productName) {
       this.country = country;
       this.subBrand = subBrand;
       this.baseReference = baseReference;
       this.sumEuros = sumEuros;
       this.sumKg = sumKg;
       this.cadena = cadena;
       this.accType = accType;
       this.productName = productName;
    }
   


    @Column(name="country")
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }


    @Column(name="sub_brand")
    public String getSubBrand() {
        return this.subBrand;
    }
    
    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand;
    }


    @Column(name="base_reference")
    public String getBaseReference() {
        return this.baseReference;
    }
    
    public void setBaseReference(String baseReference) {
        this.baseReference = baseReference;
    }


    @Column(name="sum_euros", precision=17, scale=17)
    public Double getSumEuros() {
        return this.sumEuros;
    }
    
    public void setSumEuros(Double sumEuros) {
        this.sumEuros = sumEuros;
    }


    @Column(name="sum_kg", precision=17, scale=17)
    public Double getSumKg() {
        return this.sumKg;
    }
    
    public void setSumKg(Double sumKg) {
        this.sumKg = sumKg;
    }


    @Column(name="cadena")
    public String getCadena() {
        return this.cadena;
    }
    
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }


    @Column(name="acc_type")
    public String getAccType() {
        return this.accType;
    }
    
    public void setAccType(String accType) {
        this.accType = accType;
    }


    @Column(name="product_name")
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SumVentasTop30CountryAffinityId) ) return false;
		 SumVentasTop30CountryAffinityId castOther = ( SumVentasTop30CountryAffinityId ) other; 
         
		 return ( (this.getCountry()==castOther.getCountry()) || ( this.getCountry()!=null && castOther.getCountry()!=null && this.getCountry().equals(castOther.getCountry()) ) )
 && ( (this.getSubBrand()==castOther.getSubBrand()) || ( this.getSubBrand()!=null && castOther.getSubBrand()!=null && this.getSubBrand().equals(castOther.getSubBrand()) ) )
 && ( (this.getBaseReference()==castOther.getBaseReference()) || ( this.getBaseReference()!=null && castOther.getBaseReference()!=null && this.getBaseReference().equals(castOther.getBaseReference()) ) )
 && ( (this.getSumEuros()==castOther.getSumEuros()) || ( this.getSumEuros()!=null && castOther.getSumEuros()!=null && this.getSumEuros().equals(castOther.getSumEuros()) ) )
 && ( (this.getSumKg()==castOther.getSumKg()) || ( this.getSumKg()!=null && castOther.getSumKg()!=null && this.getSumKg().equals(castOther.getSumKg()) ) )
 && ( (this.getCadena()==castOther.getCadena()) || ( this.getCadena()!=null && castOther.getCadena()!=null && this.getCadena().equals(castOther.getCadena()) ) )
 && ( (this.getAccType()==castOther.getAccType()) || ( this.getAccType()!=null && castOther.getAccType()!=null && this.getAccType().equals(castOther.getAccType()) ) )
 && ( (this.getProductName()==castOther.getProductName()) || ( this.getProductName()!=null && castOther.getProductName()!=null && this.getProductName().equals(castOther.getProductName()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCountry() == null ? 0 : this.getCountry().hashCode() );
         result = 37 * result + ( getSubBrand() == null ? 0 : this.getSubBrand().hashCode() );
         result = 37 * result + ( getBaseReference() == null ? 0 : this.getBaseReference().hashCode() );
         result = 37 * result + ( getSumEuros() == null ? 0 : this.getSumEuros().hashCode() );
         result = 37 * result + ( getSumKg() == null ? 0 : this.getSumKg().hashCode() );
         result = 37 * result + ( getCadena() == null ? 0 : this.getCadena().hashCode() );
         result = 37 * result + ( getAccType() == null ? 0 : this.getAccType().hashCode() );
         result = 37 * result + ( getProductName() == null ? 0 : this.getProductName().hashCode() );
         return result;
   }   


}


