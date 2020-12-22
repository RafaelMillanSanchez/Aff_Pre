package com.minsait.affinity.jpa.model;
// Generated 23-jul-2020 13:06:59 by Hibernate Tools 5.0.6.Final


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * CheckVisitC generated by hbm2java
 */
@Entity
@Table(name="check_visit__c"
    ,schema="salesforce"
    , uniqueConstraints = @UniqueConstraint(columnNames="sfid") 
)
public class CheckVisitC  implements java.io.Serializable {


     private Integer id;
     private Double descuentoPromocionC;
     private String checkProductC;
     private String name;
     private Boolean promocionadoC;
     private Boolean folletoC;
     private Double numeroDeFacingC;
     private Boolean isdeleted;
     private Date systemmodstamp;
     private Double porcentajeDeCumplimientoC;
     private Boolean enTiendaC;
     private Date createddate;
     private String accountC;
     private Boolean enCabeceraC;
     private Double precioC;
     private String sfid;
     private String hcLastop;
     private String hcErr;
     private String recordtypeid;
     private Boolean chkRoturaC;
     private String pictureUrlC;

    public CheckVisitC() {
    }

	
    public CheckVisitC(Integer id) {
        this.id = id;
    }
    public CheckVisitC(Integer id, Double descuentoPromocionC, String checkProductC, String name, Boolean promocionadoC, Boolean folletoC, Double numeroDeFacingC, Boolean isdeleted, Date systemmodstamp, Double porcentajeDeCumplimientoC, Boolean enTiendaC, Date createddate, String accountC, Boolean enCabeceraC, Double precioC, String sfid, String hcLastop, String hcErr, String recordtypeid, Boolean chkRoturaC, String pictureUrlC) {
       this.id = id;
       this.descuentoPromocionC = descuentoPromocionC;
       this.checkProductC = checkProductC;
       this.name = name;
       this.promocionadoC = promocionadoC;
       this.folletoC = folletoC;
       this.numeroDeFacingC = numeroDeFacingC;
       this.isdeleted = isdeleted;
       this.systemmodstamp = systemmodstamp;
       this.porcentajeDeCumplimientoC = porcentajeDeCumplimientoC;
       this.enTiendaC = enTiendaC;
       this.createddate = createddate;
       this.accountC = accountC;
       this.enCabeceraC = enCabeceraC;
       this.precioC = precioC;
       this.sfid = sfid;
       this.hcLastop = hcLastop;
       this.hcErr = hcErr;
       this.recordtypeid = recordtypeid;
       this.chkRoturaC = chkRoturaC;
       this.pictureUrlC = pictureUrlC;
    }
   
     @Id 

     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="descuento_promocion__c", precision=17, scale=17)
    public Double getDescuentoPromocionC() {
        return this.descuentoPromocionC;
    }
    
    public void setDescuentoPromocionC(Double descuentoPromocionC) {
        this.descuentoPromocionC = descuentoPromocionC;
    }

    
    @Column(name="check_product__c", length=18)
    public String getCheckProductC() {
        return this.checkProductC;
    }
    
    public void setCheckProductC(String checkProductC) {
        this.checkProductC = checkProductC;
    }

    
    @Column(name="name", length=80)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="promocionado__c")
    public Boolean getPromocionadoC() {
        return this.promocionadoC;
    }
    
    public void setPromocionadoC(Boolean promocionadoC) {
        this.promocionadoC = promocionadoC;
    }

    
    @Column(name="folleto__c")
    public Boolean getFolletoC() {
        return this.folletoC;
    }
    
    public void setFolletoC(Boolean folletoC) {
        this.folletoC = folletoC;
    }

    
    @Column(name="numero_de_facing__c", precision=17, scale=17)
    public Double getNumeroDeFacingC() {
        return this.numeroDeFacingC;
    }
    
    public void setNumeroDeFacingC(Double numeroDeFacingC) {
        this.numeroDeFacingC = numeroDeFacingC;
    }

    
    @Column(name="isdeleted")
    public Boolean getIsdeleted() {
        return this.isdeleted;
    }
    
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="systemmodstamp", length=29)
    public Date getSystemmodstamp() {
        return this.systemmodstamp;
    }
    
    public void setSystemmodstamp(Date systemmodstamp) {
        this.systemmodstamp = systemmodstamp;
    }

    
    @Column(name="porcentaje_de_cumplimiento__c", precision=17, scale=17)
    public Double getPorcentajeDeCumplimientoC() {
        return this.porcentajeDeCumplimientoC;
    }
    
    public void setPorcentajeDeCumplimientoC(Double porcentajeDeCumplimientoC) {
        this.porcentajeDeCumplimientoC = porcentajeDeCumplimientoC;
    }

    
    @Column(name="en_tienda__c")
    public Boolean getEnTiendaC() {
        return this.enTiendaC;
    }
    
    public void setEnTiendaC(Boolean enTiendaC) {
        this.enTiendaC = enTiendaC;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createddate", length=29)
    public Date getCreateddate() {
        return this.createddate;
    }
    
    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    
    @Column(name="account__c", length=18)
    public String getAccountC() {
        return this.accountC;
    }
    
    public void setAccountC(String accountC) {
        this.accountC = accountC;
    }

    
    @Column(name="en_cabecera__c")
    public Boolean getEnCabeceraC() {
        return this.enCabeceraC;
    }
    
    public void setEnCabeceraC(Boolean enCabeceraC) {
        this.enCabeceraC = enCabeceraC;
    }

    
    @Column(name="precio__c", precision=17, scale=17)
    public Double getPrecioC() {
        return this.precioC;
    }
    
    public void setPrecioC(Double precioC) {
        this.precioC = precioC;
    }

    
    @Column(name="sfid", unique=true, length=18)
    public String getSfid() {
        return this.sfid;
    }
    
    public void setSfid(String sfid) {
        this.sfid = sfid;
    }

    
    @Column(name="_hc_lastop", length=32)
    public String getHcLastop() {
        return this.hcLastop;
    }
    
    public void setHcLastop(String hcLastop) {
        this.hcLastop = hcLastop;
    }

    
    @Column(name="_hc_err")
    public String getHcErr() {
        return this.hcErr;
    }
    
    public void setHcErr(String hcErr) {
        this.hcErr = hcErr;
    }

    
    @Column(name="recordtypeid", length=18)
    public String getRecordtypeid() {
        return this.recordtypeid;
    }
    
    public void setRecordtypeid(String recordtypeid) {
        this.recordtypeid = recordtypeid;
    }

    
    @Column(name="chk_rotura__c")
    public Boolean getChkRoturaC() {
        return this.chkRoturaC;
    }
    
    public void setChkRoturaC(Boolean chkRoturaC) {
        this.chkRoturaC = chkRoturaC;
    }

    
    @Column(name="picture_url__c")
    public String getPictureUrlC() {
        return this.pictureUrlC;
    }
    
    public void setPictureUrlC(String pictureUrlC) {
        this.pictureUrlC = pictureUrlC;
    }




}

