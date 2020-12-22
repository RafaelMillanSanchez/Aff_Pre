package com.minsait.affinity.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.ResumenVentasYearRdc;

public interface VentasYearRDCRepository extends PagingAndSortingRepository<ResumenVentasYearRdc, Integer> {

	Iterable<ResumenVentasYearRdc> findByRdc(String customerRdc);
	

	
}