package com.minsait.affinity.mappers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.minsait.affinity.jpa.model.Account;
import com.minsait.affinity.jpa.model.AccountDeliveryDatesC;
import com.minsait.affinity.jpa.model.AcuerdoComercialC;
import com.minsait.affinity.jpa.model.Contact;
import com.minsait.affinity.jpa.model.Event;
import com.minsait.affinity.jpa.model.ObjetivoCualitativoC;
import com.minsait.affinity.jpa.model.ObjetivoGlobal;
import com.minsait.affinity.jpa.model.ObjetivocuantitativoC;
import com.minsait.affinity.jpa.model.Product2;
import com.minsait.affinity.jpa.model.Recordtype;
import com.minsait.affinity.jpa.model.RefClaveTablaFinal;
import com.minsait.affinity.jpa.model.ResumenVentasMonth;
import com.minsait.affinity.jpa.model.ResumenVentasMonthRdc;
import com.minsait.affinity.jpa.model.ResumenVentasYear;
import com.minsait.affinity.jpa.model.ResumenVentasYearRdc;
import com.minsait.affinity.jpa.model.Rutas;
import com.minsait.affinity.jpa.model.SubbrandC;
import com.minsait.affinity.jpa.model.Task;
import com.minsait.affinity.jpa.model.Top30FinalEurKg;
import com.minsait.affinity.jpa.model.Top30FinalEurKgAffinity;
import com.minsait.affinity.jpa.model.User;
import com.minsait.affinity.repo.AccountDeliveryDatesRepository;
import com.minsait.affinity.repo.AccountRepository;
import com.minsait.affinity.repo.ContactRepository;
import com.minsait.affinity.repo.EventRepository;
import com.minsait.affinity.repo.OrderRepository;
import com.minsait.affinity.repo.ProductRepository;
import com.minsait.affinity.repo.RecordTypeRepository;
import com.minsait.affinity.repo.SubBrandRepository;
import com.minsait.affinity.repo.TaskRepository;
import com.minsait.affinity.repo.UserRepository;
import com.minsait.affinity.web.model.WsAccount;
import com.minsait.affinity.web.model.WsAcuerdos;
import com.minsait.affinity.web.model.WsCualiObj;
import com.minsait.affinity.web.model.WsDirectClient;
import com.minsait.affinity.web.model.WsIS;
import com.minsait.affinity.web.model.WsNewContact;
import com.minsait.affinity.web.model.WsNewDirect;
import com.minsait.affinity.web.model.WsNewPotential;
import com.minsait.affinity.web.model.WsNewShipto;
import com.minsait.affinity.web.model.WsProduct;
import com.minsait.affinity.web.model.WsQuantObj;
import com.minsait.affinity.web.model.WsQuantObjGlobal;
import com.minsait.affinity.web.model.WsRefClave;
import com.minsait.affinity.web.model.WsRutas;
import com.minsait.affinity.web.model.WsSearchAccount;
import com.minsait.affinity.web.model.WsTop30EurKg;
import com.minsait.affinity.web.model.WsTop30EurKgAff;
import com.minsait.affinity.web.model.WsVentasMonth;
import com.minsait.affinity.web.model.WsVentasYear;


@Component
public class AccountDataMapper {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ContactRepository contactRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private SubBrandRepository subbrandRepository;

	@Autowired
	private AccountDeliveryDatesRepository deliveryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RecordTypeRepository rectypeRepository;
	
	/*public WsAccount salesforce2web(Account a, String username) {
		if (null == a) return null;
		WsAccount wsa = new WsAccount();
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
		
		Contact c = this.contactRepo.findMainContact(a.getSfid(),  PageRequest.of(0,1));
		if (c != null) {
			wsa.setNombreContacto(c.getName());
			wsa.setEmail(c.getEmail());
			wsa.setPhone(c.getPhone());
			wsa.setHomePhone(c.getHomephone());
			wsa.setMobile(c.getMobilephone());
			wsa.setOtherPhone(c.getOtherphone());
		}	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Event ev = new Event();
		ev = this.eventRepo.findProximaRuta(a.getSfid(), date, PageRequest.of(0,1));

		if (ev != null) wsa.setProximaRuta(dateFormat.format(ev.getActivitydate()));
		
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
			Account parent = this.accountRepository.findBySfid(a.getParentid());
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
			parent = null;
		}
		
		c = null;
		ev = null;
		date = null;
		return wsa;
	}

	public List<WsAccount> salesforce2web(Iterable<Account> allAccounts, String username) {
		List<WsAccount> lst = null;
		if (null != allAccounts) {
			lst = new ArrayList<>();
			for (Account a : allAccounts) {
				lst.add(this.salesforce2web(a, username));
			}
		}
		return lst;
	}*/
	
