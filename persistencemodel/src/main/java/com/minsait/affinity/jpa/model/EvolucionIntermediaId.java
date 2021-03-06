package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EvolucionIntermediaId generated by hbm2java
 */
@Embeddable
public class EvolucionIntermediaId  implements java.io.Serializable {


     private String sfid;
     private String userEv;
     private Double currentyearsales;
     private Double lastyearsales;

    public EvolucionIntermediaId() {
    }

    public EvolucionIntermediaId(String sfid, String userEv, Double currentyearsales, Double lastyearsales) {
       this.sfid = sfid;
       this.userEv = userEv;
       this.currentyearsales = currentyearsales;
       this.lastyearsales = lastyearsales;
    }
   


    @Column(name="sfid")
    public String getSfid() {
        return this.sfid;
    }
    
    public void setSfid(String sfid) {
        this.sfid = sfid;
    }


    @Column(name="user_ev")
    public String getUserEv() {
        return this.userEv;
    }
    
    public void setUserEv(String userEv) {
        this.userEv = userEv;
    }


    @Column(name="currentyearsales", precision=17, scale=17)
    public Double getCurrentyearsales() {
        return this.currentyearsales;
    }
    
    public void setCurrentyearsales(Double currentyearsales) {
        this.currentyearsales = currentyearsales;
    }


    @Column(name="lastyearsales", precision=17, scale=17)
    public Double getLastyearsales() {
        return this.lastyearsales;
    }
    
    public void setLastyearsales(Double lastyearsales) {
        this.lastyearsales = lastyearsales;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EvolucionIntermediaId) ) return false;
		 EvolucionIntermediaId castOther = ( EvolucionIntermediaId ) other; 
         
		 return ( (this.getSfid()==castOther.getSfid()) || ( this.getSfid()!=null && castOther.getSfid()!=null && this.getSfid().equals(castOther.getSfid()) ) )
 && ( (this.getUserEv()==castOther.getUserEv()) || ( this.getUserEv()!=null && castOther.getUserEv()!=null && this.getUserEv().equals(castOther.getUserEv()) ) )
 && ( (this.getCurrentyearsales()==castOther.getCurrentyearsales()) || ( this.getCurrentyearsales()!=null && castOther.getCurrentyearsales()!=null && this.getCurrentyearsales().equals(castOther.getCurrentyearsales()) ) )
 && ( (this.getLastyearsales()==castOther.getLastyearsales()) || ( this.getLastyearsales()!=null && castOther.getLastyearsales()!=null && this.getLastyearsales().equals(castOther.getLastyearsales()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSfid() == null ? 0 : this.getSfid().hashCode() );
         result = 37 * result + ( getUserEv() == null ? 0 : this.getUserEv().hashCode() );
         result = 37 * result + ( getCurrentyearsales() == null ? 0 : this.getCurrentyearsales().hashCode() );
         result = 37 * result + ( getLastyearsales() == null ? 0 : this.getLastyearsales().hashCode() );
         return result;
   }   


}


