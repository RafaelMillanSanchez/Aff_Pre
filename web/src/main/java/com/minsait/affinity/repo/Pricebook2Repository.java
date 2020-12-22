package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Pricebook2;

public interface Pricebook2Repository extends PagingAndSortingRepository<Pricebook2, Integer>{

	
	@Query("select p from Pricebook2 p where isstandard = true and isactive = true")
	List<Pricebook2> findStandardPricebook(PageRequest of);
	
	@Query("select p from Pricebook2 p where p.pckSalesEntityC = ?1 and p.pckChannelC = ?2")
	Pricebook2 findPricebookBySalesChannel(String salesentity, String channel);

}
