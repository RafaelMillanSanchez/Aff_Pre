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

import com.minsait.affinity.jpa.model.Account;
import com.minsait.affinity.jpa.model.Case;
import com.minsait.affinity.jpa.model.CheckVisitC;
import com.minsait.affinity.jpa.model.Contact;
import com.minsait.affinity.jpa.model.ShipToPictureC;
import com.minsait.affinity.jpa.model.SpotCheckC;
import com.minsait.affinity.jpa.model.Task;
import com.minsait.affinity.service.SalesforceAccountService;
import com.minsait.affinity.web.api.AccountApi;
import com.minsait.affinity.web.model.WsAccount;
import com.minsait.affinity.web.model.WsAcuerdos;
import com.minsait.affinity.web.model.WsAddress;
import com.minsait.affinity.web.model.WsAltaResp;
import com.minsait.affinity.web.model.WsBudget;
import com.minsait.affinity.web.model.WsCampaigns;
import com.minsait.affinity.web.model.WsCase;
import com.minsait.affinity.web.model.WsCheckPromos;
import com.minsait.affinity.web.model.WsCheckSurtidos;
import com.minsait.affinity.web.model.WsChecks;
import com.minsait.affinity.web.model.WsCierreVisita;
import com.minsait.affinity.web.model.WsCualiObj;
import com.minsait.affinity.web.model.WsDatosAdmin;
import com.minsait.affinity.web.model.WsDirectClient;
import com.minsait.affinity.web.model.WsEvolution;
import com.minsait.affinity.web.model.WsFiltro;
import com.minsait.affinity.web.model.WsFrec;
import com.minsait.affinity.web.model.WsIS;
import com.minsait.affinity.web.model.WsMessage;
import com.minsait.affinity.web.model.WsNewContact;
import com.minsait.affinity.web.model.WsNewDirect;
import com.minsait.affinity.web.model.WsNewPhotos;
import com.minsait.affinity.web.model.WsNewPotential;
import com.minsait.affinity.web.model.WsNewShipto;
import com.minsait.affinity.web.model.WsPromos;
import com.minsait.affinity.web.model.WsQuantObj;
import com.minsait.affinity.web.model.WsRDC;
import com.minsait.affinity.web.model.WsRefClave;
import com.minsait.affinity.web.model.WsRutas;
import com.minsait.affinity.web.model.WsSearch;
import com.minsait.affinity.web.model.WsSearchAccount;
import com.minsait.affinity.web.model.WsTop30EurKg;
import com.minsait.affinity.web.model.WsTop30EurKgAff;
import com.minsait.affinity.web.model.WsUnpaids;
import com.minsait.affinity.web.model.WsUpdateContact;
import com.minsait.affinity.web.model.WsVentasMonth;
import com.minsait.affinity.web.model.WsVentasYear;

import io.swagger.annotations.ApiParam;


@RestController
public class AccountControllerImpl implements AccountApi{

	@Autowired
	private SalesforceAccountService service;
	
	

