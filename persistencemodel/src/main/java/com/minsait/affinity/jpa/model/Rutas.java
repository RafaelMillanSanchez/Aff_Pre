package com.minsait.affinity.jpa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rutas {
	
	
	/*private String billingcity;
	private Date startdatetime; 
	private Date enddatetime;
	private String phoneSapPhone1C;
	private String mulShipToRouteDaysC;
    private String billingcountry;
    private String pckPaymentMethodC;
    private String pckPaymentTypeC;
    private String pckChainC;
    private Double numRankingClienteC;
    private String pckSubchainC;
    private Double numPetfoodMetersC;
    private String mailSapEmail1C;
    private String pckAfternoonClosingTimeC;
    private String pckMorningDeliveryClosingTimeC;
    private String pckAfternoonOpeningTimeC;
    private String pckMorningDeliveryOpeningTimeC;
    private String frmCustomerCodeNameC;
    private String pckMorningClosingTimeC;
    private String pckAfternoonDeliveryClosingTimeC;
    private String pckMorningOpeningTimeC;
    private String pckAfternoonDeliveryOpeningTimeC;
    private String pckAreaC;
    private String pckCustomerStatusC;
    private String pckAccountTypeC;
    private String sfid;
    private String lkpIsC;
    private String lkpIsBackupC;
    private String activityStatusC;
    private String calle;
    private String pais;
    private String postalcode;
    private String province;
    private String parentid;
    private String clienteid;
    private String nombreshipto;
    private String codigofiscal;
    private String phoneSapPhone3C;
    private String phoneSapPhone2C;
    private String txtSapidC;
    private Boolean pedidos;
    private Boolean calls;
    private Boolean chkClientWithoutVisitRouteC;
    private String txtVisitWeeksC;
    private Boolean chkClientWithoutCallRouteC;
    private String txtCallWeeksC;
    private Date dttInitVisitRouteC;
    private Date dttInitCallRouteC;
    private Date proximaruta;
    private Boolean informe;
    private String nameContact;
    private String phone;
    private String mobile;
    private String homephone;
    private String otherphone;
    private String email;
    private String frmlParentCustomerCodeC;
    private Double perPesoClienteC;
    private String deliveryDate;
    private Double geoX;
    private Double geoY;
    private String lkpRdcC;*/
    
    private Account parent;
    private Account shipto;
    private Event ruta;
    
    public Rutas (Account shipto, Account parent, Event ruta) {
    	this.setShipto(shipto);
    	this.setParent(parent);
    	this.setRuta(ruta);
    }
    
    
	
	/*public Rutas (String nombreshipto, Date startdatetime, Date enddatetime, String phoneSapPhone1C, String mulShipToRouteDaysC,
			      String pckPaymentMethodC, String pckPaymentTypeC, String pckChainC, Double numRankingClienteC, String pckSubchainC,
			      Double numPetfoodMetersC, String mailSapEmail1C, String pckAfternoonClosingTimeC,
			      String pckMorningDeliveryClosingTimeC, String pckAfternoonOpeningTimeC, String pckMorningDeliveryOpeningTimeC,
			      String pckMorningClosingTimeC, String pckAfternoonDeliveryClosingTimeC, String pckMorningOpeningTimeC, String pckAfternoonDeliveryOpeningTimeC,
			      String pckAreaC, String pckCustomerStatusC, String pckAccountTypeC, String sfid, String lkpIsC, String lkpIsBackupC, String activityStatusC,
			      String parentid, String clienteid, String phoneSapPhone3C, String phoneSapPhone2C, String txtSapidC, String billingcity, String calle,
			      String pais, String postalcode, String province, String txtVisitWeeksC, String txtCallWeeksC, Boolean chkClientWithoutVisitRouteC, Boolean chkClientWithoutCallRouteC,
			      Date dttInitVisitRouteC, Date dttInitCallRouteC, String frmlParentCustomerCodeC, Double perPesoClienteC, Account parent, Double geoX, Double geoY, String lkpRdcC) {
		this.setNombreshipto(nombreshipto);
		this.setStartdatetime(startdatetime);
		this.setEnddatetime(enddatetime);
		this.setPhoneSapPhone1C(phoneSapPhone1C);
		this.setMulShipToRouteDaysC(mulShipToRouteDaysC);
	    this.setPckPaymentMethodC(pckPaymentMethodC);
	    this.setPckPaymentTypeC(pckPaymentTypeC);
	    this.setPckChainC(pckChainC); 
	    this.setNumRankingClienteC(numRankingClienteC); 
	    this.setPckSubchainC(pckSubchainC);
	    this.setNumPetfoodMetersC(numPetfoodMetersC);
	    this.setMailSapEmail1C(mailSapEmail1C);
	    this.setPckAfternoonClosingTimeC(pckAfternoonClosingTimeC);
	    this.setPckMorningDeliveryClosingTimeC(pckMorningDeliveryClosingTimeC); 
	    this.setPckAfternoonOpeningTimeC(pckAfternoonOpeningTimeC); 
	    this.setPckMorningDeliveryClosingTimeC(pckMorningDeliveryClosingTimeC); 
	    this.setPckMorningClosingTimeC(pckMorningClosingTimeC); 
	    this.setPckAfternoonDeliveryClosingTimeC(pckAfternoonDeliveryClosingTimeC); 
	    this.setPckMorningOpeningTimeC(pckMorningOpeningTimeC); 
	    this.setPckAfternoonDeliveryOpeningTimeC(pckAfternoonDeliveryOpeningTimeC); 
	    this.setPckAreaC(pckAreaC); 
	    this.setPckCustomerStatusC(pckCustomerStatusC); 
	    this.setPckAccountTypeC(pckAccountTypeC);
	    this.setSfid(sfid);
	    this.setLkpIsC(lkpIsC);
	    this.setLkpIsBackupC(lkpIsBackupC);
	    this.setActivityStatusC(activityStatusC);
	    this.setParentid(parentid); 
	    this.setClienteid(clienteid);
	    this.setPhoneSapPhone3C(phoneSapPhone3C);
	    this.setPhoneSapPhone2C(phoneSapPhone2C);
	    this.setTxtSapidC(txtSapidC);
	    this.setBillingcity(billingcity);
	    this.setCalle(calle);
	    this.setPostalcode(postalcode);
	    this.setPais(pais);
	    this.setProvince(province);
	    this.setChkClientWithoutVisitRouteC(chkClientWithoutVisitRouteC);
	    this.setTxtVisitWeeksC(txtVisitWeeksC);
	    this.setChkClientWithoutCallRouteC(chkClientWithoutCallRouteC);
	    this.setTxtCallWeeksC(txtCallWeeksC);
	    this.setDttInitVisitRouteC(dttInitVisitRouteC);
	    this.setDttInitCallRouteC(dttInitCallRouteC);
	    this.setFrmlParentCustomerCodeC(frmlParentCustomerCodeC);
	    this.setPerPesoClienteC(perPesoClienteC);
	    this.setParent(parent);
	    this.setGeoX(geoX);
	    this.setGeoY(geoY);
	    this.setLkpRdcC(lkpRdcC);
	}


	public String getBillingcity() {
		return billingcity;
	}

	public void setBillingcity(String billingcity) {
		this.billingcity = billingcity;
	}

	public Date getStartdatetime() {
		return startdatetime;
	}

	public void setStartdatetime(Date startdatetime) {
		this.startdatetime = startdatetime;
	}

	public Date getEnddatetime() {
		return enddatetime;
	}

	public void setEnddatetime(Date enddatetime) {
		this.enddatetime = enddatetime;
	}

	public String getPhoneSapPhone1C() {
		return phoneSapPhone1C;
	}

	public void setPhoneSapPhone1C(String phoneSapPhone1C) {
		this.phoneSapPhone1C = phoneSapPhone1C;
	}

	public String getMulShipToRouteDaysC() {
		return mulShipToRouteDaysC;
	}

	public void setMulShipToRouteDaysC(String mulShipToRouteDaysC) {
		this.mulShipToRouteDaysC = mulShipToRouteDaysC;
	}

	public String getBillingcountry() {
		return billingcountry;
	}

	public void setBillingcountry(String billingcountry) {
		this.billingcountry = billingcountry;
	}

	public String getPckPaymentMethodC() {
		return pckPaymentMethodC;
	}

	public void setPckPaymentMethodC(String pckPaymentMethodC) {
		this.pckPaymentMethodC = pckPaymentMethodC;
	}

	public String getPckPaymentTypeC() {
		return pckPaymentTypeC;
	}

	public void setPckPaymentTypeC(String pckPaymentTypeC) {
		this.pckPaymentTypeC = pckPaymentTypeC;
	}

	public String getPckChainC() {
		return pckChainC;
	}

	public void setPckChainC(String pckChainC) {
		this.pckChainC = pckChainC;
	}

	public Double getNumRankingClienteC() {
		return numRankingClienteC;
	}

	public void setNumRankingClienteC(Double numRankingClienteC) {
		this.numRankingClienteC = numRankingClienteC;
	}

	public String getPckSubchainC() {
		return pckSubchainC;
	}

	public void setPckSubchainC(String pckSubchainC) {
		this.pckSubchainC = pckSubchainC;
	}

	public Double getNumPetfoodMetersC() {
		return numPetfoodMetersC;
	}

	public void setNumPetfoodMetersC(Double numPetfoodMetersC) {
		this.numPetfoodMetersC = numPetfoodMetersC;
	}

	public String getMailSapEmail1C() {
		return mailSapEmail1C;
	}

	public void setMailSapEmail1C(String mailSapEmail1C) {
		this.mailSapEmail1C = mailSapEmail1C;
	}




	public String getPckAfternoonClosingTimeC() {
		return pckAfternoonClosingTimeC;
	}


	public void setPckAfternoonClosingTimeC(String pckAfternoonClosingTimeC) {
		this.pckAfternoonClosingTimeC = pckAfternoonClosingTimeC;
	}


	public String getPckMorningDeliveryClosingTimeC() {
		return pckMorningDeliveryClosingTimeC;
	}


	public void setPckMorningDeliveryClosingTimeC(String pckMorningDeliveryClosingTimeC) {
		this.pckMorningDeliveryClosingTimeC = pckMorningDeliveryClosingTimeC;
	}


	public String getPckAfternoonOpeningTimeC() {
		return pckAfternoonOpeningTimeC;
	}


	public void setPckAfternoonOpeningTimeC(String pckAfternoonOpeningTimeC) {
		this.pckAfternoonOpeningTimeC = pckAfternoonOpeningTimeC;
	}


	public String getFrmCustomerCodeNameC() {
		return frmCustomerCodeNameC;
	}


	public void setFrmCustomerCodeNameC(String frmCustomerCodeNameC) {
		this.frmCustomerCodeNameC = frmCustomerCodeNameC;
	}


	public String getPckMorningDeliveryOpeningTimeC() {
		return pckMorningDeliveryOpeningTimeC;
	}


	public void setPckMorningDeliveryOpeningTimeC(String pckMorningDeliveryOpeningTimeC) {
		this.pckMorningDeliveryOpeningTimeC = pckMorningDeliveryOpeningTimeC;
	}


	public String getPckMorningClosingTimeC() {
		return pckMorningClosingTimeC;
	}


	public void setPckMorningClosingTimeC(String pckMorningClosingTimeC) {
		this.pckMorningClosingTimeC = pckMorningClosingTimeC;
	}


	public String getPckAfternoonDeliveryClosingTimeC() {
		return pckAfternoonDeliveryClosingTimeC;
	}


	public void setPckAfternoonDeliveryClosingTimeC(String pckAfternoonDeliveryClosingTimeC) {
		this.pckAfternoonDeliveryClosingTimeC = pckAfternoonDeliveryClosingTimeC;
	}


	public String getPckMorningOpeningTimeC() {
		return pckMorningOpeningTimeC;
	}


	public void setPckMorningOpeningTimeC(String pckMorningOpeningTimeC) {
		this.pckMorningOpeningTimeC = pckMorningOpeningTimeC;
	}


	public String getPckAfternoonDeliveryOpeningTimeC() {
		return pckAfternoonDeliveryOpeningTimeC;
	}


	public void setPckAfternoonDeliveryOpeningTimeC(String pckAfternoonDeliveryOpeningTimeC) {
		this.pckAfternoonDeliveryOpeningTimeC = pckAfternoonDeliveryOpeningTimeC;
	}


	public String getPckAreaC() {
		return pckAreaC;
	}


	public void setPckAreaC(String pckAreaC) {
		this.pckAreaC = pckAreaC;
	}


	public String getPckCustomerStatusC() {
		return pckCustomerStatusC;
	}


	public void setPckCustomerStatusC(String pckCustomerStatusC) {
		this.pckCustomerStatusC = pckCustomerStatusC;
	}


	public String getPckAccountTypeC() {
		return pckAccountTypeC;
	}


	public void setPckAccountTypeC(String pckAccountTypeC) {
		this.pckAccountTypeC = pckAccountTypeC;
	}


	public String getSfid() {
		return sfid;
	}


	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	
	public String getLkpIsC() {
        return this.lkpIsC;
    }
    
    public void setLkpIsC(String lkpIsC) {
        this.lkpIsC = lkpIsC;
    }

    public String getLkpIsBackupC() {
        return this.lkpIsBackupC;
    }
    
    public void setLkpIsBackupC(String lkpIsBackupC) {
        this.lkpIsBackupC = lkpIsBackupC;
    }


	public String getActivityStatusC() {
		return activityStatusC;
	}


	public void setActivityStatusC(String activityStatusC) {
		this.activityStatusC = activityStatusC;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getPostalcode() {
		return postalcode;
	}


	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getParentid() {
		return parentid;
	}


	public void setParentid(String parentid) {
		this.parentid = parentid;
	}


	public String getClienteid() {
		return clienteid;
	}


	public void setClienteid(String clienteid) {
		this.clienteid = clienteid;
	}


	public String getNombreshipto() {
		return nombreshipto;
	}


	public void setNombreshipto(String nombreshipto) {
		this.nombreshipto = nombreshipto;
	}


	public String getCodigofiscal() {
		return codigofiscal;
	}


	public void setCodigofiscal(String codigofiscal) {
		this.codigofiscal = codigofiscal;
	}


	public String getPhoneSapPhone3C() {
		return phoneSapPhone3C;
	}


	public void setPhoneSapPhone3C(String phoneSapPhone3C) {
		this.phoneSapPhone3C = phoneSapPhone3C;
	}


	public String getPhoneSapPhone2C() {
		return phoneSapPhone2C;
	}


	public void setPhoneSapPhone2C(String phoneSapPhone2C) {
		this.phoneSapPhone2C = phoneSapPhone2C;
	}


	public String getTxtSapidC() {
		return txtSapidC;
	}


	public void setTxtSapidC(String txtSapidC) {
		this.txtSapidC = txtSapidC;
	}


	public Boolean getPedidos() {
		return pedidos;
	}


	public void setPedidos(Boolean pedidos) {
		this.pedidos = pedidos;
	}


	public Boolean getCalls() {
		return calls;
	}


	public void setCalls(Boolean calls) {
		this.calls = calls;
	}


	public Boolean getChkClientWithoutVisitRouteC() {
		return chkClientWithoutVisitRouteC;
	}


	public void setChkClientWithoutVisitRouteC(Boolean chkClientWithoutVisitRouteC) {
		this.chkClientWithoutVisitRouteC = chkClientWithoutVisitRouteC;
	}


	public String getTxtVisitWeeksC() {
		return txtVisitWeeksC;
	}


	public void setTxtVisitWeeksC(String txtVisitWeeksC) {
		this.txtVisitWeeksC = txtVisitWeeksC;
	}


	public Boolean getChkClientWithoutCallRouteC() {
		return chkClientWithoutCallRouteC;
	}


	public void setChkClientWithoutCallRouteC(Boolean chkClientWithoutCallRouteC) {
		this.chkClientWithoutCallRouteC = chkClientWithoutCallRouteC;
	}


	public String getTxtCallWeeksC() {
		return txtCallWeeksC;
	}


	public void setTxtCallWeeksC(String txtCallWeeksC) {
		this.txtCallWeeksC = txtCallWeeksC;
	}


	public Date getDttInitVisitRouteC() {
		return dttInitVisitRouteC;
	}


	public void setDttInitVisitRouteC(Date dttInitVisitRouteC) {
		this.dttInitVisitRouteC = dttInitVisitRouteC;
	}


	public Date getDttInitCallRouteC() {
		return dttInitCallRouteC;
	}


	public void setDttInitCallRouteC(Date dttInitCallRouteC) {
		this.dttInitCallRouteC = dttInitCallRouteC;
	}


	public Date getProximaruta() {
		return proximaruta;
	}


	public void setProximaruta(Date proximaruta) {
		this.proximaruta = proximaruta;
	}


	public Boolean getInforme() {
		return informe;
	}


	public void setInforme(Boolean informe) {
		this.informe = informe;
	}


	public String getNameContact() {
		return nameContact;
	}


	public void setNameContact(String nameContact) {
		this.nameContact = nameContact;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getHomephone() {
		return homephone;
	}


	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}


	public String getOtherphone() {
		return otherphone;
	}


	public void setOtherphone(String otherphone) {
		this.otherphone = otherphone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFrmlParentCustomerCodeC() {
		return frmlParentCustomerCodeC;
	}


	public void setFrmlParentCustomerCodeC(String frmlParentCustomerCodeC) {
		this.frmlParentCustomerCodeC = frmlParentCustomerCodeC;
	}


	public Double getPerPesoClienteC() {
		return perPesoClienteC;
	}


	public void setPerPesoClienteC(Double perPesoClienteC) {
		this.perPesoClienteC = perPesoClienteC;
	}



	public String getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public Double getGeoY() {
		return geoY;
	}


	public void setGeoY(Double geoY) {
		this.geoY = geoY;
	}


	public Double getGeoX() {
		return geoX;
	}


	public void setGeoX(Double geoX) {
		this.geoX = geoX;
	}


	public String getLkpRdcC() {
		return lkpRdcC;
	}


	public void setLkpRdcC(String lkpRdcC) {
		this.lkpRdcC = lkpRdcC;
	}*/


	public Account getParent() {
		return parent;
	}


	public void setParent(Account parent) {
		this.parent = parent;
	}


	public Account getShipto() {
		return shipto;
	}


	public void setShipto(Account shipto) {
		this.shipto = shipto;
	}


	public Event getRuta() {
		return ruta;
	}


	public void setRuta(Event ruta) {
		this.ruta = ruta;
	}

	

}