	public List<WsSearchAccount> searchAccounts2web(Iterable<Account> allAccounts, List<String> recTypeIds) {
		List<WsSearchAccount> lst = null;
		if (allAccounts != null) {
			lst = new ArrayList<>();
			for (Account a : allAccounts) {
				lst.add(this.searchAccounts2web(a, recTypeIds));
			}
		}
		return lst;
	}
	
	
	private WsSearchAccount searchAccounts2web(Account a, List<String> recTypeIds) {
		if (a == null) return null;
		WsSearchAccount wsa = new WsSearchAccount();
		wsa.setId(a.getId());
		wsa.setAccountId(a.getSfid());
		wsa.setAccountName(a.getName());
		wsa.setSalesEntity(a.getPckSalesEntityC());
		wsa.setCustomerCode(a.getTxtSapidC());
		wsa.setCifNif(a.getTxtCifNifC());
		wsa.setChannel(a.getPckChannelC());
		wsa.setCustomerStatus(a.getPckCustomerStatusC());
		wsa.setParentAccount(a.getParentid());
		wsa.setAccountType(a.getPckAccountTypeC());
		wsa.setPartitaIVA(a.getTxtPartitaIvaC());
		wsa.setSdInumber(a.getTxtSdiC());
		wsa.setChain(a.getPckChainC());
		wsa.setSubchain(a.getPckSubchainC());
		wsa.setIS(a.getLkpIsC());
		wsa.setIsBackup(a.getLkpIsBackupC());
		wsa.setRDC(a.getLkpRdcC());
		wsa.setTAM(a.getPckTamC());
		String tipoCliente;
		String recordTypeid = a.getRecordtypeid();
		if (recordTypeid.equals(recTypeIds.get(0))) {
			tipoCliente = "Ship To";
			wsa.setShippingStreet(a.getShippingstreet());
			wsa.setShippingZip(a.getShippingpostalcode());
			wsa.setShippingCity(a.getShippingcity());
			wsa.setShippingCountry(a.getShippingcountry());
			wsa.setShippingState(a.getShippingstate());
			wsa.setShippingLat(a.getShippinglatitude());
			wsa.setShippingLong(a.getShippinglongitude());
		}
		else if (recordTypeid.equals(recTypeIds.get(1))) {
			tipoCliente = "Potential";
			wsa.setShippingStreet(a.getBillingstreet());
			wsa.setShippingZip(a.getBillingpostalcode());
			wsa.setShippingCity(a.getBillingcity());
			wsa.setShippingCountry(a.getBillingcountry());
			wsa.setShippingState(a.getBillingstate());
			wsa.setShippingLat(a.getBillinglatitude());
			wsa.setShippingLong(a.getBillinglongitude());
		}
		else {
			tipoCliente = "Direct";
			wsa.setShippingStreet(a.getBillingstreet());
			wsa.setShippingZip(a.getBillingpostalcode());
			wsa.setShippingCity(a.getBillingcity());
			wsa.setShippingCountry(a.getBillingcountry());
			wsa.setShippingState(a.getBillingstate());
			wsa.setShippingLat(a.getBillinglatitude());
			wsa.setShippingLong(a.getBillinglongitude());
		}
		wsa.setTipoCliente(tipoCliente);
		wsa.setPhone(a.getPhoneSapPhone1C());
		wsa.setMobilephone(a.getPhoneSapPhone2C());
		wsa.setAddphone(a.getPhoneSapPhone3C());
		wsa.setEmail1(a.getMailSapEmail1C());
		wsa.setEmail2(a.getMailSapEmail2C());
		wsa.setEmail3(a.getMailSapEmail3C());
		wsa.setWithoutCallRoute(a.getChkClientWithoutCallRouteC());
		wsa.setWithoutVisitRoute(a.getChkClientWithoutVisitRouteC());
		wsa.setInitCallRoute(a.getDttInitCallRouteC());
		wsa.setInitVisitRoute(a.getDttInitVisitRouteC());
		wsa.setCallWeeks(a.getTxtCallWeeksC());
		wsa.setVisitWeeks(a.getTxtVisitWeeksC());
		wsa.setWarnAmout(a.getPckWarnAmountC());
		wsa.setWarehouseCode(a.getPckCustomerWarehouseCodeC());
		wsa.setRouteDays(a.getMulShipToRouteDaysC());
		wsa.setMorningDelOpenTime(a.getPckMorningDeliveryOpeningTimeC());
		wsa.setMorningDelCloseTime(a.getPckMorningDeliveryClosingTimeC());
		wsa.setAfterDelOpenTime(a.getPckAfternoonDeliveryOpeningTimeC());
		wsa.setAfterDelCloseTime(a.getPckAfternoonDeliveryClosingTimeC());
		//wsa.setEmployees(a.getNumNumberofemployeesC());
		wsa.setVeterinarian(a.getChkHasVeterinarianC());
		wsa.setArea(a.getPckAreaC());
		wsa.setWindowDressing(a.getChkWindowDressingC());
		wsa.setPetfoodMeters(a.getNumPetfoodMetersC());
		wsa.setHomeDelivery(a.getChkHomeDeliveryC());
		wsa.setWorkingBrands(a.getMulWorkingBrandsC());
		wsa.setHairdresser(a.getChkHairdresserC());
		wsa.setProductRange(a.getPckProductRangeC());
		wsa.setLanguage(a.getPetxLanguageC());
		wsa.setMorningOpenTime(a.getPckMorningOpeningTimeC());
		wsa.setMorningCloseTime(a.getPckMorningClosingTimeC());
		wsa.setAfterOpenTime(a.getPckAfternoonOpeningTimeC());
		wsa.setAfterCloseTime(a.getPckAfternoonClosingTimeC());
		wsa.setTimeToCall(a.getPckPreferredTimeToCallC());
		wsa.setPayType(a.getPckPaymentTypeC());
		wsa.setPayMethod(a.getPckPaymentMethodC());
		wsa.setInterests(a.getMulInterestsC());
		wsa.setEqualizationTax(a.getChkSalesEqualizationTaxC());
		//wsa.setConvertionStatus(a.getPckConvertionStatusC());
		tipoCliente = null;
		recordTypeid = null;
		return wsa;
	}

