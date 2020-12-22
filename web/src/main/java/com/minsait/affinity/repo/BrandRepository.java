package com.minsait.affinity.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.BrandC;

public interface BrandRepository extends PagingAndSortingRepository<BrandC, Integer> {
	

}
