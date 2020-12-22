package com.minsait.affinity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.affinity.jpa.model.SubbrandC;
import com.minsait.affinity.repo.SubBrandRepository;
import com.minsait.affinity.web.model.WsBrands;

@Service
public class SalesforceBrandService {
	
	
	@Autowired
	private SubBrandRepository subbrandRepository;
	

	public List<WsBrands> getBrands() {
		Iterable<SubbrandC> subbrands = this.subbrandRepository.findSubbrands();
		List<WsBrands> wsBrands = new ArrayList<WsBrands>();
		WsBrands wsb;
		for (SubbrandC s : subbrands) {
			wsb = new WsBrands();
			wsb.setName(s.getTxtDescriptionC());
			wsBrands.add(wsb);

		}		
		subbrands = null;
		wsb = null;
		return wsBrands;
	}

}
