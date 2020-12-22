package com.minsait.affinity.mappers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.minsait.affinity.jpa.model.Event;
import com.minsait.affinity.repo.EventRepository;
import com.minsait.affinity.repo.RecordTypeRepository;
import com.minsait.affinity.web.model.WsEvent;
import com.minsait.affinity.web.model.WsNewEvent;

@Component
public class EventDataMapper {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private RecordTypeRepository rectypeRepository;

	@Transactional
	public Iterable<Event> web2salesforce(List<WsEvent> events) {
		if (null == events) return null;
		List<Event> evtoup = new ArrayList<Event>();
		SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Event e;
		for (WsEvent ev : events) {
			e = this.eventRepository.findBySfid(ev.getId());
			e.setSfid(ev.getId());
			e.setActivityStatusC(ev.getStatus());
			try {
				Date startdate = formatter6.parse(ev.getStartdate());
				Calendar startcal = Calendar.getInstance();
				startcal.setTime(startdate);
				
				Date actualdate = new Date();
				Calendar actualcal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
				actualcal.setTime(actualdate);
				
				Calendar sfcal = new GregorianCalendar(startcal.get(Calendar.YEAR), startcal.get(Calendar.MONTH), startcal.get(Calendar.DAY_OF_MONTH), actualcal.get(Calendar.HOUR_OF_DAY), actualcal.get(Calendar.MINUTE), actualcal.get(Calendar.SECOND));
				
				Date sfdate = new Date();
				sfdate = sfcal.getTime();
				e.setStartdatetime(sfdate); 
				e.setEnddatetime(sfdate); 
			} catch (java.text.ParseException ep) {
	            ep.printStackTrace();
	        }
			evtoup.add(e);
		}
		e = null;
		formatter6 = null;
		return this.eventRepository.saveAll(evtoup);	
		
	}

	public Event web2salesforce(WsNewEvent body) {
		if (body == null) return null;
		Event e = new Event();
		e.setOwnerid(body.getIdUsuario());
		e.setActivityStatusC("Not Started");
		e.setType("Visit");
		e.setSubject("Scheduled Visit");
		SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date startdate = formatter6.parse(body.getStartdate());
			Calendar startcal = Calendar.getInstance();
			startcal.setTime(startdate);
			
			Date actualdate = new Date();
			Calendar actualcal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			actualcal.setTime(actualdate);
			
			Calendar sfcal = new GregorianCalendar(startcal.get(Calendar.YEAR), startcal.get(Calendar.MONTH), startcal.get(Calendar.DAY_OF_MONTH), actualcal.get(Calendar.HOUR_OF_DAY), actualcal.get(Calendar.MINUTE), actualcal.get(Calendar.SECOND));
			
			Date sfdate = new Date();
			sfdate = sfcal.getTime();
			e.setStartdatetime(sfdate); 
			e.setEnddatetime(sfdate); 
		} catch (java.text.ParseException ep) {
            ep.printStackTrace();
        }
		e.setAccountid(body.getIdShipTo());
		e.setWhatid(body.getIdShipTo());
		String rectypeid = this.rectypeRepository.findRecordType("LIGHT Event", "Event");
		e.setRecordtypeid(rectypeid);
		rectypeid = null;
		formatter6 = null;
		return e;
	}

}
