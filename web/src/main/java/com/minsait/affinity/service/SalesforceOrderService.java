package com.minsait.affinity.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.minsait.affinity.jpa.model.Account;
import com.minsait.affinity.jpa.model.Attachment;
import com.minsait.affinity.jpa.model.Case;
import com.minsait.affinity.jpa.model.LackC;
import com.minsait.affinity.jpa.model.LightStockC;
import com.minsait.affinity.jpa.model.Order;
import com.minsait.affinity.jpa.model.OrderSummary;
import com.minsait.affinity.jpa.model.Orderitem;
import com.minsait.affinity.jpa.model.Pricebook2;
import com.minsait.affinity.jpa.model.Pricebookentry;
import com.minsait.affinity.jpa.model.Product2;
import com.minsait.affinity.jpa.model.Productos;
import com.minsait.affinity.jpa.model.SumVentasTop30;
import com.minsait.affinity.jpa.model.Ventas;
import com.minsait.affinity.jpa.model.VentasGeneral;
import com.minsait.affinity.mappers.OrderDataMapper;
import com.minsait.affinity.proximo.Proximo;
import com.minsait.affinity.repo.AccountRepository;
import com.minsait.affinity.repo.AttachmentRepository;
import com.minsait.affinity.repo.CaseRepository;
import com.minsait.affinity.repo.LackRepository;
import com.minsait.affinity.repo.OrderItemsRepository;
import com.minsait.affinity.repo.OrderRepository;
import com.minsait.affinity.repo.Pricebook2Repository;
import com.minsait.affinity.repo.PricebookentryRepository;
import com.minsait.affinity.repo.ProductListRepository;
import com.minsait.affinity.repo.ProductRepository;
import com.minsait.affinity.repo.RecordTypeRepository;
import com.minsait.affinity.repo.StockRepository;
import com.minsait.affinity.repo.SumVentasTop30Repository;
import com.minsait.affinity.repo.TablaVentasGeneralRepository;
import com.minsait.affinity.repo.TablaVentasRepository;
import com.minsait.affinity.soap.price.DTZSDFSALESORDERSIMULATE3;
import com.minsait.affinity.soap.price.DTZSDFSALESORDERSIMULATE3RESPONSE;
import com.minsait.affinity.soap.price.client.SoapPriceClient;
import com.minsait.affinity.web.model.WsBudgetOrder;
import com.minsait.affinity.web.model.WsIdOrder;
import com.minsait.affinity.web.model.WsNewOrder;
import com.minsait.affinity.web.model.WsNewOrder.TypeEnum;
import com.minsait.affinity.web.model.WsNewOrderCollectProducts;
import com.minsait.affinity.web.model.WsNewOrderLackProducts;
import com.minsait.affinity.web.model.WsNewOrderOrder;
import com.minsait.affinity.web.model.WsOrderResp;
import com.minsait.affinity.web.model.WsOrderSumm;
import com.minsait.affinity.web.model.WsOrderSummOrders;
import com.minsait.affinity.web.model.WsOrderSummProducts;
import com.minsait.affinity.web.model.WsPedido;
import com.minsait.affinity.web.model.WsPedidoLines;
import com.minsait.affinity.web.model.WsProdPrices;
import com.minsait.affinity.web.model.WsProdsToRate;
import com.minsait.affinity.web.model.WsProdsToRateProducts;
import com.minsait.affinity.web.model.WsProduct;

