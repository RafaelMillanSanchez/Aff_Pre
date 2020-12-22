package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TablaEvolucionRdcId generated by hbm2java
 */
@Embeddable
public class TablaEvolucionRdcId  implements java.io.Serializable {


     private String rdc;
     private Double ventas;
     private Integer years;
     private Boolean updated;

    public TablaEvolucionRdcId() {
    }

    public TablaEvolucionRdcId(String rdc, Double ventas, Integer years, Boolean updated) {
       this.rdc = rdc;
       this.ventas = ventas;
       this.years = years;
       this.updated = updated;
    }
   


    @Column(name="rdc")
    public String getRdc() {
        return this.rdc;
    }
    
    public void setRdc(String rdc) {
        this.rdc = rdc;
    }


    @Column(name="ventas", precision=17, scale=17)
    public Double getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Double ventas) {
        this.ventas = ventas;
    }


    @Column(name="years")
    public Integer getYears() {
        return this.years;
    }
    
    public void setYears(Integer years) {
        this.years = years;
    }


    @Column(name="updated")
    public Boolean getUpdated() {
        return this.updated;
    }
    
    public void setUpdated(Boolean updated) {
        this.updated = updated;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TablaEvolucionRdcId) ) return false;
		 TablaEvolucionRdcId castOther = ( TablaEvolucionRdcId ) other; 
         
		 return ( (this.getRdc()==castOther.getRdc()) || ( this.getRdc()!=null && castOther.getRdc()!=null && this.getRdc().equals(castOther.getRdc()) ) )
 && ( (this.getVentas()==castOther.getVentas()) || ( this.getVentas()!=null && castOther.getVentas()!=null && this.getVentas().equals(castOther.getVentas()) ) )
 && ( (this.getYears()==castOther.getYears()) || ( this.getYears()!=null && castOther.getYears()!=null && this.getYears().equals(castOther.getYears()) ) )
 && ( (this.getUpdated()==castOther.getUpdated()) || ( this.getUpdated()!=null && castOther.getUpdated()!=null && this.getUpdated().equals(castOther.getUpdated()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRdc() == null ? 0 : this.getRdc().hashCode() );
         result = 37 * result + ( getVentas() == null ? 0 : this.getVentas().hashCode() );
         result = 37 * result + ( getYears() == null ? 0 : this.getYears().hashCode() );
         result = 37 * result + ( getUpdated() == null ? 0 : this.getUpdated().hashCode() );
         return result;
   }   


}

