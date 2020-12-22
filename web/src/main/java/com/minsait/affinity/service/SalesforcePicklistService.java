package com.minsait.affinity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.affinity.jpa.model.MtPicklist;
import com.minsait.affinity.repo.PicklistRepository;
import com.minsait.affinity.web.model.WsPicklist;

@Service
public class SalesforcePicklistService {
	
	@Autowired
	private PicklistRepository picklistRepository;
	
	
	public List<WsPicklist> getPicklist(String pck, String relatedpck, String relatedvalue, String relatedpck2, String relatedvalue2 ) {
		List<MtPicklist> pk = new ArrayList<MtPicklist>();
				
		if (relatedpck != null && relatedvalue != null && relatedpck2 != null && relatedvalue2 != null ) {
			pk = this.picklistRepository.getPiklist(pck, relatedpck, relatedvalue, relatedpck2, relatedvalue2);
		}else if(relatedpck != null && relatedvalue != null && relatedpck2 == null && relatedvalue2 == null) {
			pk = this.picklistRepository.getPiklist2(pck, relatedpck, relatedvalue);
		}else if(relatedpck == null && relatedvalue == null && relatedpck2 == null && relatedvalue2 == null) {
			pk = this.picklistRepository.getPiklist3(pck);
		}
				
		
		if (pk == null) return null;
		
		List<WsPicklist> lstpicklist = new ArrayList<WsPicklist>();
		WsPicklist wsp;
		for(MtPicklist pkaux : pk) {	
			wsp = new WsPicklist();
			wsp.setCodigovalor(pkaux.getCodigovalor());
			wsp.setValor(pkaux.getValor());
			lstpicklist.add(wsp);
		}
		pk = null;
		wsp = null;
		
		return lstpicklist;
	}

	

}