	/*public WsRutas acc2ruta(Rutas o, String username, Date dat) {
		if (o == null) return null;
		WsRutas wsr = new WsRutas();
		Contact c = new Contact();
		SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
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
			wsr.setM2local(o.getShipto().getPckAreaC());
			wsr.setRoutedays(o.getShipto().getMulShipToRouteDaysC());
			wsr.setStatus(o.getShipto().getPckCustomerStatusC());
			wsr.setTipo(o.getShipto().getPckAccountTypeC());
			wsr.setShiptocode(o.getShipto().getTxtSapidC());
			wsr.setShiptoid(o.getShipto().getSfid());
			wsr.setChain(o.getShipto().getPckChainC());
			wsr.setSubchain(o.getShipto().getPckSubchainC());
			wsr.setEventid(o.getRuta().getSfid());
			
			AccountDeliveryDatesC acc = this.deliveryRepository.findDeliveryDate(o.getShipto().getSfid(), PageRequest.of(0,1));
			if (acc != null) wsr.setDeliverydate(formatter6.format(acc.getDatIddpC()));
			
			Integer orders = this.orderRepository.findOrders(o.getShipto().getSfid(), dat);
			if (orders > 0) wsr.setPedidos(true);
			else wsr.setPedidos(false);	
			List<Task> calls = this.taskRepository.findCalls(o.getShipto().getSfid(), dat);
			wsr.setLlamadaIS(false);
			User u;
			String title;
			for (Task t : calls) {
				u = this.userRepo.findBySfid(t.getOwnerid());
				title = u.getTitle();
				if (title != null && title.equals("Internal Sales")) {
					wsr.setLlamadaIS(true);
					break;
				}
			}
			
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
			int informes = this.taskRepository.findInformes(o.getShipto().getSfid());
			if (informes > 0) wsr.setInforme(true);
			else wsr.setInforme(false);
			Event e = this.eventRepo.findProximaRuta(o.getShipto().getSfid(), dat, PageRequest.of(0,1));
			if (e != null) wsr.setProximaRuta(dateFormat.format(e.getActivitydate()));
			c = this.contactRepo.findMainContact(o.getShipto().getSfid(), PageRequest.of(0,1));
			e = null;
			calls = null;
			title = null;
			orders = null;
			acc = null;
			formatter6 = null;
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
		if (o.getRuta().getStartdatetime().before(dat) && o.getRuta().getActivityStatusC().equals("Not Started")) wsr.setRutaStatus("Old");
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
		return wsr;
		
		
	}*/
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	/*public List<WsRutas> acc2ruta(Iterable<Rutas> rutas, String username, Date dat) {
		List<WsRutas> lst = null;
		if (rutas != null) {
			lst = new ArrayList<>();
			for (Rutas o : rutas) {
				lst.add(this.acc2ruta(o, username, dat));
			}		
		}
		return lst;
	}*/
	

	public List<WsRefClave> refclave2web(Iterable<RefClaveTablaFinal> refClave_lst, Map<String, String> mapReferenceSubbrand) {
		List<WsRefClave> lst = null;
		if (refClave_lst != null) {
			lst = new ArrayList<>();
			for (RefClaveTablaFinal ref : refClave_lst) {
				String subbrand = mapReferenceSubbrand.get(ref.getSubcadena());
				lst.add(this.refclave2web(ref, subbrand));
			}
		}
		return lst;
	}

	private WsRefClave refclave2web(RefClaveTablaFinal ref, String subbrand) {
		if (ref == null) return null;
		WsRefClave wsr = new WsRefClave();
		
		if (ref.getPromSales() == null) wsr.setPromSales(0.0);
		else wsr.setPromSales(round(ref.getPromSales(), 2));
		
		if (ref.getPromSalesKg() == null) wsr.setPromSalesKg(0.0);
		else wsr.setPromSalesKg(round(ref.getPromSalesKg(), 2));
		
		if (ref.getCountryPromSales() == null) wsr.setCountryPromSales(0.0);
		else wsr.setCountryPromSales(round(ref.getCountryPromSales(), 2));
		
		if (ref.getCountryPromSalesKg() == null) wsr.setCountryPromSalesKg(0.0);
		else wsr.setCountryPromSalesKg(round(ref.getCountryPromSalesKg(), 2));
		
		if (ref.getRdcPromSales() == null) wsr.setRdcPromSales(0.0);
		else wsr.setRdcPromSales(round(ref.getRdcPromSales(), 2));
		
		if (ref.getRdcPromSalesKg() == null) wsr.setRdcPromSalesKg(0.0);
		else wsr.setRdcPromSalesKg(round(ref.getRdcPromSalesKg(), 2));
		
		if (ref.getTamPromSales() == null) wsr.setTamPromSales(0.0);
		else wsr.setTamPromSales(round(ref.getTamPromSales(), 2));
		
		if (ref.getTamPromSalesKg() == null) wsr.setTamPromSalesKg(0.0);
		else wsr.setTamPromSalesKg(round(ref.getTamPromSalesKg(), 2));
		
		wsr.setBaseReference(ref.getBaseReference());
		wsr.setCustomerCode(ref.getCustomerCode());
		wsr.setProductName(ref.getProductName());
		wsr.setStatus(ref.getStatus());
		wsr.setSubbrand(subbrand);
		wsr.setFechaCompra(ref.getFechaCompra());
		return wsr;
	}

	public List<WsTop30EurKg> top30eur2web(Iterable<Top30FinalEurKg> top30eur_lst) {
		List<WsTop30EurKg> lst = null;
		if (top30eur_lst != null) {
			lst = new ArrayList<>();
			for (Top30FinalEurKg top : top30eur_lst) {
				lst.add(this.top30eur2web(top));
			}
		}
		return lst;
	}

