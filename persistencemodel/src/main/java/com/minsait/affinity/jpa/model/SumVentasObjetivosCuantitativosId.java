package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SumVentasObjetivosCuantitativosId generated by hbm2java
 */
@Embeddable
public class SumVentasObjetivosCuantitativosId  implements java.io.Serializable {


     private String rdc;
     private String valueIs;
     private Double sumKg;
     private String subbrand;
     private Date fechaCompra;
     private String salesEntity;

    public SumVentasObjetivosCuantitativosId() {
    }

    public SumVentasObjetivosCuantitativosId(String rdc, String valueIs, Double sumKg, String subbrand, Date fechaCompra, String salesEntity) {
       this.rdc = rdc;
       this.valueIs = valueIs;
       this.sumKg = sumKg;
       this.subbrand = subbrand;
       this.fechaCompra = fechaCompra;
       this.salesEntity = salesEntity;
    }
   


    @Column(name="rdc")
    public String getRdc() {
        return this.rdc;
    }
    
    public void setRdc(String rdc) {
        this.rdc = rdc;
    }


    @Column(name="value_is")
    public String getValueIs() {
        return this.valueIs;
    }
    
    public void setValueIs(String valueIs) {
        this.valueIs = valueIs;
    }


    @Column(name="sum_kg", precision=17, scale=17)
    public Double getSumKg() {
        return this.sumKg;
    }
    
    public void setSumKg(Double sumKg) {
        this.sumKg = sumKg;
    }


    @Column(name="subbrand")
    public String getSubbrand() {
        return this.subbrand;
    }
    
    public void setSubbrand(String subbrand) {
        this.subbrand = subbrand;
    }


    @Column(name="fecha_compra", length=13)
    public Date getFechaCompra() {
        return this.fechaCompra;
    }
    
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }


    @Column(name="sales_entity")
    public String getSalesEntity() {
        return this.salesEntity;
    }
    
    public void setSalesEntity(String salesEntity) {
        this.salesEntity = salesEntity;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SumVentasObjetivosCuantitativosId) ) return false;
		 SumVentasObjetivosCuantitativosId castOther = ( SumVentasObjetivosCuantitativosId ) other; 
         
		 return ( (this.getRdc()==castOther.getRdc()) || ( this.getRdc()!=null && castOther.getRdc()!=null && this.getRdc().equals(castOther.getRdc()) ) )
 && ( (this.getValueIs()==castOther.getValueIs()) || ( this.getValueIs()!=null && castOther.getValueIs()!=null && this.getValueIs().equals(castOther.getValueIs()) ) )
 && ( (this.getSumKg()==castOther.getSumKg()) || ( this.getSumKg()!=null && castOther.getSumKg()!=null && this.getSumKg().equals(castOther.getSumKg()) ) )
 && ( (this.getSubbrand()==castOther.getSubbrand()) || ( this.getSubbrand()!=null && castOther.getSubbrand()!=null && this.getSubbrand().equals(castOther.getSubbrand()) ) )
 && ( (this.getFechaCompra()==castOther.getFechaCompra()) || ( this.getFechaCompra()!=null && castOther.getFechaCompra()!=null && this.getFechaCompra().equals(castOther.getFechaCompra()) ) )
 && ( (this.getSalesEntity()==castOther.getSalesEntity()) || ( this.getSalesEntity()!=null && castOther.getSalesEntity()!=null && this.getSalesEntity().equals(castOther.getSalesEntity()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRdc() == null ? 0 : this.getRdc().hashCode() );
         result = 37 * result + ( getValueIs() == null ? 0 : this.getValueIs().hashCode() );
         result = 37 * result + ( getSumKg() == null ? 0 : this.getSumKg().hashCode() );
         result = 37 * result + ( getSubbrand() == null ? 0 : this.getSubbrand().hashCode() );
         result = 37 * result + ( getFechaCompra() == null ? 0 : this.getFechaCompra().hashCode() );
         result = 37 * result + ( getSalesEntity() == null ? 0 : this.getSalesEntity().hashCode() );
         return result;
   }   


}