	@Override
	@RequestMapping(value = "/account",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<List<WsAccount>> getAccounts(
			@ApiParam(value = "" ,required=true) @RequestHeader(value="Accept-Language", required=true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "", required = true) WsFiltro body) {
		List<WsAccount> accounts = this.service.getAllAccounts(body, username);
		if (accounts != null) return new ResponseEntity<>(accounts, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@Override
	@RequestMapping(value = "/account/rutasByUser",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsRutas>> getRutasByUser(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "", required = true) @PathVariable("date") String date) {
		List<WsRutas> rutas = this.service.getRutasSF(username, date);
		if (null == rutas) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(rutas, HttpStatus.OK);
	}
	
	@Override
	@RequestMapping(value = "/account/updateAddress",
    produces = { "application/json" }, 
    method = RequestMethod.PUT)
	public ResponseEntity<Void> updateShipToAddress(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) WsAddress body) {
		Account account = this.service.updateAddress(body);
		if (null == account) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		account = null;
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/account/newFrecuencia",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> newFrecuencia(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("username") String username,
			@ApiParam(value = "", required = true) WsFrec body) {
		Case c = this.service.crearFrecuencia(body, username);
		if (c == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Integer id = c.getId();
		if (id == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			c = null;
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/campaigns/{shipToId}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<WsCampaigns> getCampaigns(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("shipToId") String shipToId) {
		WsCampaigns campaigns = this.service.getCampagns(shipToId, true);
		if (campaigns != null) return new ResponseEntity<>(campaigns, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	@RequestMapping(value = "/account/surtidos/{shipToId}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<WsCampaigns> getSurtidos(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("shipToId") String shipToId) {
		WsCampaigns campaigns = this.service.getCampagns(shipToId, false);
		if (campaigns != null) return new ResponseEntity<>(campaigns, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	@RequestMapping(value = "/account/refclave/{customerCode}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsRefClave>> getRefClave(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("customerCode") String customerCode) {
		List<WsRefClave> lst_ref = this.service.getRefclave(customerCode);
		if (lst_ref != null) return new ResponseEntity<>(lst_ref, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/top30eurkg/{customerCode}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsTop30EurKg>> getTop30EurKg(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("customerCode") String customerCode) {
		List<WsTop30EurKg> lst_top30 = this.service.getTop30EurKg(customerCode);
		if (lst_top30 != null) return new ResponseEntity<>(lst_top30, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/top30eurkgaff/{customerCode}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsTop30EurKgAff>> getTop30EurKgAff(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("customerCode") String customerCode) {
		List<WsTop30EurKgAff> lst_top30 = this.service.getTop30EurAff(customerCode);
		if (lst_top30 !=  null) return new ResponseEntity<>(lst_top30, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@Override
	@RequestMapping(value = "/account/ventasmonth/{customer-rdc}",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<List<WsVentasMonth>> getVentasMonth(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("customer-rdc") String customerRdc,
			@ApiParam(value = "", required = true) WsRDC body) {
		List<WsVentasMonth> lst_ventas = new ArrayList<WsVentasMonth>();	
		if (!body.isRdc()) lst_ventas = this.service.getVentasMonthCustomer(customerRdc);
		else lst_ventas = this.service.getVentasMonthRDC(customerRdc);
		if (lst_ventas !=  null) return new ResponseEntity<>(lst_ventas, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/ventasyear/{customer-rdc}",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<List<WsVentasYear>> getVentasYear(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("customer-rdc") String customerRdc,
			@ApiParam(value = "", required = true) WsRDC body) {
		List<WsVentasYear> lst_ventas = new ArrayList<WsVentasYear>();	
		if (!body.isRdc()) lst_ventas = this.service.getVentasYearCustomer(customerRdc);
		else lst_ventas = this.service.getVentasYearRDC(customerRdc);
		if (lst_ventas !=  null) return new ResponseEntity<>(lst_ventas, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/promos",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsPromos>> getPromos(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username) {
		List<WsPromos> lst_promos = this.service.getPromos(username);
		if (lst_promos !=  null) return new ResponseEntity<>(lst_promos, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	@Override
	@RequestMapping(value = "/account/cierreVisita",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> cierreVisita(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) WsCierreVisita body) {
		Task t = this.service.crearCierreVisita(body);
		if (t == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Integer id = t.getId();
		if (id == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			t = null;
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/newCaso",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> newCaso(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @Valid WsCase body) {
		
		Case c = this.service.newCaso(body);
		if (c == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Integer id = c.getId();
		if (id == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			c = null;
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	/*@Override
	@RequestMapping(value = "/account/newAccount",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> newAccount(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username, 
			@ApiParam(value = "", required = true) WsNewAccount body) {
		Account a = this.service.createNewAccount(body, username);
		if (a.getId() == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(HttpStatus.OK);
	}*/

	@Override
	@RequestMapping(value = "/account/adminUpdate",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> updateAccountAdmin(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) WsDatosAdmin body) {
		Case c = this.service.updateAdminAccount(body);
		if (c == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Integer id = c.getId();
		if (id == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			c = null;
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/contactUpdate",
    produces = { "application/json" }, 
    method = RequestMethod.PUT)
	public ResponseEntity<Void> updateContactAccount(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) WsUpdateContact body) {
		Account a = this.service.updateContactInfo(body);
		if (a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			a = null;
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/evolution/{username}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<WsEvolution> getEvolution(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username) {
		WsEvolution e = this.service.getRdcEvolution(username);
		if (e == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(e, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/account/quantObj/{username}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<WsQuantObj> getObjetivosCuanti(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username) {
		WsQuantObj objetivos = this.service.getObjetivosCuantitativos(username);
		if (objetivos !=  null) return new ResponseEntity<>(objetivos, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/search",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<List<WsSearchAccount>> searchAccounts(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) WsSearch body) {
		List<WsSearchAccount> accounts = this.service.searchAccounts(body);
		if (accounts != null) return new ResponseEntity<>(accounts, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/acuerdos/{shiptoid}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsAcuerdos>> getAcuerdosComerciales(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid) {
		List<WsAcuerdos> acuerdos = this.service.getAcuerdosComerciales(shiptoid);
		if (acuerdos !=  null) return new ResponseEntity<>(acuerdos, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/budget/{username}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<WsBudget> getBudgetRDC(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username) {
		WsBudget presupuesto = this.service.getBudgetRDC(username);
		if (presupuesto != null) return new ResponseEntity<>(presupuesto, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		/*WsBudget presupuesto = new WsBudget();
		presupuesto.setAsignado(0.0);
		presupuesto.setConsumido(0.0);
		return new ResponseEntity<>(presupuesto, HttpStatus.OK); */
	}

	@Override
	@RequestMapping(value = "/account/qualiObj/{username}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsCualiObj>> getObjetivosCuali(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username) {
		List<WsCualiObj> objetivos = this.service.getObjetivosCualitativos(username);
		if (objetivos !=  null) return new ResponseEntity<>(objetivos, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/checkSurtidos/{shiptoid}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsCheckSurtidos>> getCheckSurtidos(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid) {
		List<WsCheckSurtidos> checkeos = this.service.getCheckeosSurtidos(shiptoid);
		if (checkeos !=  null) return new ResponseEntity<>(checkeos, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/createChecks/{shiptoid}",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> createChecks(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid,
			@ApiParam(value = "", required = true) WsChecks body) {
		List<CheckVisitC> surtidos = this.service.createChecksSurtidos(body, shiptoid);
		List<CheckVisitC> promos = this.service.createChecksPromos(body, shiptoid);
		List<SpotCheckC> checkPuntual = this.service.createSpotCheck(body.getPuntual(), shiptoid);
		if (surtidos == null && promos == null && checkPuntual == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			surtidos = null;
			promos = null;
			checkPuntual = null;
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/checkPromos",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsCheckPromos>> getCheckPromos(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("clienteid") String clienteid, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid") String shiptoid) {
		List<WsCheckPromos> checkeos = this.service.getCheckeosPromos(clienteid, shiptoid);
		if (checkeos !=  null) return new ResponseEntity<>(checkeos, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/messages",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsMessage>> getmessages(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username) {
		List<WsMessage> lst_message = this.service.getMessage(username);
		if (lst_message !=  null) return new ResponseEntity<>(lst_message, HttpStatus.OK); 
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@Override
	@RequestMapping(value = "/account/getAllIS/{country}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsIS>> getAllIS(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("country") String country) {
		List<WsIS> lst_is = this.service.getAllIS(country);
		if (lst_is != null) return new ResponseEntity<>(lst_is, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/getAllDirects/{country}",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<List<WsDirectClient>> getAllDirects(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage,
			@ApiParam(value = "", required = true) @PathVariable("country") String country) {
		List<WsDirectClient> lst_directs = this.service.getAllDirects(country);
		if (lst_directs != null) return new ResponseEntity<>(lst_directs, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	@RequestMapping(value = "/account/newContact",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Integer> newContact(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username")String username, 
			@ApiParam(value = "", required = true) WsNewContact body) {
		Contact c = this.service.createNewContact(body, username);
		if (c == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Integer id = c.getId();
		if (id == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			c = null;
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/newDirect",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Integer> newDirect(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username, 
			@ApiParam(value = "", required = true) WsNewDirect body) {
		Account a = this.service.createNewDirect(body, username);
		if (a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Integer id = a.getId();
		if (id == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			a = null;
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/newPotential",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Integer> newPotential(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username, 
			@ApiParam(value = "", required = true) WsNewPotential body) {
		Account a = this.service.createNewPotential(body, username);
		if (a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		Integer id = a.getId();
		if (id == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		else {
			a = null;
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/newShipto",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Integer> newShipto(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("username") String username, 
			@ApiParam(value = "", required = true) WsNewShipto body) {
		Account a = this.service.createNewShipTo(body, username);
		if (a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		Integer id = a.getId();
		if (id == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		else {
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/uploadPhotos/{shiptoid}",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> uploadPhotos(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("shiptoid")String shiptoid, 
			@ApiParam(value = "", required = true) List<WsNewPhotos> body) {
		List<ShipToPictureC> fotos = this.service.uploadPhotos2(body, shiptoid);
		if (fotos == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			fotos = null;
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@Override
	@RequestMapping(value = "/account/unpaids",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<List<WsUnpaids>> getUnpaids(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) List<String> body) {
		List<WsUnpaids> unpaids = this.service.checkUnpaids(body);	
		if (unpaids == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		else return new ResponseEntity<>(unpaids, HttpStatus.OK);
		//List<WsUnpaids> unpaids = new ArrayList<WsUnpaids>();
		//return new ResponseEntity<>(unpaids, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/account/verifyAltaCliente",
    produces = { "application/json" }, 
    method = RequestMethod.GET)
	public ResponseEntity<WsAltaResp> verifyAltaCliente(
			@ApiParam(value = "", required = true) @RequestHeader(value = "Accept-Language", required = true) String acceptLanguage, 
			@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
			@ApiParam(value = "", required = true) @PathVariable("type") Boolean type) {
		WsAltaResp verificacion = this.service.verifyAltaCliente(id, type);
		if (verificacion != null) return new ResponseEntity<>(verificacion, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}



}