	private WsTop30EurKg top30eur2web(Top30FinalEurKg top) {
		if (top == null) return null;
		WsTop30EurKg wsr = new WsTop30EurKg();
		wsr.setProductName(top.getProductName());
		wsr.setCustomerCode(top.getCustomerCode());
		wsr.setBaseReference(top.getBaseReference());
		
		if (top.getSumEurCountry() == null) wsr.setSumEurCountry(0.0);
		else wsr.setSumEurCountry(round(top.getSumEurCountry(), 2));
		
		if (top.getSumEuros() == null) wsr.setSumEuros(0.0);
		else wsr.setSumEuros(round(top.getSumEuros(), 2));
		
		if (top.getSumEurRdc() == null) wsr.setSumEurRdc(0.0);
		else wsr.setSumEurRdc(round(top.getSumEurRdc(), 2));
		
		if (top.getSumEurTam() == null) wsr.setSumEurTam(0.0);
		else wsr.setSumEurTam(round(top.getSumEurTam(), 2));
		
		if (top.getSumKg() == null) wsr.setSumKg(0.0);
		else wsr.setSumKg(round(top.getSumKg(), 2));
		
		if (top.getSumKgRdc() == null) wsr.setSumKgRdc(0.0);
		else wsr.setSumKgRdc(round(top.getSumKgRdc(), 2));
		
		if (top.getSumKgTam() == null) wsr.setSumKgTam(0.0);
		else wsr.setSumKgTam(round(top.getSumKgTam(), 2));
		
		if (top.getSumKgCountry() == null) wsr.setSumKgCountry(0.0);
		else wsr.setSumKgCountry(round(top.getSumKgCountry(), 2));
		
		return wsr;
	}

	public List<WsTop30EurKgAff> top30euraff2web(Iterable<Top30FinalEurKgAffinity> top30eur_lst) {
		List<WsTop30EurKgAff> lst = null;
		if (top30eur_lst != null) {
			lst = new ArrayList<>();
			for (Top30FinalEurKgAffinity top : top30eur_lst) {
				lst.add(this.top30euraff2web(top));
			}
		}
		return lst;
	}

	private WsTop30EurKgAff top30euraff2web(Top30FinalEurKgAffinity top) {
		if (top == null) return null;
		WsTop30EurKgAff wsr = new WsTop30EurKgAff();
		wsr.setProductName(top.getProductName());
		wsr.setCustomerCode(top.getCustomerCode());
		wsr.setBaseReference(top.getBaseReference());
		wsr.setCountry(top.getCountry());
		
		if (top.getSumEurCountry() == null) wsr.setSumEurCountry(0.0);
		else wsr.setSumEurCountry(round(top.getSumEurCountry(), 2));
		
		if (top.getSumEur() == null) wsr.setSumEur(0.0);
		else wsr.setSumEur(round(top.getSumEur(), 2));
		
		if (top.getSumEurRdc() == null) wsr.setSumEurRdc(0.0);
		else wsr.setSumEurRdc(round(top.getSumEurRdc(), 2));
		
		if (top.getSumEurTam() == null) wsr.setSumEurTam(0.0);
		else wsr.setSumEurTam(round(top.getSumEurTam(), 2));
		
		if (top.getSumKg() == null) wsr.setSumKg(0.0);
		else wsr.setSumKg(round(top.getSumKg(), 2));
		
		if (top.getSumKgRdc() == null) wsr.setSumKgRdc(0.0);
		else wsr.setSumKgRdc(round(top.getSumKgRdc(), 2));
		
		if (top.getSumKgTam() == null) wsr.setSumKgTam(0.0);
		else wsr.setSumKgTam(round(top.getSumKgTam(), 2));
		
		if (top.getSumKgCountry() == null) wsr.setSumKgCountry(0.0);
		else wsr.setSumKgCountry(round(top.getSumKgCountry(), 2));
		
		return wsr;
	}

	public List<WsVentasMonth> ventasmonth2web(Iterable<ResumenVentasMonth> lst_ventas) {
		List<WsVentasMonth> lst = null;
		if (lst_ventas != null) {
			lst = new ArrayList<>();
			for (ResumenVentasMonth elem : lst_ventas) {
				lst.add(this.ventasmonth2web(elem));
			}
		}
		return lst;
	}

	private WsVentasMonth ventasmonth2web(ResumenVentasMonth elem) {
		if (elem == null) return null;
		WsVentasMonth wsv = new WsVentasMonth();
		wsv.setBrand(elem.getBrand());
		wsv.setBrandName(elem.getBrandName());
		wsv.setSubbrandName(elem.getSubbrandName());
		wsv.setCurrentMonth(elem.getCurrentMonth());
		wsv.setMonths(elem.getMonths());
		if (elem.getVentas() == null) wsv.setVentas(0.0);
		else wsv.setVentas(round(elem.getVentas(), 2));
		wsv.setYears(elem.getYears());
		wsv.setCurrentMonth(elem.getCurrentMonth());
		if (elem.getVentasKg() == null) wsv.setVentasKg(0.0);
		else wsv.setVentasKg(round(elem.getVentasKg(), 2));
		return wsv;
	}

	public List<WsVentasMonth> ventasmonthrdc2web(Iterable<ResumenVentasMonthRdc> lst_ventas) {
		List<WsVentasMonth> lst = null;
		if (lst_ventas != null) {
			lst = new ArrayList<>();
			for (ResumenVentasMonthRdc elem : lst_ventas) {
				lst.add(this.ventasmonthrdc2web(elem));
			}
		}
		return lst;
	}

