package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Top30FinalEurKg;

public interface Top30EurKgRepository extends PagingAndSortingRepository<Top30FinalEurKg, Integer> {

	List<Top30FinalEurKg> findByCustomerCode(String customerCode);
	

	
}