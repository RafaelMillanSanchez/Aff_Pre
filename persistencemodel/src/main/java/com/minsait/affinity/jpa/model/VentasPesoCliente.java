package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * VentasPesoCliente generated by hbm2java
 */
@Entity
@Table(name="ventas_peso_cliente"
    ,schema="salesforce"
)
public class VentasPesoCliente  implements java.io.Serializable {


     private VentasPesoClienteId id;

    public VentasPesoCliente() {
    }

    public VentasPesoCliente(VentasPesoClienteId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="customerCode", column=@Column(name="customer_code") ), 
        @AttributeOverride(name="rdc", column=@Column(name="rdc") ), 
        @AttributeOverride(name="ventasEur", column=@Column(name="ventas_eur", precision=17, scale=17) ) } )
    public VentasPesoClienteId getId() {
        return this.id;
    }
    
    public void setId(VentasPesoClienteId id) {
        this.id = id;
    }




}