	private WsVentasMonth ventasmonthrdc2web(ResumenVentasMonthRdc elem) {
		if (elem == null) return null;
		WsVentasMonth wsv = new WsVentasMonth();
		wsv.setBrand(elem.getBrand());
		wsv.setBrandName(elem.getBrandName());
		wsv.setSubbrandName(elem.getSubbrandName());
		wsv.setCurrentMonth(elem.getCurrentMonth());
		wsv.setMonths(elem.getMonths());
		if (elem.getVentas() == null) wsv.setVentas(0.0);
		else wsv.setVentas(round(elem.getVentas(), 2));
		wsv.setYears(elem.getYears());
		if (elem.getVentasKg() == null) wsv.setVentasKg(0.0);
		else wsv.setVentasKg(round(elem.getVentasKg(), 2));
		return wsv;
	}

	public List<WsVentasYear> ventasyear2web(Iterable<ResumenVentasYear> lst_ventas) {
		List<WsVentasYear> lst = null;
		if (lst_ventas != null) {
			lst = new ArrayList<>();
			for (ResumenVentasYear elem : lst_ventas) {
				lst.add(this.ventasyear2web(elem));
			}
		}
		return lst;
	}

	private WsVentasYear ventasyear2web(ResumenVentasYear elem) {
		if (elem == null) return null;
		WsVentasYear wsy = new WsVentasYear();
		wsy.setBrand(elem.getBrand());
		wsy.setBrandName(elem.getBrandName());
		wsy.setSubbrandName(elem.getSubbrandName());
		wsy.setFullYear(elem.getFullYear());
		if (elem.getVentas() == null) wsy.setVentas(0.0);
		else wsy.setVentas(round(elem.getVentas(), 2));
		wsy.setYears(elem.getYears());
		if (elem.getVentasKg() == null) wsy.setVentasKg(0.0);
		else wsy.setVentasKg(round(elem.getVentasKg(), 2));
		return wsy;
	}

	public List<WsVentasYear> ventasyearrdc2web(Iterable<ResumenVentasYearRdc> lst_ventas) {
		List<WsVentasYear> lst = null;
		if (lst_ventas != null) {
			lst = new ArrayList<>();
			for (ResumenVentasYearRdc elem : lst_ventas) {
				lst.add(this.ventasyearrdc2web(elem));
			}
		}
		return lst;
	}

	private WsVentasYear ventasyearrdc2web(ResumenVentasYearRdc elem) {
		if (elem == null) return null;
		WsVentasYear wsy = new WsVentasYear();
		wsy.setBrand(elem.getBrand());
		wsy.setBrandName(elem.getBrandName());
		wsy.setSubbrandName(elem.getSubbrandName());
		wsy.setFullYear(elem.getFullYear());
		if (elem.getVentas() == null) wsy.setVentas(0.0);
		else wsy.setVentas(round(elem.getVentas(), 2));
		wsy.setYears(elem.getYears());
		if (elem.getVentasKg() == null) wsy.setVentasKg(0.0);
		else wsy.setVentasKg(round(elem.getVentasKg(), 2));
		return wsy;
	}

	public List<WsProduct> promos2web(Iterable<Product2> prods) {
		List<WsProduct> lst = null;
		if (prods != null) {
			lst = new ArrayList<>();
			for (Product2 elem : prods) {
				lst.add(this.promos2web(elem));
			}
		}
		return lst;
	}

	private WsProduct promos2web(Product2 p) {
		if (p == null) return null;
		WsProduct wsprod = new WsProduct();
		wsprod.setNameP(p.getName());
		wsprod.setCode(p.getProductcode());
		wsprod.setSubbrand(p.getFrmSubbrandC());
		wsprod.setLine(p.getFrmLineC());
		wsprod.setWeight(p.getNumWeightC());
		if (p.getNumBoxesXPaletC() != null) wsprod.setBoxesxpalet(p.getNumBoxesXPaletC());
		if (p.getNumUnitsBoxC() != null) wsprod.setUnitsbox(p.getNumUnitsBoxC());
		return wsprod;
	}

	public void objindiv2web(Iterable<ObjetivocuantitativoC> obj_cuanti_indiv, WsQuantObj wsquant) {
		if (obj_cuanti_indiv != null) {
			for (ObjetivocuantitativoC elem : obj_cuanti_indiv) {
				wsquant.addIndividualItem(this.objindiv2web(elem));
			}
		}
	}

	private WsQuantObjGlobal objindiv2web(ObjetivocuantitativoC elem) {
		if (elem == null) return null;
		WsQuantObjGlobal wsq = new WsQuantObjGlobal();
		wsq.setChannel(elem.getChannelC());
		wsq.setConsecucion(elem.getAchievementC());
		wsq.setEndDate(elem.getEnddateC());
		wsq.setObjetivo(elem.getGoalC());
		wsq.setSalesEntity(elem.getSalesentityC());
		wsq.setStartDate(elem.getStartdateC());
		SubbrandC submarca = this.subbrandRepository.findBySfid(elem.getLkpSubbrandC());
		wsq.setSubbrand(submarca.getTxtDescriptionC());
		submarca = null;
		return wsq;
	}

	public void objglobal2web(Iterable<ObjetivoGlobal> obj_cuanti_global, WsQuantObj wsquant) {
		if (obj_cuanti_global != null) {
			for (ObjetivoGlobal elem : obj_cuanti_global) {
				wsquant.addGlobalItem(this.objglobal2web(elem));
			}
		}
		
	}

