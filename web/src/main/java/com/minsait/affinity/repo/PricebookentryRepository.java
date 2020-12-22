package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Pricebook2;
import com.minsait.affinity.jpa.model.Pricebookentry;

public interface PricebookentryRepository extends PagingAndSortingRepository<Pricebookentry, Integer>{

	
	@Query("select p from Pricebookentry p where (select p2.isstandard from Pricebook2 p2 where p2.sfid = p.pricebook2id) = true and p.product2id = ?1")
	List<Pricebookentry> findPriceBookEntry(String prodId, PageRequest pageRequest);
	
	@Query("select p from Pricebookentry p where p.pricebook2id = ?1 and p.product2id = ?2")
	Pricebookentry findPricePotential(String pricebook2id, String productid);

}
