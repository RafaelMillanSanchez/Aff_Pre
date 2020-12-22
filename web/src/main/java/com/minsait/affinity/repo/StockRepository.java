package com.minsait.affinity.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.LightStockC;

public interface StockRepository extends PagingAndSortingRepository<LightStockC, Integer> {
	
	
	@Query("select s from LightStockC s where s.lkpItemC in ?1 and s.pckWarehouseC = ?2")
	Iterable<LightStockC> findStock(List<String> ids, String pckCustomerWarehouseCodeC);
	
	@Query("select s from LightStockC s where s.lkpItemC = ?1 and s.pckWarehouseC = ?2")
	LightStockC findStock(String product, String pckCustomerWarehouseCodeC, PageRequest of);
	
	@Query("select s from LightStockC s where s.lkpItemC in ?1 and s.pckWarehouseC = ?2")
	Iterable<LightStockC> findStock(Set<String> prodIds, String pckCustomerWarehouseCodeC);
	
	@Query("select s from LightStockC s where s.lkpItemC in ?1 and s.pckWarehouseC = ?2")
	List<LightStockC> findStockList(Set<String> prodIds, String pckCustomerWarehouseCodeC);

}
