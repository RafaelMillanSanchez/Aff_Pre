package com.minsait.affinity.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.ResumenVentasMonthRdc;

public interface VentasMonthRDCRepository extends PagingAndSortingRepository<ResumenVentasMonthRdc, Integer> {

	Iterable<ResumenVentasMonthRdc> findByRdc(String customerRdc);
	

	
}