	private WsQuantObjGlobal objglobal2web(ObjetivoGlobal elem) {
		if (elem == null) return null;
		WsQuantObjGlobal wsq = new WsQuantObjGlobal();
		wsq.setChannel(elem.getChannelC());
		if (elem.getAchivSum() != null) wsq.setConsecucion(elem.getAchivSum());
		else wsq.setConsecucion(0.0);
		wsq.setEndDate(elem.getEnddateC());
		if (elem.getGoalSum() != null) wsq.setObjetivo(elem.getGoalSum());
		else wsq.setObjetivo(0.0);
		wsq.setSalesEntity(elem.getSalesentityC());
		wsq.setStartDate(elem.getStartdateC());
		SubbrandC submarca = this.subbrandRepository.findBySfid(elem.getSubbrandC());
		wsq.setSubbrand(submarca.getTxtDescriptionC());
		submarca = null;
		return wsq;
	}

	public List<WsAcuerdos> acuerdos2web(Iterable<AcuerdoComercialC> acuerdos) {
		List<WsAcuerdos> lst = null;
		if (acuerdos != null) {
			lst = new ArrayList<>();
			for (AcuerdoComercialC elem : acuerdos) {
				lst.add(this.acuerdos2web(elem));
			}
		}
		return lst;
	}

	private WsAcuerdos acuerdos2web(AcuerdoComercialC elem) {
		if (elem == null) return null;
		WsAcuerdos wsa = new WsAcuerdos();
		if (elem.getConsecucionC() != null) wsa.setConsecucion(elem.getConsecucionC());
		else wsa.setConsecucion(0.0);
		wsa.setEndDate(elem.getFechaFinC());
		if (elem.getObjetivoC() != null) wsa.setObjetivo(elem.getObjetivoC());
		else wsa.setObjetivo(0.0);
		wsa.setStartDate(elem.getFechaInicioC());
		SubbrandC submarca = this.subbrandRepository.findBySfid(elem.getSubbrandC());
		wsa.setSubbrand(submarca.getTxtDescriptionC());
		submarca = null;
		return wsa;
	}

	public List<WsCualiObj> cualitativo2web(Iterable<ObjetivoCualitativoC> objetivos) {
		List<WsCualiObj> lst = null;
		if (objetivos != null) {
			lst = new ArrayList<>();
			for (ObjetivoCualitativoC elem : objetivos) {
				lst.add(this.cualitativo2web(elem));
			}
		}
		return lst;
	}

	private WsCualiObj cualitativo2web(ObjetivoCualitativoC elem) {
		if (elem == null) return null;
		WsCualiObj wsc = new WsCualiObj();
		wsc.setChannel(elem.getTxtChannelC());
		if (elem.getAchievementC() != null) wsc.setConsecucion(elem.getAchievementC());
		else wsc.setConsecucion(0.0);
		wsc.setEndDate(elem.getDatEnddateC());
		if (elem.getNumPdnaC() != null) wsc.setObjetivo(elem.getNumPdnaC());
		else wsc.setObjetivo(0.0);
		List<Product2> producto = this.productRepository.findByBaseReferenceC(elem.getLkpBaseReferenceC());
		if (producto.size() > 0) wsc.setProductName(producto.get(0).getName());
		wsc.setSalesEntity(elem.getTxtSalesEntityC());
		wsc.setStartDate(elem.getDatStartdateC());
		wsc.setTipoCliente(elem.getPckAccountTypeC());
		wsc.setChain(elem.getPckChainC());
		producto = null;
		return wsc;
	}

	public List<WsIS> salesforce2IS(Iterable<User> allIs) {
		List<WsIS> lst = null;
		if (null != allIs) {
			lst = new ArrayList<>();
			for (User u : allIs) {
				lst.add(this.salesforce2IS(u));
			}
		}
		return lst;
	}

	private WsIS salesforce2IS(User u) {
		if (u == null) return null;
		WsIS wsi = new WsIS();
		wsi.setId(u.getSfid());
		wsi.setName(u.getName());
		return wsi;
	}

	public List<WsDirectClient> salesforce2directos(List<Account> directos) {
		List<WsDirectClient> lst = null;
		if (null != directos) {
			lst = new ArrayList<>();
			for (Account a : directos) {
				lst.add(this.salesforce2directos(a));
			}
		}
		return lst;
	}

	private WsDirectClient salesforce2directos(Account a) {
		if (a == null) return null;
		WsDirectClient wsa = new WsDirectClient();
		wsa.setId(a.getSfid());
		wsa.setName(a.getName());
		wsa.setSalesEntity(a.getPckSalesEntityC());
		wsa.setChannel(a.getPckChannelC());
		wsa.setAccountType(a.getPckAccountTypeC());
		wsa.setChain(a.getPckChainC());
		wsa.setSubchain(a.getPckSubchainC());
		wsa.setIS(a.getLkpIsC());
		wsa.setIsBackup(a.getLkpIsBackupC());
		wsa.setRDC(a.getLkpRdcC());
		wsa.setTAM(a.getPckTamC());
		wsa.setShippingStreet(a.getBillingstreet());
		wsa.setShippingZip(a.getBillingpostalcode());
		wsa.setShippingCity(a.getBillingcity());
		wsa.setShippingCountry(a.getBillingcountry());
		wsa.setShippingState(a.getBillingstate());
		wsa.setPayType(a.getPckPaymentTypeC());
		wsa.setPayMethod(a.getPckPaymentMethodC());
		wsa.setInterests(a.getMulInterestsC());
		wsa.setEqualizationTax(a.getChkSalesEqualizationTaxC());
		return wsa;
	}

