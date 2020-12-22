package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.Top30FinalEurKgAffinity;

public interface Top30EurKgAffRepository extends PagingAndSortingRepository<Top30FinalEurKgAffinity, Integer> {

	List<Top30FinalEurKgAffinity> findByCustomerCode(String customerCode);
	

	
}