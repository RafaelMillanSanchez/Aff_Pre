package com.minsait.affinity.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.affinity.jpa.model.Account;
import com.minsait.affinity.jpa.model.Event;
import com.minsait.affinity.mappers.EventDataMapper;
import com.minsait.affinity.repo.AccountRepository;
import com.minsait.affinity.repo.EventRepository;
import com.minsait.affinity.web.model.WsEvent;
import com.minsait.affinity.web.model.WsNewEvent;


@Service
public class SalesforceEventService {
	
	@Autowired
	private EventDataMapper eventMapper;
	
	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private AccountRepository accountRepository;

	
	public Iterable<Event> saveWsEvent(List<WsEvent> events) {
		return this.eventMapper.web2salesforce(events);
	}


	public Iterable<Event> pasarIS(List<String> ids) {
		Iterable<Event> eventos = this.eventRepository.findAllSfid(ids);
		Set<String> shiptoids = new HashSet<String>();
		for (Event e : eventos) shiptoids.add(e.getWhatid());
		Iterable<Account> accounts = this.accountRepository.findAllSfid(shiptoids);
		Map<String, Account> mapAccounts = new HashMap<String, Account>();
		for (Account a : accounts) mapAccounts.put(a.getSfid(), a);
		Account a;
		for (Event e : eventos) {
			a = mapAccounts.get(e.getWhatid());
			e.setOwnerid(a.getLkpIsC());
			e.setType("Call");
			e.setSubject("TRASPASO: " + e.getSubject());
		}
		shiptoids = null;
		accounts = null;
		mapAccounts = null;
		a = null;
		return this.eventRepository.saveAll(eventos);
	}


	public Event insertRuta(WsNewEvent body) {
		SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (this.eventRepository.rutaExists(body.getIdShipTo(), formatter6.parse(body.getStartdate())) != null) return null;
		} catch (java.text.ParseException ep) {
            ep.printStackTrace();
        }
		
		return this.eventRepository.save(this.eventMapper.web2salesforce(body));
		
	}

}
