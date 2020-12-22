package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.ResumenVentasMonth;

public interface VentasMonthRepository extends PagingAndSortingRepository<ResumenVentasMonth, Integer> {

	List<ResumenVentasMonth> findByCustomer(String customerCode);
	

	
}