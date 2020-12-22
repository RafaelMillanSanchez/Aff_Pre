package com.minsait.affinity.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.TablaVentas;
import com.minsait.affinity.jpa.model.Ventas;

public interface TablaVentasRepository extends PagingAndSortingRepository<TablaVentas, Integer> {

	//@Query("select s from TablaVentas s where CAST (s.customerCode as text) = ?1 and substring(s.basereferencecode, 15) IN ?2")
	@Query("select new com.minsait.affinity.jpa.model.Ventas(s.customerCode, count(s.netnetsales), s.basereferencecode, s.monthNum, s.years) "
			+ "from TablaVentas s "
			+ "where CAST (s.customerCode as text) = ?1 "
			+ "and length(s.basereferencecode) = 18 "
			+ "and substring(s.basereferencecode, 15) IN ?2 "
			+ "group by s.customerCode, s.basereferencecode, s.monthNum, s.years "
			+ "order by  years desc, month_num desc")
	List<Ventas> findProductultmeses(String txtSapidC, Set<String> baseReferences);
	
}
