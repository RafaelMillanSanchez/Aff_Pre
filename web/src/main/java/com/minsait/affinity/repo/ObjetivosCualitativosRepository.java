package com.minsait.affinity.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.minsait.affinity.jpa.model.ObjetivoCualitativoC;

public interface ObjetivosCualitativosRepository extends PagingAndSortingRepository<ObjetivoCualitativoC, Integer> {

	Iterable<ObjetivoCualitativoC> findByLkpUserC(String username);

}
