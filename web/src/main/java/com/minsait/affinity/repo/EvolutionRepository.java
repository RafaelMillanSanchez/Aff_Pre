package com.minsait.affinity.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.EvolutionC;

public interface EvolutionRepository extends PagingAndSortingRepository<EvolutionC, Integer> {

	List<EvolutionC> findByUserC(String username);

}
