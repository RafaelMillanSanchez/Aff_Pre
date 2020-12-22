package com.minsait.affinity.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.TablaVentasGeneral;
import com.minsait.affinity.jpa.model.VentasGeneral;

public interface TablaVentasGeneralRepository extends PagingAndSortingRepository<TablaVentasGeneral, Integer> {

	@Query("select new com.minsait.affinity.jpa.model.VentasGeneral(s.customerCode, s.basereferencecode) "
			+ "from TablaVentasGeneral s "
			+ "where CAST (s.customerCode as text) = ?1 "
			+ "and length(s.basereferencecode) = 18 "
			+ "and substring(s.basereferencecode, 15) IN ?2")
	Iterable<VentasGeneral> findProductGeneral(String txtSapidC, Set<String> baserefs);

}
