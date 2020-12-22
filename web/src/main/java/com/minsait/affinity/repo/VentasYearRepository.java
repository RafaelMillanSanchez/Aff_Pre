package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.ResumenVentasYear;

public interface VentasYearRepository extends PagingAndSortingRepository<ResumenVentasYear, Integer> {

	List<ResumenVentasYear> findByCustomer(String customerCode);
	

	
}