	public Contact newCnt2salesforce(WsNewContact body, String username) {
		Contact c = new Contact();
		c.setLastname(body.getContactLastName());
		c.setMobilephone(body.getContactMobile());
		c.setPhone(body.getContactphone());
		c.setEmail(body.getContactEmail());
		String rectypeid = this.rectypeRepository.findRecordType("Customer Contact", "Contact");
		c.setRecordtypeid(rectypeid);
		c.setOwnerid(username);
		if (body.getAccountID() != null) c.setAccountid(body.getAccountID());
		else if (body.getWsID() != null) {
			Optional<Account> optaccount = this.accountRepository.findById(body.getWsID());
			Account a = optaccount.get();
			c.setAccountid(a.getSfid());
			c.setMainContactC(true);
			a = null;
			}
		rectypeid = null;
		return this.contactRepo.save(c);	
	}

	public Account newDirect2salesforce(WsNewDirect body, String username) {
		Account direct = new Account();
		if (body.getPotentialId() != null) direct = this.accountRepository.findBySfid(body.getPotentialId());
		User rdc = this.userRepo.findBySfid(username);
		String manager = rdc.getManagerid();
		User tam = new User();
		if (manager != null) {
			tam = this.userRepo.findBySfid(manager);
		}	
		if (rdc.getStaffCountryC() != null) {
			String country = rdc.getStaffCountryC();
			if (country == null || country.equals("Spain")) {
				direct.setPckSalesEntityC("0012");
			}
			else if (country.equals("France")) {
				direct.setPckSalesEntityC("0052");
			}
			else if (country.contentEquals("Italy")) {
				direct.setPckSalesEntityC("0022");
			}
		}
		else direct.setPckSalesEntityC("0012");
		if (!body.getClientToReplace().equals("-Ninguno-")) direct.setLkpClientToReplaceC(body.getClientToReplace());
		direct.setName(body.getName());
		String rectypeid = this.rectypeRepository.findRecordType("Direct Client", "Account");
		direct.setRecordtypeid(rectypeid);
		direct.setBillingcity(body.getBillingCity());
		direct.setBillingcountry(body.getBillingCountry());
		direct.setBillinglatitude(body.getBillingLat());
		direct.setBillinglongitude(body.getBillingLong());
		direct.setBillingpostalcode(body.getBillingZip());
		direct.setBillingstate(body.getBillingState());
		direct.setBillingstreet(body.getBillingStreet());
		direct.setTxtCifNifC(body.getCifNif());
		direct.setPckChannelC(body.getChannel());
		direct.setPckAccountTypeC(body.getAccountType());
		direct.setTxtPartitaIvaC(body.getPartitaIVA());
		direct.setTxtSdiC(body.getSdInumber());
		direct.setPckChainC(body.getChain());
		direct.setPckSubchainC(body.getSubchain());
		direct.setLkpIsC(body.getIS());
		direct.setLkpIsBackupC(body.getIsBackup());
		direct.setLkpRdcC(username);
		direct.setPckRdcC(body.getPckRdc());
		if (tam != null && tam.getTxtSapIdC() != null) direct.setPckTamC(tam.getTxtSapIdC());
		direct.setPhoneSapPhone1C(body.getPhone());
		direct.setPhoneSapPhone2C(body.getMobilephone());
		direct.setPhoneSapPhone3C(body.getAddphone());
		direct.setMailSapEmail1C(body.getEmail1());
		direct.setMailSapEmail2C(body.getEmail2());
		direct.setMailSapEmail3C(body.getEmail3());
		direct.setTxtBankNumberC(body.getBankInformation());
		direct.setPckPaymentTypeC(body.getPayType());
		direct.setPckPaymentMethodC(body.getPayMethod());
		direct.setChkSalesEqualizationTaxC(body.isEqualizationTax());
		direct.setPckWarnAmountC(body.getWarnAmout());
		direct.setOwnerid(username);
		rectypeid = null;
		rdc = null;
		manager = null;
		tam = null;
		return this.accountRepository.save(direct);
	}

	public Account newPotential2salesforce(WsNewPotential body, String username) {
		Account a = new Account();
		User rdc = this.userRepo.findBySfid(username);
		String manager = rdc.getManagerid();
		User tam = new User();
		if (manager != null) {
			tam = this.userRepo.findBySfid(manager);
		}	
		if (rdc.getStaffCountryC() != null) {
			String country = rdc.getStaffCountryC();
			if (country == null || country.equals("Spain")) {
				a.setPckSalesEntityC("0012");
			}
			else if (country.equals("France")) {
				a.setPckSalesEntityC("0052");
			}
			else if (country.contentEquals("Italy")) {
				a.setPckSalesEntityC("0022");
			}
		}
		else a.setPckSalesEntityC("0012");
		a.setTxtCifNifC(body.getCifNif());
		a.setName(body.getName());
		a.setPckChannelC(body.getChannel());
		a.setPckAccountTypeC(body.getAccountType());
		a.setTxtPartitaIvaC(body.getPartitaIVA());
		a.setTxtSdiC(body.getSdInumber());
		a.setPckChainC(body.getChain());
		a.setPckSubchainC(body.getSubchain());
		a.setLkpIsC(body.getIS());
		a.setLkpRdcC(username);
		a.setPckRdcC(body.getPckRdc());
		if (tam != null && tam.getTxtSapIdC() != null) a.setPckTamC(tam.getTxtSapIdC());
		String rectypeid = this.rectypeRepository.findRecordType("Potential Client", "Account");
		a.setRecordtypeid(rectypeid);
		a.setBillingcity(body.getBillingCity());
		a.setBillingcountry(body.getBillingCountry());
		a.setBillinglatitude(body.getBillingLat());
		a.setBillinglongitude(body.getBillingLong());
		a.setBillingpostalcode(body.getBillingZip());
		a.setBillingstate(body.getBillingState());
		a.setBillingstreet(body.getBillingStreet());
		a.setPhoneSapPhone1C(body.getPhone());
		a.setPhoneSapPhone2C(body.getMobilephone());
		a.setPhoneSapPhone3C(body.getAddphone());
		a.setMailSapEmail1C(body.getEmail1());
		a.setMailSapEmail2C(body.getEmail2());
		a.setMailSapEmail3C(body.getEmail3());
		a.setOwnerid(username);
		rectypeid = null;
		rdc = null;
		manager = null;
		tam = null;
		return this.accountRepository.save(a);
		
	}

