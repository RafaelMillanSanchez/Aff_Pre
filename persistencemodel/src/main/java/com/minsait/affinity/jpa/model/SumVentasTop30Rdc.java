package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SumVentasTop30Rdc generated by hbm2java
 */
@Entity
@Table(name="sum_ventas_top30_rdc"
    ,schema="salesforce"
)
public class SumVentasTop30Rdc  implements java.io.Serializable {


     private SumVentasTop30RdcId id;

    public SumVentasTop30Rdc() {
    }

    public SumVentasTop30Rdc(SumVentasTop30RdcId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="rdc", column=@Column(name="rdc") ), 
        @AttributeOverride(name="subBrand", column=@Column(name="sub_brand") ), 
        @AttributeOverride(name="baseReference", column=@Column(name="base_reference") ), 
        @AttributeOverride(name="cadena", column=@Column(name="cadena") ), 
        @AttributeOverride(name="accType", column=@Column(name="acc_type") ), 
        @AttributeOverride(name="sumUnidades", column=@Column(name="sum_unidades", precision=17, scale=17) ), 
        @AttributeOverride(name="sumEuros", column=@Column(name="sum_euros", precision=17, scale=17) ), 
        @AttributeOverride(name="sumKg", column=@Column(name="sum_kg", precision=17, scale=17) ) } )
    public SumVentasTop30RdcId getId() {
        return this.id;
    }
    
    public void setId(SumVentasTop30RdcId id) {
        this.id = id;
    }




}


