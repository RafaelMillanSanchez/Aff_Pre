package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EvolucionIntermedia generated by hbm2java
 */
@Entity
@Table(name="evolucion_intermedia"
    ,schema="salesforce"
)
public class EvolucionIntermedia  implements java.io.Serializable {


     private EvolucionIntermediaId id;

    public EvolucionIntermedia() {
    }

    public EvolucionIntermedia(EvolucionIntermediaId id) {
       this.id = id;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="sfid", column=@Column(name="sfid") ), 
        @AttributeOverride(name="userEv", column=@Column(name="user_ev") ), 
        @AttributeOverride(name="currentyearsales", column=@Column(name="currentyearsales", precision=17, scale=17) ), 
        @AttributeOverride(name="lastyearsales", column=@Column(name="lastyearsales", precision=17, scale=17) ) } )
    public EvolucionIntermediaId getId() {
        return this.id;
    }
    
    public void setId(EvolucionIntermediaId id) {
        this.id = id;
    }




}


