package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.LightProductListC;



public interface ProductListRepository extends PagingAndSortingRepository<LightProductListC, Integer> {

	@Query("select p.lkpProductC from LightProductListC p where p.lkpAccountC = ?1")
	List<String> findInactiveProds(String parentid);

	
}