	public Account newShipTo2salesforce(WsNewShipto body, String username) {
		Account a = new Account();
		User rdc = this.userRepo.findBySfid(username);
		String manager = rdc.getManagerid();
		User tam = new User();
		if (manager != null) {
			tam = this.userRepo.findBySfid(manager);
		}	
		if (rdc.getStaffCountryC() != null) {
			String country = rdc.getStaffCountryC();
			if (country == null || country.equals("Spain")) {
				a.setPckSalesEntityC("0012");
			}
			else if (country.equals("France")) {
				a.setPckSalesEntityC("0052");
			}
			else if (country.contentEquals("Italy")) {
				a.setPckSalesEntityC("0022");
			}
		}
		else a.setPckSalesEntityC("0012");
		a.setTxtCifNifC(body.getCifNif());
		a.setName(body.getName());
		a.setPckChannelC(body.getChannel());
		a.setPckAccountTypeC(body.getAccountType());
		a.setTxtPartitaIvaC(body.getPartitaIVA());
		a.setTxtSdiC(body.getSdInumber());
		a.setPckChainC(body.getChain());
		a.setPckSubchainC(body.getSubchain());
		a.setLkpIsC(body.getIS());
		a.setLkpIsBackupC(body.getIsBackup());
		a.setLkpRdcC(username);
		a.setPckRdcC(body.getPckRdc());
		if (tam != null && tam.getTxtSapIdC() != null) a.setPckTamC(tam.getTxtSapIdC());
		String rectypeid = this.rectypeRepository.findRecordType("Ship To", "Account");
		a.setRecordtypeid(rectypeid);
		a.setShippingstreet(body.getShippingStreet());
		a.setShippingstate(body.getShippingState());
		a.setShippingpostalcode(body.getShippingZip());
		a.setShippingcity(body.getShippingCity());
		a.setShippingcountry(body.getShippingCountry());
		a.setShippinglatitude(body.getShippingLat());
		a.setShippinglongitude(body.getShippingLong());
		a.setPhoneSapPhone1C(body.getPhone());
		a.setPhoneSapPhone2C(body.getMobilephone());
		a.setPhoneSapPhone3C(body.getAddphone());
		a.setMailSapEmail1C(body.getEmail1());
		a.setMailSapEmail2C(body.getEmail2());
		a.setMailSapEmail3C(body.getEmail3());
		a.setChkClientWithoutCallRouteC(body.isWithoutCallRoute());
		a.setChkClientWithoutVisitRouteC(body.isWithoutVisitRoute());
		a.setDttInitCallRouteC(body.getInitCallRoute());
		a.setDttInitVisitRouteC(body.getInitVisitRoute());
		a.setTxtCallWeeksC(body.getCallWeeks());
		a.setTxtVisitWeeksC(body.getVisitWeeks());
		a.setPckWarnAmountC(body.getWarnAmout());
		a.setPckCustomerWarehouseCodeC(body.getWarehouseCode());
		a.setMulShipToRouteDaysC(body.getRouteDays());
		a.setPckMorningDeliveryOpeningTimeC(body.getMorningDelOpenTime());
		a.setPckMorningDeliveryClosingTimeC(body.getMorningDelCloseTime());
		a.setPckAfternoonDeliveryOpeningTimeC(body.getAfterDelOpenTime());
		a.setPckAfternoonDeliveryClosingTimeC(body.getAfterDelCloseTime());
		a.setMulDeliveryAdditionalInfoC(body.getDeliveryAddInfo());
		a.setChkHasVeterinarianC(body.isVeterinarian());
		a.setPckAreaC(body.getArea());
		a.setChkWindowDressingC(body.isWindowDressing());
		a.setNumPetfoodMetersC(body.getPetfoodMeters());
		a.setChkHomeDeliveryC(body.isHomeDelivery());
		a.setMulWorkingBrandsC(body.getWorkingBrands());
		a.setChkHairdresserC(body.isHairdresser());
		a.setPckProductRangeC(body.getProductRange());
		a.setPetxLanguageC(body.getLanguage());
		a.setPckMorningOpeningTimeC(body.getMorningOpenTime());
		a.setPckMorningClosingTimeC(body.getMorningCloseTime());
		a.setPckAfternoonClosingTimeC(body.getAfterCloseTime());
		a.setPckAfternoonOpeningTimeC(body.getAfterOpenTime());
		a.setMulOpeningAdditionalInfoC(body.getOpeningAddInfo());
		a.setPckPreferredTimeToCallC(body.getTimeToCall());
		a.setPckPaymentTypeC(body.getPayType());
		a.setPckPaymentMethodC(body.getPayMethod());
		a.setMulInterestsC(body.getInterests());
		a.setChkSalesEqualizationTaxC(body.isEqualizationTax());
		a.setOwnerid(username);
		if (body.getParentAccount() != null) a.setParentid(body.getParentAccount());
		else if (body.getParentId() != null) {
			Optional<Account> optaccount = this.accountRepository.findById(body.getParentId());
			Account acc = optaccount.get();
			a.setParentid(acc.getSfid());
			acc = null;
		}
		rectypeid = null;
		rdc = null;
		manager = null;
		tam = null;
		return this.accountRepository.save(a);
	}

	


}