@Service
public class SalesforceOrderService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ProductListRepository prodListRepository;
	
	@Autowired
	private OrderDataMapper orderMapper;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private SumVentasTop30Repository sumVentasRepository;
	
	@Autowired
	private OrderItemsRepository itemsRepository;
	
	@Autowired
	private AttachmentRepository attachmentRepository;

	@Autowired
	private Pricebook2Repository pricebook2Repository;
	
	@Autowired
	private PricebookentryRepository pricebookentryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemsRepository orderitemRepository;

	@Autowired
	private TablaVentasRepository tablaVentasRepository;
	
	@Autowired
	private TablaVentasGeneralRepository tablaVentasGeneralRepository;

	@Autowired
	private CaseRepository caseRepository;

	@Autowired
	private LackRepository lackRepository;

	@Autowired
	private RecordTypeRepository recordTypeRepository;
	
	@Autowired
	private SoapPriceClient priceService;
	
	//listado de productos para un cliente
	public List<WsPedido> getProducts(String shiptoid) {
		Account a = this.accountRepository.findBySfid(shiptoid);
		if (a == null) return null;
		//if (a.getParentid() == null) return null;
		
		Account b = null;
		List<String> inactiveProds = new ArrayList<String>();
		if (a.getParentid() != null) {
			b = this.accountRepository.findBySfid(a.getParentid());
			if (b == null) return null;
			//productos inactivos para el cliente
			inactiveProds = this.prodListRepository.findInactiveProds(a.getParentid());
		}
		if (inactiveProds == null || inactiveProds.isEmpty()) inactiveProds.add("");
		//for (String s : inactiveProds) System.out.println(s);
		List<Productos> productos = new ArrayList<Productos>();
		//productos visibles para un cliente ship to
		if (b != null) productos = this.productRepository.findOrderProductos(b.getPckSalesEntityC(), b.getPckChannelC(), inactiveProds);
		//productos visibles para un cliente potencial
		else productos = this.productRepository.findOrderProductos(a.getPckSalesEntityC(), a.getPckChannelC(), inactiveProds);
		List<String> ids = new ArrayList<String>();
		Set<String> baserefs = new HashSet<String>(); 
		LinkedHashMap<String, LinkedHashMap<String, List<Productos>>> products =  new LinkedHashMap<String, LinkedHashMap<String, List<Productos> > >();
		for (Productos p : productos) {
			ids.add(p.getProdid());
			baserefs.add(p.getBaseReference());
			//System.out.println(p.getDescription());
		}
		if (baserefs.isEmpty()) baserefs.add("");
		if (ids.isEmpty()) ids.add("");
		String parentCode = a.getFrmlParentCustomerCodeC();
		Map<String, List<Ventas>> ventasultmesesMap = null;
		Map<String, VentasGeneral>ventasgeneralMap = null;
		Map<String, SumVentasTop30> ventasHabitualesMap = null;
		List<Ventas> ventasultmeses = null;
		if (parentCode != null) {
			Integer intcode = Integer.valueOf(parentCode);		
			String code = Integer.toString(intcode);
			//ventas 6 meses incluido actual
			Iterable<SumVentasTop30> ventasHabituales =  this.sumVentasRepository.findProduct(code, baserefs);
			//ventas ultimos 6 meses, sin actual
			ventasultmeses =  this.tablaVentasRepository.findProductultmeses(code, baserefs);
			//historico de ventas
			Iterable<VentasGeneral> ventasgeneral = this.tablaVentasGeneralRepository.findProductGeneral(code, baserefs);
			ventasultmesesMap = new HashMap<String, List<Ventas>>();
			ventasgeneralMap = new HashMap<String, VentasGeneral>();
			ventasHabitualesMap = new HashMap<String, SumVentasTop30>();
			for (SumVentasTop30 v : ventasHabituales) ventasHabitualesMap.put(v.getBaseReference(), v);
			for (Ventas v : ventasultmeses) {
				String baseref_code = v.getbase_ref_code();
				List<Ventas> values = ventasultmesesMap.get(baseref_code);
				if (values == null) {
					values = new ArrayList<Ventas>();
					ventasultmesesMap.put(baseref_code, values);
				}
				values .add(v);			
			}
			for (VentasGeneral vg : ventasgeneral) ventasgeneralMap.put(vg.getbase_ref_code(), vg);
			ventasgeneral = null;
			ventasHabituales = null;
			code = null;
		}
		
		Map<String, Double> stockMap = new HashMap<String, Double>();
		String almacen = a.getPckCustomerWarehouseCodeC();
		//si no tiene almacen, ponemos el por defecto
		if (almacen == null) {
			if (a.getPckSalesEntityC().equals("0012")) almacen = "0100";
			else if (a.getPckSalesEntityC().equals("0052")) almacen = "0300";
			else if (a.getPckSalesEntityC().equals("0022")) almacen = "0450";
		}
		//stock
		Iterable<LightStockC> stock = this.stockRepository.findStock(ids, almacen);
		for (LightStockC s : stock) stockMap.put(s.getLkpItemC(), s.getFrmlRealStockC());
		stock = null;
		//fotos del los profuctos
		Iterable<Attachment> attList = this.attachmentRepository.findFotos(ids);
		Map<String, String> fotosMap = new HashMap<String, String>();
		for (Attachment att : attList) fotosMap.put(att.getParentid(), att.getSfid() + "-" + att.getName().replaceAll("\\s+",""));
		
		for (Productos p : productos) {
			
			p.setStock(stockMap.get(p.getProdid()));
			
			if (parentCode != null) {
				String baseref_code = p.getBaseReference();
				//no ha comprado nunca
				if(ventasgeneralMap.get(baseref_code) == null)	p.setEstado("potencial");
				//ha comprado mas alla de 6 meses
				else if (ventasHabitualesMap.get(baseref_code) != null ) p.setEstado("inactivo");
				
				// ha comprado en los ultimos 6 meses
				if (ventasultmesesMap.get(baseref_code) != null ) {
					p.setEstado("habitual");
					for (Ventas venta : ventasultmesesMap.get(baseref_code)) {
						if(venta.getmes1()!= null) {
							p.setMes1(String.valueOf(venta.getmes1()));
						}else if(venta.getmes2()!= null) {
							p.setMes2(String.valueOf(venta.getmes2()));
						}else if(venta.getmes3()!= null) {
							p.setMes3(String.valueOf(venta.getmes3()));
						}else if(venta.getmes4()!= null) {
							p.setMes4(String.valueOf(venta.getmes4()));
						}else if(venta.getmes5()!= null) {
							p.setMes5(String.valueOf(venta.getmes5()));
						}
					}
				}
	
				p.setPhoto(fotosMap.get(p.getProdid()));
				
				/*for(Ventas v: ventasultmeses) {
					if (p != null && v != null) {
						if (p.getBaseReference() != null && v.getbase_ref_code() != null) {
							if (p.getBaseReference().equals(v.getbase_ref_code())) {
								if(v.getmes1()!= null) {
									p.setMes1(String.valueOf(v.getmes1()));
								}else if(v.getmes2()!= null) {
									p.setMes2(String.valueOf(v.getmes2()));
								}else if(v.getmes3()!= null) {
									p.setMes3(String.valueOf(v.getmes3()));
								}else if(v.getmes4()!= null) {
									p.setMes4(String.valueOf(v.getmes4()));
								}else if(v.getmes5()!= null) {
									p.setMes5(String.valueOf(v.getmes5()));
								}
							}
						}
					}
				}*/
				if (p.getMes1() == null) p.setMes1("0");
				if (p.getMes2() == null) p.setMes2("0");
				if (p.getMes3() == null) p.setMes3("0");
				if (p.getMes4() == null) p.setMes4("0");
				if (p.getMes5() == null) p.setMes5("0");
				p.setUlt5meses(p.getMes1()+';'+ p.getMes2()+';'+p.getMes3()+';'+ p.getMes4()+';'+p.getMes5()+';');
				
			}
			else {
				p.setMes1("0");
				p.setMes2("0");
				p.setMes3("0");
				p.setMes4("0");
				p.setMes5("0");
				p.setUlt5meses(p.getMes1()+';'+ p.getMes2()+';'+p.getMes3()+';'+ p.getMes4()+';'+p.getMes5()+';');
			}
				
			
		}
		ventasultmeses = null;
		ventasgeneralMap = null;
		ventasHabitualesMap = null;
		ventasultmesesMap = null;
		fotosMap = null;
		stockMap = null;
		attList = null;
		parentCode = null;
		ids = null;
		baserefs = null;
		inactiveProds = null;
		a = b = null;
		
		
		
		String subbrand;
		String line;	
		for (Productos p : productos) {
			
			//creamos la jerarquia de productos 
			if ((p.getStock() != null && p.getStock() > 0) || !p.getStatus().equals("P") || p.getActivepromo() || p.getLongpromo()) {
				//System.out.println(p.getDescription());
				if (p.getSubbrand() == null) subbrand = "No subbrand";
				else subbrand = p.getSubbrand();
				if (p.getLine() == null) line = "No line";
				else line = p.getLine();
				if(!products.containsKey(subbrand))						products.put(subbrand, new LinkedHashMap<String, List<Productos>>());
	            if(!products.get(subbrand).containsKey(line))			products.get(subbrand).put(line, new ArrayList<Productos>());
	            if(!products.get(subbrand).get(line).contains(p))		products.get(subbrand).get(line).add(p);
			}
		}
		
		List<WsPedido> lstpedido = new ArrayList<WsPedido>();
		WsPedido wsp;
		Map<String,List<Productos>> m1;
		WsPedidoLines wpl;
		List<Productos> l;
		WsProduct wp;
		String prodtype;
		String tipo;
		//mapeamos los campos
		for(String k : products.keySet()) {	
			wsp = new WsPedido();
			wsp.setName(k);
			m1 = products.get(k);
		    for(String k1 : m1.keySet()) {	
				wpl = new WsPedidoLines();
		    	wpl.setName(k1);
				l = m1.get(k1);
		        for (Productos p : l) {      	
					wp = new WsProduct();
		        	wp.setSubbrand(p.getSubbrand());
					wp.setCode(p.getCode());
					wp.setNameP(p.getDescription());
					wp.setPromo("normal");
					if (p.getStatus().equals("P") && !p.getLongpromo() && !p.getActivepromo()) wp.setPromo("promo");
					else if (p.getStatus().equals("P") && p.getLongpromo()) wp.setPromo("verde");
				    else if (p.getStatus().equals("P") && p.getActivepromo()) wp.setPromo("roja");
					wp.setLine(p.getLine());
					wp.setStock(p.getStock());
					wp.setWeight(p.getWeight());
					wp.setBoxesxpalet(p.getPalet());
					wp.setUnitsbox(p.getUnits());
					wp.setEstado(p.getEstado());
					prodtype = (p.getItemtype() != null) ? p.getItemtype() : p.getItemclass();
					tipo = ((prodtype.equals("MER") || prodtype.equals("MERCH") || prodtype.equals("ZMER")) ? "merch" : (prodtype.equals("DSP")  ? "display" : "regular"));
					wp.setItemtype(tipo);
					if (p.getRoundingProfile() != null && p.getRoundingProfile().equals("ZCSC")) wp.setRoundingProfile(true);
					else wp.setRoundingProfile(false);
					wpl.addProductsItem(wp);
					wp.setLast5months(p.getUlt5meses());
		        }
		        wsp.addLinesItem(wpl);
		    }
			lstpedido.add(wsp);
		}
		wsp = null;
		m1 = null;
		wpl = null;
		l = null;
		wp = null;
		productos = null;
		line = null;
		subbrand = null;
		tipo = null;
		prodtype = null;
		products = null;
		return lstpedido;
	}

	public List<WsProduct> getLastOrderProducts(String shiptoid) {
//		Account a = this.accountRepository.findBySfid(shiptoid);
//		if (a == null) return null;
//		if (a.getLkpLastOrderC() == null) return Collections.emptyList();
		
		
		String recTypeidPresu = this.recordTypeRepository.findRecordType("Presupuesto", "Order");
		Order order = this.orderRepository.findLastOrder(shiptoid, recTypeidPresu, PageRequest.of(0,1));
			
		Iterable<Orderitem> items = this.itemsRepository.findByOrderid(order.getSfid());
		order= null;
		return this.orderMapper.lastorder2web(items);
	}

	//crear pedido 
	public List<WsOrderResp> createOrder(WsNewOrder body) {
		String accountid = body.getShiptoid();
		Account a = this.accountRepository.findBySfid(accountid);
		TypeEnum typeenum = body.getType();
		String type = typeenum.toString();
		//recuperamos el primer pedido draft
		Optional<Order> op1 = this.orderRepository.findById(body.getIdorder1());
		Order o1 = new Order();
		Order o2 = new Order();
		if (op1.isPresent()) o1 = op1.get();
		else return null;
		//recuperamos el segundo pedido draft
		Optional<Order> op2 = this.orderRepository.findById(body.getIdorder2());
		if (op2.isPresent()) o2 = op2.get();
		else return null;
		
		//20201203 Inicio RMS
		o1.setTxtCustomerorderidC(body.getPedidoClienteId());
		o2.setTxtCustomerorderidC(body.getPedidoClienteId());
		//20201203 Fin RMS

		SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd");
		Boolean o2Deleted = false;
		Boolean o1Deleted = false;
		Boolean budgetCreated = false;
		String recTypeidOrder = this.recordTypeRepository.findRecordType("Tablet", "Order"); 
		String recTypeidPresu = this.recordTypeRepository.findRecordType("Presupuesto", "Order"); 
		String recTypeidLack = this.recordTypeRepository.findRecordType("Carencias de pedidos", "Lack__c"); 
		Order budget = null;
		String budgetid = body.getBudgetid();
		List<WsOrderResp> resp = new ArrayList<WsOrderResp>();
		//si la app ha recuperado presupuesto, vaciamos la info y borramos los prouctos
		if (budgetid != null && !budgetid.equals("")) {
			budget = this.orderRepository.findBySfid(budgetid);
			budget.setConditionTypeC(null);
			budget.setConditionTypeValueC(null);
			budget.setOrderCommentC(null);
			budget.setTxtOrderToDoC(null);
			Iterable<Orderitem> products = this.orderitemRepository.findByOrderid(budget.getSfid());
			this.orderitemRepository.deleteAll(products);
		}
		try {
			//si se crea presupuesto
			if (type.equals("presupuesto")) {	
				//si no hay pedido ni carencias borramos un draft
				if (body.getOrder() != null && body.getOrder().getComments() == null && body.getOrder().getProducts().size() == 0 && 
						body.getLack() != null && body.getLack().getProducts().size() == 0) 
					{
					//this.orderRepository.deleteById(o1.getId());
					o1Deleted = true;
					}
				//si hay pedido y carencias llenamos info
				else {
					//si hemos recuperado un presupuesto lo usamos
					if (budget != null) {
						//this.orderRepository.deleteById(o1.getId());
						o1 = budget;
						budgetCreated = true;
					}
					if (o1.getHcLastop().equals("FAILED")) return null;
					if (o1.getSfid() == null || o1.getHcLastop().equals("PENDING")) {
						WsOrderResp wss = new WsOrderResp();
						wss.setType("Sincronizando");
						resp.add(wss);
						return resp;
					}
					if (body.getDeliverydate() != null && !body.getDeliverydate().equals("")) o1.setDttPlannedDeliveryDateC(formatter6.parse(body.getDeliverydate()));
					o1.setRecordtypeid(recTypeidPresu);
					o1.setConditionTypeC(body.getConditionType());
					o1.setConditionTypeValueC(body.getConditionTypeValue());
					if (body.getVisitType() != null && !body.getVisitType().equals("")) o1.setPckOriginC(body.getVisitType());
				}
				//si hay recogida llenamos info
				if (body.getCollect() != null && body.getCollect().getProducts().size() > 0) {
					//si hemos recuperado un presupuesto lo usamos
					if (budget != null && !budgetCreated) {
						//this.orderRepository.deleteById(o2.getId());
						o2 = budget;
						budgetCreated = true;
					}
					if (o2.getHcLastop().equals("FAILED")) return null;
					if (o2.getSfid() == null || o2.getHcLastop().equals("PENDING")) {
						if (resp.isEmpty()) {
							WsOrderResp wss = new WsOrderResp();
							wss.setType("Sincronizando");
							resp.add(wss);
							return resp;
						}
					}
					if (body.getDeliverydate() != null && !body.getDeliverydate().equals("")) o2.setDttPlannedDeliveryDateC(formatter6.parse(body.getDeliverydate()));
					o2.setChkCollectionC(true);
					o2.setRecordtypeid(recTypeidPresu);
					if (body.getVisitType() != null && !body.getVisitType().equals("")) o2.setPckOriginC(body.getVisitType());
				}
				//si no hay recogida borramos draft
				else {
					//this.orderRepository.deleteById(o2.getId());
					o2Deleted = true;
				}
			}
			
			//la misma logica aplicada ha si se crea un pedido
			else {
				if (body.getOrder() != null && body.getOrder().getComments() == null && body.getOrder().getProducts().size() == 0 && 
						body.getLack() != null && body.getLack().getProducts().size() == 0) {
					//this.orderRepository.deleteById(o1.getId());
					o1Deleted = true;
				}
				else  {
					if (budget != null) {
						//this.orderRepository.deleteById(o1.getId());
						o1 = budget;
						budgetCreated = true;
					}
					if (o1.getHcLastop().equals("FAILED")) return null;
					if (o1.getSfid() == null || o1.getHcLastop().equals("PENDING")) {
						WsOrderResp wss = new WsOrderResp();
						wss.setType("Sincronizando");
						resp.add(wss);
						return resp;
					}
					if (body.getDeliverydate() != null && !body.getDeliverydate().equals("")) o1.setDttPlannedDeliveryDateC(formatter6.parse(body.getDeliverydate()));
					o1.setRecordtypeid(recTypeidOrder);
					o1.setConditionTypeC(body.getConditionType());
					o1.setConditionTypeValueC(body.getConditionTypeValue());
					if (body.getVisitType() != null && !body.getVisitType().equals("")) o1.setPckOriginC(body.getVisitType());
				}
				
				if (body.getCollect() != null && body.getCollect().getProducts().size() > 0) {
					if (budget != null && !budgetCreated) {
						//this.orderRepository.deleteById(o2.getId());
						o2 = budget;
						budgetCreated = true;
					}
					if (o2.getHcLastop().equals("FAILED")) return null;
					if (o2.getSfid() == null || o2.getHcLastop().equals("PENDING")) {
						if (resp.isEmpty()) {
							WsOrderResp wss = new WsOrderResp();
							wss.setType("Sincronizando");
							resp.add(wss);
							return resp;
						}
					}
					if (body.getDeliverydate() != null && !body.getDeliverydate().equals("")) o2.setDttPlannedDeliveryDateC(formatter6.parse(body.getDeliverydate()));
					o2.setChkCollectionC(true);
					o2.setRecordtypeid(recTypeidOrder);
					if (body.getVisitType() != null && !body.getVisitType().equals("")) o2.setPckOriginC(body.getVisitType());
				}
				else {
					//this.orderRepository.deleteById(o2.getId());
					o2Deleted = true;
				}
				
			}	
		}
		catch (java.text.ParseException ep) {
            ep.printStackTrace();
		}
		/*if ((standardPricebook != null && standardPricebook.size() > 0)) {
			String stdPricebookId = standardPricebook.get(0).getSfid();		
			order.setPckWarehouseCodeC(a.getPckCustomerWarehouseCodeC());
			order.setEffectivedate(Calendar.getInstance().getTime());
			order.setAccountid(accountid);
			order.setPricebook2id(stdPricebookId);
			order.setStatus("Draft");
			order = this.orderRepository.save(order);
			
		
		}*/
		//Nuevos productos carrito entrega
		WsNewOrderOrder pedido = body.getOrder();
		List<WsProdsToRateProducts> WsProductos = new ArrayList<WsProdsToRateProducts>();
		if (pedido != null && !o1Deleted) WsProductos = pedido.getProducts();
		List<Orderitem> itemsOrderToInsert = new ArrayList<Orderitem>();
		List<Orderitem> itemsCollectToInsert = new ArrayList<Orderitem>();
		pedido = null;
		//llenamos los registros orderitem con los productos del pedido
		if (WsProductos.size() > 0) {
			
			Map<String, Integer> mapWsProductos = new HashMap<String, Integer>();
			
			Map<String, Product2> mapProductos = new HashMap<String, Product2>();
		
			for (WsProdsToRateProducts p : WsProductos) mapWsProductos.put(p.getCode(), p.getQuantity());
			Iterable<Product2> productos = this.productRepository.findProductsByCode(mapWsProductos.keySet());
			for (Product2 p : productos) mapProductos.put(p.getProductcode(), p);
			Pricebookentry pricebookEntry;
			Product2 p;
			Double weight;
			Orderitem oi;
			for (String prodId : mapWsProductos.keySet()) {	
				pricebookEntry = getPricebookEntry((mapProductos.get(prodId).getSfid()));
				if (pricebookEntry != null) {			
					p = mapProductos.get(prodId);		
					weight = p.getNumWeightC();
					weight = (weight == null) ? 0.0 : weight*mapWsProductos.get(prodId);		
					oi = new Orderitem();	
					oi.setOrderid(o1.getSfid());
					oi.setProduct2id(p.getSfid());
					oi.setPricebookentryid(pricebookEntry.getSfid());
					oi.setUnitprice(pricebookEntry.getUnitprice());
					oi.setQuantity(Double.valueOf(mapWsProductos.get(prodId)));
					oi.setNumKgC(weight);
					oi.setPckUnitOfMeasureC("UN");
					itemsOrderToInsert.add(oi);
				}
			}
			mapWsProductos = null;
			mapProductos = null;
			productos = null;
			pricebookEntry = null;
			p = null;
			oi = null;
		}
		List<WsNewOrderCollectProducts> WsRecogida = new ArrayList<WsNewOrderCollectProducts>();
		if (body.getCollect() != null && !o2Deleted) {
			WsRecogida = body.getCollect().getProducts();
		}
		//lenamos los registros orderitem con los productos de la recogida
		if (WsRecogida.size() > 0) {
			
			List<String> prodIds = new ArrayList<String>();
			Map<String, Product2> mapProductos = new HashMap<String, Product2>();
			for (WsNewOrderCollectProducts ws : WsRecogida) prodIds.add(ws.getCode());
			Iterable<Product2> productos = this.productRepository.findProductsByCode(prodIds);
			for (Product2 p : productos) mapProductos.put(p.getProductcode(), p);
			Pricebookentry pricebookEntry;	
			Product2 p;
			Double weight;
			Orderitem oi;
			for (WsNewOrderCollectProducts collectProd : WsRecogida) {
				pricebookEntry = getPricebookEntry((mapProductos.get(collectProd.getCode()).getSfid()));
				if (pricebookEntry != null) {	
					p = mapProductos.get(collectProd.getCode());
					weight = p.getNumWeightC();
					weight = (weight == null) ? 0.0 : weight*collectProd.getQuantity();
					oi = new Orderitem();
					oi.setOrderid(o2.getSfid());
					oi.setProduct2id(p.getSfid());
					oi.setPricebookentryid(pricebookEntry.getSfid());
					oi.setUnitprice(pricebookEntry.getUnitprice());
					oi.setQuantity(Double.valueOf(collectProd.getQuantity()));
					oi.setNumKgC(weight);
					oi.setPckUnitOfMeasureC("UN");
					oi.setChkPayableC(collectProd.isPayable());
					itemsCollectToInsert.add(oi);
				}
				
			}
			oi = null;
			productos = null;
			mapProductos = null;
			pricebookEntry = null;
			p = null;
			prodIds = null;
		}
		Double totalWeight = 0.0;
		Product2 p;
		boolean isDisplay;
		Double unitsBox;
		Double itemQuantity;
		//actualizamos info del pedido
		for (Orderitem oi : itemsOrderToInsert) {
			p = this.productRepository.findBySfid(oi.getProduct2id());
			totalWeight += (oi.getNumKgC() == null) ? 0.0 : oi.getNumKgC();
			isDisplay = ((p.getPckItemTypeC() != null && p.getPckItemTypeC().equals("DSP")) || (p.getPckItemClassC() != null && p.getPckItemClassC().equals("FG-DISPL")));
			if (isDisplay && p.getNumUnitsBoxC() != null) {
				unitsBox = p.getNumUnitsBoxC();
				itemQuantity = oi.getQuantity()/unitsBox;
                oi.setPckUnitOfMeasureC("CJ");
                oi.setQuantity(itemQuantity);
			}
		}
		if (!o1Deleted) o1.setNumKgPlannedC(totalWeight);
		
		totalWeight = 0.0;
		//actualizamos info de la recogida
		for (Orderitem oi : itemsCollectToInsert) {
			p = this.productRepository.findBySfid(oi.getProduct2id());
			totalWeight += (oi.getNumKgC() == null) ? 0.0 : oi.getNumKgC();
			isDisplay = ((p.getPckItemTypeC() != null && p.getPckItemTypeC().equals("DSP")) || (p.getPckItemClassC() != null && p.getPckItemClassC().equals("FG-DISPL")));
			if (isDisplay && p.getNumUnitsBoxC() != null) {
				unitsBox = p.getNumUnitsBoxC();
                itemQuantity = oi.getQuantity()/unitsBox;
                oi.setPckUnitOfMeasureC("CJ");
                oi.setQuantity(itemQuantity);
			}
		}
		
		p = null;
		//insertamos productos
		if (itemsCollectToInsert.size() > 0) this.orderitemRepository.saveAll(itemsCollectToInsert);
		if (WsProductos.size() > 0)this.orderitemRepository.saveAll(itemsOrderToInsert);
		
		
		//confirmamos el pedido y si es presupuesto ponemos Budget
		if (!o1Deleted || body.getLack().getProducts().size() > 0) {
			o1.setStatus("Confirmed");
			if (o1.getRecordtypeid().equals(recTypeidPresu)) o1.setStatus("Budget");
		}
		
		//comentarios
		if (body.getOrder() != null && body.getOrder().getComments() != null && body.getOrder().getComments() != "") o1.setOrderCommentC(body.getOrder().getComments());
		//todo
		if (body.getToDo() != null && body.getToDo() != "") o1.setTxtOrderToDoC(body.getToDo());
		//actualizamos stock
		if (!o1Deleted) updateStocks(itemsOrderToInsert, a.getPckCustomerWarehouseCodeC());
		//si hay recogida
		if (o2 != null && !o2Deleted) {
			o2.setNumKgPlannedC(totalWeight);
			o2.setStatus("Confirmed");
			if (o2.getRecordtypeid().equals(recTypeidPresu)) o2.setStatus("Budget");
			if (body.getCollect() != null && body.getCollect().getComments() != null && body.getCollect().getComments() != "") o2.setOrderCommentC(body.getCollect().getComments());
		}
		
		//actualizamos pedidos
		if (!o1Deleted || body.getLack().getProducts().size() > 0) this.orderRepository.save(o1);
		if (o2 != null && !o2Deleted) this.orderRepository.save(o2);
		List<LackC> carencias = new ArrayList<LackC>();
		
		//añadimos carencias
		if (body.getLack().getProducts().size() > 0) {
			List<WsNewOrderLackProducts> carenciasToInsert = body.getLack().getProducts();
			LackC lack;
			Product2 lackprod;
			for (WsNewOrderLackProducts wslack : carenciasToInsert) {
				lack = new LackC();	
				lackprod = this.productRepository.findByProductcode(wslack.getCode());
				lack.setLkpItemC(lackprod.getSfid());
				lack.setMdAccountC(accountid);
				lack.setLkpOrderC(o1.getSfid());
				lack.setRecordtypeid(recTypeidLack);
				lack.setPckStatusC("Abierta");
				lack.setNumAvailableUnitsC(new Double(wslack.getAvailableUnits()));
				lack.setNumMissingUnitsC(new Double(wslack.getMissingUnits()));
				carencias.add(lack);
			}	
			if (carencias.size() > 0) this.lackRepository.saveAll(carencias);	
			
			carenciasToInsert = null;
			lack = null;
			lackprod = null;
			
		}		
		WsProductos = null;
		WsRecogida = null;
		recTypeidOrder = null; 
		recTypeidPresu = null; 
		recTypeidLack = null; 
		a = null;	
		carencias = null;
		itemsOrderToInsert = null;
		itemsCollectToInsert = null;
		budget = null;
		//this.itemsRepository.saveAll(itemsToInsert);
		if (!o1Deleted) return resp;
		if (!o2Deleted) return resp;
		return null;
		
	}

	//actualizar stcok en sf
	private void updateStocks(List<Orderitem> itemsOrderToInsert, String pckCustomerWarehouseCodeC) {
		Map<String, Orderitem> shopCartMap = new HashMap<String, Orderitem>();
		for (Orderitem item : itemsOrderToInsert) shopCartMap.put(item.getProduct2id(), item);
		List<LightStockC> stocks = this.stockRepository.findStockList(shopCartMap.keySet(), pckCustomerWarehouseCodeC);
		Orderitem v;
		Double quantitySF;
		Double quantityTablet;
		Double result;
		for (LightStockC stock : stocks) {	
			v = shopCartMap.get(stock.getLkpItemC());
			quantitySF = stock.getFrmlRealStockC();
			quantityTablet = v.getQuantity();
			result = quantitySF - quantityTablet;
			stock.setNumQuantityC(result);
		}
		v = null;
		quantitySF = null;
		quantityTablet = null;
		result = null;
		this.stockRepository.saveAll(stocks);
		
	}

	private Pricebookentry getPricebookEntry(String prodId) {
		List<Pricebookentry> pricebookEntry = this.pricebookentryRepository.findPriceBookEntry(prodId, PageRequest.of(0,1));
		if (pricebookEntry != null && pricebookEntry.size() > 0) return pricebookEntry.get(0);
		return null;
	}

	//creamos dos pedidos draft (pedido y recogida)
	public WsIdOrder createDraftOrder(String shiptoid, String username) {
		Account a = this.accountRepository.findBySfid(shiptoid);
		if (a == null) return null;
		List<Pricebook2> standardPricebook = this.pricebook2Repository.findStandardPricebook(PageRequest.of(0,1));
		Order order = new Order();
		Order order2 = new Order();
		if ((standardPricebook != null && standardPricebook.size() > 0)) {
			
			String stdPricebookId = standardPricebook.get(0).getSfid();		
			order.setPricebook2id(stdPricebookId);
			order2.setPricebook2id(stdPricebookId);
			
			order.setPckWarehouseCodeC(a.getPckCustomerWarehouseCodeC());
			order2.setPckWarehouseCodeC(a.getPckCustomerWarehouseCodeC());
			
			order.setEffectivedate(Calendar.getInstance().getTime());
			order2.setEffectivedate(Calendar.getInstance().getTime());
			
			order.setAccountid(shiptoid);
			order2.setAccountid(shiptoid);
			
			order.setOwnerid(username);
			order2.setOwnerid(username);

			order.setStatus("Draft");
			order2.setStatus("Draft");
			
			String recTypeidOrder = this.recordTypeRepository.findRecordType("Tablet", "Order"); 
			order.setRecordtypeid(recTypeidOrder);
			order2.setRecordtypeid(recTypeidOrder);
			
			UUID uuid1 = UUID.randomUUID();
	        String randomUUIDString1 = uuid1.toString();
	        order.setHerokuExternalIdC(randomUUIDString1);
	        
	        UUID uuid2 = UUID.randomUUID();
	        String randomUUIDString2 = uuid2.toString();
	        order2.setHerokuExternalIdC(randomUUIDString2);
	        
			order = this.orderRepository.save(order);
			order2 = this.orderRepository.save(order2);
			stdPricebookId = null;
			recTypeidOrder = null;
			uuid1 = null;
			uuid2 = null;
			randomUUIDString1 = null;
			randomUUIDString2 = null;
		
		}
		WsIdOrder WSorder = new WsIdOrder();
		if (order != null) WSorder.setOrder1(order.getId());
		if (order2 != null) WSorder.setOrder2(order2.getId());
		a = null;
		standardPricebook = null;
		order = null;
		order2 = null;
		return WSorder;
	}

	//comprobamos que los productos que llegan de app tiene stock y son visibles para el cliente
	public List<WsOrderResp> checkStockVisibility(WsNewOrder body) {
		if (body.getOrder() != null && body.getOrder().getComments() == null && body.getOrder().getProducts().size() == 0 && 
				body.getLack() != null && body.getLack().getProducts().size() == 0) return new ArrayList<WsOrderResp>();
		WsNewOrderOrder pedido = body.getOrder();
		List<WsProdsToRateProducts> WsProductos = pedido.getProducts();
		if (WsProductos != null) {
			Map<String, Integer> mapWsProductos = new HashMap<String, Integer>();
			Map<String, Product2> mapProductos = new HashMap<String, Product2>();
			List<WsOrderResp> response = new ArrayList<WsOrderResp>();
			for (WsProdsToRateProducts p : WsProductos) mapWsProductos.put(p.getCode(), p.getQuantity());
			Iterable<Product2> productos = this.productRepository.findProductsByCode(mapWsProductos.keySet());
			for (Product2 p : productos) mapProductos.put(p.getSfid(), p);
			String accountid = body.getShiptoid();
			Account a = this.accountRepository.findBySfid(accountid);
			if (a == null) return null;
			List<String> inactiveProds = new ArrayList<String>();
			Account b = new Account();
			if (a.getParentid() != null) {
				b = this.accountRepository.findBySfid(a.getParentid());
				if (b == null) return null;
				inactiveProds = this.prodListRepository.findInactiveProds(a.getParentid());
			}
			if (inactiveProds == null || inactiveProds.isEmpty()) inactiveProds.add("");
			List<String> prodlist = new ArrayList<String>();
			if (b != null) prodlist = this.productRepository.findVisibleProds(b.getPckSalesEntityC(), b.getPckChannelC(), inactiveProds, mapProductos.keySet());
			Map<String, String> visiblecodes = new HashMap<String, String>();
			for (String code : prodlist) visiblecodes.put(code, code);
			List<LightStockC> stocks = this.stockRepository.findStockList(mapProductos.keySet(), a.getPckCustomerWarehouseCodeC());
			Map<String, Double> stockMap = new HashMap<String, Double>();
			for (LightStockC stock : stocks) stockMap.put(stock.getLkpItemC(), stock.getFrmlRealStockC());
			Integer quantity;
			WsOrderResp wss;
			Double stock;
			for (Product2 prod : productos) {
				if (visiblecodes.get(prod.getProductcode()) == null) {								
					wss = new WsOrderResp();
					wss.setCode(prod.getProductcode());
					wss.setType("No Visible");
					response.add(wss);
				}
				else {
					stock = stockMap.get(prod.getSfid());
					quantity = mapWsProductos.get(prod.getProductcode());
					wss = new WsOrderResp();
					if ((Double.valueOf(stock) - quantity) < 0) {
						wss.setCode(prod.getProductcode());
						if (stock < 0) wss.setNewQuantity(0.0);
						else wss.setNewQuantity(stock);
						wss.setType("No Stock");
						response.add(wss);
					}
				}
			}
			/*for (LightStockC stock : stocks) {		
				p = mapProductos.get(stock.getLkpItemC());
				quantity = mapWsProductos.get(p.getProductcode());
				wss = new WsOrderResp();
				if ((Double.valueOf(stock.getFrmlRealStockC()) - quantity) < 0) {
					wss.setCode(p.getProductcode());
					if (stock.getFrmlRealStockC() < 0) wss.setNewQuantity(0.0);
					else wss.setNewQuantity(stock.getFrmlRealStockC());
					wss.setType("No Stock");
					response.add(wss);
				}
			}*/
			visiblecodes = null;
			stockMap = null;
			pedido = null;
			WsProductos = null;
			mapWsProductos = null;
			mapProductos = null;
			productos = null;
			accountid = null;
			a = null;
			stocks = null;
			quantity = null;
			wss = null;
			return response;
		}
		else return null;
	}

	public void deleteDraftOrder(WsIdOrder body) {
		if (body.getOrder1() != null) {
			Optional<Order> o1 = this.orderRepository.findById(body.getOrder1());
			//if (o1.isPresent()) this.orderRepository.deleteById(body.getOrder1());
		}
		if (body.getOrder2() != null) {
			Optional<Order> o2 = this.orderRepository.findById(body.getOrder2());
			//if (o2.isPresent()) this.orderRepository.deleteById(body.getOrder2());
		}
		
	}
	
	
	//resumen diario de pedidos
	public WsOrderSumm orderSummary(String username) {
		Calendar c1 = Calendar.getInstance(); 
		c1.add(Calendar.DATE, -1);
		int dayofweek = c1.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) c1.add(Calendar.DATE, -2);
		Calendar c2 = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		//pedidos de ayer confirmados y de hoy urgentes
		List<OrderSummary> pedidos = this.orderRepository.findOrderSummary(username, c1.getTime(), c2.getTime());
		c1 = null;
		c2 = null;
		if (pedidos.size() > 0) {
			Map<String, OrderSummary> mapOrders = new HashMap<String, OrderSummary>();
			Double totalEuros = 0.0;
			Double totalKG = 0.0;
			for (OrderSummary o : pedidos) {
				if (o.getTotalEuros() != null)
				mapOrders.put(o.getOrderId(), o);
				if (o.getTotalEuros() != null) totalEuros += o.getTotalEuros();
				if (o.getTotalKG() != null) totalKG += o.getTotalKG();
			}
			Iterable<Orderitem> items = this.orderitemRepository.findAllByOrderid(mapOrders.keySet());
			Map<String, List<Orderitem>> mapOrderProducts = new HashMap<String, List<Orderitem>>();
			Set<String> itemIds = new HashSet<String>();
			List<Orderitem> orderitems;
			for (Orderitem oi : items) {
				orderitems = mapOrderProducts.get(oi.getOrderid());
				if (orderitems == null) {
					orderitems = new ArrayList<>();
					mapOrderProducts.put(oi.getOrderid(), orderitems);
				}
				orderitems.add(oi);
				itemIds.add(oi.getProduct2id());
				//mapOrderProducts.computeIfAbsent(oi.getOrderid(), k -> new ArrayList<>()).add(oi);
			}
			orderitems = null;
			items = null;
			mapOrders = null;
			Iterable<Case> casosToDo = this.caseRepository.findToDo(mapOrderProducts.keySet());
			Map<String, Case> mapToDos = new HashMap<String, Case>();
			for (Case c : casosToDo) mapToDos.put(c.getLkpOrderC(), c);
			Iterable<Product2> products = this.productRepository.findProductsBySfid(itemIds);
			Map<String, Product2> mapProducts = new HashMap<String, Product2>();
			for (Product2 p : products) mapProducts.put(p.getSfid(), p);
			
			WsOrderSumm summary = new WsOrderSumm();
			summary.setTotalOrders(pedidos.size());
			summary.setTotalEuros(round(totalEuros,2));
			summary.setTotalKG(round(totalKG,2));
			WsOrderSummOrders WsPedido;
			Case c;
			WsOrderSummProducts WsProducts;
			Product2 product;
			for (OrderSummary o : pedidos) {
				WsPedido = new WsOrderSummOrders();
				WsPedido.setAddress(o.getAddress());
				if (o.getDeliveryDate() != null) WsPedido.setDeliveryDate(dateFormat.format(o.getDeliveryDate()));
				WsPedido.setLocalDiscount(o.getTotalDiscount());
				WsPedido.setNumOrder(o.getOrderNum());
				if (o.getTotalEuros() != null) WsPedido.setOrderEuros(round(o.getTotalEuros(),2));
				if (o.getTotalKG() != null) WsPedido.setOrderKG(round(o.getTotalKG(),2));
				WsPedido.setShiptoName(o.getShiptoName());
				WsPedido.setState(o.getState());
				WsPedido.setUser(o.getUserName());
				c = mapToDos.get(o.getOrderId());
				if (c != null && c.getDescription() != null) WsPedido.setToDo(c.getDescription());
				WsPedido.setUrgentDelivery(o.getUrgent());
				WsPedido.setCollect(o.getCollect());
				WsPedido.setConditionTypeValue(o.getConditionTypeValueC() != null ? o.getConditionTypeValueC() : 0.0); 
				for (Orderitem oi : mapOrderProducts.get(o.getOrderId())) {	
					WsProducts = new WsOrderSummProducts();
					product = mapProducts.get(oi.getProduct2id());
					WsProducts.setCode(product.getProductcode());
					WsProducts.setLineDiscount(oi.getCurTotalCommercialDiscountC());
					WsProducts.setName(product.getName());
					WsProducts.setQuantity(round(oi.getQuantity(),2));
					WsPedido.addProductsItem(WsProducts);
				}
				summary.addOrdersItem(WsPedido);
			}
			pedidos = null;
			WsPedido = null;
			WsProducts = null;
			product = null;
			mapProducts = null;
			mapOrderProducts = null;
			mapToDos = null;
			c = null;
			products = null;
			casosToDo = null;
			itemIds = null;
			totalEuros = null;
			totalKG = null;
			dateFormat = null;
			return summary;
		}
		else if (pedidos.size() == 0) {
			WsOrderSumm empty = new WsOrderSumm();
			empty.setTotalOrders(0);
			empty.setTotalEuros(0.0);
			empty.setTotalKG(0.0);
			empty.setOrders(new ArrayList<WsOrderSummOrders>());
			return empty;
		}
		return null;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	//recuperamos el ultimo presupuesto que se realizo al cliente
	public WsBudgetOrder getLastbudget(String shiptoid) {
		Account a = this.accountRepository.findBySfid(shiptoid);
		if (a == null) return null;
		String recTypeidPresu = this.recordTypeRepository.findRecordType("Presupuesto", "Order");
		Order presupuesto = this.orderRepository.findLastBudget(shiptoid, recTypeidPresu, PageRequest.of(0,1));
		if (presupuesto == null) return new WsBudgetOrder();
		Iterable<Orderitem> productos = this.orderitemRepository.findByOrderid(presupuesto.getSfid());
		WsBudgetOrder wsb = new WsBudgetOrder();
		wsb.setBudgetnum(presupuesto.getOrdernumber());
		wsb.setCreatedDate(presupuesto.getCreateddate());
		wsb.setBudgetid(presupuesto.getSfid());
		wsb.setComments(presupuesto.getOrderCommentC());
		wsb.setConditiontype(presupuesto.getConditionTypeC());
		wsb.setConditiontypevalue(presupuesto.getConditionTypeValueC());
		wsb.setTodo(presupuesto.getTxtOrderToDoC());
		Product2 p;
		WsProduct wsp;
		for (Orderitem oi : productos) {
			p = this.productRepository.findBySfid(oi.getProduct2id());
			wsp = new WsProduct();
			wsp.setNameP(p.getName());
			wsp.setCode(p.getProductcode());
			wsp.setSubbrand(p.getFrmSubbrandC());
			wsp.setLine(p.getFrmLineC());
			wsp.setWeight(p.getNumWeightC());
			wsp.setQuantity(oi.getQuantity());
			wsb.addProductsItem(wsp);
		}
		recTypeidPresu = null;
		a = null;
		presupuesto = null;
		productos = null;
		p = null;
		wsp = null;
		return wsb;
	}

	//servicio SOAP a SAPPI para la valoracion de pedidos
	public List<WsProdPrices> rateProducts(WsProdsToRate body, String shiptoid) {
		Account a = this.accountRepository.findBySfid(shiptoid);
		if (a == null) return null;
		String parentcode = a.getFrmlParentCustomerCodeC();
		String shiptocode = a.getTxtSapidC();
		//si no es cliente potencial consultamos precios en sap
		if (parentcode != null && parentcode != null) {

			Proximo.setup();
			DTZSDFSALESORDERSIMULATE3 request = new DTZSDFSALESORDERSIMULATE3();
			
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			Integer year = calendar.get(Calendar.YEAR);
			Integer month = calendar.get(Calendar.MONTH);
			Integer day = calendar.get(Calendar.DAY_OF_MONTH);	
			
			GregorianCalendar gcal = new GregorianCalendar();
			XMLGregorianCalendar fec = null;
			try {
				fec = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
			fec.setYear(year);
			fec.setMonth(month);
			fec.setDay(day);
			request.setIFDATE(fec);
			
			if (parentcode != null) request.setIFKUNNR(parentcode);
			else request.setIFKUNNR("");
			if (shiptocode != null) request.setIFKUNWE(shiptocode);
			else request.setIFKUNWE("");
			request.setIFDISTRCHAN(a.getPckChannelC());
			request.setIFSALESORG(a.getPckSalesEntityC());
			request.setIFDOCTYPE("");
			request.setITDETAIL(new DTZSDFSALESORDERSIMULATE3.ITDETAIL());
			DTZSDFSALESORDERSIMULATE3.ITDETAIL.Item item;
			//llenamos la info de los productos
			for (WsProdsToRateProducts rate : body.getProducts()) {
				Product2 product = this.productRepository.findByProductcode(rate.getCode());
				item = new DTZSDFSALESORDERSIMULATE3.ITDETAIL.Item();
				item.setMATNR(rate.getCode());
				BigDecimal menge = new BigDecimal(rate.getQuantity());
				item.setMENGE(menge);
				String vrkme = product.getPckItemTypeC();
				if (vrkme == null) vrkme = "UN";
				vrkme = (vrkme.equals("DSP")) ? "CJ" : "UN";
				item.setVRKME(vrkme);
				//item.setCONDTYPE(body.getCondType());
				//BigDecimal condvalue = new BigDecimal(body.getCondValue());
				//item.setCONDVALUE(condvalue);
				request.getITDETAIL().getItem().add(item);
			}
			DTZSDFSALESORDERSIMULATE3.LTORDERITEMSOUT orderitemsout = new DTZSDFSALESORDERSIMULATE3.LTORDERITEMSOUT();
			DTZSDFSALESORDERSIMULATE3.LTORDERITEMSOUT.Item  itemout = new DTZSDFSALESORDERSIMULATE3.LTORDERITEMSOUT.Item();
			orderitemsout.getItem().add(itemout);
			request.setLTORDERITEMSOUT(orderitemsout);
			
			DTZSDFSALESORDERSIMULATE3RESPONSE response = null;
			try {
				response = this.priceService.getPrices(request);
			}
			//cerramos conexion con el proxy
			catch (Exception e){
				System.setProperty("java.net.useSystemProxies", "true");
				System.clearProperty("socksProxyHost");
				return null;
				
			}
			//cerramos conexion con el proxy
			if (response == null) {
				System.setProperty("java.net.useSystemProxies", "true");
				System.clearProperty("socksProxyHost");
				return null;
			}
			
			List<WsProdPrices> prices = new ArrayList<WsProdPrices>();
			WsProdPrices price;
			for (DTZSDFSALESORDERSIMULATE3RESPONSE.LTORDERITEMSOUT.Item product : response.getLTORDERITEMSOUT().getItem()) {	
				price = new WsProdPrices();
				Double subtotal5 = Double.valueOf(product.getSUBTOTAL5());
				subtotal5 = round(subtotal5/100,2);
				Double subtotal2 = Double.valueOf(product.getSUBTOTAL2());
				subtotal2 = round(subtotal2/100,2);
				Double subtotal3 = Double.valueOf(product.getSUBTOTAL3());
				subtotal3 = round(subtotal3/100,2);
				Double netvaule1 = product.getNETVALUE1().doubleValue();
				Double tax = product.getTXDOCCUR().doubleValue();
				Double finalvalue = netvaule1 + tax;
				price.setTotalrate(subtotal5);
				Double dcmDtoCommercialDiscount = subtotal2 - subtotal5;
				if (dcmDtoCommercialDiscount < 0.0) dcmDtoCommercialDiscount = dcmDtoCommercialDiscount*-1;
				price.setDtoCommercialDiscount(round(dcmDtoCommercialDiscount, 2));
				Double dcmDtoFinancialDiscount = subtotal3 - subtotal5 - (subtotal2 - subtotal5);
				if (dcmDtoFinancialDiscount < 0.0) dcmDtoFinancialDiscount = dcmDtoFinancialDiscount*-1;
				price.setDtoFinancialDiscount(round(dcmDtoFinancialDiscount, 2));
				price.setNetvalue(netvaule1);
				price.setTax(tax);
				price.setFinalvalue(round(finalvalue, 2));
				String code = product.getMATERIAL();
				code = code.replaceFirst("^0+(?!$)", "");
				price.setCode(code);
				prices.add(price);
			}
			//cerramos conexion con el proxy
			System.setProperty("java.net.useSystemProxies", "true");
			System.clearProperty("socksProxyHost");
			return prices;
		}
		
		//si es cliente potencial consultamos precios en salesforce
		else {
			List<WsProdPrices> prices = new ArrayList<WsProdPrices>();
			WsProdPrices price;
			Product2 product;
			Pricebookentry pricebookEntry;
			String salesentity = a.getPckSalesEntityC();
			String channel = a.getPckChannelC();
			Pricebook2 pricebookSales = this.pricebook2Repository.findPricebookBySalesChannel(salesentity, channel);
			if (pricebookSales != null) {
				for (WsProdsToRateProducts rate : body.getProducts()) {
					product = this.productRepository.findByProductcode(rate.getCode());
					String pricebookSalesid = pricebookSales.getSfid();
					String productid = product.getSfid();
					pricebookEntry = this.pricebookentryRepository.findPricePotential(pricebookSalesid, productid);
					price = new WsProdPrices();
					price.setCode(rate.getCode());
					price.setDtoCommercialDiscount(0.0);
					price.setDtoFinancialDiscount(0.0);
					if (pricebookEntry != null) price.setFinalvalue(pricebookEntry.getUnitprice() * rate.getQuantity());
					else price.setFinalvalue(0.0);
					price.setNetvalue(0.0);
					price.setTax(0.0);
					if (pricebookEntry != null) price.setTotalrate(pricebookEntry.getUnitprice() * rate.getQuantity());
					else price.setTotalrate(0.0);
					prices.add(price);
				}
			}
			return prices; 
		}
	}
	
	//antes de crear el pedido, verificamos si existen los dos pedidos drafts. sino existen creamos otros dos
	public WsIdOrder verifyDraft(String shiptoid, String username, WsIdOrder idorder) {
		WsIdOrder resp = new WsIdOrder();
		Boolean draft1ok = false;
		Boolean draft2ok = false;
		resp.setConfirmed(false);
		if (idorder.getOrder1() != null) {
			Optional<Order> o1 = this.orderRepository.findById(idorder.getOrder1());
			Order draft1 = new Order();
			if (o1.isPresent()) {
				draft1 = o1.get();
				if (!draft1.getHcLastop().equals("FAILED") && draft1.getStatus().equals("Draft")) {
					draft1ok = true;
				}
				else if (!draft1.getHcLastop().equals("FAILED") && draft1.getStatus().equals("Confirmed")) {
					draft1ok = true;
					resp.setConfirmed(true); 
				}
			}
		}
		if (idorder.getOrder2() != null) {
			Optional<Order> o2 = this.orderRepository.findById(idorder.getOrder2());
			Order draft2 = new Order();
			if (o2.isPresent()) {
				draft2 = o2.get();
				if (!draft2.getHcLastop().equals("FAILED") && draft2.getStatus().equals("Draft"))  {
					draft2ok = true;
				}
				else if (!draft2.getHcLastop().equals("FAILED") && draft2.getStatus().equals("Confirmed")) {
					draft2ok = true;
					resp.setConfirmed(true); 
				}
			}
		}
		if ((draft1ok && draft2ok) || resp.isConfirmed()) {
			resp.setOrder1(null);
			resp.setOrder2(null);
		}
		else {
			resp = createDraftOrder(shiptoid, username);
			System.out.println("DRAFT VERIFICADO ORDERS IDS: " + resp.getOrder1() + " " + resp.getOrder2());
		}
		return resp;
	}

}
