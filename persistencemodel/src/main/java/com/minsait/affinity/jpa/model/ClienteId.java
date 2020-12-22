package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ClienteId generated by hbm2java
 */
@Embeddable
public class ClienteId  implements java.io.Serializable {


     private String baseRef;
     private String customercode;
     private String pais;
     private Integer valor;

    public ClienteId() {
    }

    public ClienteId(String baseRef, String customercode, String pais, Integer valor) {
       this.baseRef = baseRef;
       this.customercode = customercode;
       this.pais = pais;
       this.valor = valor;
    }
   


    @Column(name="base_ref", nullable=false)
    public String getBaseRef() {
        return this.baseRef;
    }
    
    public void setBaseRef(String baseRef) {
        this.baseRef = baseRef;
    }


    @Column(name="customercode", nullable=false)
    public String getCustomercode() {
        return this.customercode;
    }
    
    public void setCustomercode(String customercode) {
        this.customercode = customercode;
    }


    @Column(name="pais", nullable=false)
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }


    @Column(name="valor", nullable=false)
    public Integer getValor() {
        return this.valor;
    }
    
    public void setValor(Integer valor) {
        this.valor = valor;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ClienteId) ) return false;
		 ClienteId castOther = ( ClienteId ) other; 
         
		 return ( (this.getBaseRef()==castOther.getBaseRef()) || ( this.getBaseRef()!=null && castOther.getBaseRef()!=null && this.getBaseRef().equals(castOther.getBaseRef()) ) )
 && ( (this.getCustomercode()==castOther.getCustomercode()) || ( this.getCustomercode()!=null && castOther.getCustomercode()!=null && this.getCustomercode().equals(castOther.getCustomercode()) ) )
 && ( (this.getPais()==castOther.getPais()) || ( this.getPais()!=null && castOther.getPais()!=null && this.getPais().equals(castOther.getPais()) ) )
 && ( (this.getValor()==castOther.getValor()) || ( this.getValor()!=null && castOther.getValor()!=null && this.getValor().equals(castOther.getValor()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBaseRef() == null ? 0 : this.getBaseRef().hashCode() );
         result = 37 * result + ( getCustomercode() == null ? 0 : this.getCustomercode().hashCode() );
         result = 37 * result + ( getPais() == null ? 0 : this.getPais().hashCode() );
         result = 37 * result + ( getValor() == null ? 0 : this.getValor().hashCode() );
         return result;
   }   


}


