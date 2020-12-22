package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TablaVentasInicial generated by hbm2java
 */
@Entity
@Table(name="tabla_ventas_inicial"
    ,schema="salesforce"
)
public class TablaVentasInicial  implements java.io.Serializable {


     private TablaVentasInicialId id;

    public TablaVentasInicial() {
    }

    public TablaVentasInicial(TablaVentasInicialId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="customerCode", column=@Column(name="customer_code") ), 
        @AttributeOverride(name="basereferencecode", column=@Column(name="basereferencecode") ), 
        @AttributeOverride(name="salesUnits", column=@Column(name="sales_units", precision=17, scale=17) ), 
        @AttributeOverride(name="salesPetfoodkgwithloading", column=@Column(name="sales_petfoodkgwithloading", precision=17, scale=17) ), 
        @AttributeOverride(name="totalgrosssales", column=@Column(name="totalgrosssales", precision=17, scale=17) ), 
        @AttributeOverride(name="netsales", column=@Column(name="netsales", precision=17, scale=17) ), 
        @AttributeOverride(name="netnetsales", column=@Column(name="netnetsales", precision=17, scale=17) ), 
        @AttributeOverride(name="netcustomersales", column=@Column(name="netcustomersales", precision=17, scale=17) ), 
        @AttributeOverride(name="years", column=@Column(name="years") ), 
        @AttributeOverride(name="monthNum", column=@Column(name="month_num") ) } )
    public TablaVentasInicialId getId() {
        return this.id;
    }
    
    public void setId(TablaVentasInicialId id) {
        this.id = id;
    }




}


