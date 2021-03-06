package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SumVentasRdcPesoclienteId generated by hbm2java
 */
@Embeddable
public class SumVentasRdcPesoclienteId  implements java.io.Serializable {


     private String rdc;
     private Double sumVentas;

    public SumVentasRdcPesoclienteId() {
    }

    public SumVentasRdcPesoclienteId(String rdc, Double sumVentas) {
       this.rdc = rdc;
       this.sumVentas = sumVentas;
    }
   


    @Column(name="rdc")
    public String getRdc() {
        return this.rdc;
    }
    
    public void setRdc(String rdc) {
        this.rdc = rdc;
    }


    @Column(name="sum_ventas", precision=17, scale=17)
    public Double getSumVentas() {
        return this.sumVentas;
    }
    
    public void setSumVentas(Double sumVentas) {
        this.sumVentas = sumVentas;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SumVentasRdcPesoclienteId) ) return false;
		 SumVentasRdcPesoclienteId castOther = ( SumVentasRdcPesoclienteId ) other; 
         
		 return ( (this.getRdc()==castOther.getRdc()) || ( this.getRdc()!=null && castOther.getRdc()!=null && this.getRdc().equals(castOther.getRdc()) ) )
 && ( (this.getSumVentas()==castOther.getSumVentas()) || ( this.getSumVentas()!=null && castOther.getSumVentas()!=null && this.getSumVentas().equals(castOther.getSumVentas()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRdc() == null ? 0 : this.getRdc().hashCode() );
         result = 37 * result + ( getSumVentas() == null ? 0 : this.getSumVentas().hashCode() );
         return result;
   }   


}


