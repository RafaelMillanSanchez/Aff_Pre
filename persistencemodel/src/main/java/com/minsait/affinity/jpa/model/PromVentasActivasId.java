package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PromVentasActivasId generated by hbm2java
 */
@Embeddable
public class PromVentasActivasId  implements java.io.Serializable {


     private Double promSales;
     private Double promSalesKg;
     private String cadena;
     private String accType;
     private String rdc;
     private String tam;
     private String country;
     private String baseReference;
     private Boolean status;
     private String productName;
     private String customerCode;

    public PromVentasActivasId() {
    }

    public PromVentasActivasId(Double promSales, Double promSalesKg, String cadena, String accType, String rdc, String tam, String country, String baseReference, Boolean status, String productName, String customerCode) {
       this.promSales = promSales;
       this.promSalesKg = promSalesKg;
       this.cadena = cadena;
       this.accType = accType;
       this.rdc = rdc;
       this.tam = tam;
       this.country = country;
       this.baseReference = baseReference;
       this.status = status;
       this.productName = productName;
       this.customerCode = customerCode;
    }
   


    @Column(name="prom_sales", precision=17, scale=17)
    public Double getPromSales() {
        return this.promSales;
    }
    
    public void setPromSales(Double promSales) {
        this.promSales = promSales;
    }


    @Column(name="prom_sales_kg", precision=17, scale=17)
    public Double getPromSalesKg() {
        return this.promSalesKg;
    }
    
    public void setPromSalesKg(Double promSalesKg) {
        this.promSalesKg = promSalesKg;
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


    @Column(name="country")
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }


    @Column(name="base_reference")
    public String getBaseReference() {
        return this.baseReference;
    }
    
    public void setBaseReference(String baseReference) {
        this.baseReference = baseReference;
    }


    @Column(name="status")
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }


    @Column(name="product_name")
    public String getProductName() {
        return this.productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }


    @Column(name="customer_code")
    public String getCustomerCode() {
        return this.customerCode;
    }
    
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PromVentasActivasId) ) return false;
		 PromVentasActivasId castOther = ( PromVentasActivasId ) other; 
         
		 return ( (this.getPromSales()==castOther.getPromSales()) || ( this.getPromSales()!=null && castOther.getPromSales()!=null && this.getPromSales().equals(castOther.getPromSales()) ) )
 && ( (this.getPromSalesKg()==castOther.getPromSalesKg()) || ( this.getPromSalesKg()!=null && castOther.getPromSalesKg()!=null && this.getPromSalesKg().equals(castOther.getPromSalesKg()) ) )
 && ( (this.getCadena()==castOther.getCadena()) || ( this.getCadena()!=null && castOther.getCadena()!=null && this.getCadena().equals(castOther.getCadena()) ) )
 && ( (this.getAccType()==castOther.getAccType()) || ( this.getAccType()!=null && castOther.getAccType()!=null && this.getAccType().equals(castOther.getAccType()) ) )
 && ( (this.getRdc()==castOther.getRdc()) || ( this.getRdc()!=null && castOther.getRdc()!=null && this.getRdc().equals(castOther.getRdc()) ) )
 && ( (this.getTam()==castOther.getTam()) || ( this.getTam()!=null && castOther.getTam()!=null && this.getTam().equals(castOther.getTam()) ) )
 && ( (this.getCountry()==castOther.getCountry()) || ( this.getCountry()!=null && castOther.getCountry()!=null && this.getCountry().equals(castOther.getCountry()) ) )
 && ( (this.getBaseReference()==castOther.getBaseReference()) || ( this.getBaseReference()!=null && castOther.getBaseReference()!=null && this.getBaseReference().equals(castOther.getBaseReference()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getProductName()==castOther.getProductName()) || ( this.getProductName()!=null && castOther.getProductName()!=null && this.getProductName().equals(castOther.getProductName()) ) )
 && ( (this.getCustomerCode()==castOther.getCustomerCode()) || ( this.getCustomerCode()!=null && castOther.getCustomerCode()!=null && this.getCustomerCode().equals(castOther.getCustomerCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPromSales() == null ? 0 : this.getPromSales().hashCode() );
         result = 37 * result + ( getPromSalesKg() == null ? 0 : this.getPromSalesKg().hashCode() );
         result = 37 * result + ( getCadena() == null ? 0 : this.getCadena().hashCode() );
         result = 37 * result + ( getAccType() == null ? 0 : this.getAccType().hashCode() );
         result = 37 * result + ( getRdc() == null ? 0 : this.getRdc().hashCode() );
         result = 37 * result + ( getTam() == null ? 0 : this.getTam().hashCode() );
         result = 37 * result + ( getCountry() == null ? 0 : this.getCountry().hashCode() );
         result = 37 * result + ( getBaseReference() == null ? 0 : this.getBaseReference().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getProductName() == null ? 0 : this.getProductName().hashCode() );
         result = 37 * result + ( getCustomerCode() == null ? 0 : this.getCustomerCode().hashCode() );
         return result;
   }   


}


