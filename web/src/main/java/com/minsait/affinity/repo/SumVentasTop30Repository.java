package com.minsait.affinity.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.SumVentasTop30;

public interface SumVentasTop30Repository extends PagingAndSortingRepository<SumVentasTop30, Integer> {

	@Query("select s from SumVentasTop30 s where s.customerCode = ?1 and s.baseReference IN ?2")
	Iterable<SumVentasTop30> findProduct(String txtSapidC, Set<String> baserefs);

}
