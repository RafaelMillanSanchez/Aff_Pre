package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PromVentasPaisActivas generated by hbm2java
 */
@Entity
@Table(name="prom_ventas_pais_activas"
    ,schema="salesforce"
)
public class PromVentasPaisActivas  implements java.io.Serializable {


     private PromVentasPaisActivasId id;

    public PromVentasPaisActivas() {
    }

    public PromVentasPaisActivas(PromVentasPaisActivasId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="baseReference", column=@Column(name="base_reference") ), 
        @AttributeOverride(name="promSales", column=@Column(name="prom_sales", precision=17, scale=17) ), 
        @AttributeOverride(name="promSalesKg", column=@Column(name="prom_sales_kg", precision=17, scale=17) ), 
        @AttributeOverride(name="cadena", column=@Column(name="cadena") ), 
        @AttributeOverride(name="accType", column=@Column(name="acc_type") ), 
        @AttributeOverride(name="billingcountry", column=@Column(name="billingcountry") ), 
        @AttributeOverride(name="hierarchies", column=@Column(name="hierarchies") ), 
        @AttributeOverride(name="status", column=@Column(name="status") ) } )
    public PromVentasPaisActivasId getId() {
        return this.id;
    }
    
    public void setId(PromVentasPaisActivasId id) {
        this.id = id;
    }




}


