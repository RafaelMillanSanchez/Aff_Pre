package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MtReferenciasBase generated by hbm2java
 */
@Entity
@Table(name="mt_referencias_base"
    ,schema="salesforce"
)
public class MtReferenciasBase  implements java.io.Serializable {


     private MtReferenciasBaseId id;

    public MtReferenciasBase() {
    }

    public MtReferenciasBase(MtReferenciasBaseId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="surtidos", column=@Column(name="surtidos") ), 
        @AttributeOverride(name="refBase", column=@Column(name="ref_base") ), 
        @AttributeOverride(name="chains", column=@Column(name="chains") ), 
        @AttributeOverride(name="subchain", column=@Column(name="subchain") ), 
        @AttributeOverride(name="accType", column=@Column(name="acc_type") ), 
        @AttributeOverride(name="status", column=@Column(name="status") ), 
        @AttributeOverride(name="description", column=@Column(name="description") ), 
        @AttributeOverride(name="herarchies", column=@Column(name="herarchies") ) } )
    public MtReferenciasBaseId getId() {
        return this.id;
    }
    
    public void setId(MtReferenciasBaseId id) {
        this.id = id;
    }




}

