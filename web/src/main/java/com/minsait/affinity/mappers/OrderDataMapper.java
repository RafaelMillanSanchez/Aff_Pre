package com.minsait.affinity.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minsait.affinity.jpa.model.Orderitem;
import com.minsait.affinity.jpa.model.Product2;
import com.minsait.affinity.repo.ProductRepository;
import com.minsait.affinity.web.model.WsProduct;

@Component
public class OrderDataMapper {

	@Autowired
	private ProductRepository productRepository;

	public List<WsProduct> lastorder2web(Iterable<Orderitem> items) {
		List<WsProduct> lst = null;
		if (items != null) {
			lst = new ArrayList<>();
			for (Orderitem elem : items) {
				lst.add(this.lastorder2web(elem));
			}
		}
		return lst;
	}

	private WsProduct lastorder2web(Orderitem elem) {
		if (elem == null) return null;
		Product2 p = this.productRepository.findBySfid(elem.getProduct2id());
		WsProduct wsprod = new WsProduct();
		wsprod.setNameP(p.getName());
		wsprod.setCode(p.getProductcode());
		wsprod.setSubbrand(p.getFrmSubbrandC());
		wsprod.setLine(p.getFrmLineC());
		wsprod.setWeight(p.getNumWeightC());
		wsprod.setQuantity(elem.getQuantity());
		return wsprod;
	}
	
	
/*
	public List<WsPedido> product2web(Iterable<Productos> productos) {
		List<WsPedido> lst = null;
		if (productos != null) {
			lst = new ArrayList<>();
			for (Productos p : productos) {
				lst.add(this.product2web(p));
			}		
		}
		return lst;
	}

	private WsPedido product2web(Productos p) {
		if (p == null) return null;
		WsPedido wsp = new WsPedido();
		wsp.setSubbrand(p.getSubbrand());
		wsp.setCode(p.getCode());
		wsp.setNameP(p.getDescription());
		if (p.getLongpromo()) wsp.setPromo("verde");
		else if (p.getActivepromo()) wsp.setPromo("roja");
		else if (p.getStatus().equals("P")) wsp.setPromo("promo");
		wsp.setLine(p.getLine());
		wsp.setStock(p.getStock());
		wsp.setWeight(p.getWeight());
		return wsp;
	}*/

}
