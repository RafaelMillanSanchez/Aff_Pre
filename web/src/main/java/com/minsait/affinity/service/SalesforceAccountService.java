package com.minsait.affinity.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.affinity.jpa.model.Account;
import com.minsait.affinity.jpa.model.AccountDeliveryDatesC;
import com.minsait.affinity.jpa.model.AcuerdoComercialC;
import com.minsait.affinity.jpa.model.Attachment;
import com.minsait.affinity.jpa.model.Case;
import com.minsait.affinity.jpa.model.CheckAssortedpromoProductC;
import com.minsait.affinity.jpa.model.CheckProductC;
import com.minsait.affinity.jpa.model.CheckVisitC;
import com.minsait.affinity.jpa.model.Contact;
import com.minsait.affinity.jpa.model.Event;
import com.minsait.affinity.jpa.model.EvolutionC;
import com.minsait.affinity.jpa.model.LightCampaignAccountC;
import com.minsait.affinity.jpa.model.LightCampaignC;
import com.minsait.affinity.jpa.model.LightCampaignProductC;
import com.minsait.affinity.jpa.model.LightMessagesC;
import com.minsait.affinity.jpa.model.LightStockC;
import com.minsait.affinity.jpa.model.LocalBudgetRdcC;
import com.minsait.affinity.jpa.model.MarketCatalogsC;
import com.minsait.affinity.jpa.model.ObjetivoCualitativoC;
import com.minsait.affinity.jpa.model.ObjetivoGlobal;
import com.minsait.affinity.jpa.model.ObjetivocuantitativoC;
import com.minsait.affinity.jpa.model.Order;
import com.minsait.affinity.jpa.model.Product2;
import com.minsait.affinity.jpa.model.RefClaveTablaFinal;
import com.minsait.affinity.jpa.model.ResumenVentasMonth;
import com.minsait.affinity.jpa.model.ResumenVentasMonthRdc;
import com.minsait.affinity.jpa.model.ResumenVentasYear;
import com.minsait.affinity.jpa.model.ResumenVentasYearRdc;
import com.minsait.affinity.jpa.model.Rutas;
import com.minsait.affinity.jpa.model.ShipToPictureC;
import com.minsait.affinity.jpa.model.SpotCheckC;
import com.minsait.affinity.jpa.model.SubbrandC;
import com.minsait.affinity.jpa.model.Task;
import com.minsait.affinity.jpa.model.Top30FinalEurKg;
import com.minsait.affinity.jpa.model.Top30FinalEurKgAffinity;
import com.minsait.affinity.jpa.model.User;
import com.minsait.affinity.jpa.model.UserBackupC;
import com.minsait.affinity.mappers.AccountDataMapper;
import com.minsait.affinity.proximo.Proximo;
import com.minsait.affinity.repo.AccountDeliveryDatesRepository;
import com.minsait.affinity.repo.AccountRepository;
import com.minsait.affinity.repo.AcuerdoComercialRepository;
import com.minsait.affinity.repo.AttachmentRepository;
import com.minsait.affinity.repo.BudgetRDCRepository;
import com.minsait.affinity.repo.CampaignAccountRepository;
import com.minsait.affinity.repo.CampaignProductRepository;
import com.minsait.affinity.repo.CampaignsRepository;
import com.minsait.affinity.repo.CaseRepository;
import com.minsait.affinity.repo.CatalogRepository;
import com.minsait.affinity.repo.CheckAssortedProductRepository;
import com.minsait.affinity.repo.CheckProductRepository;
import com.minsait.affinity.repo.CheckVisitRepository;
import com.minsait.affinity.repo.ContactRepository;
import com.minsait.affinity.repo.EventRepository;
import com.minsait.affinity.repo.EvolutionRepository;
import com.minsait.affinity.repo.MessageRepository;
import com.minsait.affinity.repo.ObjetivoCuantitativoRepository;
import com.minsait.affinity.repo.ObjetivosCualitativosRepository;
import com.minsait.affinity.repo.OrderRepository;
import com.minsait.affinity.repo.ProductRepository;
import com.minsait.affinity.repo.RecordTypeRepository;
import com.minsait.affinity.repo.RefClaveRepository;
import com.minsait.affinity.repo.ShipToPictureRepository;
import com.minsait.affinity.repo.SpotCheckRepository;
import com.minsait.affinity.repo.StockRepository;
import com.minsait.affinity.repo.SubBrandRepository;
import com.minsait.affinity.repo.TaskRepository;
import com.minsait.affinity.repo.Top30EurKgAffRepository;
import com.minsait.affinity.repo.Top30EurKgRepository;
import com.minsait.affinity.repo.UserBackupRepository;
import com.minsait.affinity.repo.UserRepository;
import com.minsait.affinity.repo.UserRoleRepository;
import com.minsait.affinity.repo.VentasMonthRDCRepository;
import com.minsait.affinity.repo.VentasMonthRepository;
import com.minsait.affinity.repo.VentasYearRDCRepository;
import com.minsait.affinity.repo.VentasYearRepository;
import com.minsait.affinity.soap.unpaid.DetailUnpaidInvoicesRequest;
import com.minsait.affinity.soap.unpaid.DetailUnpaidInvoicesResponse;
import com.minsait.affinity.soap.unpaid.client.SoapClient;
import com.minsait.affinity.web.model.WsAccount;
import com.minsait.affinity.web.model.WsAcuerdos;
import com.minsait.affinity.web.model.WsAddress;
import com.minsait.affinity.web.model.WsAltaResp;
import com.minsait.affinity.web.model.WsBudget;
import com.minsait.affinity.web.model.WsCampaigns;
import com.minsait.affinity.web.model.WsCampaignsInner;
import com.minsait.affinity.web.model.WsCampaignsInnerProducts;
import com.minsait.affinity.web.model.WsCase;
import com.minsait.affinity.web.model.WsCase.TypeEnum;
import com.minsait.affinity.web.model.WsCheckPromos;
import com.minsait.affinity.web.model.WsCheckPromosProducts;
import com.minsait.affinity.web.model.WsCheckSurtidos;
import com.minsait.affinity.web.model.WsCheckSurtidosProducts;
import com.minsait.affinity.web.model.WsChecks;
import com.minsait.affinity.web.model.WsChecksPromos;
import com.minsait.affinity.web.model.WsChecksPuntual;
import com.minsait.affinity.web.model.WsChecksSurtidos;
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
import com.minsait.affinity.web.model.WsProduct;
import com.minsait.affinity.web.model.WsPromos;
import com.minsait.affinity.web.model.WsQuantObj;
import com.minsait.affinity.web.model.WsRefClave;
import com.minsait.affinity.web.model.WsRutas;
import com.minsait.affinity.web.model.WsSearch;
import com.minsait.affinity.web.model.WsSearchAccount;
import com.minsait.affinity.web.model.WsTop30EurKg;
import com.minsait.affinity.web.model.WsTop30EurKgAff;
import com.minsait.affinity.web.model.WsUnpaids;
import com.minsait.affinity.web.model.WsUnpaidsInner;
import com.minsait.affinity.web.model.WsUpdateContact;
import com.minsait.affinity.web.model.WsVentasMonth;
import com.minsait.affinity.web.model.WsVentasYear;



