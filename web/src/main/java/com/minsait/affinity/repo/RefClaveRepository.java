package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.RefClaveTablaFinal;

public interface RefClaveRepository extends PagingAndSortingRepository<RefClaveTablaFinal, Integer> {

	List<RefClaveTablaFinal> findByCustomerCode(String customerCode);
	

	
}