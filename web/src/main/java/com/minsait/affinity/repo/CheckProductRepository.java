package com.minsait.affinity.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.CheckProductC;

public interface CheckProductRepository extends PagingAndSortingRepository<CheckProductC, Integer>{

	List<CheckProductC> findByCheckAssortedProductC(String sfid);
	
	
	@Query("select p from CheckProductC p where p.checkAssortedProductC IN ?1")
	List<CheckProductC> findProductosSurtidos(Set<String> surtidos_ids);

	@Query("select p from CheckProductC p where p.checkPromotionProductC IN ?1")
	List<CheckProductC> findProductosPromos(Set<String> promos_ids);

}
