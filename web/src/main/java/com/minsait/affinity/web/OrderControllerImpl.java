package com.minsait.affinity.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.affinity.jpa.model.Order;
import com.minsait.affinity.service.SalesforceOrderService;
import com.minsait.affinity.web.api.OrderApi;
import com.minsait.affinity.web.model.WsBudgetOrder;
import com.minsait.affinity.web.model.WsIdOrder;
import com.minsait.affinity.web.model.WsNewOrder;
import com.minsait.affinity.web.model.WsOrderResp;
import com.minsait.affinity.web.model.WsOrderSumm;
import com.minsait.affinity.web.model.WsPedido;
import com.minsait.affinity.web.model.WsProdPrices;
import com.minsait.affinity.web.model.WsProdsToRate;
import com.minsait.affinity.web.model.WsProduct;

import io.swagger.annotations.ApiParam;

@RestController
public class OrderControllerImpl implements OrderApi {


	@Autowired
	private SalesforceOrderService service;

	@Override
	@RequestMapping(value = "/order/products/{shiptoid}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsPedido>> getProducts(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid) {
		List<WsPedido> brands = this.service.getProducts(shiptoid);
		if (brands != null) return new ResponseEntity<>(brands, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/order/lastorder/{shiptoid}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsProduct>> getLastOrder(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid) {
		List<WsProduct> lastproducts = this.service.getLastOrderProducts(shiptoid);
		if (lastproducts != null) return new ResponseEntity<>(lastproducts, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/order/createOrder",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<List<WsOrderResp>> createOrder(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @Valid WsNewOrder body) {
		List<WsOrderResp> stocks = this.service.checkStockVisibility(body);	
		if (stocks == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else if (stocks.size() > 0) {
			System.out.println("NO HAY STOCK/ VISIBILIDAD PARA SHIP TO: " + body.getShiptoid() + " ORDERS IDS: " + body.getIdorder1() + " " + body.getIdorder2());
			return new ResponseEntity<>(stocks, HttpStatus.OK);
		}
		List<WsOrderResp> order = this.service.createOrder(body);
		if (order != null) {
			if (!order.isEmpty()) return new ResponseEntity<>(HttpStatus.CONFLICT);
			System.out.println("PEDIDO CREADO PARA SHIP TO: " + body.getShiptoid() + " ORDERS IDS: " + body.getIdorder1() + " " + body.getIdorder2());
			return new ResponseEntity<>(new ArrayList<WsOrderResp>(), HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@Override
	@RequestMapping(value = "/order/draftOrder",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<WsIdOrder> createDraftOrder(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String username) {
		WsIdOrder order = this.service.createDraftOrder(shiptoid, username);
		if (order == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else if (order.getOrder1() != null && order.getOrder2() != null) {
			System.out.println("DRAFT CREADO ORDERS IDS: " + order.getOrder1() + " " + order.getOrder2());
			return new ResponseEntity<>(order, HttpStatus.OK);
		}
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/order/deleteDraftOrder",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> deleteDraftOrder(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @Valid WsIdOrder body) {
		this.service.deleteDraftOrder(body);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/order/summary/{username}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<WsOrderSumm> orderSummary(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username) {
		WsOrderSumm summary = this.service.orderSummary(username);
		if (summary.getTotalOrders() == 0) return new ResponseEntity<>(summary, HttpStatus.OK);
		else if (summary != null) return new ResponseEntity<>(summary, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/order/lastbudget/{shiptoid}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<WsBudgetOrder> getLastbudget(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid) {
		WsBudgetOrder presupuesto = this.service.getLastbudget(shiptoid);
		if (presupuesto != null) return new ResponseEntity<>(presupuesto, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/order/rateOrder/{shiptoid}",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<List<WsProdPrices>> rateOrder(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid,
			@ApiParam(value = "", required = true) WsProdsToRate body) {
		List<WsProdPrices> precios = this.service.rateProducts(body, shiptoid);
		if (precios == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		else return new ResponseEntity<>(precios, HttpStatus.OK);
		//List<WsProdPrices> precios = new ArrayList<WsProdPrices>();
		//return new ResponseEntity<>(precios, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/order/verifyDraft",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<WsIdOrder> verifyDraft(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid,
			@ApiParam(value = "", required = true) @PathVariable("username") String username, 
			@ApiParam(value = "", required = true) WsIdOrder idorder) {
		WsIdOrder order = this.service.verifyDraft(shiptoid, username, idorder);
		if (order == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(order, HttpStatus.OK);
	}

}