@Service
public class SalesforceAccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CaseRepository caseRepository;
	
	@Autowired
	private AccountDataMapper accountMapper;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private CampaignAccountRepository campaccRepo;
	
	@Autowired
	private CampaignsRepository campaignRepo;
	
	@Autowired
	private CampaignProductRepository camprodRepo;
	
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private RefClaveRepository refClaveRepo;
	
	@Autowired
	private Top30EurKgRepository top30eurRepo;
	
	@Autowired
	private Top30EurKgAffRepository top30euraffRepo;
	
	@Autowired
	private VentasMonthRepository ventasMonthRepo;
	
	@Autowired
	private VentasMonthRDCRepository ventasMonthRDCRepo;
	
	@Autowired
	private VentasYearRepository ventasYearRepo;
	
	@Autowired
	private VentasYearRDCRepository ventasYearRDCRepo;
	
	@Autowired
	private UserBackupRepository userBackupRepo;
	
	@Autowired
	private CatalogRepository catalogRepo;
	
	@Autowired
	private CatalogRepository marketCatalogRepository;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Autowired
	private EvolutionRepository evolutionRepository;

	@Autowired
	private ObjetivoCuantitativoRepository objCuantiRepository;
	
	@Autowired
	private AcuerdoComercialRepository acuerdosRepository;
	
	@Autowired
	private BudgetRDCRepository budgetRepository;
	
	@Autowired
	private ObjetivosCualitativosRepository objetivosCualiRepository;
	
	@Autowired
	private CheckAssortedProductRepository checkAccountRepository;
	
	@Autowired
	private CheckProductRepository checkProductRepository;
	
	@Autowired
	private CheckVisitRepository checkVisitRepository;

	@Autowired
	private MessageRepository messagerepository;
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private RecordTypeRepository recordTypeRepository;
	
	@Autowired
	private SpotCheckRepository spotRepository;
	
	@Autowired
	private SoapClient unpaidService;
	
	@Autowired
	private SubBrandRepository subbrandrepo;
	
	@Autowired
	private ShipToPictureRepository shiptopicturesRepo;
	
	@Autowired
	private AccountDeliveryDatesRepository deliveryRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	
	
	//Busca clientes Ship To y Potenciales
	public List<WsAccount> getAllAccounts(WsFiltro body, String username) {
		Iterable<UserBackupC> substitutes = this.userBackupRepo.findByLkpSubstituteC(username);
		List<String> rdcs = new ArrayList<String>();
		rdcs.add(username);
		for (UserBackupC u : substitutes) rdcs.add(u.getLkpMissingUserC());
		substitutes = null;
		List<Account> allAccounts = null;
		List<Account> distanceAccounts = new ArrayList<Account>();
		String recTypeShipto = this.recordTypeRepository.findRecordType("Ship To", "Account"); 
		String recTypePotential = this.recordTypeRepository.findRecordType("Potential Client", "Account");
		List<String> recTypeIds = new ArrayList<String>();
		recTypeIds.add(recTypeShipto);
		recTypeIds.add(recTypePotential);
		//Busqueda que no sea por distancia ni por nombre de cliente directo
		if (!body.isFilterDistance() && !body.getClientName().equals("")) allAccounts = this.accountRepository.findShipTo(body.getShiptoName(), body.getShiptoCode(), body.getClientCode(), body.getPoblacion(), body.getPostalcode(), body.getClientName(), rdcs, recTypeShipto);
		//Busqueda que no sea por distancia
		else if (!body.isFilterDistance()) allAccounts = this.accountRepository.findPotential(body.getShiptoName(), body.getShiptoCode(), body.getClientCode(), body.getPoblacion(), body.getPostalcode(), rdcs, recTypeIds);
		//Busqueda por distancia
		else {
			allAccounts = this.accountRepository.findShipToByDistance(rdcs, recTypeIds);	
			for (Account a : allAccounts) {
				//ship to
				if (a.getRecordtypeid().equals(recTypeShipto)) {		
					if (a.getShippinglatitude() != null && a.getShippinglongitude() != null) {	
						//calcular distancia
						if (body.getKm() > distance(body.getGeoY(), body.getGeoX(), a.getShippinglatitude(), a.getShippinglongitude())) {
							distanceAccounts.add(a);
						}
					}
				}
				else {
					//potencial
					if (a.getBillinglatitude() != null && a.getBillinglongitude() != null) {	
						//calcular distancia
						if (body.getKm() > distance(body.getGeoY(), body.getGeoX(), a.getBillinglatitude(), a.getBillinglongitude())) {
							distanceAccounts.add(a);
						}
					}
				}
			}
			allAccounts = null;
			recTypeIds = null;
			rdcs = null;
			if (distanceAccounts.size() > 0) {
				//clientes directos
				Set<String> parentIds = new HashSet<String>();
				Map<String, Account> mapParentAccount = new HashMap<String, Account>();
				for (Account a : distanceAccounts) parentIds.add(a.getParentid());
				Iterable<Account> directClients = this.accountRepository.findAllSfid(parentIds);
				for (Account a : directClients) mapParentAccount.put(a.getSfid(), a);
				
				//contactos
				Set<String> accountIds = new HashSet<String>();
				Map<String, Contact> mapContactAccount = new HashMap<String, Contact>();
				for (Account a : distanceAccounts) accountIds.add(a.getSfid());
				Iterable<Contact> contacts = this.contactRepo.findMainContacts(accountIds);
				for (Contact c : contacts) mapContactAccount.put(c.getAccountid(), c);
				
				return salesforce2web(distanceAccounts, username, mapParentAccount, mapContactAccount);
				//return accountMapper.salesforce2web(distanceAccounts, username);
			}
			else return Collections.emptyList();
		}
		recTypeIds = null;
		recTypePotential = null;
		recTypeShipto = null;
		if (allAccounts.size() > 0) {
			//clientes directos
			Set<String> parentIds = new HashSet<String>();
			Map<String, Account> mapParentAccount = new HashMap<String, Account>();
			for (Account a : allAccounts) parentIds.add(a.getParentid());
			Iterable<Account> directClients = this.accountRepository.findAllSfid(parentIds);
			for (Account a : directClients) mapParentAccount.put(a.getSfid(), a);
			
			//contactos
			Set<String> accountIds = new HashSet<String>();
			Map<String, Contact> mapContactAccount = new HashMap<String, Contact>();
			for (Account a : allAccounts) accountIds.add(a.getSfid());
			Iterable<Contact> contacts = this.contactRepo.findMainContacts(accountIds);
			for (Contact c : contacts) mapContactAccount.put(c.getAccountid(), c);
			
			rdcs = null;
			return salesforce2web(allAccounts, username, mapParentAccount, mapContactAccount);
		}
		else return Collections.emptyList();
		//return accountMapper.salesforce2web(allAccounts, username);
	}
	
	//mapeo de campos
	private List<WsAccount> salesforce2web(List<Account> distanceAccounts, String username,
			Map<String, Account> mapParentAccount, Map<String, Contact> mapContactAccount) {
		List<WsAccount> lst = null;
		if (null != distanceAccounts) {
			lst = new ArrayList<>();
			DateFormat dateFormat;
			Contact c;
			Account parent;
			WsAccount wsa;
			for (Account a : distanceAccounts) {
				if (null == a) return null;
				wsa = new WsAccount();
				wsa.setNombreshipto(a.getName());
				wsa.setAfternoonclosetime(a.getPckAfternoonClosingTimeC());
				wsa.setAfternoonclosedeltime(a.getPckAfternoonDeliveryClosingTimeC());
				wsa.setAfternoonopentime(a.getPckAfternoonOpeningTimeC());
				wsa.setAfternoonopendeltime(a.getPckAfternoonDeliveryOpeningTimeC());
				wsa.setMorningclosetime(a.getPckMorningClosingTimeC());
				wsa.setMorningclosedeltime(a.getPckMorningDeliveryClosingTimeC());
				wsa.setMorningopentime(a.getPckMorningOpeningTimeC());
				wsa.setMorningopendeltime(a.getPckMorningDeliveryOpeningTimeC());
				wsa.setM2local(a.getPckAreaC());
				wsa.setClientecode(a.getFrmlParentCustomerCodeC());
				wsa.setShiptocode(a.getTxtSapidC());
				wsa.setPhoneShipto(a.getPhoneSapPhone1C());
				wsa.setMobileShipto(a.getPhoneSapPhone2C());
				wsa.setAddphoneShipto(a.getPhoneSapPhone3C());
				wsa.setEmail1Shipto(a.getMailSapEmail1C());
				wsa.setEmail2Shipto(a.getMailSapEmail2C());
				wsa.setEmail3Shipto(a.getMailSapEmail3C());
				wsa.setVeterinarian(a.getChkHasVeterinarianC());
				wsa.setWindowdressing(a.getChkWindowDressingC());
				wsa.setHairdresser(a.getChkHairdresserC());
				wsa.setLanguage(a.getPetxLanguageC());
				//wsa.setBudget(a.getNumConsumedBudgetC());
				
				c = mapContactAccount.get(a.getSfid());
				if (c != null) {
					wsa.setNombreContacto(c.getName());
					wsa.setEmail(c.getEmail());
					wsa.setPhone(c.getPhone());
					wsa.setHomePhone(c.getHomephone());
					wsa.setMobile(c.getMobilephone());
					wsa.setOtherPhone(c.getOtherphone());
				}			
				wsa.setRoutedays(a.getMulShipToRouteDaysC());
				wsa.setStatus(a.getPckCustomerStatusC());
				wsa.setTipo(a.getPckAccountTypeC());
				wsa.setClienteid(a.getParentid());
				wsa.setShiptoid(a.getSfid());
				wsa.setChain(a.getPckChainC());
				wsa.setSubchain(a.getPckSubchainC());
				//wsa.setRanking(BigDecimal.valueOf(a.getNumRankingClienteC()));
				wsa.setRanking(String.valueOf(a.getNumRankingClienteC()));
				wsa.setCalle(a.getShippingstreet());
				wsa.setCiudad(a.getShippingcity());
				wsa.setPais(a.getShippingcountry());
				wsa.setPostalcode(a.getShippingpostalcode());
				wsa.setProvince(a.getShippingstate());
				
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (a.getDttInitCallRouteC() != null) wsa.setInitCall(dateFormat.format(a.getDttInitCallRouteC()));
				if (a.getDttInitVisitRouteC() != null) wsa.setInitVisit(dateFormat.format(a.getDttInitVisitRouteC()));
				wsa.setNoCallroute(a.getChkClientWithoutCallRouteC());
				wsa.setNoVisitRoute(a.getChkClientWithoutVisitRouteC());
				wsa.setVisitWeeks(a.getTxtVisitWeeksC());
				wsa.setCallWeeks(a.getTxtCallWeeksC());
				wsa.setGeoX(a.getShippinglongitude());
				wsa.setGeoY(a.getShippinglatitude());
				if (!a.getLkpRdcC().equals(username)) wsa.setBackup(true);
				else wsa.setBackup(false);
				
				if (a.getParentid() != null) {
					parent = mapParentAccount.get(a.getParentid());
					wsa.setNombrecliente(parent.getName());
					wsa.setCalleDirecto(parent.getBillingstreet());
					wsa.setCiudadDirecto(parent.getBillingcity());
					wsa.setPaisDirecto(parent.getBillingcountry());
					wsa.setPostalcodeDirecto(parent.getBillingpostalcode());
					wsa.setProvinceDirecto(parent.getBillingstate());
					wsa.setMobileDirecto(parent.getPhoneSapPhone2C());
					wsa.setPhoneDirecto(parent.getPhoneSapPhone1C());
					wsa.setAddphoneDirecto(parent.getPhoneSapPhone3C());
					wsa.setEmail1Directo(parent.getMailSapEmail1C());
					wsa.setEmail2Directo(parent.getMailSapEmail2C());
					wsa.setEmail3Directo(parent.getMailSapEmail3C());
					wsa.setPaymethod(parent.getPckPaymentMethodC());
					wsa.setPaytype(parent.getPckPaymentTypeC());
					
				}
				
				
				
				lst.add(wsa);
			}
			dateFormat = null;
			c = null;
			parent = null;
			distanceAccounts = null;
			username = null;
			mapParentAccount = null;
			mapContactAccount = null;
			wsa = null;
		}
		return lst;
	}


	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;
			return (dist);
		}
	}
	
	//busqueda de clientes ship to, directos y potenciales 
	public List<WsSearchAccount> searchAccounts(WsSearch body) {
		String country = body.getCountry();
		List<String> countryCodes = new ArrayList<String>();
		if (country == null || country.equals("Spain")) {
			countryCodes.add("0011");
			countryCodes.add("0012");
		}
		else if (country.equals("France")) {
			countryCodes.add("0052");
		}
		else if (country.contentEquals("Italy")) {
			countryCodes.add("0022");
		}
		String recTypeShipto = this.recordTypeRepository.findRecordType("Ship To", "Account"); 
		String recTypePotential = this.recordTypeRepository.findRecordType("Potential Client", "Account");
		String recTypeDirect = this.recordTypeRepository.findRecordType("Direct Client", "Account");
		List<String> recTypeIds = new ArrayList<String>();
		recTypeIds.add(recTypeShipto);
		recTypeIds.add(recTypePotential);
		recTypeIds.add(recTypeDirect);
		Iterable<Account> allAccounts = this.accountRepository.findAllAccounts(body.getEmail(), body.getName(), body.getPhone(), countryCodes, recTypeIds);
		recTypeDirect = null;
		recTypePotential = null;
		recTypeShipto = null;
		countryCodes = null;
		country = null;
		return accountMapper.searchAccounts2web(allAccounts, recTypeIds);
	}	
	
	


	public WsAccount getAccountBySalesforceId(String salesforceId) {
		//Account theAcc = this.accountRepository.findBySfid(salesforceId);
		//return this.accountMapper.salesforce2web(theAcc);
		return null;
	}

	@Transactional
	public WsAccount deleteAccountBySalesforceId(String salesforceId) {
		//Account theAcc = this.accountRepository.deleteBySfid(salesforceId);
		//if (theAcc != null) return this.accountMapper.salesforce2web(theAcc);
		return null;
	}
	
	//rutas entre hoy y hace 7 dias
	public List<WsRutas> getRutasSF(String username, String date) {
		try {
			SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd");  
			Date dat = formatter6.parse(date);
			Date lastweek = org.apache.commons.lang3.time.DateUtils.addDays(dat, -7);
			//rdc de sustitucion
			Iterable<UserBackupC> substitutes = this.userBackupRepo.findByLkpSubstituteC(username);
			List<String> rdcs = new ArrayList<String>();
			rdcs.add(username);
			for (UserBackupC u : substitutes) rdcs.add(u.getLkpMissingUserC());
			String recTypeShipto = this.recordTypeRepository.findRecordType("Ship To", "Account"); 
			String recTypePotential = this.recordTypeRepository.findRecordType("Potential Client", "Account");
			List<String> recTypeIds = new ArrayList<String>();
			recTypeIds.add(recTypeShipto);
			recTypeIds.add(recTypePotential);
			List<Rutas> rutas = this.accountRepository.rutasAccount(rdcs, dat, recTypeIds, lastweek);

			Set<String> accountIds = new HashSet<String>();	
			for (Rutas r : rutas) {
				if (r.getShipto() != null) {
					accountIds.add(r.getShipto().getSfid());
				}
				
			}
			if (rutas.size() > 0) {
				
				//fechas de envio
				Map<String, Date> mapDeliveryDates = new HashMap<String, Date>();
				Iterable<AccountDeliveryDatesC> delivDates = this.deliveryRepository.findDeliveryDates(accountIds);			
				for (AccountDeliveryDatesC del : delivDates) mapDeliveryDates.put(del.getLkpAccountC(), del.getDatIddpC());
				
				//contactos
				Map<String, Contact> mapContactAccount = new HashMap<String, Contact>();
				Iterable<Contact> contacts = this.contactRepo.findMainContacts(accountIds);
				for (Contact c : contacts) mapContactAccount.put(c.getAccountid(), c);
					
				//pedidos del dia
				Map<String, Boolean> mapOrder = new HashMap<String, Boolean>();
				Iterable<Order> orders = this.orderRepository.findOrders(accountIds, dat);
				for (Order o : orders) mapOrder.put(o.getAccountid(), true);
				
				//informe comercial del dia
				Date nextDay = org.apache.commons.lang3.time.DateUtils.addDays(dat, +1);
				Map<String, Boolean> mapInforme = new HashMap<String, Boolean>();
				Iterable<Task> informes = this.taskRepository.findInformes(accountIds, dat, nextDay);
				for (Task t : informes) mapInforme.put(t.getAccountid(), true);
				
				
				formatter6 = null;
				lastweek = null;
				substitutes = null;
				rdcs = null;
				recTypeIds = null;
				return acc2ruta(rutas, username, dat, mapDeliveryDates, mapContactAccount, mapOrder, mapInforme);
			}
			else return Collections.emptyList();
		} catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
	}

	//mapeo de campos
	private List<WsRutas> acc2ruta(Iterable<Rutas> rutas, String username, Date dat, Map<String, Date> mapDeliveryDates, Map<String, Contact> mapContactAccount, Map<String, Boolean> mapOrder, Map<String, Boolean> mapInforme) {
		List<WsRutas> lst = null;
		if (rutas != null) {
			lst = new ArrayList<>();
			WsRutas wsr;
			Contact c;
			SimpleDateFormat formatter6;
			DateFormat dateFormat;
			Date deliveryDate;
			Boolean order;
			Boolean informe;
			for (Rutas o : rutas) {
				if (o == null) return null;
				wsr = new WsRutas();
				formatter6 = new SimpleDateFormat("yyyy-MM-dd");
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				if (o.getShipto() != null)  {	
					wsr.setNombreshipto(o.getShipto().getName());
					wsr.setCalle(o.getShipto().getShippingstreet());
					wsr.setCiudad(o.getShipto().getShippingcity());
					wsr.setPais(o.getShipto().getShippingcountry());
					wsr.setPostalcode(o.getShipto().getShippingpostalcode());
					wsr.setProvince(o.getShipto().getShippingstate());
					wsr.setGeoX(o.getShipto().getShippinglongitude());
					wsr.setGeoY(o.getShipto().getShippinglatitude());
					wsr.setPhoneShipto(o.getShipto().getPhoneSapPhone1C());
					wsr.setMobileShipto(o.getShipto().getPhoneSapPhone2C());
					wsr.setAddphoneShipto(o.getShipto().getPhoneSapPhone3C());
					wsr.setEmail1Shipto(o.getShipto().getMailSapEmail1C());
					wsr.setEmail2Shipto(o.getShipto().getMailSapEmail2C());
					wsr.setEmail3Shipto(o.getShipto().getMailSapEmail3C());
					wsr.setVeterinarian(o.getShipto().getChkHasVeterinarianC());
					wsr.setWindowdressing(o.getShipto().getChkWindowDressingC());
					wsr.setHairdresser(o.getShipto().getChkHairdresserC());
					wsr.setLanguage(o.getShipto().getPetxLanguageC());
					//wsr.setBudget(o.getShipto().getNumConsumedBudgetC());
					wsr.setAfternoonclosetime(o.getShipto().getPckAfternoonClosingTimeC());
					wsr.setAfternoonopentime(o.getShipto().getPckAfternoonOpeningTimeC());
					wsr.setMorningclosetime(o.getShipto().getPckMorningClosingTimeC());
					wsr.setMorningopentime(o.getShipto().getPckMorningOpeningTimeC());
					wsr.setAfternoonclosedeltime(o.getShipto().getPckAfternoonDeliveryClosingTimeC());
					wsr.setAfternoonopendeltime(o.getShipto().getPckAfternoonDeliveryOpeningTimeC());
					wsr.setMorningclosedeltime(o.getShipto().getPckMorningDeliveryClosingTimeC());
					wsr.setMorningopendeltime(o.getShipto().getPckMorningDeliveryOpeningTimeC());	
					wsr.setDeliveryAddInfo(o.getShipto().getMulDeliveryAdditionalInfoC());
					wsr.setOpeningAddInfo(o.getShipto().getMulOpeningAdditionalInfoC());
					wsr.setM2local(o.getShipto().getPckAreaC());
					wsr.setRoutedays(o.getShipto().getMulShipToRouteDaysC());
					wsr.setStatus(o.getShipto().getPckCustomerStatusC());
					wsr.setTipo(o.getShipto().getPckAccountTypeC());
					wsr.setShiptocode(o.getShipto().getTxtSapidC());
					wsr.setShiptoid(o.getShipto().getSfid());
					wsr.setChain(o.getShipto().getPckChainC());
					wsr.setSubchain(o.getShipto().getPckSubchainC());
					wsr.setEventid(o.getRuta().getSfid());
					// 20201202 Inicio RMS ASF516
					if (o.getShipto().getLkpIsC() != null) {
						User u1 = this.userRepo.findBySfid(o.getShipto().getLkpIsC());
						wsr.setLkpIsC( u1.getName());
					}
					
					if (o.getShipto().getLkpIsBackupC() != null) {
						User u2 = this.userRepo.findBySfid(o.getShipto().getLkpIsBackupC());
						wsr.setLkpIsBackupC(u2.getName());
					}
					// 20201202 Fin RMS ASF516

					
					deliveryDate = mapDeliveryDates.get(o.getShipto().getSfid());
					if (deliveryDate != null) wsr.setDeliverydate(formatter6.format(deliveryDate));
					
					order = mapOrder.get(o.getShipto().getSfid());
					if (order == null) wsr.setPedidos(false);
					else wsr.setPedidos(true);	
					
					if (o.getShipto().getNumRankingClienteC() != null) wsr.setRanking(String.valueOf(o.getShipto().getNumRankingClienteC()));
					if (o.getShipto().getPerPesoClienteC() != null) wsr.setPeso(round(o.getShipto().getPerPesoClienteC(), 2));
					if (o.getShipto().getDttInitCallRouteC() != null) wsr.setInitCall(dateFormat.format(o.getShipto().getDttInitCallRouteC()));
					if (o.getShipto().getDttInitVisitRouteC() != null) wsr.setInitVisit(dateFormat.format(o.getShipto().getDttInitVisitRouteC()));
					wsr.setNoCallroute(o.getShipto().getChkClientWithoutCallRouteC());
					wsr.setNoVisitRoute(o.getShipto().getChkClientWithoutVisitRouteC());
					wsr.setVisitWeeks(o.getShipto().getTxtVisitWeeksC());
					wsr.setCallWeeks(o.getShipto().getTxtCallWeeksC());	
					if (!o.getShipto().getLkpRdcC().equals(username)) wsr.setBackup(true);
					else wsr.setBackup(false);
					
					informe = mapInforme.get(o.getShipto().getSfid());
					if (informe == null) wsr.setInforme(false);
					else wsr.setInforme(true);	
				}
				
				if (o.getParent() != null) {
					wsr.setNombrecliente(o.getParent().getName());
					wsr.setClientecode(o.getParent().getTxtSapidC());
					wsr.setCalleDirecto(o.getParent().getBillingstreet());
					wsr.setCiudadDirecto(o.getParent().getBillingcity());
					wsr.setPaisDirecto(o.getParent().getBillingcountry());
					wsr.setPostalcodeDirecto(o.getParent().getBillingpostalcode());
					wsr.setProvinceDirecto(o.getParent().getBillingstate());
					wsr.setMobileDirecto(o.getParent().getPhoneSapPhone2C());
					wsr.setPhoneDirecto(o.getParent().getPhoneSapPhone1C());
					wsr.setAddphoneDirecto(o.getParent().getPhoneSapPhone3C());
					wsr.setEmail1Directo(o.getParent().getMailSapEmail1C());
					wsr.setEmail2Directo(o.getParent().getMailSapEmail2C());
					wsr.setEmail3Directo(o.getParent().getMailSapEmail3C());
					wsr.setPaymethod(o.getParent().getPckPaymentMethodC());
					wsr.setPaytype(o.getParent().getPckPaymentTypeC());
					wsr.setClienteid(o.getParent().getSfid());
				}
					
				if (o.getRuta().getStartdatetime() != null) wsr.setStarttime(dateFormat.format(o.getRuta().getStartdatetime()));
				if (o.getRuta().getEnddatetime() != null) wsr.setEndtime(dateFormat.format(o.getRuta().getEnddatetime()));
				
				wsr.setRutaStatus(o.getRuta().getActivityStatusC()); 	
				//rutas expiradas
				if (o.getRuta().getStartdatetime().before(dat) && o.getRuta().getActivityStatusC().equals("Not Started")) wsr.setRutaStatus("Old");
				
				c = mapContactAccount.get(o.getShipto().getSfid());
				if (c != null) {
					wsr.setNombreContacto(c.getName());
					wsr.setEmail(c.getEmail());
					wsr.setPhone(c.getPhone());
					wsr.setHomePhone(c.getHomephone());
					wsr.setMobile(c.getMobilephone());
					wsr.setOtherPhone(c.getOtherphone());
				}
				c = null;
				dateFormat = null;
				formatter6 = null;
				deliveryDate = null;
				order = null;
				informe = null;
				lst.add(wsr);
			}	
			wsr = null;
			username = null;
			rutas = null;
			dat = null;
			mapDeliveryDates = null;
			mapContactAccount = null;
			mapOrder = null;
			mapInforme = null;
		}
		return lst;
	}
	
	//redonde de deciamles
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}


	public Account updateAddress(WsAddress body) {
		Account a = this.accountRepository.findBySfid(body.getId());
		if (body.getGeoY() != null) a.setShippinglatitude(body.getGeoY());
		if (body.getGeoX() != null) a.setShippinglongitude(body.getGeoX());
		return this.accountRepository.save(a);
	}

	//cambiar frecuencia de ruta
	public Case crearFrecuencia(WsFrec body, String username) {
		Case c = new Case();
		Account a = this.accountRepository.findBySfid(body.getId());
		if (a == null) return null;
		if (a.getPckTamC() != null) {
			User u = this.userRepo.findByTxtSapIdC(a.getPckTamC());
			if (u != null) {
				c.setAccountid(body.getId());		
				c.setOwnerid(u.getSfid());
				c.setLkpContactPersonC(username);
				c.setStatus("New");
				c.setType("Request");
				c.setPckCaseSubtypeC("Cambio frecuencia");
				c.setOrigin("App");
				c.setPriority("Medium");
				SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					if (body.getInitCall() != null && !body.getInitCall().equals("")) c.setDttInitCallRouteC(formatter6.parse(body.getInitCall()));
					if (body.getInitVisit() != null && !body.getInitVisit().equals("")) c.setDttInitVisitRouteC(formatter6.parse(body.getInitVisit()));
				} catch (java.text.ParseException ep) {
		            ep.printStackTrace();
				}
				if (body.isNoCallroute()) c.setChkClientWithoutCallRouteC(true);
				if (body.isNoVisitRoute()) c.setChkClientWithoutVisitRouteC(true);
				if (body.getVisitWeeks() != null && !body.getVisitWeeks().equals("")) c.setTxtVisitWeeksC(body.getVisitWeeks());
				if (body.getCallWeeks() != null && !body.getCallWeeks().equals("")) c.setTxtCallWeeksC(body.getCallWeeks());
				String recTypeid = this.recordTypeRepository.findRecordType("Cambio frecuencia", "Case"); 
				c.setRecordtypeid(recTypeid);
				a = null;
				recTypeid = null;
				formatter6 = null;
				u = null;
				return this.caseRepository.save(c);
			}
			else return null;
		}
		else return null;
		
	}


	public WsCampaigns getCampagns(String shipToId, boolean campaign) {
		Account a = this.accountRepository.findBySfid(shipToId);
		if (a == null || a.getParentid() == null) return null;
		//todos los clientes directos con campaña
		List<LightCampaignAccountC> campacc = this.campaccRepo.findByLkpAccountC(a.getParentid());
		if (campacc.size() > 0) {
			List<String> campaignIds = new ArrayList<String>();
			for (LightCampaignAccountC lca : campacc) {
				campaignIds.add(lca.getLkpCampaignC());
			}
			Iterable<LightCampaignC> campaigns;
			if (campaign) {
				//campañas en fecha
				campaigns = this.campaignRepo.findCampaigns(campaignIds);
			}
			else {
				//surtidos en fecha
				campaigns = this.campaignRepo.findSurtidos(campaignIds);
			}
			if (campaigns.spliterator().getExactSizeIfKnown() <= 0) return new WsCampaigns();
			Map<String, LightCampaignC> campaignMap = new HashMap<String, LightCampaignC>();
			for (LightCampaignC c : campaigns) {
				campaignMap.put(c.getSfid(), c);
			}
			List<LightCampaignProductC> camprod = new ArrayList<LightCampaignProductC>();
			if (campaignMap.keySet().size() > 0) {
				//productos de todas las campañas
				camprod = this.camprodRepo.findCampaignsProducts(campaignMap.keySet());
			}
			if (camprod.size() > 0 && camprod != null) {
				Set<String> prodIds = new HashSet<String>();
				
				Map<String, List<LightCampaignProductC>> campaignProductsMap = new HashMap<String, List<LightCampaignProductC>>();
				List<LightCampaignProductC> values;
				String lkpcampaing;
				//llenar el map con las campañas y sus productos
				for (LightCampaignProductC cp : camprod) {
					lkpcampaing = cp.getLkpCampaignC();
					prodIds.add(cp.getLkpProductC());	
					values = campaignProductsMap.get(lkpcampaing);
					if (values == null) {
						values = new ArrayList<LightCampaignProductC>();
						campaignProductsMap.put(lkpcampaing, values);
					}
					values.add(cp);
				}
				values = null;
				lkpcampaing = null;
				Iterable<Product2> products = this.productRepo.findProductsBySfid(prodIds);
				
				Map<String, Product2> productMap = new HashMap<String, Product2>();
				
				for (Product2 p : products) productMap.put(p.getSfid(), p);
				
				List<String> idsFotos = new ArrayList<String>();
				idsFotos.addAll(prodIds);
				idsFotos.addAll(campaignMap.keySet());
				
				//fotos de las campañas y de los productos
				Iterable<Attachment> productFotos = this.attachmentRepository.findFotos(idsFotos);
				Map<String, String> ProductFotosMap = new HashMap<String, String>();
				for (Attachment att : productFotos) ProductFotosMap.put(att.getParentid(), att.getSfid() + "-" + att.getName().replaceAll("\\s+",""));
				
				//productos promocionados
				Iterable<MarketCatalogsC> promos = this.marketCatalogRepository.findMarketCatalog(productMap.keySet(), a.getPckSalesEntityC(), a.getPckChannelC());
				Map<String, String> marketMap = new HashMap<String, String>();
				for (MarketCatalogsC m : promos) marketMap.put(m.getItemC(), m.getSfid());
				
				//stock
				Iterable<LightStockC> stock = this.stockRepository.findStock(prodIds, a.getPckCustomerWarehouseCodeC());
				Map<String, Double> stockMap = new HashMap<String, Double>();
				for (LightStockC s : stock) stockMap.put(s.getLkpItemC(), s.getFrmlRealStockC());
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				WsCampaigns wscampaigns = new WsCampaigns();
				WsCampaignsInnerProducts wsprod;
				Product2 p;
				String key;
				LightCampaignC c;
				WsCampaignsInner ci;
				
				//recorremos el map y mapeamos los campos
				for(Map.Entry<String, List<LightCampaignProductC>> entry : campaignProductsMap.entrySet()) {  
					  key = entry.getKey();	  
					  c = campaignMap.get(key);				  
					  ci = new WsCampaignsInner();
					  ci.setNameC(c.getName());
					  ci.setCodeC(c.getTxtDiscountCodeC());
					  ci.setDescriptionC(c.getTxtDescriptionC());
					  if (c.getDatStartDateC() != null) ci.setStartDate(dateFormat.format(c.getDatStartDateC()));
					  if (c.getDatEndDateC() != null) ci.setEndDate(dateFormat.format(c.getDatEndDateC()));
					  ci.setType(c.getPckTypeC());
					  ci.setUnitType(c.getPckUnitTypeC());
					  ci.setMinQuantity(c.getNumMinQuantityC());
					  ci.setMaxQuantity(c.getNumMaxQuantityC());
					  ci.setActive(c.getFrmlActiveC());
					  ci.setPhoto(ProductFotosMap.get(c.getSfid()));
					  for (LightCampaignProductC value : entry.getValue()) {				  
						  wsprod = new WsCampaignsInnerProducts();				  
						  p = productMap.get(value.getLkpProductC());
						  wsprod.setStock(stockMap.get(p.getSfid()));
						  if (marketMap.get(p.getSfid()) != null) wsprod.setPromoted(true);
						  else wsprod.setPromoted(false);
						  wsprod.setCode(p.getProductcode());
						  wsprod.setNameP(p.getName());
						  wsprod.setBusiness(p.getFrmBusinessC());
						  wsprod.setSegment(p.getFrmSegmentC());
						  wsprod.setBrand(p.getFrmBrandC());
						  wsprod.setSubbrand(p.getFrmSubbrandC());
						  wsprod.setRange(p.getFrmRangeC());
						  wsprod.setSubrange(p.getFrmSubrangeC());
						  wsprod.setLine(p.getFrmLineC());
						  wsprod.setVariety(p.getFrmVarietyC());
						  wsprod.setDescription(p.getTxtSalesArgumentC());
						  wsprod.setPhoto(ProductFotosMap.get(p.getSfid()));
						  wsprod.setQuantity(value.getNumQuantityC());
						  ci.addProductsItem(wsprod);
					  }
					  wscampaigns.add(ci);
				}
				prodIds = null;
				ci = null;
				wsprod = null;
				p = null;
				ProductFotosMap = null;
				marketMap = null;
				stockMap = null;
				c = null;
				key = null;
				campaignMap = null;
				dateFormat = null;
				stock = null;
				promos = null;
				productFotos = null;
				idsFotos = null;
				productMap = null;
				products = null;
				camprod = null;
				campaignProductsMap = null;
				campaigns = null;
				campaignIds = null;
				campacc = null;
				a = null;
				return wscampaigns;
			}
			campaignMap = null;
			camprod = null;
			campaigns = null;
			campaignIds = null;
			campacc = null;
			a = null;
			return new WsCampaigns();
		}
		campacc = null;
		a = null;
		return new WsCampaigns();
	}

	//referencias clave
	public List<WsRefClave> getRefclave(String customerCode) {
		if (customerCode == null) return null;
		Integer intcode = Integer.valueOf(customerCode);		
		String code = Integer.toString(intcode); 
		Iterable<RefClaveTablaFinal> refClave_lst = this.refClaveRepo.findByCustomerCode(code);
		Set<String> subbrandNames = new HashSet<String>();
		for (RefClaveTablaFinal refclave : refClave_lst) subbrandNames.add(refclave.getSubcadena());
		if (subbrandNames.isEmpty()) subbrandNames.add("");
		Iterable<SubbrandC> subbrands = this.subbrandrepo.findByName(subbrandNames);
		Map<String, String> mapSubbrand = new HashMap <String, String>();
		for (SubbrandC s : subbrands) {		
			mapSubbrand.put(s.getName(), s.getTxtDescriptionC());
		}
		code = null;
		subbrandNames = null;
		subbrands = null;
		return this.accountMapper.refclave2web(refClave_lst, mapSubbrand);
	}


	public List<WsTop30EurKg> getTop30EurKg(String customerCode) {
		if (customerCode == null) return null;
		Integer intcode = Integer.valueOf(customerCode);		
		String code = Integer.toString(intcode); 
		Iterable<Top30FinalEurKg> top30eur_lst = this.top30eurRepo.findByCustomerCode(code);
		code = null;
		return this.accountMapper.top30eur2web(top30eur_lst);
	}


	public List<WsTop30EurKgAff> getTop30EurAff(String customerCode) {
		if (customerCode == null) return null;
		Integer intcode = Integer.valueOf(customerCode);		
		String code = Integer.toString(intcode);
		Iterable<Top30FinalEurKgAffinity> top30eur_lst = this.top30euraffRepo.findByCustomerCode(code);
		code = null;
		return this.accountMapper.top30euraff2web(top30eur_lst);
	}


	public List<WsVentasMonth> getVentasMonthCustomer(String customerRdc) {
		if (customerRdc == null) return null;
		Integer intcode = Integer.valueOf(customerRdc);		
		String code = Integer.toString(intcode);
		Iterable<ResumenVentasMonth> lst_ventas = this.ventasMonthRepo.findByCustomer(code);
		code = null;
		return this.accountMapper.ventasmonth2web(lst_ventas);
	}


	public List<WsVentasMonth> getVentasMonthRDC(String customerRdc) {
		Iterable<ResumenVentasMonthRdc> lst_ventas = this.ventasMonthRDCRepo.findByRdc(customerRdc);
		return this.accountMapper.ventasmonthrdc2web(lst_ventas);
	}


	public List<WsVentasYear> getVentasYearCustomer(String customerRdc) {
		if (customerRdc == null) return null;
		Integer intcode = Integer.valueOf(customerRdc);		
		String code = Integer.toString(intcode);
		Iterable<ResumenVentasYear> lst_ventas = this.ventasYearRepo.findByCustomer(code);
		code = null;
		return this.accountMapper.ventasyear2web(lst_ventas);
	}


	public List<WsVentasYear> getVentasYearRDC(String customerRdc) {
		Iterable<ResumenVentasYearRdc> lst_ventas = this.ventasYearRDCRepo.findByRdc(customerRdc);
		return this.accountMapper.ventasyearrdc2web(lst_ventas);
	}

	
	//promociones
	public List<WsPromos> getPromos(String rdc) {
		User u = this.userRepo.findBySfid(rdc);
		if (u == null) return null;
		//Account a = this.accountRepository.findBySfid(shiptoid);
		String country = u.getStaffCountryC();
		List<String> countryCodes = new ArrayList<String>();
		if (country == null || country.equals("Spain")) {
			countryCodes.add("0011");
			countryCodes.add("0012");
		}
		else if (country.equals("France")) {
			countryCodes.add("0052");
		}
		else if (country.contentEquals("Italy")) {
			countryCodes.add("0022");
		}
		
		//productos promocionados
		Iterable<MarketCatalogsC> catalogs = this.catalogRepo.findCatalogs(countryCodes);
		Map<String, String> marketProductsMap = new HashMap<String, String>();
		Map<String, List<String>> marketFotoMap = new HashMap<String, List<String>>();
		Set<String> prodIds = new HashSet<String>();
		String id;
		String photoName;
		List<String> fotos;
		
		//las promos se identifican y se agrupan por el nombre de la promo (TxtPromotionalPictureNameC)
		for (MarketCatalogsC c : catalogs) {
			if (c.getTxtPromotionalPictureNameC() != null) {
				id = c.getSfid();
				photoName = c.getTxtPromotionalPictureNameC();
				//id promo -> id producto
				marketProductsMap.put(c.getSfid(), c.getItemC());
				fotos = marketFotoMap.get(photoName);
				if (fotos == null) {
					fotos = new ArrayList<String>();
					marketFotoMap.put(photoName, fotos);
				}
				fotos.add(id);
				prodIds.add(c.getItemC());
			}
		}
		fotos = null;
		photoName = null;
		id = null;
		catalogs = null;
		countryCodes = null;
		country = null;
		u = null;
		if (prodIds.isEmpty()) prodIds.add("");
		//productos de las promos
		Iterable<Product2> prods = this.productRepo.findProductsBySfid(prodIds);
		Set<String> parentIdFotos = new HashSet<String>();
		parentIdFotos.addAll(prodIds);
		parentIdFotos.addAll(marketProductsMap.keySet());
		if (parentIdFotos.isEmpty()) parentIdFotos.add("");
		//fotos de las promos
		Iterable<Attachment> attList = this.attachmentRepository.findFotos(parentIdFotos);
		Map<String, String> FotoParentIDMap = new HashMap<String, String>();
		for (Attachment att : attList)  {
			FotoParentIDMap.put(att.getParentid(), att.getSfid() + "-" + att.getName().replaceAll("\\s+",""));
		}
		
		Map<String, Product2> ProductsMap = new HashMap<String, Product2>();
		for (Product2 p : prods) ProductsMap.put(p.getSfid(), p);
		
		//Iterable<LightStockC> stock = this.stockRepository.findStock(prodIds, a.getPckCustomerWarehouseCodeC());
		//Map<String, Double> StockMap = new HashMap<String, Double>();
		//for (LightStockC s : stock) StockMap.put(s.getLkpItemC(), s.getFrmlRealStockC());
		
		//recorremos el mapa donde la key es el nombre de la promo y los valores son los productos
		if (marketProductsMap.keySet().size() > 0) {
			List<WsPromos> list_wspromos = new ArrayList<WsPromos>();
			WsProduct wsprod;
			Product2 p;
			String prodId;
			String key;
			WsPromos wspromo;
			for (Map.Entry<String, List<String>> entry : marketFotoMap.entrySet()) {
				wspromo = new WsPromos();
				key = entry.getKey();
				wspromo.setName(key);
				String parentid = null;
				if (entry.getValue().get(0) != null) parentid = entry.getValue().get(0);
				//nombre de la foto de la promo
				String photoS3name = FotoParentIDMap.get(parentid);
				wspromo.setCode(photoS3name);
				wspromo.setPhoto(photoS3name);
				for (String value : entry.getValue()) {	    
					prodId = marketProductsMap.get(value);	    
					p = ProductsMap.get(prodId);  
					wsprod = new WsProduct();
					wsprod.setNameP(p.getName());
					wsprod.setCode(p.getProductcode());
					wsprod.setSubbrand(p.getFrmSubbrandC());
					wsprod.setLine(p.getFrmLineC());
					wsprod.setWeight(p.getNumWeightC());
					if (p.getNumBoxesXPaletC() != null) wsprod.setBoxesxpalet(p.getNumBoxesXPaletC());
					if (p.getNumUnitsBoxC() != null) wsprod.setUnitsbox(p.getNumUnitsBoxC());
					wsprod.setPhoto(FotoParentIDMap.get(p.getSfid()));
					//wsprod.setStock(StockMap.get(p.getSfid()));
					wspromo.addProductsItem(wsprod);
				}
				list_wspromos.add(wspromo);
			}
			wspromo = null;
			wsprod = null;
			FotoParentIDMap = null;
			p = null;
			prodId = null;
			marketProductsMap = null;
			key = null;
			ProductsMap = null;
			prods = null;
			attList = null;
			prodIds = null;
			parentIdFotos = null;
			return list_wspromos;
		}
		else {
			ProductsMap = null;
			marketProductsMap = null;
			prods = null;
			FotoParentIDMap = null;
			attList = null;
			parentIdFotos = null;
			prodIds = null;
			return Collections.emptyList();
		}
	}


	public Task crearCierreVisita(WsCierreVisita body) {
		Task t = new Task();
		Account a = this.accountRepository.findBySfid(body.getShiptoid());
		if (a == null) return null;
		t.setWhatid(a.getSfid());
		Event e = this.eventRepo.findBySfid(body.getEventid());
		e.setActivityStatusC("Completed");
		this.eventRepo.save(e);
		Date date = new Date();
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		
		t.setDttVisitStartTimeC(date);	
		t.setType("Visit");
		t.setPckVisitTypeC(body.getType());
		t.setDescription(body.getDescription());
		t.setSubject("Visita realizada el " + dateFormatGmt.format(new Date()));
		t.setStatus("Completed");
		User u = this.userRepo.findBySfid(body.getUsername());
		t.setOwnerid(u.getSfid());
		a = null;
		date = null;
		u = null;
		return this.taskRepository.save(t);
	}


	public Case newCaso(WsCase body) {
		Case c = new Case();
		Account a = this.accountRepository.findBySfid(body.getShiptoid());
		if (a.getLkpIsC() != null) {
			User u = this.userRepo.findBySfid(a.getLkpIsC());
			Contact contact = this.contactRepo.findMainContact(body.getShiptoid(), PageRequest.of(0,1));
			if (u != null) {
				c.setAccountid(body.getShiptoid());		
				c.setOwnerid(u.getSfid());
				c.setStatus("New");
				TypeEnum typeenum = body.getType();
				String type = typeenum.toString();
				if (type.equals("incidencia")) {
					c.setType("Incidence");
					//c.setPckCaseSubtypeC("Acciones en SAP ECC");
				}
				else if (type.equals("sales")) {
					c.setType("Sales Development Incidence");
					//c.setPckCaseSubtypeC("Acciones en SAP ECC");
				}
				
				c.setOrigin("App");
				c.setPriority("Medium");
				String recTypeid = this.recordTypeRepository.findRecordType("LIGHT Case Record Type", "Case"); 
				c.setRecordtypeid(recTypeid);
				c.setDescription(body.getDescription());
				if (contact != null) c.setContactid(contact.getSfid());
				contact = null;
				recTypeid = null;
				type = null;
				typeenum = null;
				a = null;
				return this.caseRepository.save(c);
			}
			else return null;
		}
		else return null;
		
	}


	/*public Account createNewAccount(WsNewAccount body, String username) {
		return this.accountMapper.newAcc2salesforce(body, username);
	}*/


	public Case updateAdminAccount(WsDatosAdmin body) {
		if (body.getShiptoid() == null) return null;
		Case c = new Case();
		Contact cnt = this.contactRepo.findMainContact(body.getShiptoid(), PageRequest.of(0,1));
		c.setOwnerid(body.getUsername());
		String recTypeid = this.recordTypeRepository.findRecordType("LIGHT Case Record Type", "Case"); 
		c.setRecordtypeid(recTypeid);
		c.setType("To Do");
		c.setStatus("New");
		c.setDescription(body.getDescription());
		c.setAccountid(body.getShiptoid());
		c.setOrigin("Internal");
		if (cnt != null) c.setContactid(cnt.getSfid());
		cnt = null;
		recTypeid = null;
		c.setPckCaseSubtypeC("Acciones en SAP ECC");
		this.caseRepository.save(c);
		return c;
	}


	public Account updateContactInfo(WsUpdateContact body) {
		if (body.getShiptoid() == null) return null;
		Account a = this.accountRepository.findBySfid(body.getShiptoid());
		Contact c = this.contactRepo.findMainContact(body.getShiptoid(), PageRequest.of(0,1));
		if (c != null) {
			if (body.getLastname() != null) c.setLastname(body.getLastname());
			if (body.getEmail() != null) c.setEmail(body.getEmail());
			if (body.getPhone() != null) c.setPhone(body.getPhone());
			if (body.getMobilephone() != null) c.setMobilephone(body.getMobilephone());
			this.contactRepo.save(c);
			return a;
		}
		else return null;
		
		
	}


	public WsEvolution getRdcEvolution(String username) {
		List<EvolutionC> e = this.evolutionRepository.findByUserC(username);
		WsEvolution wse = new WsEvolution();
		if (e.size() > 0) {			
			if (e.get(0).getLastyearsalesC() == null) {
				wse.setCurrentYear(0.0);
			} 
			else wse.setLastYear(e.get(0).getLastyearsalesC());
			
			if (e.get(0).getCurrentyearsalesC() == null) {
				wse.setCurrentYear(0.0);
			}
			else wse.setCurrentYear(e.get(0).getCurrentyearsalesC());
		}
		else {
			wse.setLastYear(0.0);
			wse.setCurrentYear(0.0);
		}
		e = null;
		return wse;
	}


	public WsQuantObj getObjetivosCuantitativos(String username) {
		User rdc = this.userRepo.findBySfid(username);
		if (rdc == null) return null;
		WsQuantObj wsquant = new WsQuantObj();
		//objetivo individual del rdc
		Iterable<ObjetivocuantitativoC> obj_cuanti_individual = this.objCuantiRepository.findCuantiRDC(username);
		this.accountMapper.objindiv2web(obj_cuanti_individual, wsquant);
		
		String country = rdc.getStaffCountryC();
		List<String> countryCodes = new ArrayList<String>();
		if (country == null || country.equals("Spain")) {
			countryCodes.add("0011");
			countryCodes.add("0012");
		}
		else if (country.equals("France")) {
			countryCodes.add("0052");
		}
		else if (country.contentEquals("Italy")) {
			countryCodes.add("0022");
		}
		//objetivo global de todos los rdc
		Iterable<ObjetivoGlobal> obj_cuanti_global = this.objCuantiRepository.findCuantiGlobal(countryCodes);
		
		this.accountMapper.objglobal2web(obj_cuanti_global, wsquant);	
		rdc = null;
		obj_cuanti_individual = null;
		country = null;
		obj_cuanti_global = null;
		countryCodes = null;
		return wsquant;
	}


	public List<WsAcuerdos> getAcuerdosComerciales(String shiptoid) {
		Iterable<AcuerdoComercialC> acuerdos = this.acuerdosRepository.findAcuerdos(shiptoid);
		return this.accountMapper.acuerdos2web(acuerdos);
	}


	public WsBudget getBudgetRDC(String username) {
		List<LocalBudgetRdcC> presupuesto = this.budgetRepository.findByOwnerid(username);
		WsBudget wsb = new WsBudget();
		if (presupuesto.size() > 0) {
			if (presupuesto.get(0).getPresupuestoAsignadoC() != null) wsb.setAsignado(presupuesto.get(0).getPresupuestoAsignadoC());
			else wsb.setAsignado(0.0);
			if (presupuesto.get(0).getPresupuestoConsumidoC() != null) wsb.setConsumido(presupuesto.get(0).getPresupuestoConsumidoC());
			else wsb.setConsumido(0.0);
		}
		else {
			wsb.setAsignado(0.0);
			wsb.setConsumido(0.0);
		}
		presupuesto = null;
		return wsb;
	}


	public List<WsCualiObj> getObjetivosCualitativos(String username) {
		Iterable<ObjetivoCualitativoC> objetivos = this.objetivosCualiRepository.findByLkpUserC(username);
		return this.accountMapper.cualitativo2web(objetivos);
	}


	//punto de venta surtidos
	public List<WsCheckSurtidos> getCheckeosSurtidos(String shiptoid) {
		List<WsCheckSurtidos> lst_surtidos = new ArrayList<WsCheckSurtidos>();
		//surtidos activos
		List<CheckAssortedpromoProductC> checkAccounts = this.checkAccountRepository.findSurtidosActuales(shiptoid);
		if (checkAccounts.size() > 0) {
			Set<String> surtidos_ids = new HashSet<String>();
			Map<String, CheckAssortedpromoProductC> map_surtidos = new HashMap<String, CheckAssortedpromoProductC>();
			List<String> parentids = new ArrayList<String>();
			for (CheckAssortedpromoProductC c : checkAccounts) {
				surtidos_ids.add(c.getSfid());
				map_surtidos.put(c.getSfid(), c);
				parentids.add(c.getSfid());
			}
			//productos de los surtidos
			List<CheckProductC> check_productos = this.checkProductRepository.findProductosSurtidos(surtidos_ids);
			List<String> check_prod_ids = new ArrayList<String>();
			List<String> prod_ids = new ArrayList<String>();
			Map<String, List<CheckProductC>> map_surtidos_productos = new HashMap<String, List<CheckProductC>>();
			if (check_productos.size() > 0) {
				List<CheckProductC> values;
				//llenamos el map de surtidos con cada producto. id surtido -> productos
				for (CheckProductC check : check_productos) {
					check_prod_ids.add(check.getSfid());
					prod_ids.add(check.getProductC());	
					parentids.add(check.getProductC());
					values = map_surtidos_productos.get(check.getCheckAssortedProductC());
					if (values == null) {
						values = new ArrayList<>();
						map_surtidos_productos.put(check.getCheckAssortedProductC(), values);
					}
					values.add(check);
				}
				values = null;
				surtidos_ids = null;
				checkAccounts = null;
				Iterable<Product2> productos = this.productRepo.findProductsBySfid(prod_ids);
				
				//fotos de productos
				Iterable<Attachment> photos = this.attachmentRepository.findFotos(parentids);
				Map<String, String> map_photos = new HashMap<String, String>();
				//nombre de la foto = sfid + '-' + name
				for (Attachment a : photos) map_photos.put(a.getParentid(), a.getSfid() + "-" + a.getName().replaceAll("\\s+",""));
				
				Map<String, Product2> map_products = new HashMap<String, Product2>();
				for (Product2 p : productos) map_products.put(p.getSfid(), p);
				Product2 product;
				CheckVisitC visit;
				WsCheckSurtidosProducts wsCheck;
				String key;
				WsCheckSurtidos wsc;
				CheckAssortedpromoProductC surtido;
				//recorremos el mapa y llenamos los campos
				for (Map.Entry<String, List<CheckProductC>> entry : map_surtidos_productos.entrySet()) {	
					wsc = new WsCheckSurtidos();
					key = entry.getKey();				
					surtido = map_surtidos.get(key);
					wsc.setInitDate(surtido.getFechaInicioC());
					wsc.setEndDate(surtido.getFechaFinC());
					wsc.setName(surtido.getName());
					wsc.setSurtidoPhoto(map_photos.get(surtido.getSfid()));
					for (CheckProductC p : entry.getValue()) {				
						wsCheck = new WsCheckSurtidosProducts();	
						//informacion de la ultima visita 
						visit = this.checkVisitRepository.findLastCheckVisitSurtido(p.getSfid(), PageRequest.of(0,1));
						if (visit != null) {
							if (visit.getNumeroDeFacingC() != null) wsCheck.setLastFacing((int)Math.round(visit.getNumeroDeFacingC()));
							wsCheck.setLastPrecio(visit.getPrecioC());
							wsCheck.setLastCheck(visit.getEnTiendaC());
							wsCheck.setLastCumplimiento(visit.getPorcentajeDeCumplimientoC());
							wsCheck.setLastrotura(visit.getChkRoturaC());
						}		
						product = map_products.get(p.getProductC());
						wsCheck.setProductCode(product.getProductcode());
						wsCheck.setProductKG(product.getNumWeightC());
						wsCheck.setProductName(product.getName());
						wsCheck.setIdsurtido(p.getSfid());
						if (p.getNumeroDeFacingC() != null) wsCheck.setFacing((int)Math.round(p.getNumeroDeFacingC()));
						wsCheck.setPrecio(p.getPrecioC());
						wsCheck.setRotura(p.getChkRoturaC());
						wsCheck.setProductPhoto(map_photos.get(p.getSfid()));	
						wsc.addProductsItem(wsCheck);
					}
					lst_surtidos.add(wsc);	
				}
				wsc = null;
				key = null;
				surtido = null;
				wsCheck = null;
				visit = null;
				product = null;
				map_products = null;
				photos = null;
				map_photos = null;
				productos = null;
				prod_ids = null;
				check_prod_ids = null;
				check_productos = null;
				map_surtidos = null;
				map_surtidos_productos = null;
			}
		}
		return lst_surtidos;
	}


	public List<WsMessage> getMessage(String username) {
		Iterable<LightMessagesC> lst_message = this.messagerepository.Mensajesrdc(username);
		List<WsMessage> WsMessage = new ArrayList<WsMessage>();
		WsMessage wsp;
		for(LightMessagesC msgaux: lst_message) {
			wsp = new WsMessage();
			wsp.setMessageName(msgaux.getName());
			wsp.setMessageBody(msgaux.getLightMessagebodyC()); 
			wsp.setPhoto(msgaux.getLightBodyC());
			WsMessage.add(wsp);
		}
		lst_message = null;
		wsp = null;
		return WsMessage;
	}


	//punto de venta promos
	public List<WsCheckPromos> getCheckeosPromos(String clienteid, String shiptoid) {
		List<WsCheckPromos> lst_promos = new ArrayList<WsCheckPromos>();
		//promos asociadas al cliente directo
		List<CheckAssortedpromoProductC> checkAccounts = this.checkAccountRepository.findPromosActuales(clienteid);
		if (checkAccounts.size() > 0) {
			Set<String> promos_ids = new HashSet<String>();
			Map<String, CheckAssortedpromoProductC> map_promos = new HashMap<String, CheckAssortedpromoProductC>();
			for (CheckAssortedpromoProductC c : checkAccounts) {
				promos_ids.add(c.getSfid());
				map_promos.put(c.getSfid(), c);
			}
			checkAccounts = null;
			//productos promos
			List<CheckProductC> check_productos = this.checkProductRepository.findProductosPromos(promos_ids);
			List<String> check_prod_ids = new ArrayList<String>();
			List<String> prod_ids = new ArrayList<String>();
			Map<String, List<CheckProductC>> map_promos_productos = new HashMap<String, List<CheckProductC>>();
			if (check_productos.size() > 0) {
				List<CheckProductC> values;
				for (CheckProductC check : check_productos) {
					check_prod_ids.add(check.getSfid());
					prod_ids.add(check.getProductC());
					values = map_promos_productos.get(check.getCheckPromotionProductC());
					if (values == null) {
						values = new ArrayList<>();
						map_promos_productos.put(check.getCheckPromotionProductC(), values);
					}
					values.add(check);
				}
				values = null;
				promos_ids = null;
				Iterable<Product2> productos = this.productRepo.findProductsBySfid(prod_ids);
				//fotos de productos
				Iterable<Attachment> photos = this.attachmentRepository.findFotos(prod_ids);
				Map<String, String> map_photos = new HashMap<String, String>();
				for (Attachment a : photos) map_photos.put(a.getParentid(), a.getSfid() + "-" + a.getName().replaceAll("\\s+",""));
				Map<String, Product2> map_products = new HashMap<String, Product2>();
				for (Product2 p : productos) map_products.put(p.getSfid(), p);
				
				
				Product2 product;
				CheckVisitC visit;
				WsCheckPromosProducts wsCheck;
				CheckAssortedpromoProductC promo;
				WsCheckPromos wsc;
				String key;
				for (Map.Entry<String, List<CheckProductC>> entry : map_promos_productos.entrySet()) {
					wsc = new WsCheckPromos();	
					key = entry.getKey();
					promo = map_promos.get(key);
					wsc.setInitDate(promo.getFechaInicioC());
					wsc.setEndDate(promo.getFechaFinC());
					wsc.setName(promo.getName());
					for (CheckProductC p : entry.getValue()) {	
						wsCheck = new WsCheckPromosProducts();	
						//info de la ultima visita
						visit = this.checkVisitRepository.findLastCheckVisitPromo(p.getSfid(), shiptoid, PageRequest.of(0,1));
						if (visit != null) {
							if (visit.getNumeroDeFacingC() != null) wsCheck.setLastFacing((int)Math.round(visit.getNumeroDeFacingC()));
							wsCheck.setLastPrecio(visit.getPrecioC());
							wsCheck.setLastCheck(visit.getEnTiendaC());
							wsCheck.setLastCabecera(visit.getEnCabeceraC());
							wsCheck.setLastCumplimiento(visit.getPorcentajeDeCumplimientoC());
							wsCheck.setLastDescuento(visit.getDescuentoPromocionC());
							wsCheck.setLastFolleto(visit.getFolletoC());
							wsCheck.setLastPromocionado(visit.getPromocionadoC());
							//recupero el nombre de la foto a partir de su url
							String photopromo = visit.getPictureUrlC();
							if (photopromo != null && wsc.getPromoPhoto() == null) {
								Integer index = photopromo.lastIndexOf('/');
								if (index != -1) photopromo = photopromo.substring(index + 1);
								Integer index2 = photopromo.indexOf('?');
								if (index2 != -1) photopromo = photopromo.substring(0, index2);
								wsc.setPromoPhoto(photopromo);
							}			
							
						}				
						product = map_products.get(p.getProductC());
						wsCheck.setProductCode(product.getProductcode());
						wsCheck.setProductKG(product.getNumWeightC());
						wsCheck.setProductName(product.getName());
						wsCheck.setCabecera(p.getCabeceraC());
						wsCheck.setFolleto(p.getFolletoC());
						wsCheck.setPromocionado(p.getPromocionadoC());
						wsCheck.setDescuento(p.getDescuentoPromociNC());
						wsCheck.setIdpromo(p.getSfid());
						if (p.getNumeroDeFacingC() != null) wsCheck.setFacing((int)Math.round(p.getNumeroDeFacingC()));
						wsCheck.setPrecio(p.getPrecioC());
						wsCheck.setProductPhoto(map_photos.get(p.getSfid()));	
						wsc.addProductsItem(wsCheck);
					}
					lst_promos.add(wsc);	
				}
				wsc = null;
				key = null;
				promo = null;
				wsCheck = null;
				visit = null;
				product = null;
				map_products = null;
				photos = null;
				map_photos = null;
				productos = null;
				prod_ids = null;
				check_prod_ids = null;
				check_productos = null;
				map_promos = null;
			}
			map_promos_productos = null;
		}		
		return lst_promos;
	}

	
	//crea registro surtido de la visita
	public List<CheckVisitC> createChecksSurtidos(WsChecks body, String shiptoid) {
		List<WsChecksSurtidos> surtidos = body.getSurtidos();
		if (surtidos != null) {		
			List<CheckVisitC> checkToInsert = new ArrayList<CheckVisitC>();
			CheckVisitC check;
			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
			for (WsChecksSurtidos product : surtidos) {		
				check = new CheckVisitC();
				check.setName("Assortment Visit " + dateFormatGmt.format(new Date()));
				check.setAccountC(shiptoid);
				check.setCheckProductC(product.getIdsurtido());
				check.setEnTiendaC(product.isCheck());
				check.setChkRoturaC(product.isRotura());
				if(product.getFacing() != null) check.setNumeroDeFacingC(new Double(product.getFacing()));
				check.setPorcentajeDeCumplimientoC(product.getCumplimiento());
				check.setPrecioC(product.getPrecio());
				String recTypeid = this.recordTypeRepository.findRecordType("Assortment Visit", "Check_Visit__c"); 
				check.setRecordtypeid(recTypeid);
				checkToInsert.add(check);
			}
			check = null;
			surtidos = null;
			this.checkVisitRepository.saveAll(checkToInsert);
			return checkToInsert;
		}
		else return null;
	}

	
	//crea registro promo de la visita
	public List<CheckVisitC> createChecksPromos(WsChecks body, String shiptoid) {
		List<WsChecksPromos> promos = body.getPromos();	
		if (promos != null) {		
			List<CheckVisitC> checkToInsert = new ArrayList<CheckVisitC>();
			CheckVisitC check;
			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
			for (WsChecksPromos product : promos) {
				check = new CheckVisitC();
				check.setName("Promotion Visit " + dateFormatGmt.format(new Date()));
				check.setAccountC(shiptoid);
				check.setCheckProductC(product.getIdpromo());
				check.setEnTiendaC(product.isCheck());
				check.setDescuentoPromocionC(product.getDescuento());
				check.setEnCabeceraC(product.isCabecera());
				check.setFolletoC(product.isFolleto());
				check.setPromocionadoC(product.isPromocionado());
				check.setPictureUrlC(product.getPromoPhoto());
				if(product.getFacing() != null) check.setNumeroDeFacingC(new Double(product.getFacing()));
				check.setPorcentajeDeCumplimientoC(product.getCumplimiento());
				check.setPrecioC(product.getPrecio());
				String recTypeid = this.recordTypeRepository.findRecordType("Promotion Visit", "Check_Visit__c"); 
				check.setRecordtypeid(recTypeid);
				checkToInsert.add(check);
				recTypeid = null;
			}
			check = null;
			promos = null;
			this.checkVisitRepository.saveAll(checkToInsert);
			return checkToInsert;
		}
		else return null;
		
	}


	public List<WsIS> getAllIS(String country) {
		if (country == null) return null;
		List<String> roles = new ArrayList<String>();
		if (country.equals("Spain")) {
			roles.add("ES DD Customer Service Manager");
			roles.add("ES DD Coordinator");
			roles.add("ES DD Internal Sales");
		}
		else if (country.equals("France")) {
			roles.add("FR DD Customer Service Manager");
			roles.add("FR DD Coordinator");
			roles.add("FR DD Internal Sales");
		}
		else {
			roles.add("IT DD Customer Service Manager");
			roles.add("IT DD Coordinator");
			roles.add("IT DD Internal Sales");
		}
		
		List<String> idRoles = this.userRoleRepo.findRolesIS(roles);
		
		Iterable<User> allIs = this.userRepo.findISByCountry(idRoles);
		idRoles = null;
		roles = null;
		return this.accountMapper.salesforce2IS(allIs);
	}


	public List<WsDirectClient> getAllDirects(String country) {
		String recId = this.recordTypeRepository.findRecordType("Direct Client", "Account");
		List<Account> directos = this.accountRepository.findDirectos(country, recId);
		recId = null;
		return this.accountMapper.salesforce2directos(directos);
	}

	
	//checks puntuales
	public List<SpotCheckC> createSpotCheck(List<WsChecksPuntual> list, String shiptoid) {
		SpotCheckC newSpot;
		List<SpotCheckC> lstSpots = new ArrayList<SpotCheckC>();
		if (list != null) {
			SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			dateFormatGmt.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
			for (WsChecksPuntual check : list) {
				newSpot = new SpotCheckC();
				newSpot.setName("Spot Check " + dateFormatGmt.format(new Date()));
				newSpot.setNumMetrosC(check.getMetros());
				newSpot.setPrecioC(check.getPrecio());
				newSpot.setPckMarcaC(check.getMarca());
				newSpot.setTxtOtrosElementosC(check.getOtrosElem());
				newSpot.setChkPresenciaC(check.isPresencia());
				newSpot.setLkpPuntoDeEntregaC(shiptoid);
				lstSpots.add(newSpot);
				
			}
			this.spotRepository.saveAll(lstSpots);		
		}
		return lstSpots;
	}


	public Contact createNewContact(WsNewContact body, String username) {
		return this.accountMapper.newCnt2salesforce(body, username);
	}


	public Account createNewDirect(WsNewDirect body, String username) {
		return this.accountMapper.newDirect2salesforce(body, username);
	}


	public Account createNewPotential(WsNewPotential body, String username) {
		return this.accountMapper.newPotential2salesforce(body, username);
	}


	public Account createNewShipTo(WsNewShipto body, String username) {
		return this.accountMapper.newShipTo2salesforce(body, username);
	}


	/*public List<Attachment> uploadPhotos(List<WsChecksFotos> list, String shiptoid) {
		if (list != null) {
			List<Attachment> fotos = new ArrayList<Attachment>();
			for (WsChecksFotos wsfoto : list) {
				Attachment newfoto = new Attachment();
				newfoto.setName(wsfoto.getName());
				newfoto.setBody(wsfoto.getBase64body());
				newfoto.setParentid(shiptoid);
				fotos.add(newfoto);
			}
			if (fotos.size() > 0) this.attachmentRepository.saveAll(fotos);
			return fotos;
		}
		else return null;
	}*/

	//servicio SOAP a SAPPI Impagados
	public List<WsUnpaids> checkUnpaids(List<String> body) {
		Proximo.setup();
		DetailUnpaidInvoicesRequest request = new DetailUnpaidInvoicesRequest();
		DetailUnpaidInvoicesRequest.Items item;
		for (String customerCode : body) {	
			item = new DetailUnpaidInvoicesRequest.Items();
			item.setCustomer(customerCode);
			request.getItems().add(item);
		}
		DetailUnpaidInvoicesResponse response = null;
		
		try {
			response = this.unpaidService.getUnpaidCustomers(request);
		}
		//cerramos conexion con proxy
		catch (Exception e){
			System.setProperty("java.net.useSystemProxies", "true");
			System.clearProperty("socksProxyHost");
			return null;
			
		}
		//cerramos conexion con proxy
		if (response == null) {
			System.setProperty("java.net.useSystemProxies", "true");
			System.clearProperty("socksProxyHost");
			return null;
		}
		request = null;
		List<DetailUnpaidInvoicesResponse.Items> unpaids = response.getItems();
		response = null;
		List<WsUnpaids> lst_wsunp = new ArrayList<WsUnpaids>();
		Map<String, List<DetailUnpaidInvoicesResponse.Items>> map_unpaids = new HashMap<String, List<DetailUnpaidInvoicesResponse.Items>>();
		for (DetailUnpaidInvoicesResponse.Items item1 : unpaids) {
			map_unpaids.computeIfAbsent(item1.getCustomer(), k -> new ArrayList<>()).add(item1);
		}
		unpaids = null;
		WsUnpaidsInner wsu_inner;
		WsUnpaids wsu;
		for(Map.Entry<String, List<DetailUnpaidInvoicesResponse.Items>> entry : map_unpaids.entrySet()) {
			wsu = new WsUnpaids();    		
			for (DetailUnpaidInvoicesResponse.Items item2 : entry.getValue()) {			
				wsu_inner = new WsUnpaidsInner();
				wsu_inner.setAmount(item2.getAmount());
				wsu_inner.setChanneldist(item2.getChannelDist());
				wsu_inner.setCompanycode(item2.getCompanyCode());
				wsu_inner.setCurrency(item2.getCurrency());
				wsu_inner.setCustomer(item2.getCustomer());
				wsu_inner.setDebitcredit(item2.getDebitCredit());
				wsu_inner.setDocumenttype(item2.getDocumentType());
				wsu_inner.setDuedate(item2.getDueDate());
				wsu_inner.setHeadertext(item2.getHeaderText());
				wsu_inner.setInvoicedate(item2.getInvoiceDate());
				wsu_inner.setInvoicenumber(item2.getInvoiceNumber());
				wsu_inner.setItemtext(item2.getItemText());
				wsu_inner.setSalesorg(item2.getSalesOrg());
				wsu.add(wsu_inner);
			}
			lst_wsunp.add(wsu);
		}
		wsu_inner = null;
		wsu = null;
		//cerramos conexion con proxy
		System.setProperty("java.net.useSystemProxies", "true");
		System.clearProperty("socksProxyHost");
		return lst_wsunp;
	}


	public List<ShipToPictureC> uploadPhotos2(List<WsNewPhotos> body, String shiptoid) {
		if (body != null) {
			List<ShipToPictureC> fotos = new ArrayList<ShipToPictureC>();
			ShipToPictureC newfoto;
			String tipo;
			for (WsNewPhotos wsfoto : body) {	
				newfoto = new ShipToPictureC();
				newfoto.setName(wsfoto.getName());
				newfoto.setPictureUrlC(wsfoto.getUrl());
				newfoto.setLkpShipToC(shiptoid);
				tipo = wsfoto.getType();
				if (tipo.equals("")) newfoto.setPckPictureTypeC("-Ninguno-");
				else newfoto.setPckPictureTypeC(wsfoto.getType());
				fotos.add(newfoto);
			}
			newfoto = null;
			if (fotos.size() > 0) this.shiptopicturesRepo.saveAll(fotos);
			return fotos;
		}
		else return null;
	}

	public WsAltaResp verifyAltaCliente(Integer id, Boolean type) {
		//VERIFICAMOS QUE SE HA CREADO EL CONTACTO Y/O CUENTA
		WsAltaResp resp = new WsAltaResp();
		if (type) {
			
			Optional<Account> optAccount = this.accountRepository.findById(id);		
			
			if (optAccount.isPresent()) {
				Account account = new Account();
				account = optAccount.get();
				
				if (account.getHcLastop().equals("FAILED")) {
					String jsonError = account.getHcErr();
					
					if (jsonError == null || jsonError.isEmpty()) {
						resp.setResponse("FAILED");
						return resp;
					}
					
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = null;
					try {
						node = mapper.readTree(jsonError);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String msgErr = node.get("msg").asText();
					resp.setResponse(msgErr);
					return resp;
				}
								
				if (account.getSfid() != null) {
					resp.setResponse("OK");
					return resp;
				}
				
				else {
					resp.setResponse("PENDING");
					return resp;
				}
			}
			else {
				resp.setResponse("FAILED");
				return resp;
			}
		}
		else {
			Optional<Contact> optContact = this.contactRepo.findById(id);
			if (optContact.isPresent()) {
				Contact contact = new Contact();
				contact = optContact.get();
				if (contact.getHcLastop().equals("FAILED")) {
					
					String jsonError = contact.getHcErr();
					
					if (jsonError == null || jsonError.isEmpty()) {
						resp.setResponse("FAILED");
						return resp;
					}
					
					ObjectMapper mapper = new ObjectMapper();
					JsonNode node = null;
					try {
						node = mapper.readTree(jsonError);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String msgErr = node.get("msg").asText();
					resp.setResponse(msgErr);
					return resp;
				}
				if (contact.getSfid() != null) {
					resp.setResponse("OK");
					return resp;
				}
				else {
					resp.setResponse("PENDING");
					return resp;
				}
			}
			else {
				resp.setResponse("FAILED");
				return resp;
			}
		}
	}


	

}
