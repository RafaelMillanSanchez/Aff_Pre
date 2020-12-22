package com.minsait.affinity.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.SubbrandC;

public interface SubBrandRepository extends PagingAndSortingRepository<SubbrandC, Integer> {

	SubbrandC findBySfid(String subbrandC);

	@Query("select s from SubbrandC s where s.name IN ?1")
	Iterable<SubbrandC> findByName(Set<String> subbrandNames);

	@Query("select s from SubbrandC s order by s.numSubbrandOrderC asc")
	Iterable<SubbrandC> findSubbrands();
	

}