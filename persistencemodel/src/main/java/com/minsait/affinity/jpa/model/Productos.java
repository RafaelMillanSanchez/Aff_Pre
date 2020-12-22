package com.minsait.affinity.jpa.model;

public class Productos {
	
	private String prodid;
	private String code;
	private String description;
	private String status;
	private Boolean longpromo;
	private Boolean activepromo;
	private Double stock;
	private String line;
	private String subbrand;
	private Double weight;
	private Double units;
	private Double palet;
	private String baseReference;
	private String estado;
	private String photo;
	private String roundingProfile;
	private String mes1;
	private String mes2;
	private String mes3;
	private String mes4;
	private String mes5;
	private String ult5meses;
	private String itemtype;
	private String itemclass;
	
	public Productos (String prodid, String code, String description, String status, Boolean longpromo, Boolean activepromo, String line, String subbrand, Double weight, Double units, Double palet, String baseReference, String roundingProfile, String itemtype, String itemclass) {
		this.setProdid(prodid);
		this.setCode(code);
		this.setDescription(description);
		this.setStatus(status);
		this.setLongpromo(longpromo);
		this.setActivepromo(activepromo);
		this.setLine(line);
		this.setSubbrand(subbrand);
		this.setWeight(weight);
		this.setUnits(units);
		this.setPalet(palet);
		this.setBaseReference(baseReference);
		this.setRoundingProfile(roundingProfile);
		this.setItemtype(itemtype);
		this.setItemclass(itemclass);
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getStock() {
		return stock;
	}
	
	public void setStock(Double stock2) {
		this.stock = stock2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getLongpromo() {
		return longpromo;
	}

	public void setLongpromo(Boolean longpromo) {
		this.longpromo = longpromo;
	}

	public Boolean getActivepromo() {
		return activepromo;
	}

	public void setActivepromo(Boolean activepromo) {
		this.activepromo = activepromo;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getSubbrand() {
		return subbrand;
	}

	public void setSubbrand(String subbrand) {
		this.subbrand = subbrand;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public Double getUnits() {
		return units;
	}

	public void setUnits(Double units) {
		this.units = units;
	}

	public Double getPalet() {
		return palet;
	}

	public void setPalet(Double palet) {
		this.palet = palet;
	}

	public String getBaseReference() {
		return baseReference;
	}

	public void setBaseReference(String baseReference) {
		this.baseReference = baseReference;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRoundingProfile() {
		return roundingProfile;
	}

	public void setRoundingProfile(String roundingProfile) {
		this.roundingProfile = roundingProfile;
	}

	public String getMes1() {
		return mes1;
	}

	public void setMes1(String mes1) {
		this.mes1 = mes1;
	}

	public String getMes2() {
		return mes2;
	}

	public void setMes2(String mes2) {
		this.mes2 = mes2;
	}

	public String getMes3() {
		return mes3;
	}

	public void setMes3(String mes3) {
		this.mes3 = mes3;
	}

	public String getMes4() {
		return mes4;
	}

	public void setMes4(String mes4) {
		this.mes4 = mes4;
	}

	public String getMes5() {
		return mes5;
	}

	public void setMes5(String mes5) {
		this.mes5 = mes5;
	}

	public String getUlt5meses() {
		return ult5meses;
	}

	public void setUlt5meses(String ult5meses) {
		this.ult5meses = ult5meses;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getItemclass() {
		return itemclass;
	}

	public void setItemclass(String itemclass) {
		this.itemclass = itemclass;
	}
	

}
