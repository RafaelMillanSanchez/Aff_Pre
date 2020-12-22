package com.minsait.affinity.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.affinity.jpa.model.Event;
import com.minsait.affinity.repo.EventRepository;
import com.minsait.affinity.service.SalesforceEventService;
import com.minsait.affinity.web.api.EventApi;
import com.minsait.affinity.web.model.WsEvent;
import com.minsait.affinity.web.model.WsNewEvent;

import io.swagger.annotations.ApiParam;

@RestController
public class EventControllerImpl implements EventApi{
	
	@Autowired
	private SalesforceEventService service;
	@Autowired
	private EventRepository eventRepository;

	@Override
	@RequestMapping(value = "/event",
    produces = { "application/json" }, 
    method = RequestMethod.PUT)
	public ResponseEntity<List<WsEvent>> updateRuta(
			@ApiParam(value = "" ,required=true) @RequestHeader(value="Accept-Language", required=true) String acceptLanguage,
			@ApiParam(value = "", required = true) List<WsEvent> events) {
		System.out.println(events);
		Iterable<Event> es = this.service.saveWsEvent(events);
		if (es == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(events, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/event/cancel",
    produces = { "application/json" }, 
    method = RequestMethod.PUT)
	public ResponseEntity<Void> cancelRutas(
		@ApiParam(value = "" ,required=true) @RequestHeader(value="Accept-Language", required=true) String acceptLanguage,
		@ApiParam(value = "", required = true) List<String> ids) {
		Integer i = this.eventRepository.cancelRutas("Cancelled", ids);
		if (i == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/event/rutaIS",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> rutasAIS(
			@ApiParam(value = "" ,required=true) @RequestHeader(value="Accept-Language", required=true) String acceptLanguage,
			@ApiParam(value = "", required = true) List<String> ids) {	
		Iterable<Event> evs = this.service.pasarIS(ids);
		if (evs == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/event/newRuta",
    produces = { "application/json" }, 
    method = RequestMethod.POST)
	public ResponseEntity<Void> newRuta(
			@ApiParam(value = "" ,required=true) @RequestHeader(value="Accept-Language", required=true) String acceptLanguage, 
			@ApiParam(value = "", required = true) WsNewEvent body) {
		Event e = this.service.insertRuta(body);
		if (e == null)  return new ResponseEntity<>(HttpStatus.CONFLICT);
		else return new ResponseEntity<>(HttpStatus.OK);
	}

	

}
